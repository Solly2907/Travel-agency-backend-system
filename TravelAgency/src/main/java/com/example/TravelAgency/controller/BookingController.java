package com.example.TravelAgency.controller;

import com.example.TravelAgency.DataStore.BookingDatabase;
import com.example.TravelAgency.DataStore.NotificationDatabase;
import com.example.TravelAgency.DataStore.UserDatabase;
import com.example.TravelAgency.Notification.Notification;
import com.example.TravelAgency.entities.*;
import com.example.TravelAgency.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController

public class BookingController {

    private BookingService bookingService;
    private EventBookingService eventBookingService;
    private EventAvailabilityService eventAvailabilityService;
   // private EventRecommendationService eventRecommendationService;
    private BookingDatabase bookingDatabase;
    private NotificationDatabase notificationDatabase;
    private UserDatabase userDatabase;
    private EventSearchService eventSearchService;
    private RoomSearchService roomSearchService;


    public BookingController() {
        this.bookingDatabase = new BookingDatabase();
        this.bookingService = new BookingService(bookingDatabase);
        this.eventBookingService = new EventBookingService(bookingDatabase);
        this.eventAvailabilityService = new EventAvailabilityService(bookingDatabase);
       // this.eventRecommendationService = new EventRecommendationService(notificationDatabase);
        this.notificationDatabase = new NotificationDatabase();
        this.userDatabase = new UserDatabase();
        this.eventSearchService = new EventSearchService();
        this.roomSearchService = new RoomSearchService();
    }




    @PostMapping("/book")
    public Booking bookRoom(@RequestParam String roomId, @RequestParam String userId,
                            @RequestParam String hotelId,
                            @RequestParam String checkInDate, @RequestParam String checkOutDate, @RequestParam String bookingLocation) {
        Booking booking = bookingService.bookRoom(roomId, userId, hotelId, checkInDate, checkOutDate, bookingLocation);
        bookingDatabase.addRoomBooking(booking);

      // EventRecommendationService.recommendEvents(booking);


        Optional<User> userOptional = userDatabase.findUserByUsername(userId);
        System.out.println(userDatabase.getUsers());
        try {
            String notificationId = UUID.randomUUID().toString();
            Notification notification = new Notification();
            notification.setNotificationId(notificationId);
            User user = userOptional.orElseThrow(() -> new RuntimeException("User not found"));
            notification.setRecipient(user.getEmail());
            notification.setUserId(userId);
            notification.setTemplateId("BOOKING_SUCCESS");
            notification.setMessage("Your room booking at hotel " + hotelId + " was successful! Booking ID: " + booking.getBookingId());
            notification.setChannel(user.getChannel());
            if(user.getEmail()==null){
                notification.setRecipient(user.getPhone());
            } else if (user.getPhone()==null) {
                notification.setRecipient(user.getEmail());
            }
            notification.setBookingId(booking.getBookingId());



            try {
                notificationDatabase.saveNotification(notification);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        return booking;
    }

    @GetMapping("/user/{userId}/bookings")
    public List<Booking> getUserBookings(@PathVariable String userId) {
        return bookingService.getBookingsByUserId(userId);
    }

    @PostMapping("/events/book")
    public String bookEvent(@RequestParam String eventId, @RequestParam String userId) {

        if (eventAvailabilityService.checkEventAvailability(eventId).isPresent()) {
            EventBooking booking = eventBookingService.bookEvent(eventId, userId);


            bookingDatabase.addEventBooking(booking);
            Optional<User> userOptional = userDatabase.findUserByUsername(userId);

            try {
                String notificationId = UUID.randomUUID().toString();
                Notification notification = new Notification();
                notification.setNotificationId(notificationId);
                User user = userOptional.orElseThrow(() -> new RuntimeException("User not found"));
                notification.setRecipient(user.getEmail());
                notification.setUserId(userId);
                notification.setTemplateId("BOOKING_SUCCESS");
                notification.setMessage("Your event booking " + eventId + " was successful! Event Booking ID: " + booking.getId());
                notification.setChannel(user.getChannel());
                if(user.getEmail()==null){
                    notification.setRecipient(user.getPhone());
                } else if (user.getPhone()==null) {
                    notification.setRecipient(user.getEmail());
                }
                notification.setBookingId(booking.getId());




                // Save the notification to the file
                try {
                    notificationDatabase.saveNotification(notification);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }        } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }

            return "Event booked successfully with Booking ID: " + booking.getId();
        }


        return "Event is not available.";
    }


    @GetMapping("/events/availability")
    public ResponseEntity<Object> checkEventAvailability(@RequestParam String eventId) {
        Optional<Event> eventOptional = eventAvailabilityService.checkEventAvailability(eventId);

        if (eventOptional.isPresent()) {
            return ResponseEntity.ok(eventOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Event is not available.");
        }
    }

    @GetMapping("/events/searchByDate")
    public List<Event> searchEventsByDate(@RequestParam String date) {
        return eventSearchService.searchEventsByDate(date);
    }


    @PostMapping("/events/modify")
    public String modifyEventBooking(@RequestParam String bookingId, @RequestBody EventBooking updatedBooking) {
        if (eventBookingService.modifyBooking(bookingId, updatedBooking)) {
            return "Event booking modified successfully!";
        } else {
            return "Failed to modify event booking.";
        }
    }

    @DeleteMapping("/events/cancel")
    public String cancelEventBooking(@RequestParam String bookingId) {
        if (eventBookingService.cancelBooking(bookingId)) {
            return "Event booking canceled successfully!";
        } else {
            return "Failed to cancel event booking.";
        }
    }

    @GetMapping("/rooms/bookings")
    public List<Booking> getAllRoomBookings() {
        return bookingDatabase.getAllRoomBookings();
    }

    @GetMapping("/rooms/searchByHotelAndType")
    public List<Room> searchRooms(@RequestParam String hotelId, @RequestParam String type) {
        return roomSearchService.searchRooms(hotelId, type);
    }



    @GetMapping("/events/all")
    public List<EventBooking> getAllEventBookings() {
        return bookingDatabase.getAllEventBookings();
    }


    @GetMapping("/events/check-booked")
    public String isEventBooked(@RequestParam String eventId) {
        boolean isBooked = bookingDatabase.isEventBooked(eventId);
        return isBooked ? "Event is already booked." : "Event is available for booking.";
    }

    @GetMapping("/events/user")
    public List<EventBooking> getUserEventBookings(@RequestParam String userId) {
        return bookingDatabase.getEventBookingsByUserId(userId);
    }

    @GetMapping("/rooms/booked")
    public String isRoomBooked(@RequestParam String roomId, @RequestParam String startDate, @RequestParam String endDate) {
        boolean isBooked = bookingDatabase.isRoomBooked(roomId, startDate, endDate);
        return isBooked ? "Room is already booked." : "Room is available for booking.";
    }

    @PutMapping("/rooms/update")
    public String updateRoomBooking(@RequestParam String bookingId, @RequestBody Booking updatedBooking) {
        boolean isUpdated = bookingDatabase.updateRoomBooking(bookingId, updatedBooking);
        return isUpdated ? "Room booking updated successfully!" : "Failed to update room booking.";
    }

    @DeleteMapping("/rooms/remove")
    public String removeRoomBooking(@RequestParam String bookingId) {
        boolean isRemoved = bookingDatabase.removeRoomBooking(bookingId);
        return isRemoved ? "Room booking removed successfully!" : "Failed to remove room booking.";
    }

    @PutMapping("/events/update")
    public String updateEventBooking(@RequestParam String bookingId, @RequestBody EventBooking updatedBooking) {
        boolean isUpdated = bookingDatabase.updateEventBooking(bookingId, updatedBooking);
        return isUpdated ? "Event booking updated successfully!" : "Failed to update event booking.";
    }

    @DeleteMapping("/events/remove")
    public String removeEventBooking(@RequestParam String bookingId) {
        boolean isRemoved = bookingDatabase.removeEventBooking(bookingId);
        return isRemoved ? "Event booking removed successfully!" : "Failed to remove event booking.";
    }
}

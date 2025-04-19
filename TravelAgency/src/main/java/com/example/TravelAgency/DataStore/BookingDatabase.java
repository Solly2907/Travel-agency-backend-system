package com.example.TravelAgency.DataStore;

import com.example.TravelAgency.entities.Booking;
import com.example.TravelAgency.entities.EventBooking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingDatabase {
    private List<Booking> roomBookings = new ArrayList<>();
    private List<EventBooking> eventBookings = new ArrayList<>();


    public void addRoomBooking(Booking booking) {
        roomBookings.add(booking);
    }

    public List<Booking> getAllRoomBookings() {
        return roomBookings;
    }

    public Optional<Booking> getRoomBookingById(String bookingId) {
        return roomBookings.stream()
                .filter(booking -> booking.getBookingId().equals(bookingId))
                .findFirst();
    }

    public boolean removeRoomBooking(String bookingId) {
        return roomBookings.removeIf(booking -> booking.getBookingId().equals(bookingId));
    }

    public boolean updateRoomBooking(String bookingId, Booking updatedBooking) {
        Optional<Booking> booking = getRoomBookingById(bookingId);
        if (booking.isPresent()) {
            int index = roomBookings.indexOf(booking.get());
            roomBookings.set(index, updatedBooking);
            return true;
        }
        return false;
    }

    public void addEventBooking(EventBooking eventBooking) {
        eventBookings.add(eventBooking);
    }

    public List<EventBooking> getAllEventBookings() {
        return eventBookings;
    }

    public Optional<EventBooking> getEventBookingById(String bookingId) {
        return eventBookings.stream()
                .filter(eventBooking -> eventBooking.getId().equals(bookingId))
                .findFirst();
    }

    public boolean removeEventBooking(String bookingId) {
        return eventBookings.removeIf(eventBooking -> eventBooking.getId().equals(bookingId));
    }

    public boolean updateEventBooking(String bookingId, EventBooking updatedEventBooking) {
        Optional<EventBooking> eventBooking = getEventBookingById(bookingId);
        if (eventBooking.isPresent()) {
            int index = eventBookings.indexOf(eventBooking.get());
            eventBookings.set(index, updatedEventBooking);
            return true;
        }
        return false;
    }

    public List<Booking> getBookingsByUserId(String userId) {
        List<Booking> userBookings = new ArrayList<>();
        for (Booking booking : roomBookings) {
            if (booking.getUserId().equals(userId)) {
                userBookings.add(booking);
            }
        }
        return userBookings;
    }

    public List<EventBooking> getEventBookingsByUserId(String userId) {
        List<EventBooking> userEventBookings = new ArrayList<>();
        for (EventBooking eventBooking : eventBookings) {
            if (eventBooking.getUserId().equals(userId)) {
                userEventBookings.add(eventBooking);
            }
        }
        return userEventBookings;
    }

    public boolean isEventBooked(String eventId) {
        return eventBookings.stream()
                .anyMatch(eventBooking -> eventBooking.getEventId().equals(eventId));
    }

    public boolean isRoomBooked(String roomId, String startDate, String endDate) {
        return roomBookings.stream()
                .anyMatch(booking -> booking.getRoomId().equals(roomId) &&
                        booking.getStartDate().equals(startDate) &&
                        booking.getEndDate().equals(endDate));
    }
}

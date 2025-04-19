package com.example.TravelAgency.services;

import com.example.TravelAgency.entities.Booking;
import com.example.TravelAgency.DataStore.BookingDatabase;
import com.example.TravelAgency.entities.Hotel;
import com.example.TravelAgency.entities.Room;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BookingService {
    private BookingDatabase bookingDatabase;
    private static final String ROOM_JSON_FILE = "C:\\Users\\ahmed\\Downloads\\TravelAgency\\TravelAgency\\hotelData.json";  // Adjust the path to your actual file

    public BookingService(BookingDatabase bookingDatabase) {
        this.bookingDatabase = bookingDatabase;
    }

    public List<Booking> getBookingsByUserId(String userId) {
        List<Booking> allBookings = bookingDatabase.getAllRoomBookings();
        return allBookings.stream()
                .filter(booking -> booking.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public Booking bookRoom(String roomId, String userId, String hotelId, String checkInDate, String checkOutDate, String bookingLocation) {

        if (!isRoomAvailable(roomId, checkInDate, checkOutDate)) {
            throw new IllegalStateException("Room is already booked for the specified dates.");
        }

        double totalAmount = calculateTotalAmount(roomId, checkInDate, checkOutDate);

        String bookingId = "B" + System.currentTimeMillis();
        Booking booking = new Booking(bookingId, roomId, userId, hotelId, "Booked", totalAmount, checkInDate, checkOutDate, bookingLocation);

        bookingDatabase.addRoomBooking(booking);
        return booking;
    }


    public boolean isRoomAvailable(String roomId, String checkInDate, String checkOutDate) {
        List<Booking> existingBookings = bookingDatabase.getAllRoomBookings();
        for (Booking existingBooking : existingBookings) {
            if (existingBooking.getRoomId().equals(roomId) &&
                    isDateOverlap(existingBooking.getCheckInDate(), existingBooking.getCheckOutDate(), checkInDate, checkOutDate)) {
                return false;
            }
        }
        return true;
    }


    private boolean isDateOverlap(String existingCheckInDate, String existingCheckOutDate, String newCheckInDate, String newCheckOutDate) {
        return !(newCheckOutDate.compareTo(existingCheckInDate) <= 0 || newCheckInDate.compareTo(existingCheckOutDate) >= 0);
    }


    public double calculateTotalAmount(String roomId, String checkInDate, String checkOutDate) {

        List<Hotel> hotels = readRoomsFromJsonFile();


        Room room = hotels.stream()
                .flatMap(hotel -> hotel.getRooms().stream())
                .filter(r -> r.getRoomId().equals(roomId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));

        int numberOfDays = calculateNumberOfDays(checkInDate, checkOutDate);

        return room.getPricePerNight() * numberOfDays;
    }


    private int calculateNumberOfDays(String checkInDate, String checkOutDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date checkIn = sdf.parse(checkInDate);
            Date checkOut = sdf.parse(checkOutDate);

            long diffInMillies = checkOut.getTime() - checkIn.getTime();
            long diffInDays = diffInMillies / (1000 * 60 * 60 * 24);
            return (int) diffInDays;
        } catch (ParseException e) {
            throw new RuntimeException("Error parsing dates.", e);
        }
    }


    public List<Hotel> readRoomsFromJsonFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(ROOM_JSON_FILE), objectMapper.getTypeFactory().constructCollectionType(List.class, Hotel.class));
        } catch (IOException e) {
            throw new RuntimeException("Error reading room data from JSON file.", e);
        }
    }

    public List<Room> getRoomsByHotelId(String hotelId) {
        List<Hotel> hotels = readRoomsFromJsonFile();
        return hotels.stream()
                .filter(hotel -> hotel.getHotelId().equals(hotelId))
                .flatMap(hotel -> hotel.getRooms().stream())
                .collect(Collectors.toList());
    }
}

package com.example.TravelAgency.services;

import com.example.TravelAgency.entities.Booking;
import com.example.TravelAgency.DataStore.BookingDatabase;
import com.example.TravelAgency.entities.Event;
import com.example.TravelAgency.entities.EventBooking;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EventBookingService {
    private BookingDatabase bookingDatabase;
    private static final String EVENT_JSON_FILE = "C:\\Users\\ahmed\\Downloads\\TravelAgency\\TravelAgency\\EventData.json";  // Adjust the path to your actual file

    public EventBookingService() {}

    public EventBookingService(BookingDatabase bookingDatabase) {
        this.bookingDatabase = bookingDatabase;
    }


    public EventBooking bookEvent(String eventId, String userId) {

        if (!isEventAvailable(eventId)) {
            throw new IllegalStateException("Event is already booked.");
        }


        double totalAmount = calculateTotalAmount(eventId);

        String bookingId = "E" + System.currentTimeMillis();
        EventBooking eventBooking = new EventBooking(bookingId, eventId, userId, totalAmount, false); // Created but not paid


        try {
            bookingDatabase.addEventBooking(eventBooking);
        } catch (Exception e) {
            throw new RuntimeException("Error while adding event booking to the database", e);
        }

        return eventBooking;
    }


    public boolean isEventAvailable(String eventId) {
        List<EventBooking> existingBookings = bookingDatabase.getAllEventBookings();
        for (EventBooking existingBooking : existingBookings) {
            if (existingBooking.getEventId().equals(eventId)) {
                return false;
            }
        }


        List<Event> events = readEventsFromJsonFile();
        Event event = events.stream()
                .filter(e -> e.getEventId().equals(eventId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        return event.getAvailableTickets() > 0;
    }


    public double calculateTotalAmount(String eventId) {

        List<Event> events = readEventsFromJsonFile();


        Event event = events.stream()
                .filter(e -> e.getEventId().equals(eventId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));


        return event.getTicketPrice();
    }

    public boolean modifyBooking(String bookingId, EventBooking updatedBooking) {

        boolean isUpdated = bookingDatabase.updateEventBooking(bookingId, updatedBooking);

        if (isUpdated) {
            return true;
        } else {
            return false;
        }
    }


    public boolean cancelBooking(String bookingId) {

        boolean isRemoved = bookingDatabase.removeEventBooking(bookingId);

        if (isRemoved) {
            return true;
        } else {
            return false;
        }
    }


    public List<Event> readEventsFromJsonFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(EVENT_JSON_FILE), objectMapper.getTypeFactory().constructCollectionType(List.class, Event.class));
        } catch (IOException e) {

            System.err.println("Error reading event data from JSON file: " + e.getMessage());
            throw new RuntimeException("Error reading event data from JSON file.", e);
        }
    }


    public List<Event> getEventsByEventId(String eventId) {
        List<Event> events = readEventsFromJsonFile();
        return events.stream()
                .filter(event -> event.getEventId().equals(eventId))
                .collect(Collectors.toList());
    }
}

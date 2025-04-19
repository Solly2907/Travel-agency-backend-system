package com.example.TravelAgency.services;

import com.example.TravelAgency.DataStore.BookingDatabase;
import com.example.TravelAgency.entities.Event;
import com.example.TravelAgency.entities.EventBooking;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EventAvailabilityService {
    private BookingDatabase bookingDatabase;
    private static final String EVENT_JSON_FILE = "C:\\Users\\ahmed\\Downloads\\TravelAgency\\TravelAgency\\EventData.json";  // Adjust the path to your actual file

    public EventAvailabilityService() {}
    // Constructor to initialize the BookingDatabase
    public EventAvailabilityService(BookingDatabase bookingDatabase) {
        this.bookingDatabase = bookingDatabase;
    }


    public Optional<Event> checkEventAvailability(String eventId) {
        // Check if the event has already been booked
        List<EventBooking> existingBookings = bookingDatabase.getAllEventBookings();
        for (EventBooking booking : existingBookings) {
            if (booking.getEventId().equals(eventId)) {
                return Optional.empty();  // Event is already booked
            }
        }


        List<Event> events = readEventsFromJsonFile();
        Event event = events.stream()
                .filter(e -> e.getEventId().equals(eventId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        if (event.getAvailableTickets() <= 0) {
            return Optional.empty();
        }


        return Optional.of(event);
    }



    private List<Event> readEventsFromJsonFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(EVENT_JSON_FILE), objectMapper.getTypeFactory().constructCollectionType(List.class, Event.class));
        } catch (IOException e) {
            // Log and rethrow the exception
            System.err.println("Error reading event data from JSON file: " + e.getMessage());
            throw new RuntimeException("Error reading event data from JSON file.", e);
        }
    }


    public Event getEventById(String eventId) {
        List<Event> events = readEventsFromJsonFile();
        return events.stream()
                .filter(event -> event.getEventId().equals(eventId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
    }
}

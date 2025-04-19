/*package com.example.TravelAgency.services;

import com.example.TravelAgency.DataStore.NotificationDatabase;
import com.example.TravelAgency.entities.Booking;
import com.example.TravelAgency.entities.Event;
import com.example.TravelAgency.Notification.Notification;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventRecommendationService {

    private static final String EVENT_DATA_FILE_PATH = "C:\\Users\\ahmed\\Downloads\\TravelAgency\\TravelAgency\\EventData.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final NotificationDatabase notificationDatabase;

    public EventRecommendationService(NotificationDatabase notificationDatabase) {
        this.notificationDatabase = notificationDatabase;
    }

    // Read events from the JSON file
    private List<Event> readEventsFromFile() throws IOException {
        File file = new File(EVENT_DATA_FILE_PATH);
        if (!file.exists()) {
            return List.of(); // Return empty list if file doesn't exist
        }
        return objectMapper.readValue(file, new TypeReference<List<Event>>() {});
    }

    // Recommend events based on booking details
    public void recommendEvents(Booking booking) {
        try {
            List<Event> events = readEventsFromFile();

            // Parse booking dates
            LocalDate checkInDate = LocalDate.parse(booking.getCheckInDate());
            LocalDate checkOutDate = LocalDate.parse(booking.getCheckOutDate());

            // Filter events by location and date
            List<Event> recommendedEvents = events.stream()
                    .filter(event -> {
                        try {
                            // Parse the event date as LocalDate
                            LocalDate eventDate = LocalDate.parse(event.getDate());
                            return event.getLocation().equalsIgnoreCase(booking.getBookingLocation()) &&
                                    !eventDate.isBefore(checkInDate) &&
                                    !eventDate.isAfter(checkOutDate);
                        } catch (DateTimeParseException e) {
                            // Log and ignore invalid dates
                            System.err.println("Invalid date format for event: " + event.getName() + ", Date: " + event.getDate());
                            return false;
                        }
                    })
                    .collect(Collectors.toList());

            // Notify the user about recommended events
            for (Event event : recommendedEvents) {
                Notification notification = new Notification();
                notification.setRecipient(booking.getUserId());
                notification.setUserId(booking.getUserId());
                notification.setTemplateId("EVENT_RECOMMENDATION");
                notification.setMessage("Recommended Event: " + event.getName() +
                        " at " + event.getLocation() + " on " + event.getDate() +
                        ". Don't miss it during your stay!");
                notification.setChannel("Dashboard");
                notification.setBookingId(booking.getBookingId());

                notificationDatabase.saveNotification(notification);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading event data from file", e);
        }
    }
}
*/
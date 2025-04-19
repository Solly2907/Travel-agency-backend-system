package com.example.TravelAgency.DataStore;

import com.example.TravelAgency.Notification.Notification;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class NotificationDatabase {

    private static final String NOTIFICATION_FILE_PATH = "C:\\Users\\ahmed\\Downloads\\TravelAgency\\TravelAgency\\NotificationData.json"; // JSON file path
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Notification> readNotificationsFromFile() throws IOException {
        File file = new File(NOTIFICATION_FILE_PATH);
        if (!file.exists()) {
            return List.of(); // Return empty list if file doesn't exist
        }
        return objectMapper.readValue(file, new TypeReference<List<Notification>>() {
        });
    }

    // Write notifications to the JSON file
    public void writeNotificationsToFile(List<Notification> notifications) throws IOException {
        objectMapper.writeValue(new File(NOTIFICATION_FILE_PATH), notifications);
    }

    // Save a new notification to the file
    public void saveNotification(Notification notification) throws IOException {
        List<Notification> notifications = readNotificationsFromFile();
        notifications.add(notification);
        writeNotificationsToFile(notifications);
    }

    // Get all notifications from the file
    public List<Notification> getAllNotifications() throws IOException {
        return readNotificationsFromFile();
    }
}

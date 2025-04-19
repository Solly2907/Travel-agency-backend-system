package com.example.TravelAgency.Notification;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class NotificationProducer implements Runnable {
    private NotificationQueue notificationQueue;
    private static final String NOTIFICATIONS_JSON = "C:\\Users\\ahmed\\Downloads\\TravelAgency\\TravelAgency\\NotificationData.json"; // Path to JSON file

    public NotificationProducer(NotificationQueue queue) {
        this.notificationQueue = queue;
    }

    @Override
    public void run() {
        try {
            List<Notification> notifications = loadNotificationsFromJson();
            for (Notification notification : notifications) {
                notificationQueue.addNotification(notification);
                System.out.println("Notification added to queue: " + notification.getNotificationId());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private List<Notification> loadNotificationsFromJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(NOTIFICATIONS_JSON), objectMapper.getTypeFactory().constructCollectionType(List.class, Notification.class));
    }
}

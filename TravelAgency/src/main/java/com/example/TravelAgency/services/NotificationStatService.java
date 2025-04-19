package com.example.TravelAgency.services;

import com.example.TravelAgency.DataStore.NotificationDatabase;
import com.example.TravelAgency.Notification.Notification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class NotificationStatService {

    private final NotificationDatabase notificationDatabase;

    public NotificationStatService(NotificationDatabase notificationDatabase) {
        this.notificationDatabase = notificationDatabase;
    }


    public Map<String, Integer> getSentCountByType() throws IOException {
        List<Notification> notifications = notificationDatabase.getAllNotifications();
        Map<String, Integer> sentCount = new HashMap<>();

        for (Notification notification : notifications) {
            sentCount.put(notification.getTemplateId(), sentCount.getOrDefault(notification.getTemplateId(), 0) + 1);
        }

        return sentCount;
    }

    public Map<String, Integer> getFailureCountByType() {
        return new HashMap<>();
    }

    public String getMostNotifiedRecipient() throws IOException {
        List<Notification> notifications = notificationDatabase.getAllNotifications();
        Map<String, Integer> recipientCount = new HashMap<>();

        for (Notification notification : notifications) {
            recipientCount.put(notification.getRecipient(),
                    recipientCount.getOrDefault(notification.getRecipient(), 0) + 1);
        }

        return recipientCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);  // Return null if no recipients
    }

    public String getMostSentNotificationTemplate() throws IOException {
        List<Notification> notifications = notificationDatabase.getAllNotifications();
        Map<String, Integer> templateCount = new HashMap<>();

        for (Notification notification : notifications) {
            templateCount.put(notification.getTemplateId(),
                    templateCount.getOrDefault(notification.getTemplateId(), 0) + 1);
        }

        return templateCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);  // Return null if no templates
    }

    public Map<String, Integer> getRecipientNotificationCount() throws IOException {
        List<Notification> notifications = notificationDatabase.getAllNotifications();
        Map<String, Integer> recipientNotificationCount = new HashMap<>();

        for (Notification notification : notifications) {
            recipientNotificationCount.put(notification.getRecipient(),
                    recipientNotificationCount.getOrDefault(notification.getRecipient(), 0) + 1);
        }

        return recipientNotificationCount;
    }

    public void printStatistics() throws IOException {
        System.out.println("Sent Notifications By Type: " + getSentCountByType());
        System.out.println("Failure Notifications By Type: " + getFailureCountByType());
        System.out.println("Most Notified Recipient: " + getMostNotifiedRecipient());
        System.out.println("Most Sent Notification Template: " + getMostSentNotificationTemplate());
        System.out.println("Notifications Per Recipient: " + getRecipientNotificationCount());
    }
}

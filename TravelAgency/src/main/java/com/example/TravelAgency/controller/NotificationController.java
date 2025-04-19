package com.example.TravelAgency.controller;

import com.example.TravelAgency.services.NotificationStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class NotificationController {

    private final NotificationStatService notificationStatService;

    @Autowired
    public NotificationController(NotificationStatService notificationStatService) {
        this.notificationStatService = notificationStatService;
    }

    // Get the total number of sent notifications by type
    @GetMapping("/sent-count")
    public Map<String, Integer> getSentCountByType() throws IOException {
        return notificationStatService.getSentCountByType();
    }


    @GetMapping("/failure-count")
    public Map<String, Integer> getFailureCountByType() {
        return notificationStatService.getFailureCountByType();
    }

    @GetMapping("/most-notified-recipient")
    public String getMostNotifiedRecipient() throws IOException {
        return notificationStatService.getMostNotifiedRecipient();
    }

    // Get the most sent notification template
    @GetMapping("/most-sent-template")
    public String getMostSentNotificationTemplate() throws IOException {
        return notificationStatService.getMostSentNotificationTemplate();
    }

    // Get the number of notifications sent for each recipient
    @GetMapping("/recipient-notification-count")
    public Map<String, Integer> getRecipientNotificationCount() throws IOException {
        return notificationStatService.getRecipientNotificationCount();
    }

    @GetMapping("/print-statistics")
    public void printStatistics() throws IOException {
        notificationStatService.printStatistics();
    }
}

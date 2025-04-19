package com.example.TravelAgency.Notification;

import java.util.Map;

public class NotificationConsumer implements Runnable {
    private NotificationQueue notificationQueue;

    public NotificationConsumer(NotificationQueue queue) {
        this.notificationQueue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Notification notification = notificationQueue.takeNotification();
                sendNotification(notification);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendNotification(Notification notification) {
        System.out.println("Sending Notification to " + notification.getRecipient() + ":");
        System.out.println("Subject: " + notification.getTemplateId());
    }


}

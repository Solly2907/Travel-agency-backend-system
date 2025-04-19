package com.example.TravelAgency.Notification;

public class NotificationApp {
    public static void main(String[] args) {
        NotificationQueue notificationQueue = new NotificationQueue();

        NotificationProducer producer = new NotificationProducer(notificationQueue);
        Thread producerThread = new Thread(producer);
        producerThread.start();

        NotificationConsumer consumer = new NotificationConsumer(notificationQueue);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
    }
}

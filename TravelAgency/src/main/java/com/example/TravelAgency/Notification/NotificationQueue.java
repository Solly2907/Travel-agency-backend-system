package com.example.TravelAgency.Notification;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class NotificationQueue {
    private final BlockingQueue<Notification> queue = new LinkedBlockingQueue<>();

    // Producer: Adds notifications to the queue
    public void addNotification(Notification notification) throws InterruptedException {
        queue.put(notification);
    }

    // Consumer: Takes notifications from the queue and processes them
    public Notification takeNotification() throws InterruptedException {
        return queue.take();
    }
}

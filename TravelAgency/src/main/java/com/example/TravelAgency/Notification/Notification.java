package com.example.TravelAgency.Notification;

import java.util.Map;

public class Notification {
    private String notificationId;
    private String userId;
    private String recipient; // Email or Phone Number
    private String templateId;
    private String channel;
    private String message;
    private String bookingId;

    public Notification (){};

    public Notification(String notificationId, String userId, String recipient, String templateId,String channel,String message,String bookingId) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.recipient = recipient;
        this.templateId = templateId;
        this.channel = channel;
        this.message = message;
        this.bookingId = bookingId;
    }

    // Getters and Setters
    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getChannel() {
        return channel;
    }
    public void setChannel(String chanel) {
        this.channel = chanel;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getBookingId() {
        return bookingId;
    }
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
}


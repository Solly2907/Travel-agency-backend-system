package com.example.TravelAgency.entities;

public class EventBooking {
    private String id;
    private String eventId;
    private String userId;
    private double amount;
    private boolean paid;

    // Constructor with all fields, including id (for bookings that have an id generated)
    public EventBooking(String id, String eventId, String userId, double amount, boolean paid) {
        this.id = id;
        this.eventId = eventId;
        this.userId = userId;
        this.amount = amount;
        this.paid = paid;
    }

    // Getter and Setter methods
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}

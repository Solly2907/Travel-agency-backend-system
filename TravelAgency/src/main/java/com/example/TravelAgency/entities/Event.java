package com.example.TravelAgency.entities;

public class Event {
    private String eventId;
    private String name;
    private String location;
    private String date;
    private String type;
    private int availableTickets;
    private double ticketPrice;

    public Event() {
    }

    public Event(String eventId, String name, String location, String date, String type, int availableTickets, double ticketPrice) {
        this.eventId = eventId;
        this.name = name;
        this.location = location;
        this.date = date;
        this.type = type;
        this.availableTickets = availableTickets;
        this.ticketPrice = ticketPrice;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId='" + eventId + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", availableTickets=" + availableTickets +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}

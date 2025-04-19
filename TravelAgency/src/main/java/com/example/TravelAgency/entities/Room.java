package com.example.TravelAgency.entities;

public class Room {
    private String roomId;
    private String hotelId;
    private String type;
    private double pricePerNight;
    private boolean available;

    public Room(String roomId, String hotelId, String type, double pricePerNight, boolean available) {
        this.roomId = roomId;
        this.hotelId = hotelId;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.available = available;
    }

    public Room() {

    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}


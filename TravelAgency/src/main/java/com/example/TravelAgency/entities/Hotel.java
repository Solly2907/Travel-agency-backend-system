package com.example.TravelAgency.entities;

import java.util.List;

public class Hotel {
    private String hotelId;
    private String name;
    private String location;
    private String description;
    private List<Room> rooms;

    public Hotel(String hotelId, String name, String location, String description, List<Room> rooms) {
        this.hotelId = hotelId;
        this.name = name;
        this.location = location;
        this.description = description;
        this.rooms = rooms;
    }

    public Hotel() {
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}

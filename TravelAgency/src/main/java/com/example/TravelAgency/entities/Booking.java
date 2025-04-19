package com.example.TravelAgency.entities;

public class Booking {
    private String bookingId;
    private String roomId;
    private String userId;
    private String hotelId;
    private String bookingStatus;
    private double totalAmount;
    private String checkInDate;
    private String checkOutDate;
    private String bookingLocation;


    public Booking(String bookingId, String roomId, String userId, String hotelId, String bookingStatus, double totalAmount, String checkInDate, String checkOutDate,  String bookingLocation) {
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.userId = userId;
        this.hotelId = hotelId;
        this.bookingStatus = bookingStatus;
        this.totalAmount = totalAmount;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingLocation = bookingLocation;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getStartDate() {
        return getCheckInDate();
    }

    public String getEndDate() {
        return getCheckOutDate();
    }
    public String getBookingLocation() {
        return bookingLocation;
    }
    public void setBookingLocation(String bookingLocation) {
        this.bookingLocation = bookingLocation;
    }
}

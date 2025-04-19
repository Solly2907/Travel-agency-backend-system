package com.example.TravelAgency.entities;

public class User {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String channel;

    public User(String username, String password, String email, String phone, String channel) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.channel = channel;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getChannel() {
        return channel;
    }
    public void setChannel(String channel) {
        this.channel = channel;
    }
}

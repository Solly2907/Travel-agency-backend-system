package com.example.TravelAgency.entities;

public class UserAuthEntity {
    private String username;
    private String token;
    public UserAuthEntity(String username, String token) {
        this.username = username;
        this.token = token;
    }
    public String getUsername() {
        return username;
    }
    public String getToken() {
        return token;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setAuthToken(String token) {
        this.token = token;
    }

}

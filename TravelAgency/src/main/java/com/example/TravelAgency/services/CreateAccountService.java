package com.example.TravelAgency.services;

import com.example.TravelAgency.DataStore.UserDatabase;
import com.example.TravelAgency.entities.User;

public class CreateAccountService {
    private UserDatabase userDatabase;

    public CreateAccountService(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public void createAccount(String username, String password, String email, String phoneNumber, String channel) {
        if (!userDatabase.checkUserExists(username)) {
            User newUser = new User(username, password, email, phoneNumber, channel);
            userDatabase.addUser(newUser);
        } else {
            throw new RuntimeException("User already exists.");
        }
    }
}

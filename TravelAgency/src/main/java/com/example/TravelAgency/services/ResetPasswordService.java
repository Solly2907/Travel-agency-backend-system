package com.example.TravelAgency.services;

import com.example.TravelAgency.DataStore.UserDatabase;

public class ResetPasswordService {
    private UserDatabase userDatabase;

    public ResetPasswordService(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public void resetPassword(String username, String password, String newPassword) {
        userDatabase.findUserByUsernameAndPassword(username, password)
                .ifPresent(user -> user.setPassword(newPassword));
    }
}

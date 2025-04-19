package com.example.TravelAgency.services;

import com.example.TravelAgency.DataStore.UserAuthintication;

public class LogoutService {
    private UserAuthintication userAuthintication;

    public LogoutService(UserAuthintication userAuthintication) {
        this.userAuthintication = userAuthintication;
    }

    public boolean logout(String token) {
        return userAuthintication.removeUserByToken(token);
    }
}

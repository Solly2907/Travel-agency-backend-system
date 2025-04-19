package com.example.TravelAgency.services;

import com.example.TravelAgency.DataStore.UserAuthintication;
import com.example.TravelAgency.DataStore.UserDatabase;
import com.example.TravelAgency.entities.UserAuthEntity;

import java.util.UUID;

public class LoginService {
    private UserDatabase userDatabase;
    private UserAuthintication userAuthintication;

    public LoginService(UserDatabase userDatabase, UserAuthintication userAuthintication ) {
        this.userDatabase = userDatabase;
        this.userAuthintication = userAuthintication;
    }

    public String login(String username, String password) {
        return userDatabase.findUserByUsername(username)
                .filter(user -> user.getPassword().equals(password))
                .map(user -> {
                    String token = UUID.randomUUID().toString();
                    userAuthintication.addUser(new UserAuthEntity(user.getUsername(), token));
                    return token;
                })
                .orElse(null);
    }
}

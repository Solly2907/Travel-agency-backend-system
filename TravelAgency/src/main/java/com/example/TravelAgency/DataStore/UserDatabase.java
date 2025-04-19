package com.example.TravelAgency.DataStore;

import com.example.TravelAgency.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDatabase {
    private static  List<User> users = new ArrayList<>();

    public Optional<User> findUserByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    public Optional<User> findUserByUsernameAndPassword(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean checkUserExists(String username) {
        return users.stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }

    public List<User> getUsers() {
        return users;
    }
}

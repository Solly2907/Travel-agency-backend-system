package com.example.TravelAgency.DataStore;

import com.example.TravelAgency.entities.UserAuthEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserAuthintication {
    List<UserAuthEntity> userAuthList = new ArrayList<>();

    public Optional<UserAuthEntity> findUserByUsername(String username) {
        return userAuthList.stream()
                .filter(userAuthEntity -> userAuthEntity.getUsername().equals(username))
                .findFirst();
    }

    public void addUser(UserAuthEntity user) {
        userAuthList.add(user);
    }

    public boolean checkUserExists(String username) {
        return userAuthList.stream()
                .anyMatch(userAuthEntity -> userAuthEntity.getUsername().equals(username));
    }

    public boolean removeUserByToken(String token) {
        return userAuthList.removeIf(userAuthEntity -> userAuthEntity.getToken().equals(token));
    }
}

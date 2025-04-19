package com.example.TravelAgency.services;

import com.example.TravelAgency.entities.Room;
import com.example.TravelAgency.entities.Hotel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomSearchService {

    private static final String HOTEL_DATA_FILE_PATH = "C:\\Users\\ahmed\\Downloads\\TravelAgency\\TravelAgency\\hotelData.json"; // JSON file path
    private final ObjectMapper objectMapper = new ObjectMapper();


    private List<Hotel> readHotelsFromFile() throws IOException {
        File file = new File(HOTEL_DATA_FILE_PATH);
        if (!file.exists()) {
            return List.of();
        }
        return objectMapper.readValue(file, new TypeReference<List<Hotel>>() {});
    }


    public List<Room> searchRooms(String hotelId, String type) {
        try {
            List<Hotel> hotels = readHotelsFromFile();
            List<Room> allRooms = new ArrayList<>();


            for (Hotel hotel : hotels) {
                if (hotel.getRooms() != null) {
                    allRooms.addAll(hotel.getRooms());
                }
            }


            return allRooms.stream()
                    .filter(room -> room.getHotelId().equals(hotelId) && room.getType().equalsIgnoreCase(type))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Error reading hotel data from file", e);
        }
    }
}

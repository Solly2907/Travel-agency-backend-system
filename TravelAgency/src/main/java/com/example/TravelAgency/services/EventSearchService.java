package com.example.TravelAgency.services;

import com.example.TravelAgency.entities.Event;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventSearchService {

    private static final String EVENT_DATA_FILE_PATH = "C:\\Users\\ahmed\\Downloads\\TravelAgency\\TravelAgency\\EventData.json";
    private final ObjectMapper objectMapper = new ObjectMapper();


    private List<Event> readEventsFromFile() throws IOException {
        File file = new File(EVENT_DATA_FILE_PATH);
        if (!file.exists()) {
            return List.of();
        }
        return objectMapper.readValue(file, new TypeReference<List<Event>>() {});
    }

    public List<Event> searchEventsByDate(String date) {
        try {
            List<Event> events = readEventsFromFile();
            return events.stream()
                    .filter(event -> event.getDate().equals(date))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Error reading events from file", e);
        }
    }
}

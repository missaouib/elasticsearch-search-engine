package com.richcode.service;

import com.richcode.domain.Plant;
import com.richcode.repository.PlantRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PlantService {

    private PlantRepository plantRepository;

    private static final List<String> SEARCHABLE_FIELDS = Arrays.asList("name","scientificName","family");

    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public List<Plant> searchPlants(String text, List<String> fields, int limit) {

        List<String> fieldsToSearchBy = fields.isEmpty() ? SEARCHABLE_FIELDS : fields;

        boolean containsInvalidField = fieldsToSearchBy.stream(). anyMatch(f -> !SEARCHABLE_FIELDS.contains(f));

        if(containsInvalidField) {
            throw new IllegalArgumentException();
        }

        return plantRepository.find(
                text, limit, fieldsToSearchBy.toArray(new String[0]));
    }
}
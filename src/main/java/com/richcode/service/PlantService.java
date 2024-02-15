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

    public List<Plant> findAll(){
        return plantRepository.findAll();
    }
}
package com.richcode.controller;

import com.richcode.domain.Plant;
import com.richcode.model.SearchRequestDTO;
import com.richcode.service.PlantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/plant")
public class PlantController {

    private PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping("/search")
    public List<Plant> searchPlants(SearchRequestDTO searchRequestDTO) {

        log.info("Request for plant search received with data : " + searchRequestDTO);

        return plantService.searchPlants(searchRequestDTO.getText(), searchRequestDTO.getFields(), searchRequestDTO.getLimit());
    }
}
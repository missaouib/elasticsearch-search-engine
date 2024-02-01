package com.richcode.repository;

import com.richcode.search.repository.SearchRepository;
import org.springframework.stereotype.Repository;
import com.richcode.domain.Plant;
@Repository
public interface PlantRepository extends SearchRepository<Plant, Long> {
}
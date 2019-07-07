package com.rybacki.TouristsFlightManagement.repository;

import com.rybacki.TouristsFlightManagement.model.Tourist;
import org.springframework.data.repository.CrudRepository;


public interface TouristRepository extends CrudRepository<Tourist, Integer> {
}

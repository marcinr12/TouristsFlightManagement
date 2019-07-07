package com.rybacki.TouristsFlightManagement.repository;

import com.rybacki.TouristsFlightManagement.model.Flight;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface FlightRepository extends CrudRepository<Flight, Integer> {

    @Transactional
    @Query("select seatsNumber from  Flight f where id = ?1")
    Iterable<Integer> selectSeatsNumberByFlightId(int id);

    @Transactional
    @Query("select occupiedSeats from  Flight f where id = ?1")
    Iterable<Integer> selectOccupiedSeatsByFlightId(int id);

    @Modifying
    @Transactional
    @Query("update Flight set occupiedSeats = ?1 where id = ?2")
    void updateOccupiedSeatsByFlightId(int occupiedSeats, int id);

}

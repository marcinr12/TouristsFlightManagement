package com.rybacki.TouristsFlightManagement.repository;

import com.rybacki.TouristsFlightManagement.model.Connector;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ConnectorRepository extends CrudRepository<Connector, Integer> {

    // Tourist
    @Modifying
    @Transactional
    @Query("delete from Connector c where touristID = ?1")
    void deleteConnectorByTouristId(int touristID);


    @Transactional
    @Query("select flightID from  Connector c where  touristID = ?1")
    Iterable<Integer> selectFlightsByTouristId(int touristID);

    // Flight and Tourist
    @Modifying
    @Transactional
    @Query("delete from Connector c where touristID = ?1 and flightID = ?2")
    void deleteConnectorByTouristAndFlightId(int touristID, int flightID);

    // Flight
    @Modifying
    @Transactional
    @Query("delete from Connector c where flightID = ?1")
    void deleteConnectorByFlightId(int flightID);

    @Transactional
    @Query("select touristID from  Connector c where  flightID = ?1")
    Iterable<Integer> selectTouristsByFlightId(int flightID);


    @Transactional
    @Query(value = "select count(*) from Connector c where flightID = ?1", nativeQuery=true)
    Integer countOfTouristConnectedToFlight(int flightID);
}

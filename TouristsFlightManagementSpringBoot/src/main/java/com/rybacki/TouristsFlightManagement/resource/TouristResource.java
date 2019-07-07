package com.rybacki.TouristsFlightManagement.resource;


import com.rybacki.TouristsFlightManagement.model.Tourist;
import com.rybacki.TouristsFlightManagement.repository.ConnectorRepository;
import com.rybacki.TouristsFlightManagement.repository.FlightRepository;
import com.rybacki.TouristsFlightManagement.repository.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping(value = "/rest/tourist")
public class TouristResource {

    @Autowired
    TouristRepository touristRepository;

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    ConnectorRepository connectorRepository;

    @GetMapping(value = "all")
    public Iterable<Tourist> getAll() {
        return touristRepository.findAll();
    }

    @PostMapping(value = "load")
    public Iterable<Tourist> persist(@RequestBody Tourist tourist){
        touristRepository.save(tourist);
        return  touristRepository.findAll();
    }

    @DeleteMapping (value = "delete/{id}")
    public boolean deleteById(@PathVariable("id") int id){

        Iterable<Integer> flights = connectorRepository.selectFlightsByTouristId(id);
        connectorRepository.deleteConnectorByTouristId(id);

        try {
            touristRepository.deleteById(id);

            for (Integer flightID: flights) {
                Integer occupiedSeats = connectorRepository.countOfTouristConnectedToFlight(flightID);
                flightRepository.updateOccupiedSeatsByFlightId(occupiedSeats, flightID);
            }

            return false;
        }
        catch (Exception handlerException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (value = "select/{touristID}")
    public Iterable<Integer> selectFlightsByTouristId(@PathVariable("touristID") int touristId) {
        return connectorRepository.selectFlightsByTouristId(touristId);
    }




}

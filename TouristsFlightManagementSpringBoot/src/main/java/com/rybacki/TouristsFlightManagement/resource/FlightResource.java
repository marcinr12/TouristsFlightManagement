package com.rybacki.TouristsFlightManagement.resource;


import com.rybacki.TouristsFlightManagement.model.Flight;
import com.rybacki.TouristsFlightManagement.repository.ConnectorRepository;
import com.rybacki.TouristsFlightManagement.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/rest/flight")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FlightResource {


    @Autowired
    FlightRepository flightRepository;

    @Autowired
    ConnectorRepository connectorRepository;

    @GetMapping(value = "all")
    public Iterable<Flight> getAll() {
        return flightRepository.findAll();
    }

    @PostMapping(value = "load")
    public Iterable<Flight> persist(@RequestBody final Flight tourist){
        flightRepository.save(tourist);
        return  flightRepository.findAll();
    }

    @DeleteMapping (value = "delete/{id}")
    public boolean deleteById(@PathVariable("id") int id){

        connectorRepository.deleteConnectorByFlightId(id);
        try {
            flightRepository.deleteById(id);
            return false;
        }
        catch (Exception handlerException)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (value = "select/{flightID}")
    public Iterable<Integer> selectTouristsByFlightId(@PathVariable("flightID") int flightId) {
        return connectorRepository.selectTouristsByFlightId(flightId);
    }
}

package com.rybacki.TouristsFlightManagement.resource;

import com.rybacki.TouristsFlightManagement.model.Connector;
import com.rybacki.TouristsFlightManagement.repository.ConnectorRepository;
import com.rybacki.TouristsFlightManagement.repository.FlightRepository;
import com.rybacki.TouristsFlightManagement.repository.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest/connector")
public class ConnectorResource {

    @Autowired
    ConnectorRepository connectorRepository;

    @Autowired
    TouristRepository touristRepository;

    @Autowired
    FlightRepository flightRepository;

    @PostMapping ("")
    public Iterable<Connector> postConnection(@RequestBody Connector connector) {
        Iterable<Integer> occupiedSeatsIterable = flightRepository.selectOccupiedSeatsByFlightId(connector.getFlightID());
        Iterable<Integer> seatsNumberIterable = flightRepository.selectSeatsNumberByFlightId(connector.getFlightID());

        int occupiedSeats = occupiedSeatsIterable.iterator().next();
        int seatsNumber = seatsNumberIterable.iterator().next();

        if(seatsNumber > occupiedSeats) {
            connectorRepository.save(connector);
            flightRepository.updateOccupiedSeatsByFlightId(occupiedSeats + 1, connector.getFlightID());
        }
        return  connectorRepository.findAll();
    }

    @DeleteMapping("/{touristID}/{flightID}")
    public void deleteConnection(@PathVariable("touristID") int touristId, @PathVariable("flightID") int flightID) {
        connectorRepository.deleteConnectorByTouristAndFlightId(touristId, flightID);

        Integer occupiedSeats = connectorRepository.countOfTouristConnectedToFlight(flightID);
        flightRepository.updateOccupiedSeatsByFlightId(occupiedSeats, flightID);

    }

}

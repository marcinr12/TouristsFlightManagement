package com.rybacki.TouristsFlightManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Connector {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "touristID")
    private int touristID;
    @Column(name = "flightID")
    private int flightID;

    public Connector() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTouristID() {
        return touristID;
    }

    public void setTouristID(int touristID) {
        this.touristID = touristID;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }
}

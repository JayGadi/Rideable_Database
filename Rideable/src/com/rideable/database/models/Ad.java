package com.rideable.database.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Jay on 11/25/2015.
 */
@XmlRootElement
public class Ad implements Serializable{

    private double departureLongitude;
    private double departureLatitude;
    private double arrivalLongitude;
    private double arrivalLatitude;
    private double price;

    private int id;
    private int passengers;
    
    private String departureCity;
    private String arrivalCity;

    private String departureDate;
    
    @JsonIgnore
    private User aUser;
   
    @JsonIgnore
	private Set<User> ridePassengers = new HashSet<User>(0);
    
	public Ad(){}
    public  Ad(double dLong, double dLat, double aLong, double  aLat, String dCity, String aCity, double price, int passengers, String dDate){

        this.departureLongitude = dLong;
        this.departureLatitude = dLat;
        this.arrivalLongitude = aLong;
        this.arrivalLatitude = aLat;
        this.departureCity = dCity;
        this.arrivalCity = aCity;
        this.passengers = passengers;
        this.departureDate = dDate;
        this.price = price;
       

    }
    
    public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDepartureCity() {
		return departureCity;
	}
	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}
	public String getArrivalCity() {
		return arrivalCity;
	}
	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}
	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDepartureLongitude() {
        return departureLongitude;
    }

    public void setDepartureLongitude(double departureLongitude) {
        this.departureLongitude = departureLongitude;
    }

    public double getDepartureLatitude() {
        return departureLatitude;
    }

    public void setDepartureLatitude(double departueLatitude) {
        this.departureLatitude = departueLatitude;
    }

    public double getArrivalLongitude() {
        return arrivalLongitude;
    }

    public void setArrivalLongitude(double arrivalLongitude) {
        this.arrivalLongitude = arrivalLongitude;
    }

    public double getArrivalLatitude() {
        return arrivalLatitude;
    }

    public void setArrivalLatitude(double arrivalLatitude) {
        this.arrivalLatitude = arrivalLatitude;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }
    public void setRidePassengers(Set<User> ridePassengers){
    	this.ridePassengers = ridePassengers;
    }
    public Set<User> getRidePassengers(){
    	return ridePassengers;
    }
    public void addRidePassenger(User aPassenger){
    	getRidePassengers().add(aPassenger);
    }
    
    public User getaUser() {
		return aUser;
	}

	public void setaUser(User aUser) {
		this.aUser = aUser;
	}
	
	
    
 
}


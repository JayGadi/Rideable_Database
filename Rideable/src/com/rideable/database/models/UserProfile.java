package com.rideable.database.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserProfile {

	private int id;
	private int rating;
	private User user;
	
	public UserProfile() {}
	public UserProfile(User aUser){
		this.rating = 100;
		this.user = aUser;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}

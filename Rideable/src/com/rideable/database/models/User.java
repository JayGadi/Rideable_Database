package com.rideable.database.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
public class User implements Serializable {

	private int id;
	private String firstName;
	private String lastName;
	private String userEmail;
	private String userPassword;
	private String regId;

	@JsonIgnore
	private List<Ad> ads;

	public User() {
	}

	public User(String fname, String lname, String email, String password) {
		this.firstName = fname;
		this.lastName = lname;
		this.userEmail = email;
		this.userPassword = password;
		setAds(new ArrayList<Ad>());

	}

	/* Accessors and Mutators */

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public List<Ad> getAds() {
		return ads;
	}

	public void setAds(List<Ad> ads) {
		this.ads = ads;
	}

	public void addAd(Ad ad) {
		getAds().add(ad);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!this.getClass().equals(obj.getClass()))
			return false;

		User obj2 = (User) obj;
		if ((this.id == obj2.getId()) && (this.userEmail.equals(obj2.getUserEmail()))) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int tmp = 0;
		tmp = userEmail.hashCode();
		return tmp;
	}

}

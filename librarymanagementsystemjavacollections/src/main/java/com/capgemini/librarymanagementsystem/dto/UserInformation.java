package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserInformation implements Serializable {
	
	private int userId;
	private String userName;
	private String firstName;
	private String lastName;
	private String userEmailId;
	private String userPassword;
	private int noOfBooksBorrowed = 0;
	private double fine;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getNoOfBooksBorrowed() {
		return noOfBooksBorrowed;
	}

	public void setNoOfBooksBorrowed(int noOfBooksBorrowed) {
		this.noOfBooksBorrowed = noOfBooksBorrowed;
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

	public double getFine() {
		return fine;
	}

	public void setFine(double fine) {
		this.fine = fine;
	}

}

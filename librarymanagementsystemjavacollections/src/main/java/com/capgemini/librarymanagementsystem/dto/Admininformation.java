package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Admininformation implements Serializable {
	
	private String adminEmailId = "jessy@gmail.com";
	private String adminPassword = "Jessy@123";
	
	public String getAdminEmailId() {
		return adminEmailId;
	}
	public void setAdminEmailId(String adminEmailId) {
		this.adminEmailId = adminEmailId;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	

}

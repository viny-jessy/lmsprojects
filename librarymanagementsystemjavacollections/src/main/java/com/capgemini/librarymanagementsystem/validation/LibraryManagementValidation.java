package com.capgemini.librarymanagementsystem.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibraryManagementValidation {

	public boolean idValidation(String id) {
		Pattern pattern = Pattern.compile("^[0-9]{6}$");
		Matcher matcher = pattern.matcher(id);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public boolean nameValidation(String name) {
		Pattern pattern = Pattern.compile("[a-zA-Z-]*");
		Matcher matcher = pattern.matcher(name);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public boolean useridValidation(String userid) {
		Pattern pattern = Pattern.compile("^[0-9]{3}$");
		Matcher matcher = pattern.matcher(userid);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public boolean userpasswordValidation(String password) {
		Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,15}$");
		Matcher matcher = pattern.matcher(password);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public boolean useremailValidation(String email) {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9_.]*@[a-zA-Z]+([.][a-zA-Z]+)+");
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

}

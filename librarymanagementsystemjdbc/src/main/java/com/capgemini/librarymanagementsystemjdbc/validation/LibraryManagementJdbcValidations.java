package com.capgemini.librarymanagementsystemjdbc.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibraryManagementJdbcValidations {
	public boolean validateByBookId(String id) {
		Pattern pattern = Pattern.compile("^[0-9]{6}$");
		Matcher matcher = pattern.matcher(id);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
	public boolean validateByName(String name) {
		Pattern pattern = Pattern.compile("[a-zA-Z-]*");
		Matcher matcher = pattern.matcher(name);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
	public boolean validateByEmail(String email) {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9_.]*@[a-zA-Z]+([.][a-zA-Z]+)+");
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
	public boolean ValidateByUserId(String userId) {
		Pattern pattern = Pattern.compile("[0-9]{3}");
		Matcher matcher = pattern.matcher(userId);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
	public boolean passwordValidation(String password) {
		Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,15}$");
		Matcher matcher = pattern.matcher(password);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}



}

package com.capgemini.libraryhibernate.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.libraryhibernate.exception.LibraryManagementSystemException;

public class LibraryValidations {

	public boolean Idvalidation(int id) throws LibraryManagementSystemException {
		String validateid = "[\\d&&[^0]][\\d]{2}";
		boolean isValid = Pattern.matches(validateid, String.valueOf(id));
		if (isValid) {
			return true;
		} else {
			throw new LibraryManagementSystemException("Id should contain only 3 digits");
		}
	}

	public boolean validateName(String name) throws LibraryManagementSystemException {
		String validateName = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$";
		boolean result = false;
		Pattern pattern = Pattern.compile(validateName);
		Matcher matcher = pattern.matcher(name);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new LibraryManagementSystemException("Name should contains only Alphates");
		}
		return result;
	}

	public boolean validateEmail(String email) throws LibraryManagementSystemException {
		String validateEmail = "[\\w&&[^_]]{3,50}[@]{1}\\D{2,50}[.]{1}\\D{2,50}";
		boolean isValid = Pattern.matches(validateEmail, email);
		if (isValid) {
			return true;
		} else {
			throw new LibraryManagementSystemException("Emial sholud be in this format ex(vinitha@gmail.com)");
		}
	}

	public boolean validatePassword(String password) throws LibraryManagementSystemException {
		String validatePassword = "^.{4,12}$";
		boolean isValid = Pattern.matches(validatePassword, password);
		if (isValid) {
			return true;
		} else {
			throw new LibraryManagementSystemException("password should contain (4-12) characters");
		}
	}
}

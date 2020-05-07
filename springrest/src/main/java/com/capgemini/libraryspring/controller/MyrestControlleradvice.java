package com.capgemini.libraryspring.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capgemini.libraryspring.dto.LibraryResponse;
import com.capgemini.libraryspring.exceptions.LibraryManagementSystemException;

@RestControllerAdvice

public class MyrestControlleradvice {

	@ExceptionHandler
	public LibraryResponse myExceptionHnadler1(LibraryManagementSystemException libraryException) {
		LibraryResponse response = new LibraryResponse();
		response.setError(true);
		response.setMessage(libraryException.getMessage());

		return response;
	}

}

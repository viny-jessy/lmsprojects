package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@SuppressWarnings("serial")
public class RequestInformation implements Serializable {

	private BooksInformation bookInformation;
	private UserInformation userInformation;
	private boolean isBookIssued;
	private boolean isBookReturned;
	private Date bookIssuedDate;
	private Date bookReturndate;

	public BooksInformation getBookInformation() {
		return bookInformation;
	}

	public void setBookInformation(BooksInformation bookInformation) {
		this.bookInformation = bookInformation;
	}

	public UserInformation getUserInformation() {
		return userInformation;
	}

	public void setUserInformation(UserInformation userInformation) {
		this.userInformation = userInformation;
	}

	public boolean isBookIssued() {
		return isBookIssued;
	}

	public void setBookIssued(boolean isBookIssued) {
		this.isBookIssued = isBookIssued;
	}

	public boolean isBookReturned() {
		return isBookReturned;
	}

	public void setBookReturned(boolean isBookReturned) {
		this.isBookReturned = isBookReturned;
	}

	public Date getBookIssuedDate() {
		return bookIssuedDate;
	}

	public void setBookIssuedDate(Date bookIssuedDate) {
		this.bookIssuedDate = bookIssuedDate;
	}

	public Date getBookReturndate() {
		return bookReturndate;
	}

	public void setBookReturndate(Date bookReturndate) {
		this.bookReturndate = bookReturndate;
	}

}

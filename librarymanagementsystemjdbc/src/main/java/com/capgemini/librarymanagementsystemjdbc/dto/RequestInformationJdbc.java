package com.capgemini.librarymanagementsystemjdbc.dto;

import java.io.Serializable;
import java.sql.Date;

public class RequestInformationJdbc implements Serializable{

	private int requestId;
	private int bookId;
	private int userId;
	private String userName;
	private String bookName;
	private BookInformationJdbc bookBean;
	private Admin_User_InformationJdbc userBean;
	private Date issueDate;
	private Date returnDate;
	private Date expectedReturnDate;
	
	
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
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
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public BookInformationJdbc getBookBean() {
		return bookBean;
	}
	public void setBookBean(BookInformationJdbc bookBean) {
		this.bookBean = bookBean;
	}
	public Admin_User_InformationJdbc getUserBean() {
		return userBean;
	}
	public void setUserBean(Admin_User_InformationJdbc userBean) {
		this.userBean = userBean;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public Date getExpectedReturnDate() {
		return expectedReturnDate;
	}
	public void setExpectedReturnDate(Date expectedReturnDate) {
		this.expectedReturnDate = expectedReturnDate;
	}
	
	
	
}

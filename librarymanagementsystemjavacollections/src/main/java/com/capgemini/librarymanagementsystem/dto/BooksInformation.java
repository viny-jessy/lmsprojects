package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class BooksInformation implements Serializable {

	private int bookId;
	private String bookName;
	private String bookCategory;
	private String bookAuthor;
	private String bookPublisher;
	private Date issueDate;
	private Date returnDate;
    private String updatebookname; 
    
	public String getUpdatebookname() {
		return updatebookname;
	}

	public void setUpdatebookname(String updatebookname) {
		this.updatebookname = updatebookname;
	}

	public Date getIssuedate() {
		return issueDate;
	}

	public void setIssuedate(Date issuedate) {
		this.issueDate = issuedate;
	}

	public int getBookid() {
		return bookId;
	}

	public void setBookid(int bookid) {
		this.bookId = bookid;
	}

	public String getBookname() {
		return bookName;
	}

	public void setBookname(String bookname) {
		this.bookName = bookname;
	}

	public String getBookcategory() {
		return bookCategory;
	}

	public void setBookcategory(String bookcategory) {
		this.bookCategory = bookcategory;
	}

	public String getBookauthor() {
		return bookAuthor;
	}

	public void setBookauthor(String bookauthor) {
		this.bookAuthor = bookauthor;
	}

	public String getBookpublisher() {
		return bookPublisher;
	}

	public void setBookpublisher(String bookpublisher) {
		this.bookPublisher = bookpublisher;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

}

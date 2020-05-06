package com.capgemini.librarymanagementsystem.dao;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.capgemini.librarymanagementsystem.database.Database;
import com.capgemini.librarymanagementsystem.dto.Admininformation;
import com.capgemini.librarymanagementsystem.dto.BooksInformation;
import com.capgemini.librarymanagementsystem.dto.RequestInformation;
import com.capgemini.librarymanagementsystem.dto.UserInformation;
import com.capgemini.librarymanagementsystem.exception.LibrarayManagementExceptions;


public class AdminDAOImplementation implements AdminDAO {

	private HashMap<Integer, BooksInformation> bookBeans = new HashMap<Integer, BooksInformation>();
	Set<Entry<Integer, BooksInformation>> set = bookBeans.entrySet();
	
	
	Calendar calendar = Calendar.getInstance();
	Date date = new Date();
	Date bookreturnDate = new Date();
	Date actualReturnDate = calendar.getTime(); 
	Date expectedReturnedDate = new Date();
	SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	String todayDate = dateFormat.format(calendar.getTime());
	String returnDate = dateFormat.format(actualReturnDate);
	
	
	
	@Override
	public boolean enrollUser(UserInformation user) {

		for (UserInformation user1 : Database.USERS) {
			if (user1.getUserId() == user.getUserId()) {
				return false;
			}

		}
		Database.USERS.add(user);

		return true;
	}

	@Override
	public Admininformation adminLogin(String adminEmailId, String adminPassword) {
		Admininformation admin = new Admininformation();
		if (admin.getAdminEmailId().equals(adminEmailId) && admin.getAdminPassword().equals(adminPassword)) {
			return admin;
		}
		throw new LibrarayManagementExceptions("Admin invlaid login credentials!");
	}

	@Override
	public boolean isBookAdded(BooksInformation bookInfo) {
		for (BooksInformation bookBean : Database.BOOK) {
			if (bookBean.getBookid() == bookInfo.getBookid()) {
				return false;
			}
		}
		Database.BOOK.add(bookInfo);
		return true;
	}

	@Override
	public boolean isBookRemoved(int bookId) {
		for (BooksInformation bookInfo : Database.BOOK) {
			if (bookInfo.getBookid() == bookId) {
				Database.BOOK.remove(bookInfo);
				return true;
			}
		}
		return true;
	}

	@Override
	public BooksInformation searchBook(int bookId) {
		for (BooksInformation bookBean : Database.BOOK) {
			if (bookBean.getBookid() == bookId) {

				return bookBean;
			}
		}
		throw new LibrarayManagementExceptions("Search Book not found");
	}

	@Override
	public BooksInformation searchBookByBookName(String bookName) {
		
		for (BooksInformation bookInfo : Database.BOOK) {
			if (bookInfo.getBookname() .equals(bookName)) {

				return bookInfo;
			}
		}
		throw new LibrarayManagementExceptions("Search Book not found");
	}


	@Override
	public BooksInformation searchBookByBookAuthorName(String bookAuthor) {
		
		
		for (BooksInformation bookInformation : Database.BOOK) {
			if (bookInformation.getBookauthor() .equals(bookAuthor)) {

				return bookInformation;
			}
		}
		throw new LibrarayManagementExceptions("Search Book not found");
	}
	
	@Override
	public boolean isBookIssued(UserInformation user, BooksInformation book) {
	
		boolean isValid = false;
		calendar.add(Calendar.DATE, 15);
		expectedReturnedDate = calendar.getTime();
		RequestInformation requestInfo = new RequestInformation();

		int noOfBooksBorrowed = user.getNoOfBooksBorrowed();
		for (RequestInformation info : Database.REQUEST) {
			if (info.getUserInformation().getUserId() == user.getUserId()) {
				if (info.getBookInformation().getBookid() == book.getBookid()) {
					requestInfo = info;
					isValid = true;

				}
			}
		}

		if (isValid)

		{
			System.out.println("I am entreing into is valid");

			for (BooksInformation info2 : Database.BOOK) {
				if (info2.getBookid() == book.getBookid()) {
					book = info2;
				}

			}
			for (UserInformation userInfo2 : Database.USERS) {
				if (userInfo2.getUserId() == user.getUserId()) {
					user = userInfo2;
					noOfBooksBorrowed = user.getNoOfBooksBorrowed();
				}
			}
			if (noOfBooksBorrowed < 3) {
				// System.out.println("entered into no of books borrowed");
				boolean isRemoved = Database.BOOK.remove(book);
				if (isRemoved) {
					// System.out.println("book removed from db");
					// System.out.println("enter into removed");
					noOfBooksBorrowed++;
					System.out.println(noOfBooksBorrowed);
					user.setNoOfBooksBorrowed(noOfBooksBorrowed);
					requestInfo.setBookIssued(true);
					requestInfo.setBookIssuedDate(date);
					requestInfo.setBookReturndate(expectedReturnedDate);
					return true;
				} 

			} 

		} 
			throw new LibrarayManagementExceptions("Book data or User data is incorrect");
	}
	
	@Override
	public boolean isBookReceived(UserInformation userInfo, BooksInformation bookInfo) {

		boolean isValid = false;
		RequestInformation requestInfo1 = new RequestInformation();
		for (RequestInformation requestInfo : Database.REQUEST) {

			if (requestInfo.getBookInformation().getBookid() == bookInfo.getBookid()
					&& requestInfo.getUserInformation().getUserId() == userInfo.getUserId() && requestInfo.isBookReturned() == true) {
				isValid = true;
				requestInfo1 = requestInfo;

			}
		}
		if (isValid) {
			calendar.add(Calendar.DATE, 15);
			actualReturnDate = calendar.getTime();

			bookInfo.setBookauthor(requestInfo1.getBookInformation().getBookauthor());
			bookInfo.setBookname(requestInfo1.getBookInformation().getBookname());
			Database.BOOK.add(bookInfo);
			Database.REQUEST.remove(requestInfo1);

			for (UserInformation userInfo2 : Database.USERS) {
				if (userInfo2.getUserId() == userInfo.getUserId()) {
					userInfo = userInfo2;
				}
			}
			int noOfBooksBorrowed = userInfo.getNoOfBooksBorrowed();
			noOfBooksBorrowed--;
			userInfo.setNoOfBooksBorrowed(noOfBooksBorrowed);
			if (actualReturnDate.after(date)) {
				userInfo.setFine(5.0);
			}
			return true;
		}
		throw new LibrarayManagementExceptions("Book not received");
	}
	

	
	
	
	@Override
	public List<BooksInformation> showAllLibraryBooks() {
		List<BooksInformation> booksList = new LinkedList<BooksInformation>();
		for (BooksInformation book : Database.BOOK) {

			book.getBookid();
			book.getBookauthor();
			book.getBookname();
			booksList.add(book);
		}
		return booksList;
	}

	@Override
	public List<UserInformation> showAllUsers() {
		List<UserInformation> user = new LinkedList<UserInformation>();
		for (UserInformation userInfo : Database.USERS) {
			userInfo.getUserId();
			userInfo.getUserName();
			userInfo.getUserEmailId();
			userInfo.getNoOfBooksBorrowed();
			user.add(userInfo);
		}
		return user;
	}

	@Override
	public List<RequestInformation> showAllUserRequest() {
		List<RequestInformation> request = new LinkedList<RequestInformation>();
		for (RequestInformation requestInformation : Database.REQUEST) {
			requestInformation.getBookInformation();
			requestInformation.getUserInformation();
			requestInformation.isBookIssued();
			requestInformation.isBookReturned();
			request.add(requestInformation);
		}
		return request;
	}
	}


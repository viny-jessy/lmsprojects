package com.capgemini.librarymanagementsystem.dao;

import com.capgemini.librarymanagementsystem.database.Database;
import com.capgemini.librarymanagementsystem.dto.BooksInformation;
import com.capgemini.librarymanagementsystem.dto.RequestInformation;
import com.capgemini.librarymanagementsystem.dto.UserInformation;
import com.capgemini.librarymanagementsystem.exception.LibrarayManagementExceptions;


public class UserDAOImplementation implements UserDAO {

	@Override
	public UserInformation userLogin(String userEmailId, String userPassword) {
		for (UserInformation user : Database.USERS)
			if (user.getUserEmailId().equals(userEmailId) && user.getUserPassword().equals(userPassword)) {
				return user;
			}
		throw new LibrarayManagementExceptions("Invalid user credentials");
	}

	@Override
	public BooksInformation bookSearch(int bookId) {
		for (BooksInformation book : Database.BOOK) {
			if (book.getBookid() == bookId) {
				return book;
			}
		}
		throw new LibrarayManagementExceptions("Invalid search");
	}

	@Override
	public BooksInformation searchBookByBookName(String bookName) {
		
		for (BooksInformation bookBean : Database.BOOK) {
			if (bookBean.getBookname() == bookName) {

				return bookBean;
			}
		}
		throw new LibrarayManagementExceptions("Search Book not found");
	}


	@Override
	public BooksInformation searchBookByBookAuthorName(String bookAuthorName) {
		
		
		for (BooksInformation bookBean : Database.BOOK) {
			if (bookBean.getBookauthor() == bookAuthorName) {

				return bookBean;
			}
		}
		throw new LibrarayManagementExceptions("Search Book not found");
	}
	
	
	@Override
	public RequestInformation bookReturn(UserInformation userInfo, BooksInformation bookInfo) {

		for (RequestInformation requestInfo : Database.REQUEST) {
			if (requestInfo.getBookInformation().getBookid() == bookInfo.getBookid()
					&& requestInfo.getUserInformation().getUserId() == userInfo.getUserId()
					&& requestInfo.isBookIssued() == true) {

				System.out.println("Returning Issued book only");
				requestInfo.setBookReturned(true);
				//requestInfo.setBookReturndate(bookReturndate);
				return requestInfo;
			}
		}
		throw new LibrarayManagementExceptions("Invalid return ");
	}

		
@Override
public RequestInformation bookRequest(UserInformation userInfo, BooksInformation bookInfo) {
	
	boolean flag = false, isRequestExists = false;
	RequestInformation requestInfo = new RequestInformation();
	UserInformation userInfo1 = new UserInformation();
	BooksInformation bookInfo1 = new BooksInformation();

	for (RequestInformation requestInfo2 : Database.REQUEST) {
		if (bookInfo.getBookid() == requestInfo2.getBookInformation().getBookid()) {
			isRequestExists = true;
		}
	}
	if (!isRequestExists) {
		for (UserInformation user : Database.USERS) {
			if (userInfo.getUserId() == user.getUserId()) {
				for (BooksInformation book : Database.BOOK) {
					if (book.getBookid() == bookInfo.getBookid()) {
						userInfo1 = user;
						bookInfo1 = book;
						flag = true;
					}
				}
			}
		}
		if (flag == true) {
			requestInfo.setBookInformation(bookInfo1);
			requestInfo.setUserInformation(userInfo1);
			Database.REQUEST.add(requestInfo);
			return requestInfo;
		}
	}
	throw new LibrarayManagementExceptions("Invalid request or you cannot request that book");
}

}
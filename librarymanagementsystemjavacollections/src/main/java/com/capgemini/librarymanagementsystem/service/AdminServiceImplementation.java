package com.capgemini.librarymanagementsystem.service;

import java.util.HashMap;
import java.util.List;

import com.capgemini.librarymanagementsystem.dao.AdminDAO;
import com.capgemini.librarymanagementsystem.database.Database;
import com.capgemini.librarymanagementsystem.dto.Admininformation;
import com.capgemini.librarymanagementsystem.dto.BooksInformation;
import com.capgemini.librarymanagementsystem.dto.RequestInformation;
import com.capgemini.librarymanagementsystem.dto.UserInformation;
import com.capgemini.librarymanagementsystem.exception.LibrarayManagementExceptions;
import com.capgemini.librarymanagementsystem.factory.LMSFactory;

public class AdminServiceImplementation implements AdminService {

	private AdminDAO admindao = LMSFactory.getadminDao();

	@Override
	public boolean enrollUser(UserInformation userInfo) {

		return admindao.enrollUser(userInfo);
	}

	@Override
	public Admininformation adminLogin(String adminEmailId, String adminPassword) {

		return admindao.adminLogin(adminEmailId, adminPassword);
	}

	@Override
	public boolean isBookAdded(BooksInformation bookInfo) {

		return admindao.isBookAdded(bookInfo);
	}

	@Override
	public boolean isBookRemoved(int bookId) {

		return admindao.isBookRemoved(bookId);
	}

	@Override
	public BooksInformation searchBook(int bookId) {

		return admindao.searchBook(bookId);
	}

	@Override
	public BooksInformation searchBookByBookName(String bookName) {
		
		return admindao.searchBookByBookName(bookName);
	}

	@Override
	public BooksInformation searchBookByBookAuthorName(String bookAuthor) {
		
		return admindao.searchBookByBookAuthorName(bookAuthor);
	}
	
	@Override
	public boolean isBookIssued(UserInformation user, BooksInformation book) {

		return admindao.isBookIssued(user, book);
	}

	@Override
	public boolean isBookReceived(UserInformation userInfo, BooksInformation bookInfo) {

		return admindao.isBookReceived(userInfo, bookInfo);
	}

	@Override
	public List<BooksInformation> showAllLibraryBooks() {

		return admindao.showAllLibraryBooks();
	}

	@Override
	public List<UserInformation> showAllUsers() {

		return admindao.showAllUsers();
	}

	@Override
	public List<RequestInformation> showAllUserRequest() {

		return admindao.showAllUserRequest();
	}
		
}

package com.capgemini.librarymanagementsystem.service;

import com.capgemini.librarymanagementsystem.dao.UserDAO;
import com.capgemini.librarymanagementsystem.dto.BooksInformation;
import com.capgemini.librarymanagementsystem.dto.RequestInformation;
import com.capgemini.librarymanagementsystem.dto.UserInformation;
import com.capgemini.librarymanagementsystem.factory.LMSFactory;

public class UserServiceImplementation implements UserService {

	private UserDAO dao = LMSFactory.getuserDao();

	@Override
	public UserInformation userLogin(String userEmailId, String userPassword) {

		return dao.userLogin(userEmailId, userPassword);
	}

	@Override
	public BooksInformation bookSearch(int bookId) {

		return dao.bookSearch(bookId);
	}
	
	@Override
	public BooksInformation searchBookByBookName(String bookName) {
		
		return dao.searchBookByBookName(bookName);
	}

	@Override
	public BooksInformation searchBookByBookAuthorName(String bookAuthorName) {
		
		return dao.searchBookByBookAuthorName(bookAuthorName);
	}

	@Override
	public RequestInformation bookRequest(UserInformation userInfo, BooksInformation bookInfo) {

		return dao.bookRequest(userInfo, bookInfo);
	}

	@Override
	public RequestInformation bookReturn(UserInformation userInfo, BooksInformation bookInfo) {

		return dao.bookReturn(userInfo, bookInfo);
	}

	
}

package com.capgemini.librarymanagementsystem.dao;

import com.capgemini.librarymanagementsystem.dto.BooksInformation;
import com.capgemini.librarymanagementsystem.dto.RequestInformation;
import com.capgemini.librarymanagementsystem.dto.UserInformation;

public interface UserDAO {
	
	UserInformation userLogin(String userEmailId, String userPassword);

	BooksInformation bookSearch(int bookId);
	
    BooksInformation searchBookByBookName(String bookName);
	
	BooksInformation searchBookByBookAuthorName(String bookAuthorName);

	RequestInformation bookRequest(UserInformation userInfo, BooksInformation bookInfo);

	RequestInformation bookReturn(UserInformation userInfo, BooksInformation bookInfo);

}

package com.capgemini.librarymanagementsystem.dao;


import java.util.List;
import com.capgemini.librarymanagementsystem.dto.Admininformation;
import com.capgemini.librarymanagementsystem.dto.BooksInformation;
import com.capgemini.librarymanagementsystem.dto.RequestInformation;
import com.capgemini.librarymanagementsystem.dto.UserInformation;

public interface AdminDAO {

	public boolean enrollUser(UserInformation user);

	Admininformation adminLogin(String adminEmailId, String adminPassword);

	boolean isBookAdded(BooksInformation bookInfo);

	boolean isBookRemoved(int bookId);

	BooksInformation searchBook(int bookId);
	
	BooksInformation searchBookByBookName(String bookName);
	
	BooksInformation searchBookByBookAuthorName(String bookAuthor);

	boolean isBookIssued(UserInformation user, BooksInformation book);

	boolean isBookReceived(UserInformation userInfo, BooksInformation bookInfo);
	
	 List<BooksInformation> showAllLibraryBooks();

	List<UserInformation> showAllUsers();

	List<RequestInformation> showAllUserRequest();

	

}

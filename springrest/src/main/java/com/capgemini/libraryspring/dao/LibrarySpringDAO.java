package com.capgemini.libraryspring.dao;

import java.util.List;

import com.capgemini.libraryspring.dto.*;

public interface LibrarySpringDAO {

	LibraryDetails Login(String email, String password);

	boolean enrollUser(LibraryDetails user);

	boolean addBook(BookDetails book);

	public boolean updateBook(BookDetails bookInfo);

	List<BookDetails> searchByBookId(int bookId);
	
	List<BookDetails> searchByBookName(String bookName);
	
	List<BookDetails> searchByAuthorName(String authorName);

	List<BookDetails> ListOfBooks();

	List<LibraryDetails> ListOfUsers();

	List<RequestDetails> ListOfRequest();

	boolean isBookCheckedOut(int requestId);

	boolean isBookReceived(int requestId);

	boolean removeBook(int bookId);

	boolean placeBookRequest(int userId, int bookId);

	boolean isBookCheckedIn(int userId, int bookId);

	// List<BookDetails> searchBook(BookDetails bookInfo);

}

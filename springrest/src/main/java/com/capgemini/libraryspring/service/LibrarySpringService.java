package com.capgemini.libraryspring.service;

import java.util.List;

import com.capgemini.libraryspring.dto.BookDetails;
import com.capgemini.libraryspring.dto.LibraryDetails;
import com.capgemini.libraryspring.dto.RequestDetails;

public interface LibrarySpringService {
	
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

}

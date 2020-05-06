package com.capgemini.libraryhibernate.dao;

import java.util.List;

import com.capgemini.libraryhibernate.dto.BookDetails;
import com.capgemini.libraryhibernate.dto.LibraryDetails;
import com.capgemini.libraryhibernate.dto.RequestDetails;

public interface LibraryHibernateDAO {

	boolean adminLogin(int id, String password);

	boolean enrollUser(LibraryDetails user);

	boolean addBook(BookDetails book);

	public boolean updateBook(BookDetails bookInfo);

	public BookDetails searchByBookId(int bookId);

	boolean userLogin(int id, String password);

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

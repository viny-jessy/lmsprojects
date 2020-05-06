package com.capgemini.libraryhibernate.service;

import java.util.List;

import com.capgemini.libraryhibernate.dao.LibraryHibernateDAO;
import com.capgemini.libraryhibernate.dto.BookDetails;
import com.capgemini.libraryhibernate.dto.LibraryDetails;
import com.capgemini.libraryhibernate.dto.RequestDetails;
import com.capgemini.libraryhibernate.factory.LibraryManagementFactory;

public class LibraryHibernateServiceImplementation implements LibraryHibernateService {

	private LibraryHibernateDAO libraryDao = LibraryManagementFactory.getLibraryDAO();

	@Override
	public boolean adminLogin(int id, String password) {
		
		return libraryDao.adminLogin(id, password);
	}

	@Override
	public boolean enrollUser(LibraryDetails user) {
		
		return libraryDao.enrollUser(user);
	}

	@Override
	public boolean addBook(BookDetails book) {
		
		return libraryDao.addBook(book);
	}

	@Override
	public boolean updateBook(BookDetails bookInfo) {
		
		return libraryDao.updateBook(bookInfo);
	}

	@Override
	public BookDetails searchByBookId(int bookId) {
		
		return libraryDao.searchByBookId(bookId);
	}

	@Override
	public boolean userLogin(int id, String password) {
		
		return libraryDao.userLogin(id, password);
	}

	@Override
	public List<BookDetails> ListOfBooks() {
		
		return libraryDao.ListOfBooks();
	}

	@Override
	public List<LibraryDetails> ListOfUsers() {
		
		return libraryDao.ListOfUsers();
	}

	@Override
	public List<RequestDetails> ListOfRequest() {
		
		return libraryDao.ListOfRequest();
	}

	@Override
	public boolean isBookCheckedOut(int requestId) {
		
		return libraryDao.isBookCheckedOut(requestId);
	}

	@Override
	public boolean isBookReceived(int requestId) {
		
		return libraryDao.isBookReceived(requestId);
	}

	@Override
	public boolean removeBook(int bookId) {
		
		return libraryDao.removeBook(bookId);
	}

	@Override
	public boolean placeBookRequest(int userId, int bookId) {
		
		return libraryDao.placeBookRequest(userId, bookId);
	}

	@Override
	public boolean isBookCheckedIn(int userId, int bookId) {
		
		return libraryDao.isBookCheckedIn(userId, bookId);
	}

	
}

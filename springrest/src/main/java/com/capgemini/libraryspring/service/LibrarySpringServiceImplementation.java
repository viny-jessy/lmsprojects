package com.capgemini.libraryspring.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capgemini.libraryspring.dao.LibrarySpringDAO;
import com.capgemini.libraryspring.dto.BookDetails;
import com.capgemini.libraryspring.dto.LibraryDetails;
import com.capgemini.libraryspring.dto.RequestDetails;

@Service
public class LibrarySpringServiceImplementation implements LibrarySpringService {

	@Autowired
	private LibrarySpringDAO libraryDao;
	

	@Override
	public LibraryDetails Login(String email, String password) {
		
		return libraryDao.Login(email, password);
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

	@Override
	public List<BookDetails> searchByBookId(int bookId) {

		return libraryDao.searchByBookId(bookId);
	}

	@Override
	public List<BookDetails> searchByBookName(String bookName) {
		
		return libraryDao.searchByBookName(bookName);
	}

	@Override
	public List<BookDetails> searchByAuthorName(String authorName) {
		
		return libraryDao.searchByAuthorName(authorName);
	}

	
}

package com.capgemini.librarymanagementsystemjdbc.service;

import java.util.List;
import com.capgemini.librarymanagementsystemjdbc.dao.AdminJdbcDao;
import com.capgemini.librarymanagementsystemjdbc.dto.Admin_User_InformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.dto.BookInformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestInformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.factory.LMSJdbcFactory;

public class AdminJdbcServiceImplementation implements AdminJdbcService{
	
	private AdminJdbcDao admindao = LMSJdbcFactory.getAdminJdbcDao();

	@Override
	public boolean enrollUser(Admin_User_InformationJdbc user1) {
		
		return admindao.enrollUser(user1);
	}

	@Override
	public Admin_User_InformationJdbc signIn(String emailId, String password) {
		
		return admindao.signIn(emailId, password);
	}

	@Override
	public boolean enrollBook(BookInformationJdbc info) {
		
		return admindao.enrollBook(info);
	}

	@Override
	public boolean removeBook(int bookId) {
		
		return admindao.removeBook(bookId);
	}

	@Override
	public boolean updateBook(BookInformationJdbc bookBean) {
		
		return admindao.updateBook(bookBean);
	}

	@Override
	public BookInformationJdbc searchBookByBookId(int bookId) {
	
		return admindao.searchBookByBookId(bookId);
	}

	@Override
	public List<Admin_User_InformationJdbc> listOfUsers() {
		
		return admindao.listOfUsers();
	}

	@Override
	public List<BookInformationJdbc> listOfBooks() {
		
		return admindao.listOfBooks();
	}

	@Override
	public List<RequestInformationJdbc> showAllUserRequests() {
		
		return admindao.showAllUserRequests();
	}


	@Override
	public boolean isBookReceived(int requestId) {
	
		return admindao.isBookReceived(requestId);
	}

	@Override
	public boolean bookIssue(int requestId) {
		
		return admindao.bookIssue(requestId);
	}

	
}

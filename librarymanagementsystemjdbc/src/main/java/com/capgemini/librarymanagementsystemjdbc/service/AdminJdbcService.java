package com.capgemini.librarymanagementsystemjdbc.service;

import java.util.List;
import com.capgemini.librarymanagementsystemjdbc.dto.Admin_User_InformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.dto.BookInformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestInformationJdbc;


public interface AdminJdbcService {
	boolean enrollUser(Admin_User_InformationJdbc user);
	Admin_User_InformationJdbc signIn(String emailId, String password);
	boolean enrollBook(BookInformationJdbc info);
	boolean removeBook(int bookId);
	boolean updateBook(BookInformationJdbc bookBean);
	BookInformationJdbc searchBookByBookId(int bookId);
	List<Admin_User_InformationJdbc> listOfUsers();
	List<BookInformationJdbc> listOfBooks();
	List<RequestInformationJdbc> showAllUserRequests();
	boolean bookIssue(int requestId);
	boolean isBookReceived(int requestId);

}

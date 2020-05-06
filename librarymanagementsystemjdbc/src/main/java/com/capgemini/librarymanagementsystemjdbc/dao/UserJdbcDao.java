package com.capgemini.librarymanagementsystemjdbc.dao;

import com.capgemini.librarymanagementsystemjdbc.dto.Admin_User_InformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.dto.BookInformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestInformationJdbc;

public interface UserJdbcDao {

	Admin_User_InformationJdbc login(String email,String password);
	BookInformationJdbc searchById(int id);
//	RequestInfo bookRequest(int userId,int bookId);
	RequestInformationJdbc bookRequest(Admin_User_InformationJdbc userBean, BookInformationJdbc bookBean);
	boolean bookReturn(int userId,int bookId);


}

package com.capgemini.librarymanagementsystemjdbc.service;

import com.capgemini.librarymanagementsystemjdbc.dao.UserJdbcDao;
import com.capgemini.librarymanagementsystemjdbc.dto.Admin_User_InformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.dto.BookInformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestInformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.factory.LMSJdbcFactory;

public class UserJdbcserviceImplementation implements UserJdbcService{
	private UserJdbcDao user = LMSJdbcFactory.getUserJdbcDao();

	@Override
	public Admin_User_InformationJdbc login(String email, String password) {
		
		return user.login(email, password);
	}

	@Override
	public BookInformationJdbc searchById(int id) {
		
		return user.searchById(id);
	}

	@Override
	public RequestInformationJdbc bookRequest(Admin_User_InformationJdbc userBean, BookInformationJdbc bookBean) {
		
		return user.bookRequest(userBean, bookBean);
	}

	@Override
	public boolean bookReturn(int userId, int bookId) {
		
		return user.bookReturn(userId, bookId);
	}
}

package com.capgemini.librarymanagementsystemjdbc.factory;



import java.util.Date;

import com.capgemini.librarymanagementsystemjdbc.dao.AdminJdbcDao;
import com.capgemini.librarymanagementsystemjdbc.dao.AdminJdbcImplementation;
import com.capgemini.librarymanagementsystemjdbc.dao.UserJdbcDao;
import com.capgemini.librarymanagementsystemjdbc.dao.UserJdbcDaoImplementation;
import com.capgemini.librarymanagementsystemjdbc.dto.Admin_User_InformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.dto.BookInformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestInformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.service.AdminJdbcService;
import com.capgemini.librarymanagementsystemjdbc.service.AdminJdbcServiceImplementation;
import com.capgemini.librarymanagementsystemjdbc.service.UserJdbcService;
import com.capgemini.librarymanagementsystemjdbc.service.UserJdbcserviceImplementation;
import com.capgemini.librarymanagementsystemjdbc.validation.LibraryManagementJdbcValidations;

public class LMSJdbcFactory {

	private LMSJdbcFactory() {

	}

	public static BookInformationJdbc getBookInformationJdbc() {

		return new BookInformationJdbc();
	}

	public static RequestInformationJdbc getRequestInformationJdbc() {

		return new RequestInformationJdbc();
	}

	public static UserJdbcDao getUserJdbcDao() {

		return new UserJdbcDaoImplementation();
	}

	public static AdminJdbcDao getAdminJdbcDao() {

		return new AdminJdbcImplementation();
	}

	public static AdminJdbcService getAdminJdbcService() {

		return new AdminJdbcServiceImplementation();
	}

	public static UserJdbcService getUserJdbcService() {

		return new UserJdbcserviceImplementation();
	}

	public static LibraryManagementJdbcValidations getLibraryManagementJdbcValidations() {

		return new LibraryManagementJdbcValidations();
	}

	public static Admin_User_InformationJdbc getAdmin_User_InformationJdbc() {

		return new Admin_User_InformationJdbc();
	}
	
	public static Date getDate() {
		return new Date();
	}


}

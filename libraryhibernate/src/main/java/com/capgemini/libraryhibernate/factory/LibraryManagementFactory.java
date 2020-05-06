package com.capgemini.libraryhibernate.factory;


import com.capgemini.libraryhibernate.dao.LibraryHibernateDAO;
import com.capgemini.libraryhibernate.dao.LibraryHibernateDAOImplementation;
import com.capgemini.libraryhibernate.service.LibraryHibernateService;
import com.capgemini.libraryhibernate.service.LibraryHibernateServiceImplementation;

public class LibraryManagementFactory {
	public static LibraryHibernateDAO getLibraryDAO() {
		return new LibraryHibernateDAOImplementation();
	}
	public static LibraryHibernateService getLibraryService()
	{
		return new LibraryHibernateServiceImplementation();
	}

}

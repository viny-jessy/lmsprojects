package com.capgemini.librarymanagementsystem.database;

import java.util.LinkedList;
import java.util.List;
import com.capgemini.librarymanagementsystem.dto.Admininformation;
import com.capgemini.librarymanagementsystem.dto.BooksInformation;
import com.capgemini.librarymanagementsystem.dto.RequestInformation;
import com.capgemini.librarymanagementsystem.dto.UserInformation;

public class Database {

	public static final List<UserInformation> USERS = new LinkedList<UserInformation>();
	public static final List<Admininformation> ADMIN = new LinkedList<Admininformation>();
	public static final List<BooksInformation> BOOK = new LinkedList<BooksInformation>();
	public static final List<RequestInformation> REQUEST = new LinkedList<RequestInformation>();
	
}

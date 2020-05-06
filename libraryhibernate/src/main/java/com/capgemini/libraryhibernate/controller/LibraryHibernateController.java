package com.capgemini.libraryhibernate.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.capgemini.libraryhibernate.dto.BookDetails;
import com.capgemini.libraryhibernate.dto.LibraryDetails;
import com.capgemini.libraryhibernate.dto.RequestDetails;
import com.capgemini.libraryhibernate.exception.LibraryManagementSystemException;
import com.capgemini.libraryhibernate.factory.LibraryManagementFactory;
import com.capgemini.libraryhibernate.service.LibraryHibernateService;
import com.capgemini.libraryhibernate.validation.LibraryValidations;

public class LibraryHibernateController {

	private static final LibraryHibernateService SERVICE = LibraryManagementFactory.getLibraryService();
	private static final LibraryValidations VALIDATION = new LibraryValidations();
	public static Scanner scanner = new Scanner(System.in);

	public static int selectChoice() {
		boolean flag = false;
		int choice = 0;
		do {
			try {
				choice = scanner.nextInt();
				flag = true;
			} catch (InputMismatchException e) {
				flag = false;
				System.err.println("Yor choice should be a digit ");
				scanner.next();
			}
		} while (!flag);
		return choice;
	}

	public static int verifyRequestId() {
		boolean flag = false;
		int choice = 0;
		do {
			try {
				choice = scanner.nextInt();
				flag = true;
			} catch (InputMismatchException e) {
				flag = false;
				System.err.println("request id should be a digit  ");
				scanner.next();
			}
		} while (!flag);
		return choice;
	}

	public static int verfiyId() {
		boolean flag = false;
		int id = 0;
		do {
			try {
				id = scanner.nextInt();
				VALIDATION.Idvalidation(id);
				flag = true;
			} catch (InputMismatchException e) {
				flag = false;
				System.err.println("Id Should contains only digits");
				scanner.next();
			} catch (LibraryManagementSystemException e) {
				flag = false;
				System.err.println(e.getMessage());
			}
		} while (!flag);

		return id;
	}

	public static String verifyName() {
		String name = null;
		boolean flag = false;
		do {
			try {
				name = scanner.nextLine();
				VALIDATION.validateName(name);
				flag = true;
			} catch (LibraryManagementSystemException e) {
				flag = false;
				System.err.println(e.getMessage());
			}
		} while (!flag);

		return name;

	}

	public static String verifyEmailId() {
		String emailId = null;
		boolean flag = false;
		do {
			try {
				emailId = scanner.next();
				VALIDATION.validateEmail(emailId);
				flag = true;
			} catch (LibraryManagementSystemException e) {
				flag = false;
				System.err.println(e.getMessage());
			}
		} while (!flag);
		return emailId;
	}

	public static String verifyPassword() {
		String password = null;
		boolean flag = false;
		do {
			try {
				password = scanner.next();
				VALIDATION.validatePassword(password);
				flag = true;
			} catch (LibraryManagementSystemException e) {
				flag = false;
				System.err.println(e.getMessage());
			}
		} while (!flag);
		return password;
	}

	public static boolean verifyBookAvailability() {
		boolean isbokkAvailabler = false;
		boolean flag = false;
		do {
			try {
				isbokkAvailabler = scanner.nextBoolean();
				flag = true;
			} catch (InputMismatchException e) {
				System.err.println("enter either true/ false");
				flag = false;
				scanner.next();
			}
		} while (!flag);
		return isbokkAvailabler;
	}

	public static String verifyRole() {
		String role = null;
		boolean flag = false;

		do {
			role = scanner.next();
			if (role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("user")) {
				flag = true;
			} else {
				flag = false;
				System.err.println("choose role as either admin/user");
			}
		} while (!flag);

		return role.toLowerCase();
	}

	public static void main(String[] args) {

		int choice = 0;
		int verify = 0;
		int userChoice = 0;
		int userId = 0;
		int requestUserId = 0;
		int bookId = 0;
		int updateBookId = 0;
		int requestId = 0;
		String role;
		String name = null;
		String emailId = null;
		String password = null;
		String bookTitle = null;
		boolean flag = false;
		boolean isAvailable = false;
		BookDetails bookInfo = null;
		LibraryDetails LibraryUsers = null;

		do {
			System.out.println("************** Welcome To Digital Library **************");
			System.out.println("1. To Perform Admin operations");
			System.out.println("2. To perform User Operations");
			System.out.println("Enter your choice");
			choice = selectChoice();

			switch (choice) {
			case 1:
				System.out.println("***************Welcome admin Login page*************");
				System.out.println("/nEnter Admin id");
				userId = verfiyId();
				System.out.println("/nEnter Admin password");
				password = verifyPassword();

				try {
					SERVICE.adminLogin(userId, password);
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
					System.out.println("Admin logged in to Account sucessfully");
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

					do {
						System.out.println("1. To Register User");
						System.out.println("2. To Add Book In to Library");
						System.out.println("3. To update Book In to Library");
						System.out.println("4. To Remove Book from library");
						System.out.println("5. To Search Book by bookId");
						System.out.println("6. To show list of Books");
						System.out.println("7. To Show list of Users");
						System.out.println("8. To Show list of Requests");
						System.out.println("9. To Issue book");
						System.out.println("10. To Receive Book returned by user");
						System.out.println("0. Exit");
						System.out.println("Enter your choice");
						verify = selectChoice();

						switch (verify) {
						case 1:
							System.out.println("Enter below Details to register user");
							System.out.println("/n->Enter user id :");
							userId = verfiyId();
							scanner.nextLine();
							System.out.println("/n->Enter user name :");
							name = verifyName();
							System.out.println("/n->Enter User Email Id :");
							emailId = verifyEmailId();
							System.out.println("/n->Enter password :");
							password = verifyPassword();
							System.out.println("/n->Enter Role :");
							role = verifyRole();

							LibraryUsers = new LibraryDetails();
							LibraryUsers.setId(userId);
							LibraryUsers.setName(name);
							LibraryUsers.setEmailId(emailId);
							LibraryUsers.setPassword(password);
							LibraryUsers.setRole(role);

							try {
								SERVICE.enrollUser(LibraryUsers);
								System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^");
								System.out.println("User Registered Succesfully");
								System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^");
							} catch (LibraryManagementSystemException e) {
								System.err.println(e.getMessage());
							}
							break;

						case 2:
							System.out.println("Adding Book To Library ");
							System.out.println("-------------------------");
							System.out.println("Enter book Id: ");
							bookId = verfiyId();
							System.out.println("Enter book Book Title: ");
							scanner.nextLine();
							bookTitle = verifyName();
							System.out.println("Enter Authour Name: ");
							name = verifyName();
							System.out.println("Is Book Available for Borrowing");
							isAvailable = verifyBookAvailability();

							bookInfo = new BookDetails();
							bookInfo.setBookId(bookId);
							bookInfo.setAuthourName(name);
							bookInfo.setBookName(bookTitle);
							bookInfo.setAvailable(isAvailable);


							try {
								SERVICE.addBook(bookInfo);
								System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
								System.out.println("Successfully Added book in to library");
								System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
							} catch (LibraryManagementSystemException e) {
								System.err.println(e.getMessage());
							}
							break;

							

			
							
							
							
						case 3:

							System.out.println("\n-->Enter Below details to update book in to library");
							System.out.println("\n-->Enter Book id");
							updateBookId = verfiyId();
							try {
								BookDetails books = SERVICE.searchByBookId(updateBookId);

								System.out.println("Book Id             : " + books.getBookId());
								System.out.println("Book Title          : " + books.getBookName());
								System.out.println("Authour Name        : " + books.getAuthourName());
								System.out.println("Book Available		: " + books.isAvailable());
								System.out.println("===================================");
							} catch (LibraryManagementSystemException e) {
								System.err.println(e.getMessage());
							}

							System.out.println("/n->Enter book Book Name");
							scanner.nextLine();
							bookTitle = verifyName();
							System.out.println("/n->Enter Authour Name");
							name = verifyName();

							BookDetails bookInfo11 = new BookDetails();

							bookInfo11.setBookId(updateBookId);
							bookInfo11.setBookName(bookTitle);
							bookInfo11.setAuthourName(name);

							try {

								boolean bookRemoved = SERVICE.removeBook(updateBookId);
								if (bookRemoved) {
									boolean bookAdded = SERVICE.addBook(bookInfo11);
									System.out.println("Is Book updated in to account  : " + updateBookId);
									if (bookAdded) {
										System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
										System.out.println("Book is updated in to account successfully!!");
										System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
									}
								}
							} catch (LibraryManagementSystemException e) {
								System.err.println(e.getMessage());
							}
							break;

						case 4:
							System.out.println("/n->Enter below Details to remove book from library ");
							System.out.println("/n->Enter Book Id To Remove");
							bookInfo = new BookDetails();
							bookId = verfiyId();
							bookInfo.setBookId(bookId);

							try {
								SERVICE.removeBook(bookId);
								System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
								System.out.println("SuccessfullyRemoved book from libarary");
								System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
							} catch (LibraryManagementSystemException e) {
								System.err.println(e.getMessage());
							}
							break;

						case 5:
							System.out.println("Enter Book ID To Search:");
							bookId = verfiyId();
							bookInfo.setBookId(bookId);
							System.out.println("===================================");
							try {
								BookDetails books = SERVICE.searchByBookId(bookId);

								System.out.println("Book Id             : " + books.getBookId());
								System.out.println("Book Title          : " + books.getBookName());
								System.out.println("Authour Name        : " + books.getAuthourName());
								System.out.println("Book Available		: " + books.isAvailable());
								System.out.println("===================================");
							} catch (LibraryManagementSystemException e) {
								System.err.println(e.getMessage());
							}
							break;

						case 6:
							try {
								List<BookDetails> allBooks = SERVICE.ListOfBooks();
								System.out.println("Here are the book Details :");
								System.out.println("==============================");

								for (BookDetails book : allBooks) {
									System.out.println("Book id             :" + book.getBookId());
									System.out.println("Book Name           :" + book.getBookName());
									System.out.println("Book Authour        :" + book.getAuthourName());
									System.out.println("Book Available		:" + book.isAvailable());
									System.out.println("==============================");
								}
							} catch (LibraryManagementSystemException e) {
								System.err.println(e.getMessage());
							}
							break;

						case 7:
							try {
								List<LibraryDetails> users = SERVICE.ListOfUsers();
								System.out.println("Here are list of library users :");
								System.out.println("==========================================");
								for (LibraryDetails info : users) {
									System.out.println("User id                 : " + info.getId());
									System.out.println("User Name               : " + info.getName());
									System.out.println("User Email              : " + info.getEmailId());
									System.out.println("No Of Books Borrowed 	: " + info.getNoOfBooksBorrowed());
									System.out.println("Fine                    : " + info.getFine());
									System.out.println("Role                    : " + info.getRole());
									System.out.println("==========================================");
								}
							} catch (LibraryManagementSystemException e) {
								System.err.println(e.getMessage());
							}
							break;

						case 8:
							try {
								List<RequestDetails> requestInfos = SERVICE.ListOfRequest();
								System.out.println("Requests for Books are :");
								System.out.println("==========================================");

								for (RequestDetails info : requestInfos) {
									System.out.println("Request Id              : " + info.getRequestId());
									System.out.println("Book Id                 : " + info.getBookId());
									System.out.println("User Id                 : " + info.getUserId());
									System.out.println("Book IssuedDate         : " + info.getIssuedDate());
									System.out.println("Expected Return Date	: " + info.getExpectedReturnDate());
									System.out.println("Book ReturnedDate       : " + info.getBookReturnedDate());
									System.out.println("==========================================");
								}
							} catch (LibraryManagementSystemException e) {
								System.err.println(e.getMessage());
							}
							break;

						case 9:
							System.out.println("/n->Enter Below Details to issue book");
							System.out.println("/n->Enter Request Id");
							requestId = verifyRequestId();

							try {
								SERVICE.isBookCheckedOut(requestId);
								System.out.println("^^^^^^^^^^^^^^^^^^^^^^^");
								System.out.println("Book Issued Sucessfully");
								System.out.println("^^^^^^^^^^^^^^^^^^^^^^^");
							} catch (LibraryManagementSystemException e) {
								System.err.println(e.getMessage());
							}
							break;

						case 10:
							System.out.println("/n->Enter below Details to return book back to library");
							System.out.println("/n->Enter Request Id");
							requestId = verifyRequestId();

							try {
								SERVICE.isBookReceived(requestId);
								System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
								System.out.println("sucessfully recieved returned book by user");
								System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
							} catch (LibraryManagementSystemException e) {
								System.err.println(e.getMessage());
							}
							break;

						case 0:
							break;

						default:
							System.err.println("Choice Should Be in Between 1 to 9");
							break;

						}
					} while (verify != 0); // do while for Admin options // Admin activities end
				} catch (LibraryManagementSystemException e) {
					System.err.println(e.getMessage());
				}
				break;

			// User Page

			case 2:
				System.out.println("***************Welcome to User Log page***************");
				System.out.println("/nEnter User id: ");
				userId = verfiyId();
				System.out.println("/nEnter User password: ");
				password = verifyPassword();

				try {
					SERVICE.userLogin(userId, password);
					System.out.println("User logged in");
					do {
						System.out.println("1. To show list of books in library");
						System.out.println("2. To Search a Book by id");
						System.out.println("3. To place a request to librarian");
						System.out.println("4. To return book to library");
						System.out.println("0. Exit");
						System.out.println("Enter your choice");
						userChoice = selectChoice();

						switch (userChoice) {
						case 1:
							try {
								List<BookDetails> allBooks = SERVICE.ListOfBooks();
								System.out.println("Here are the book Details :");
								System.out.println("==============================");

								for (BookDetails book : allBooks) {
									System.out.println("Book id             :" + book.getBookId());
									System.out.println("Book Name           :" + book.getBookName());
									System.out.println("Book Authour        :" + book.getAuthourName());
									System.out.println("Book Available		:" + book.isAvailable());
									System.out.println("==============================");
								}
							} catch (LibraryManagementSystemException e) {
								System.err.println(e.getMessage());
							}
							break;

						case 2:
							System.out.println("/n->Enter below Details to search a book in librray");
							System.out.println("/n->Enter Book ID To Search:");
							bookId = verfiyId();
							bookInfo.setBookId(bookId);
							System.out.println("===================================");
							try {
								BookDetails books = SERVICE.searchByBookId(bookId);

								System.out.println("Book Id             : " + books.getBookId());
								System.out.println("Book Title          : " + books.getBookName());
								System.out.println("Authour Name        : " + books.getAuthourName());
								System.out.println("Book Available		: " + books.isAvailable());
								System.out.println("===================================");
							} catch (LibraryManagementSystemException e) {
								System.err.println(e.getMessage());
							}
							break;

						case 3:
							System.out.println("->Enter Below Details to place a request for book");
							System.out.println("->/nEnter user id");
							do {
								requestUserId = verfiyId();
								if (requestUserId == userId) {
									flag = true;
								} else {
									flag = false;
									System.err.println("/nEnter Your User Id");
								}
							} while (!flag);

							System.out.println("/nEnter book id");
							bookId = verfiyId();

							try {
								SERVICE.placeBookRequest(requestUserId, bookId);
								System.out.println("Request placed to Admin Succesfully");
							} catch (LibraryManagementSystemException e) {
								System.err.println(e.getMessage());
							}
							break;

						case 4:
							System.out.println("Returning a book:");
							System.out.println("------------------");
							System.out.println("Enter User Id");
							do {
								requestUserId = verfiyId();
								if (requestUserId == userId) {
									flag = true;
								} else {
									flag = false;
									System.err.println("Enter Your User Id");
								}
							} while (!flag);

							System.out.println("Enter Book Id");
							bookId = verfiyId();

							try {
								SERVICE.isBookCheckedIn(requestUserId, bookId);
								System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
								System.out.println("Successfully Book returned to admin");
								System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
							} catch (LibraryManagementSystemException e) {
								System.err.println(e.getMessage());
							}
							break;

						case 0:
							break;

						default:
							System.err.println(" your Should be in between 0-4");
							break;
						}
					} while (userChoice != 0);

				} catch (LibraryManagementSystemException e) {
					System.err.println(e.getMessage());
				}
				break;

			default:
				System.err.println("Choice should be in beween 1 and 2");
				break;

			}

		} while (true);

	}

}

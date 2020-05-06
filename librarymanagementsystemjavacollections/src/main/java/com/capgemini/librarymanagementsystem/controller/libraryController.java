package com.capgemini.librarymanagementsystem.controller;


import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import com.capgemini.librarymanagementsystem.dto.Admininformation;
import com.capgemini.librarymanagementsystem.dto.BooksInformation;
import com.capgemini.librarymanagementsystem.dto.RequestInformation;
import com.capgemini.librarymanagementsystem.dto.UserInformation;
import com.capgemini.librarymanagementsystem.exception.LibrarayManagementExceptions;
import com.capgemini.librarymanagementsystem.factory.LMSFactory;
import com.capgemini.librarymanagementsystem.service.AdminService;
import com.capgemini.librarymanagementsystem.service.UserService;
import com.capgemini.librarymanagementsystem.validation.LibraryManagementValidation;

public class libraryController {

	public static void main(String[] args) {

		libraryController librarycontroller = new libraryController();

		try {
			librarycontroller.Controller();
		} catch (InputMismatchException e) {
			System.err.println(
					"\nYou have encountered InputMismatchException please check the details that you have entered");
		} catch (NumberFormatException e) {
			System.err.println(
					"\nYou have encountered NumberFormatException please check the details that you have entered");
		} catch (NoSuchElementException e) {
			System.out.println(
					"\nYou have encountered NoSuchElementException please check the details that you have entered");
		} finally {
			try {
				librarycontroller.Controller();
			} catch (InputMismatchException e) {
				System.err.println("\nPlease enter the data in correct format : " + e);
			} catch (NumberFormatException e) {
				System.err.println("\nPlease enter the data in correct format : " + e);
			} catch (NoSuchElementException e) {
				System.err.println("\nPlease enter the data in correct format : " + e);
			} finally {
				librarycontroller.Controller();
			}
		}
	}

	public void Controller() {

		AdminService service = LMSFactory.getAdminService();
		UserService userService = LMSFactory.getUserService();
		Scanner scanner = new Scanner(System.in);
		UserInformation userInfo = new UserInformation();
		BooksInformation bookBean = new BooksInformation();
		Admininformation adminBean = new Admininformation();
		LibraryManagementValidation validation = LMSFactory.getLibraryManagementValidation();

		int selectChoice, check, userChoice;
		do {

			System.out.println("1. To log in to Admin account");
			System.out.println("2. To log in to User account");
			System.out.println("Enter your choice");
			System.out.println("");
			selectChoice = scanner.nextInt();
			switch (selectChoice) {

			case 1:
				System.out.println("Please enter emailid in this format (vinitha@gmail.com)");
				System.out.println("");
				System.out.println("Enter Admin Email id");
				String adminEmailId = scanner.next();
				boolean validateByEmailId = validation.useremailValidation(adminEmailId);
				while (!validateByEmailId) {
					try {
						throw new LibrarayManagementExceptions("Please enter valid Email Id");
					} catch (LibrarayManagementExceptions lmse) {
						System.out.println(".............................................................");
						System.err.println("please eneter a valid email address!!");
						System.out.println(".............................................................");
						adminEmailId = scanner.next();
						if (validation.useremailValidation(adminEmailId)) {
							break;
						}
					}
				}
				System.out.println("");
				System.out.println("Enter Admin password in this format (Vinitha@123)");
				System.out.println("");
				System.out.println(
						"Password should contain (8-15)characters, atleast one uppercase,lower case,special character");
				String adminPassword = scanner.next();
				boolean validatePassword = validation.userpasswordValidation(adminPassword);
				while (!validatePassword) {
					try {
						throw new LibrarayManagementExceptions("Please enter valid password");
					} catch (LibrarayManagementExceptions lmse) {
						System.out.println(
								"......................................................................................................");
						System.err.println(
								"Enter a combination atleast 8 number,letters,punctuation marks with upper and lower case letters");
						System.out.println(
								".......................................................................................................");

						adminPassword = scanner.next();
						if (validation.userpasswordValidation(adminPassword)) {
							break;
						}
					}
				}

				try {
					Admininformation adminBean1 = service.adminLogin(adminEmailId, adminPassword);
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
					System.out.println("Admin logged in to Account Successfully!.......");
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
					do {
						System.out.println("1. To Register user");
						System.out.println("2. To Search book by bookid");
						System.out.println("3. To Search book by bookname");
						System.out.println("4. To search book by book author name");
						System.out.println("5. To Add Book in to library");
						System.out.println("6. To Remove Book from library");
						System.out.println("7. To Show list of books present in library");
						System.out.println("8. To Issue book to user");
						System.out.println("9. To Show list of users enrolled in library");
						System.out.println("10. To Show list of Requests placed by users");
						System.out.println("11. To Receive Returned Book");
						System.out.println("0. To Exit");

						System.out.println("Enter your choice");
						check = scanner.nextInt();

						switch (check) {
						case 1:
							System.out.println("-->Please enter below details to register");
							System.out.println("-->Entering User Id should be a 3 digit number");
							System.out.println("-->Enter user id");
							String registraionId = scanner.next();
							boolean validateUserId = validation.useridValidation(registraionId);
							while (!validateUserId) {
								try {
									throw new LibrarayManagementExceptions("please enter valid user id");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("............................");
									System.err.println("Please enter valid user id!!");
									System.out.println("............................");
									registraionId = scanner.next();
									if (validation.useridValidation(registraionId)) {
										break;
									}
								}
							}
							System.out.println("Enter user name");
							String userName = scanner.next();
							boolean ValidateByName = validation.nameValidation(userName);
							while (!ValidateByName) {
								try {
									throw new LibrarayManagementExceptions("Please enter valid user name");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("..............................");
									System.err.println("Please enter valid user name!!");
									System.out.println("..............................");
									userName = scanner.next();
									if (validation.nameValidation(userName)) {
										break;
									}
								}
							}
							System.out.println("Enter Email Id");
							String userEmailId = scanner.next();
							boolean validateByEmail = validation.useremailValidation(userEmailId);
							while (!validateByEmail) {
								try {
									throw new LibrarayManagementExceptions("Please enter valid Email Id");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("............................");
									System.err.println("Please valid Email address!!");
									System.out.println("............................");
									userEmailId = scanner.next();
									if (validation.useremailValidation(userEmailId)) {
										break;
									}
								}
							}
							System.out.println("");
							System.out.println("Enter Admin password in this format (Vinitha@123)");
							System.out.println("");
							System.out.println(
									"Password shoud contain 8-15 characters,atleast one uppercase,lowercase and atleast one special charater");
							String userPassword = scanner.next();
							boolean validateByPassword = validation.userpasswordValidation(userPassword);
							while (!validateByPassword) {
								try {
									throw new LibrarayManagementExceptions("Please enter valid password");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println(
											"................................................................................................");
									System.err.println(
											"Enter a combination atleast 8 number,letters,punctuation marks with upper and lower case letters");
									System.out.println(
											"................................................................................................");
									userPassword = scanner.next();
									if (validation.userpasswordValidation(userPassword)) {
										break;
									}
								}
							}

							UserInformation userInformation = new UserInformation();

							userInformation.setUserId(Integer.parseInt(registraionId));
							userInformation.setUserName(userName);
							userInformation.setUserEmailId(userEmailId);
							userInformation.setUserPassword(userPassword);
							try {
								service.enrollUser(userInformation);

								System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
								System.out.println("user Registered Successfully!!!");
								System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
							} catch (LibrarayManagementExceptions lmse) {
								System.out.println("...............................");
								System.err.println("sorry User already registered!!");
								System.out.println("...............................");
							}
							break;

						case 2:
							System.out.println("-->Enter Below Details to search abook");
							System.out.println("-->Please make sure that Book Id must contain 6 digit number");
							System.out.println("-->Enter book Id");
							String searchBookId = scanner.next();
							boolean validateByBookId = validation.idValidation(searchBookId);
							while (!validateByBookId) {
								try {
									throw new LibrarayManagementExceptions("Please enter valid data");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("..........................");
									System.err.println("Please enter valid book id");
									System.out.println("..........................");
									searchBookId = scanner.next();
									if (validation.idValidation(searchBookId)) {
										break;
									}
								}
							}
							try {
								BooksInformation bookSearch = service.searchBook(Integer.parseInt(searchBookId));
								System.out.println("Search book Found");
								System.out.println("Here is the book details:");
								System.out.println("Book Id                  : " + bookSearch.getBookid());
								System.out.println("Book name                : " + bookSearch.getBookname());
								System.out.println("Author name              : " + bookSearch.getBookauthor());

							} catch (LibrarayManagementExceptions lmse) {
								System.out.println("..........................................");
								System.err.println("sorry book is not available in the library");
								System.out.println("..........................................");

							}
							break;

						case 3:
							System.out.println("-->Enter Below Details to search abook");
							System.out.println("-->Enter book Name");
							String searchBookName = scanner.next();
							boolean validateByBookName = validation.nameValidation(searchBookName);
							while (!validateByBookName) {
								try {
									throw new LibrarayManagementExceptions("Please enter valid data");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("..........................");
									System.err.println("Please enter valid book Name");
									System.out.println("..........................");
									searchBookName = scanner.next();
									if (validation.nameValidation(searchBookName)) {
										break;
									}
								}
							}
							try {
								BooksInformation searchBookName1 = service.searchBookByBookName(searchBookName);
								System.out.println("Search book Found");
								System.out.println("Here is the book details:");
								System.out.println("Book Id                  : " + searchBookName1.getBookid());
								System.out.println("Book name                : " + searchBookName1.getBookname());
								System.out.println("Author name              : " + searchBookName1.getBookauthor());

							} catch (LibrarayManagementExceptions lmse) {
								System.out.println("..........................................");
								System.err.println("sorry book is not available in the library");
								System.out.println("..........................................");

							}
							break;

						case 4:
							System.out.println("-->Enter Below Details to search abook by book author name");
							System.out.println("-->Enter book author name");
							String bookAuthorName = scanner.next();
							boolean validateByBookauthorname = validation.nameValidation(bookAuthorName);
							while (!validateByBookauthorname) {
								try {
									throw new LibrarayManagementExceptions("Please enter valid data");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("..........................");
									System.err.println("Please enter valid book author name");
									System.out.println("..........................");
									bookAuthorName = scanner.next();
									if (validation.nameValidation(bookAuthorName)) {
										break;
									}
								}
							}
							try {
								BooksInformation bookSearchByAuthorName = service.searchBookByBookAuthorName(bookAuthorName);
								System.out.println("Search book Found");
								System.out.println("Here is the book details:");
								System.out.println("Book Id                  : " + bookSearchByAuthorName.getBookid());
								System.out.println("Book name                : " + bookSearchByAuthorName.getBookname());
								System.out.println("Author name              : " + bookSearchByAuthorName.getBookauthor());

							} catch (LibrarayManagementExceptions lmse) {
								System.out.println("..........................................");
								System.err.println("sorry book is not available in the library");
								System.out.println("..........................................");

							}
							break;

						case 5:
							System.out.println("-->Enter Below details to add book in to library");
							System.out.println("-->Please make sure that Book Id must contain 6 digit number");
							System.out.println("-->Enter Book id");
							String bookId = scanner.next();
							boolean validateBookId = validation.idValidation(bookId);
							while (!validateBookId) {
								try {
									throw new LibrarayManagementExceptions("Please enter valid data");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("..........................");
									System.err.println("Please enter valid book id");
									System.out.println("..........................");
									bookId = scanner.next();
									if (validation.idValidation(bookId)) {
										break;
									}
								}
							}
							System.out.println("Enter Book Author name");
							String authourName = scanner.next();
							boolean ValidateByAuthorName = validation.nameValidation(authourName);
							while (!ValidateByAuthorName) {
								try {
									throw new LibrarayManagementExceptions("Please enter valid data");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("....................................");
									System.err.println("Please enter proper book author name");
									System.out.println("....................................");
									authourName = scanner.next();
									if (validation.nameValidation(authourName)) {
										break;
									}
								}
							}
							System.out.println("Enter Book Title");
							String bookTitle = scanner.next();
							boolean ValidateByBookName = validation.nameValidation(bookTitle);
							while (!ValidateByBookName) {
								try {
									throw new LibrarayManagementExceptions("Please enter valid data");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("............................");
									System.err.println("Please enter valid book name");
									System.out.println("............................");
									bookTitle = scanner.next();
									if (validation.nameValidation(bookTitle)) {
										break;
									}
								}
							}
							System.out.println("Enter Book Category");
							String bookcategory = scanner.next();
							boolean ValidateByBookCategory = validation.nameValidation(bookcategory);
							while (!ValidateByBookCategory) {
								try {
									throw new LibrarayManagementExceptions("Please enter valid data");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("................................");
									System.err.println("Please enter valid book Category");
									System.out.println("................................");
									bookcategory = scanner.next();
									if (validation.nameValidation(bookcategory)) {
										break;
									}
								}
							}

							BooksInformation bookInfo1 = new BooksInformation();

							bookInfo1.setBookid(Integer.parseInt(bookId));
							bookInfo1.setBookauthor(authourName);
							bookInfo1.setBookname(bookTitle);
							bookInfo1.setBookcategory(bookcategory);
							try {

								boolean bookAdded = service.isBookAdded(bookInfo1);
								System.out.println("Is Book Added in to account  : " + bookAdded);

								if (bookAdded) {
									System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
									System.out.println("Book is added in to account successfully!!");
									System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
								}
							} catch (LibrarayManagementExceptions lmse) {
								System.out.println("...................................");
								System.out.println("Book is Added in to account already");
								System.out.println("...................................");
							}

							break;
						case 6:

							System.out.println("-->Enter Below details to remove book from library");
							System.out.println("-->Please make sure that Book Id must contain 6 digit number");
							System.out.println("-->Enter Book id");
							String removeBookId = scanner.next();
							boolean validateByBookId1 = validation.idValidation(removeBookId);
							while (!validateByBookId1) {
								try {
									throw new LibrarayManagementExceptions("Please enter valid data");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("..........................");
									System.err.println("Please enter valid book id");
									System.out.println("..........................");
									removeBookId = scanner.next();
									if (validation.idValidation(removeBookId)) {
										break;
									}
								}
							}
							bookBean.setBookid(Integer.parseInt(removeBookId));
							try {
								boolean bookRemoved = service.isBookRemoved(Integer.parseInt(removeBookId));
								if (bookRemoved) {
									System.out.println("Book Removed successfully from libraray!");
								}
							} catch (LibrarayManagementExceptions lmse) {
								System.out.println(
										"......................................................................................................");
								System.err.println("Sorry book is not available to remove");
								System.out.println(
										"......................................................................................................");
							}
							break;

						case 7:

							try {
								System.out.println("List of Books that are available in library are :");

								List<BooksInformation> allBooks = service.showAllLibraryBooks();
								for (BooksInformation book : allBooks) {
									System.out
											.println("==============================================================");
									System.out.println("Book id                      : " + book.getBookid());
									System.out.println("Book Name                    : " + book.getBookname());
									System.out.println("Book Authour                 : " + book.getBookauthor());
									System.out.println("Book Category                : " + book.getBookcategory());
									System.out
											.println("==============================================================");
								}
							} catch (LibrarayManagementExceptions e) {
								System.out.println(".....................................");
								System.err.println("sorry no books are present in library");
								System.out.println(".....................................");
							}
							break;

						case 8:

							BooksInformation bookInfo2 = new BooksInformation();
							UserInformation userInfo2 = new UserInformation();
							System.out.println("-->Enter Below details to issue book to the user");
							System.out.println("-->Please make sure that Book Id must contain 6 digit number");
							System.out.println("-->Enter Book id");
							String bookId1 = scanner.next();
							boolean validateByBookid1 = validation.idValidation(bookId1);
							while (!validateByBookid1) {
								try {
									throw new LibrarayManagementExceptions("Please enter valid data");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("..........................");
									System.err.println("Please enter valid book id");
									System.out.println("..........................");
									bookId1 = scanner.next();
									if (validation.idValidation(bookId1)) {
										break;
									}
								}
							}
							System.out.println("-->Please enter below details to issue book");
							System.out.println("-->plesae make sure you enter 3 digits user id");
							System.out.println("-->Enter user id");
							String userId = scanner.next();
							boolean validateByUserId = validation.useridValidation(userId);
							while (!validateByUserId) {
								try {
									throw new LibrarayManagementExceptions("pleas enter valid data");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("..........................");
									System.err.println("Please enter valid user Id");
									System.out.println("..........................");
									userId = scanner.next();
									if (validation.useridValidation(userId)) {
										break;
									}
								}
							}

							bookInfo2.setBookid(Integer.parseInt(bookId1));
							userInfo2.setUserId(Integer.parseInt(userId));
							try {
								boolean isIssued = service.isBookIssued(userInfo2, bookInfo2);
								if (isIssued) {
									System.out.println("Book Issued Successfully!!");
								}

							} catch (LibrarayManagementExceptions e) {
								System.out.println("..............................");
								System.out.println("sorry book can not be issued!!");
								System.out.println("..............................");
							}
							break;

						case 9:
							try {
								System.out.println("List of users           :");

								List<UserInformation> userInfos = service.showAllUsers();
								for (UserInformation info : userInfos) {
									System.out.println(
											"|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
									System.out.println("User id                            : " + info.getUserId());
									System.out.println("User Name                          : " + info.getUserName());
									System.out.println("User Email                         : " + info.getUserEmailId());
									System.out.println(
											"User No Of Books Borrowed          : " + info.getNoOfBooksBorrowed());
									System.out.println(
											"|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
								}
							} catch (LibrarayManagementExceptions e) {
								System.out.println("..................");
								System.err.println("There are no users");
								System.out.println("..................");
							}
							break;
						case 10:
							try {
								System.out.println("List of request for book       : ");

								List<RequestInformation> requestInfos = service.showAllUserRequest();
								for (RequestInformation info : requestInfos) {

									System.out.println(
											"|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
									System.out.println("Book id                          : "
											+ info.getBookInformation().getBookid());
									System.out.println("Book Name                        : "
											+ info.getBookInformation().getBookname());
									System.out.println("User id                          : "
											+ info.getUserInformation().getUserId());
									System.out.println("User Name                        : "
											+ info.getUserInformation().getUserName());
									System.out.println("Book Issued                      : " + info.isBookIssued());
									System.out.println("Book Returned                    : " + info.isBookReturned());
									System.out
											.println("Book Issue Date                  : " + info.getBookIssuedDate());
									System.out
											.println("Book returned Date               : " + info.getBookReturndate());
									System.out.println(
											"|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
								}
							} catch (LibrarayManagementExceptions e) {
								System.out.println("............................");
								System.out.println("sorry invalid book request!!");
								System.out.println("............................");
							}
							break;
						case 11:
							System.out.println("Enter below details to recieve returned book");
							System.out.println("-->plesae make sure you enter 3 digits user id");
							System.out.println("-->Enter user id");
							String user1 = scanner.next();
							boolean validateByUserId1 = validation.useridValidation(user1);
							while (!validateByUserId1) {
								try {
									throw new LibrarayManagementExceptions("pleas enter valid data");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("...........................");
									System.err.println("Please enter valid  user id");
									System.out.println("...........................");
									user1 = scanner.next();
									if (validation.useridValidation(user1)) {
										break;
									}
								}
							}
							System.out.println("-->Please make sure that Book Id must contain 6 digit number");
							System.out.println("-->Enter Book id");
							String book1 = scanner.next();
							boolean validateByBid = validation.idValidation(book1);
							while (!validateByBid) {
								try {
									throw new LibrarayManagementExceptions("Please enter valid data");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("..........................");
									System.err.println("Please enter valid book id");
									System.out.println("..........................");
									book1 = scanner.next();
									if (validation.idValidation(book1)) {
										break;
									}
								}
							}
							bookBean.setBookid(Integer.parseInt(book1));
							userInfo.setUserId(Integer.parseInt(user1));
							try {
								boolean isReceived = service.isBookReceived(userInfo, bookBean);
								if (isReceived) {
									System.out.println(" successfully received book returned by the user");
									System.out.println("Fine        :" + userInfo.getFine());
								}
							} catch (LibrarayManagementExceptions e) {
								System.out.println("................................");
								System.err.println(e.getMessage());
								System.out.println("................................");
							}
							break;
						}

					} while (check != 0);

				} catch (LibrarayManagementExceptions e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				System.out.println("");
				System.out.println("Please enter emailid in this format (vinitha@gmail.com)");
				System.out.println("");
				System.out.println("Enter user Email id");
				String userEmailId1 = scanner.next();
				boolean validateByEmail = validation.useremailValidation(userEmailId1);
				while (!validateByEmail) {
					try {
						throw new LibrarayManagementExceptions("Please enter valid Email Id");
					} catch (LibrarayManagementExceptions lmse) {
						System.out.println("..........................");
						System.err.println("Please valid Email Address");
						System.out.println("..........................");
						userEmailId1 = scanner.next();
						if (validation.useremailValidation(userEmailId1)) {
							break;
						}
					}
				}
				System.out.println("");
				System.out.println("Enter user password in this format (Vinitha@123)");
				System.out.println("");
				System.out.println(
						"Password should contain (8-15)characters, atleast one uppercase,lower case,special character");
				String userPassword1 = scanner.next();
				boolean validateByPassword = validation.userpasswordValidation(userPassword1);
				while (!validateByPassword) {
					try {
						throw new LibrarayManagementExceptions("Please enter valid password");
					} catch (LibrarayManagementExceptions lmse) {
						System.out.println("...........................");
						System.err.println("Please enter valid password");
						System.out.println("...........................");
						userPassword1 = scanner.next();
						if (validation.userpasswordValidation(userPassword1)) {
							break;
						}
					}
				}

				try {
					UserInformation userInformation = userService.userLogin(userEmailId1, userPassword1);
					System.out.println("");
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
					System.out.println("User logged in to account successfully!!");
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
					do {
						System.out.println("");
						System.out.println("1. To show list of Books in library");
						System.out.println("2. To search a Book in library");
                        System.out.println("3. To Search Book by Book name");
                        System.out.println("4. To Search Book by Book Author Name");
						System.out.println("5. To  place a request for book");
						System.out.println("6. To Return Book in library");
						System.out.println("0. To Exit");
						System.out.println("Enter your choice");
						userChoice = scanner.nextInt();
						switch (userChoice) {
						case 1:
							try {
								System.out.println("show List of Books in library :");

								List<BooksInformation> allBooks = service.showAllLibraryBooks();
								for (BooksInformation book : allBooks) {
									System.out.println("");
									System.out.println(
											"|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
									System.out.println("Book Id                           : " + book.getBookid());
									System.out.println("Book Name                         : " + book.getBookname());
									System.out.println("Book Author name                  : " + book.getBookauthor());
									System.out
											.println("Book Publisher                    : " + book.getBookpublisher());
									System.out.println("Book Category                     : " + book.getBookcategory());
									System.out.println(
											"|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
								}
							} catch (LibrarayManagementExceptions e) {
								System.out.println(".........................");
								System.out.println("no books found in library");
								System.out.println(".........................");
							}
							break;
						case 2:
							System.out.println("Enter below details to search a book in library");
							System.out.println("-->Please make sure that Book Id must contain 6 digit number");
							System.out.println("-->Enter Book id");
							String searchBookId = scanner.next();
							boolean validateByBookId = validation.idValidation(searchBookId);
							while (!validateByBookId) {
								try {
									throw new LibrarayManagementExceptions("Please enter valid data");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println(
											"......................................................................................................");
									System.err.println("Please enter valid book id");
									System.out.println(
											"......................................................................................................");
									searchBookId = scanner.next();
									if (validation.idValidation(searchBookId)) {
										break;
									}
								}
							}
							try {
								BooksInformation bookSearch = service.searchBook(Integer.parseInt(searchBookId));
								System.out.println("search book found");
								System.out.println("-------------------------------------");
								System.out.println("Book Id                           : " + bookSearch.getBookid());
								System.out.println("Book Name                         : " + bookSearch.getBookname());
								System.out.println("Book Author name                  : " + bookSearch.getBookauthor());
								System.out.println(
										"Book Publisher                    : " + bookSearch.getBookpublisher());
								System.out
										.println("Book Category                     : " + bookSearch.getBookcategory());
								System.out.println("-------------------------------------");
							} catch (LibrarayManagementExceptions e) {
								System.out.println(
										"......................................................................................................");
								System.out.println("sorry search book is not available");
								System.out.println(
										"......................................................................................................");
							}
							break;
							
						case 3:
							System.out.println("-->Enter Below Details to search abook");
							System.out.println("-->Enter book Name");
							String bookName = scanner.next();
							boolean validateByBookName = validation.nameValidation(bookName);
							while (!validateByBookName) {
								try {
									throw new LibrarayManagementExceptions("Please enter valid data");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("..........................");
									System.err.println("Please enter valid book Name");
									System.out.println("..........................");
									bookName = scanner.next();
									if (validation.nameValidation(bookName)) {
										break;
									}
								}
							}
							try {
								BooksInformation searchBookName = userService.searchBookByBookName(bookName);
								System.out.println("Search book Found");
								System.out.println("Here is the book details:");
								System.out.println("Book Id                  : " + searchBookName.getBookid());
								System.out.println("Book name                : " + searchBookName.getBookname());
								System.out.println("Author name              : " + searchBookName.getBookauthor());

							} catch (LibrarayManagementExceptions lmse) {
								System.out.println("..........................................");
								System.err.println("sorry book is not available in the library");
								System.out.println("..........................................");

							}
							break;

						case 4:
							System.out.println("-->Enter Below Details to search abook by book author name");
							System.out.println("-->Enter book author name");
							String bookAuthorName = scanner.next();
							boolean validateByBookauthorname = validation.nameValidation(bookAuthorName);
							while (!validateByBookauthorname) {
								try {
									throw new LibrarayManagementExceptions("Please enter valid data");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println("..........................");
									System.err.println("Please enter valid book author name");
									System.out.println("..........................");
									bookAuthorName = scanner.next();
									if (validation.nameValidation(bookAuthorName)) {
										break;
									}
								}
							}
							try {
								BooksInformation bookSearchByAuthorName = userService.searchBookByBookAuthorName(bookAuthorName);
								System.out.println("Search book Found");
								System.out.println("Here is the book details:");
								System.out.println("Book Id                  : " + bookSearchByAuthorName.getBookid());
								System.out.println("Book name                : " + bookSearchByAuthorName.getBookname());
								System.out.println("Author name              : " + bookSearchByAuthorName.getBookauthor());

							} catch (LibrarayManagementExceptions lmse) {
								System.out.println("..........................................");
								System.err.println("sorry book is not available in the library");
								System.out.println("..........................................");

							}
							break;


						case 5:
							System.out.println("-->Please make sure that Book Id must contain 6 digit number");
							System.out.println("-->Enter Book id");
							String bookId = scanner.next();
							boolean validateByBookId1 = validation.idValidation(bookId);
							while (!validateByBookId1) {
								try {
									throw new LibrarayManagementExceptions("Please enter valid data");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println(
											"......................................................................................................");
									System.err.println("Please enter valid book id");
									System.out.println(
											"......................................................................................................");
									bookId = scanner.next();
									if (validation.idValidation(bookId)) {
										break;
									}
								}
							}
							bookBean.setBookid(Integer.parseInt(bookId));

							System.out.println("Enter user id");
							String userId = scanner.next();
							boolean validateByUserId1 = validation.useridValidation(userId);
							while (!validateByUserId1) {
								try {
									throw new LibrarayManagementExceptions("pleas enter valid data");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println(
											"......................................................................................................");
									System.err.println("Please enter valid user id");
									System.out.println(
											"......................................................................................................");
									userId = scanner.next();
									if (validation.useridValidation(userId)) {
										break;
									}
								}
							}
							userInformation.setUserId(Integer.parseInt(userId));

							try {
								RequestInformation request = userService.bookRequest(userInformation, bookBean);
								System.out.println("Request placed to admin");
								System.out.println("-------------------------------------");
								System.out.println("User Id-----" + request.getUserInformation().getUserId());
								System.out.println("User name---" + request.getUserInformation().getUserName());
								System.out.println("Book Id-----" + request.getBookInformation().getBookid());
								System.out.println("Book Name---" + request.getBookInformation().getBookname());
								System.out.println("-------------------------------------");

							} catch (LibrarayManagementExceptions e) {
								System.out.println(
										"......................................................................................................");
								System.err.println("Invalid book request");
								System.out.println(
										"......................................................................................................");
							}
							break;
						case 6:
							System.out.println("Returning a book:");
							System.out.println("------------------");
							System.out.println("-->Entering User Id should be a 3 digit number");
							System.out.println("-->Enter user id");
							String user = scanner.next();
							boolean validateByUser = validation.useridValidation(user);
							while (!validateByUser) {
								try {
									throw new LibrarayManagementExceptions("pleas enter valid data");
								} catch (LibrarayManagementExceptions lmse) {
									System.out.println(
											"......................................................................................................");
									System.err.println("Please enter valid id number");
									System.out.println(
											"......................................................................................................");
									user = scanner.next();
									if (validation.useridValidation(user)) {
										break;
									}
								}
							}
							System.out.println("-->Please make sure that Book Id must contain 6 digit number");
							System.out.println("-->Enter Book id");
							int book = scanner.nextInt();
							userInformation.setUserId(Integer.parseInt(user));
							bookBean.setBookid(book);

							try {
								RequestInformation requestInfo = userService.bookReturn(userInformation, bookBean);
								System.out.println("Returning book to admin");
								System.out.println("User Id                          : "
										+ requestInfo.getUserInformation().getUserId());
								System.out.println("Book Id                          : "
										+ requestInfo.getBookInformation().getBookid());
								System.out
										.println("Is book Returning                : " + requestInfo.isBookReturned());

							} catch (LibrarayManagementExceptions e) {
								System.out.println(
										"......................................................................................................");
								System.out.println("sorry invalid book request");
								System.out.println(
										"......................................................................................................");
							}
							break;

						default:
							System.out.println("Invalid option");
							break;
						}
					} while (userChoice != 0);

				} catch (LibrarayManagementExceptions e) {
					System.out.println(
							"......................................................................................................");
					System.err.println("User cannot log in");
					System.out.println(
							"......................................................................................................");
				}
			}
		} while (true);
	}
}

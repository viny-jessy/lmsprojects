package com.capgemini.librarymanagementsystemjdbc.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import com.capgemini.librarymanagementsystemjdbc.dto.Admin_User_InformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.dto.BookInformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestInformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.exception.LibraryManagementJdbcExceptions;
import com.capgemini.librarymanagementsystemjdbc.factory.LMSJdbcFactory;
import com.capgemini.librarymanagementsystemjdbc.service.AdminJdbcService;
import com.capgemini.librarymanagementsystemjdbc.service.UserJdbcService;
import com.capgemini.librarymanagementsystemjdbc.validation.LibraryManagementJdbcValidations;

public class LibraryController {

	public static void main(String[] args) {
		LibraryController controller = new LibraryController();
		try {
			controller.lmsController();
		} catch (InputMismatchException e) {
			System.err.println("You have Encountered input mismatch exception please check and enter your credentials properly");
		}catch(NumberFormatException nfe) {
			System.err.println("You have Encountered number format exception please check and enter your credentials properly");
		}catch(NoSuchElementException nsee) {
			System.out.println("You have Encountered no such element exception please check and enter your credentials properly");
		}finally {
			try {
				controller.lmsController();
			} catch (InputMismatchException e) {
				System.err.println("Enter valid data");
			} catch(NumberFormatException nfe) {
				System.err.println("please enter valid credentials in case of  number format exception");
			}catch(NoSuchElementException nsee) {
				System.out.println("please enter valid credentials in case of  no such element exception");
			}finally {
				controller.lmsController();
			}
		}
	}
		public void lmsController(){
			
		Admin_User_InformationJdbc libraryUserBean=LMSJdbcFactory.getAdmin_User_InformationJdbc();
		AdminJdbcService adminService=LMSJdbcFactory.getAdminJdbcService();
		UserJdbcService userService=LMSJdbcFactory.getUserJdbcService();
		BookInformationJdbc bookBean=LMSJdbcFactory.getBookInformationJdbc();
		RequestInformationJdbc requestInfo=LMSJdbcFactory.getRequestInformationJdbc();
		LibraryManagementJdbcValidations lmsValidation=LMSJdbcFactory.getLibraryManagementJdbcValidations();
		Calendar calendar = Calendar.getInstance();
		Date actualReturnDate = LMSJdbcFactory.getDate();

		Scanner scanner=new Scanner(System.in);
		int choice;
		int check;
		int userChoice;
		do {
			System.out.println("1.To Login in to Admin Account");
			System.out.println("2.To Login in to User Account");
			System.out.println("Enter your choice");
			choice = scanner.nextInt();
			switch(choice) {
			case 1:
				System.out.println("-----------------");
				System.out.println("Enter Admin Email id");
				String emailId = scanner.next();
				boolean validateByEmailId=lmsValidation.validateByEmail(emailId);
				while(!validateByEmailId) {
					try {
						throw new LibraryManagementJdbcExceptions("Please enter valid Email Id");
					} catch (LibraryManagementJdbcExceptions lmse) {
						System.err.println("Please valid Email");
						emailId = scanner.next();
						if (lmsValidation.validateByEmail(emailId)) {
							break;
						}
					}
				}
				System.out.println("Enter Admin password");
				String password = scanner.next();
				boolean validateByPassword=lmsValidation.passwordValidation(password);
				while(!validateByPassword) {
					try {
						throw new LibraryManagementJdbcExceptions("Please enter valid password");
					}catch (LibraryManagementJdbcExceptions lmse) {
						System.err.println("Please valid password");
						password = scanner.next();
						if (lmsValidation.passwordValidation(password)) {
							break;
						}
					}
				}
				try {
					Admin_User_InformationJdbc bean=adminService.signIn(emailId, password);
					if(bean!=null) {
					System.out.println("Admin Login Successfull");
					
					do {
						System.out.println("1. Register");
						System.out.println("2. Search");
						System.out.println("3. Add Book");
						System.out.println("4. Show All Books");
						System.out.println("5. Show Users");
						System.out.println("6. Remove Book");
						System.out.println("7. Show Requests");
						System.out.println("8. Book Issue");
						System.out.println("9. Receive Returned Book");
						System.out.println("0. Exit");

						System.out.println("Enter your choice");
						check = scanner.nextInt();
						switch(check) {
						case 1:
							System.out.println("Enter user id");
							String userId=scanner.next();
							boolean validateByUserId=lmsValidation.ValidateByUserId(userId);
							while(!validateByUserId) {
								try {
									throw new LibraryManagementJdbcExceptions("Please enter valid user Id");
								}catch(LibraryManagementJdbcExceptions lmse){
									System.out.println("Please enter valid user id");
									userId=scanner.next();
									if (lmsValidation.ValidateByUserId(userId)) {
										
										break;
									}
								}
							}
							System.out.println("Enter username");
							String userName=scanner.next();
							boolean validateByName=lmsValidation.validateByName(userName);
							while(!validateByName) {
								try {
									throw new LibraryManagementJdbcExceptions("Please enter only alphabets");
								}catch(LibraryManagementJdbcExceptions lmse) {
									System.out.println("Please enter only alphabets");
									userName=scanner.next();
									if(lmsValidation.validateByName(userName)) {
										
										break;
									}
								}
							}
							System.out.println("Enter Firstname");
							String firstName=scanner.next();
							boolean validateFirstName=lmsValidation.validateByName(firstName);
							while(!validateFirstName) {
								try {
									throw new LibraryManagementJdbcExceptions("Please enter only alphabets");
								}catch(LibraryManagementJdbcExceptions lmse) {
									System.out.println("Please enter only alphabets");
									firstName=scanner.next();
									if(lmsValidation.validateByName(firstName)) {
										
										break;
									}
								}
							}
							System.out.println("Enter Lastname");
							String lastName=scanner.next();
							boolean validateLastName=lmsValidation.validateByName(lastName);
							while(!validateLastName) {
								try {
									throw new LibraryManagementJdbcExceptions("Please enter only alphabets");
								}catch(LibraryManagementJdbcExceptions lmse) {
									System.out.println("Please enter only alphabets");
									lastName=scanner.next();
									if(lmsValidation.validateByName(lastName)) {
										
										break;
									}
								}
							}
							System.out.println("Enter email id");
							String email=scanner.next();
							boolean validateByEmailId1=lmsValidation.validateByEmail(email);
							while(!validateByEmailId1) {
								try {
									throw new LibraryManagementJdbcExceptions("Please enter valid email id");
								}catch(LibraryManagementJdbcExceptions lmse) {
									System.out.println("Please enter only valid email id");
									email=scanner.next();
									if(lmsValidation.validateByEmail(email)) {
										
										break;
									}
								}
							}
							System.out.println("Enter password");
							String userPassword=scanner.next();
							boolean validatePassword=lmsValidation.passwordValidation(userPassword);
							while(!validatePassword) {
								try {
									throw new LibraryManagementJdbcExceptions("Please valid password");
								}catch(LibraryManagementJdbcExceptions lmse) {
									System.out.println("Please enter valid password");
									userPassword=scanner.next();
									if(lmsValidation.passwordValidation(userPassword)) {
										
										break;
									}
								}
							}
							System.out.println("Enter role");
							String role=scanner.next();
							boolean validateRole=lmsValidation.validateByName(role);
							while(!validateRole) {
								try {
									throw new LibraryManagementJdbcExceptions("Please enter only alphabets");
								}catch(LibraryManagementJdbcExceptions lmse) {
									System.out.println("Please enter only alphabets");
									role=scanner.next();
									if(lmsValidation.validateByName(role)) {
										
										break;
									}
								}
							}
							Admin_User_InformationJdbc user1=new Admin_User_InformationJdbc();
							user1.setId(Integer.parseInt(userId));
							user1.setUserName(userName);
							user1.setFirstName(firstName);
							user1.setLastName(lastName);
							user1.setEmailId(email);
							user1.setPassword(userPassword);
							user1.setRole(role);
							
							boolean res=adminService.enrollUser(user1);
							
							if(res) {
								System.out.println("user registered succesfully");
							}else {
								System.out.println("not registered");
							}
							break;
						case 2:
							System.out.println("Search a Book");
							System.out.println("Enter book Id");
							String searchBookId = scanner.next();
							boolean validateByBookId=lmsValidation.validateByBookId(searchBookId);
							while(!validateByBookId) {
								try {
									throw new LibraryManagementJdbcExceptions("please enter valid book id");
								}catch(LibraryManagementJdbcExceptions lmse) {
									System.out.println("Please enter only valid book Id");
									searchBookId=scanner.next();
									if(lmsValidation.validateByBookId(searchBookId)) {
										break;
									}
								}
							}
							
							try {
							BookInformationJdbc bookInfo=adminService.searchBookByBookId(Integer.parseInt(searchBookId));
							if(bookInfo!=null) {
							System.out.println("Book is found");
							System.out.println(bookInfo.getBookId());
							System.out.println(bookInfo.getBookName());
							System.out.println(bookInfo.getAuthorName());
							
							}
							}catch(LibraryManagementJdbcExceptions e) {
								System.out.println(e.getMessage());
							}
							
							break;
						case 3:
							System.out.println("Enter book id");
							String bookId=scanner.next();
							boolean validateBookId=lmsValidation.validateByBookId(bookId);
							while(!validateBookId) {
								try {
									throw new LibraryManagementJdbcExceptions("please enter valid book id");
								}catch(LibraryManagementJdbcExceptions lmse) {
									System.out.println("Please enter only valid book Id");
									bookId=scanner.next();
									if(lmsValidation.validateByBookId(bookId)) {
										break;
									}
								}
							}
							System.out.println("Enter Book name");
							String bookname=scanner.next();
							boolean validateByBookName=lmsValidation.validateByName(bookname);
							while(!validateByBookName) {
								try {
									throw new LibraryManagementJdbcExceptions("Please enter only alphabets");
								}catch(LibraryManagementJdbcExceptions lmse) {
									System.out.println("Please enter only alphabets");
									bookname=scanner.next();
									if(lmsValidation.validateByName(bookname)) {
										
										break;
									}
								}
							}
							System.out.println("Enter author name");
							String authorname=scanner.next();
							boolean validateByAuthorName=lmsValidation.validateByName(authorname);
							while(!validateByAuthorName) {
								try {
									throw new LibraryManagementJdbcExceptions("Please enter only alphabets");
								}catch(LibraryManagementJdbcExceptions lmse) {
									System.out.println("Please enter only alphabets");
									authorname=scanner.next();
									if(lmsValidation.validateByName(authorname)) {
										
										break;
									}
								}
							}
							System.out.println("Enter publishers name");
							String bookPublisher=scanner.next();
							boolean validateByPublisherName=lmsValidation.validateByName(bookPublisher);
							while(!validateByPublisherName) {
								try {
									throw new LibraryManagementJdbcExceptions("Please enter only alphabets");
								}catch(LibraryManagementJdbcExceptions lmse) {
									System.out.println("Please enter only alphabets");
									bookPublisher=scanner.next();
									if(lmsValidation.validateByName(bookPublisher)) {
										
										break;
									}
								}
							}
							System.out.println("Enter category");
							String category=scanner.next();
							boolean validateByCategory=lmsValidation.validateByName(category);
							while(!validateByCategory) {
								try {
									throw new LibraryManagementJdbcExceptions("Please enter only alphabets");
								}catch(LibraryManagementJdbcExceptions lmse) {
									System.out.println("Please enter only alphabets");
									category=scanner.next();
									if(lmsValidation.validateByName(category)) {
										
										break;
									}
								}
							}
							
							BookInformationJdbc bookBean1 = new BookInformationJdbc();

							bookBean1.setBookId(Integer.parseInt(bookId));
							bookBean1.setAuthorName(authorname);
							bookBean1.setBookName(bookname);
							bookBean1.setPublisher(bookPublisher);
							bookBean1.setCategory(category);
						try {
							boolean bookAdded = adminService.enrollBook(bookBean1);
							System.out.println(bookAdded);

							if (bookAdded) {
								System.out.println("book is added");
							} 
						}catch(LibraryManagementJdbcExceptions e) {
							System.out.println(e.getMessage());
						}

							break;
						case 4:
							
							try {
								System.out.println("Books present in library are :");
								System.out.println("-------------------------------");

								List<BookInformationJdbc> allBooks = adminService.listOfBooks();
								Iterator<BookInformationJdbc> iterator=allBooks.iterator();
								while(iterator.hasNext()) {
									
									BookInformationJdbc book=(BookInformationJdbc)iterator.next();
								
									System.out.println("Book id ---------->"  + book.getBookId());
									System.out.println("Book Name --------> " + book.getBookName());
									System.out.println("Book Authour------> " + book.getAuthorName());
									System.out.println("Book publisher------->"+book.getPublisher());
									System.out.println("Book Category-------->"+book.getCategory());
									System.out.println("-----------------------------------------------------");
								}
								
							} catch (LibraryManagementJdbcExceptions e) {
								System.out.println(e.getMessage());
							}
							break;
						case 5:
							try {
								System.out.println("users in library are :");
								System.out.println("-------------------------------");

								List<Admin_User_InformationJdbc> allBooks = adminService.listOfUsers();
								Iterator<Admin_User_InformationJdbc> iterator=allBooks.iterator();
								while(iterator.hasNext()) {
									
									Admin_User_InformationJdbc user=(Admin_User_InformationJdbc)iterator.next();
								
									System.out.println("user id ---------->" + user.getId());
									System.out.println("Username --------> " + user.getUserName());
									System.out.println("Firstname------> " + user.getFirstName());
									System.out.println("Lastname------->"+user.getLastName());
									System.out.println("Email Id-------->"+user.getEmailId());
									System.out.println("Role---------->"+user.getRole());
									System.out.println("-----------------------------------------------------");
								}
								
							} catch (LibraryManagementJdbcExceptions e) {
								System.out.println(e.getMessage());
							}
							break;
						case 6:
							System.out.println("Enter the bookId to be removed:");
							String bookId1=scanner.next();
							boolean validateBookId1=lmsValidation.validateByBookId(bookId1);
							while(!validateBookId1) {
								try {
									throw new LibraryManagementJdbcExceptions("please enter valid book id");
								}catch(LibraryManagementJdbcExceptions lmse) {
									System.out.println("Please enter only valid book Id");
									bookId1=scanner.next();
									if(lmsValidation.validateByBookId(bookId1)) {
										break;
									}
								}
							}
							try {
							boolean isDeleted=adminService.removeBook(Integer.parseInt(bookId1));
							if(isDeleted) {
								System.out.println("Book removed from library");
							}
							}catch(LibraryManagementJdbcExceptions e) {
								System.out.println(e.getMessage());
							}
							break;
						case 7:
							
							try {
								System.out.println("Requests for Books are :");
								System.out.println("-------------------------------");

								List<RequestInformationJdbc> requestInfos = adminService.showAllUserRequests();
								for (RequestInformationJdbc info : requestInfos) {
									
									System.out.println("Request Id------------>"+info.getRequestId());
									System.out.println("User id----------- " + info.getUserId());
									System.out.println("Book id ---------- " + info.getBookId());
									System.out.println("Book Name--------->"+info.getBookName());
									System.out.println("User name------>"+info.getUserName());
																		System.out.println("-------------------------------");
								}
							} catch (LibraryManagementJdbcExceptions e) {
								System.out.println(e.getMessage());
							}
							break;
						case 8:
							System.out.println("Enter Request Id");
							 int rid = scanner.nextInt();
							try {
								boolean issue = adminService.bookIssue(rid);
								if (issue) {
									System.out.println("Book Issued");
								} 
							} catch (LibraryManagementJdbcExceptions e) {
								System.err.println(e.getMessage());
							}
							break;
						case 9:
							System.out.println("Receive Returned Book");
							System.out.println("-----------------------");
							System.out.println("Enter Request Id");
							rid = scanner.nextInt();
							try {
								boolean result = adminService.isBookReceived(rid);
								if (result) {
									System.out.println("Book Received");
								}
							} catch (LibraryManagementJdbcExceptions e) {
								System.err.println(e.getMessage());
							}

							break;			
						}
					
						
					}while(check!=0);
					}
				}catch(Exception e) {
					System.out.println("Invalid Credentials");
				}
				break;
			case 2:
				System.out.println("-----------------");
				System.out.println("Enter User Email id eg:xyz@gmail.com");
				String userEmailId = scanner.next();
				boolean validateByEmail=lmsValidation.validateByEmail(userEmailId);
				while(!validateByEmail) {
					try {
						throw new LibraryManagementJdbcExceptions("Please enter valid Email Id");
					} catch (LibraryManagementJdbcExceptions lmse) {
						System.err.println("Please valid Email");
						userEmailId = scanner.next();
						if (lmsValidation.validateByEmail(userEmailId)) {
							break;
						}
					}
				}
				System.out.println("Enter User password it should contain (8-15)characters one uppercase,lowercase,special charcter");
				String userPassword = scanner.next();
				boolean validatePassword=lmsValidation.passwordValidation(userPassword);
				while(!validatePassword) {
					try {
						throw new LibraryManagementJdbcExceptions("Please enter valid password");
					}catch (LibraryManagementJdbcExceptions lmse) {
						System.err.println("Please valid password");
						userPassword = scanner.next();
						if (lmsValidation.passwordValidation(userPassword)) {
							break;
						}
					}
				}

				try {
					Admin_User_InformationJdbc userInfo = userService.login(userEmailId, userPassword);
					if(userInfo!=null) {
					System.out.println("User logged in");
					do {
						System.out.println("1. Search a Book");
						System.out.println("2. Request a Book");
						System.out.println("3. Return Book");
						System.out.println("0. Exit");
						System.out.println("Enter your choice");
						userChoice = scanner.nextInt();
						switch (userChoice) {
						case 1:
							System.out.println("Search a Book");
							System.out.println("Enter book Id");
							String searchBookId = scanner.next();
							boolean validateBySearchBookId=lmsValidation.validateByBookId(searchBookId);
							while(!validateBySearchBookId) {
								try {
									throw new LibraryManagementJdbcExceptions("please enter valid book id");
								}catch(LibraryManagementJdbcExceptions lmse) {
									System.out.println("Please enter only valid book Id");
									searchBookId=scanner.next();
									if(lmsValidation.validateByBookId(searchBookId)) {
										break;
									}
								}
							}
							try {
							BookInformationJdbc bookInfo=userService.searchById(Integer.parseInt(searchBookId));
							if(bookInfo!=null) {
							System.out.println("Book is found");
							System.out.println("Book Id---------->"+bookInfo.getBookId());
							System.out.println("Book name is---------->"+bookInfo.getBookName());
							System.out.println("Author name is---------->"+bookInfo.getAuthorName());
							}
							}catch(LibraryManagementJdbcExceptions e) {
								System.out.println("No such book is found in library");
							}
							
							break;
						case 2:
							Admin_User_InformationJdbc userBean=new Admin_User_InformationJdbc();
							BookInformationJdbc booksBean=new  BookInformationJdbc();
							System.out.println("Enter user Id of 3 digits");
							String userId=scanner.next();
							boolean validatedByUserId=lmsValidation.ValidateByUserId(userId);
							while(!validatedByUserId) {
								try {
									throw new LibraryManagementJdbcExceptions("please enter valid book id");
								}catch(LibraryManagementJdbcExceptions lmse) {
									System.out.println("Please enter only valid book Id");
									userId=scanner.next();
									if(lmsValidation.ValidateByUserId(userId)) {
										break;
									}
								}
							}
							userBean.setId(Integer.parseInt(userId));
							System.out.println("Enter book Id of 6 digits");
							String bookId=scanner.next();
							boolean validatedByBookId=lmsValidation.validateByBookId(bookId);
							while(!validatedByBookId) {
								try {
									throw new LibraryManagementJdbcExceptions("please enter valid book id");
								}catch(LibraryManagementJdbcExceptions lmse) {
									System.out.println("Please enter only valid book Id");
									bookId=scanner.next();
									if(lmsValidation.validateByBookId(bookId)) {
										break;
									}
								}
							}
							booksBean.setBookId(Integer.parseInt(bookId));
							System.out.println("Enter BookName");
							String bookName2=scanner.next();
							booksBean.setBookName(bookName2);
							System.out.println("Enter user name");
							String userName2=scanner.next();
							userBean.setUserName(userName2);
							
							try {
								
								RequestInformationJdbc request=userService.bookRequest(userBean, booksBean);
								System.out.println("Request placed to admin");
								System.out.println("User Id-----" + request.getUserBean().getId());
								System.out.println("Book Id-----" + request.getBookBean().getBookId());
								System.out.println("Book Name----->"+request.getBookBean().getBookName());
								
								

							} catch (LibraryManagementJdbcExceptions e) {

								System.out.println(e.getMessage());
							}
							break;	
						case 3:
							
							Admin_User_InformationJdbc info = new Admin_User_InformationJdbc();

							System.out.println("Returning a book:");
							System.out.println("------------------");
							System.out.println("Enter User Id");
							 String id = scanner.next();
							 boolean validateId=lmsValidation.ValidateByUserId(id);
							 while(!validateId) {
									try {
										throw new LibraryManagementJdbcExceptions("please enter valid book id");
									}catch(LibraryManagementJdbcExceptions lmse) {
										System.out.println("Please enter only valid book Id");
										id=scanner.next();
										if(lmsValidation.ValidateByUserId(id)) {
											break;
										}
									}
								}
							System.out.println("Enter Book Id");
							 String bookId4 = scanner.next();
							 boolean validatedBookId=lmsValidation.validateByBookId(bookId4);
							 while(!validatedBookId) {
									try {
										throw new LibraryManagementJdbcExceptions("please enter valid book id");
									}catch(LibraryManagementJdbcExceptions lmse) {
										System.out.println("Please enter only valid book Id");
										bookId4=scanner.next();
										if(lmsValidation.validateByBookId(bookId4)) {
											break;
										}
									}
								}

							try {
								boolean isReturn = userService.bookReturn(Integer.parseInt(id), Integer.parseInt(bookId4));
								if (isReturn) {
									System.out.println("Returning request placed to Admin");
								}
							} catch (LibraryManagementJdbcExceptions e) {
								System.err.println(e.getMessage());
							}
							break;
							
						}
						
			}while(userChoice!=0);
					}
				}catch(Exception e) {
					
				}
			}
		}while(true);
}

}

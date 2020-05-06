package com.capgemini.libraryhibernate.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.capgemini.libraryhibernate.dto.BookDetails;
import com.capgemini.libraryhibernate.dto.LibraryDetails;
import com.capgemini.libraryhibernate.dto.RequestDetails;
import com.capgemini.libraryhibernate.exception.LibraryManagementSystemException;

public class LibraryHibernateDAOImplementation implements LibraryHibernateDAO {

	// To login in to Admin Account
	@Override
	public boolean adminLogin(int id, String password) {
		LibraryDetails users = new LibraryDetails();
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();

			transaction.begin();
			users = manager.find(LibraryDetails.class, id);

			if (users.getPassword().equals(password) && users.getId()==id) {
				//users.getRole().equalsIgnoreCase("admin")
				transaction.commit();
				return true;
			} else {
				throw new LibraryManagementSystemException("Invalid password");
			}

		} catch (Exception e) {
			transaction.rollback();
			throw new LibraryManagementSystemException("Invalid  Credentials");
		} finally {
			manager.close();
			factory.close();
		}
	}

	// To register User
	@Override
	public boolean enrollUser(LibraryDetails user) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(user);
			transaction.commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			throw new LibraryManagementSystemException("User is already Registered");
		} finally {
			manager.close();
			factory.close();
		}
	}

	// To login in to user account
	@Override
	public boolean userLogin(int id, String password) {
		LibraryDetails users = new LibraryDetails();
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();

			transaction.begin();
			users = manager.find(LibraryDetails.class, id);

			if (users.getPassword().equals(password) && users.getRole().equalsIgnoreCase("user")) {
				transaction.commit();
				return true;
			} else {
				throw new LibraryManagementSystemException("Invalid password");
			}

		} catch (Exception e) {
			transaction.rollback();
			throw new LibraryManagementSystemException("Invalid  Credentials");
		}
	}

	// To add book in to library
	@Override
	public boolean addBook(BookDetails book) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(book);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			throw new LibraryManagementSystemException("Book is already added in to library account");
		} finally {
			manager.close();
			factory.close();
		}

	}


	// To update the book details
	public boolean updateBook(BookDetails bookInfo) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BookDetails record = manager.find(BookDetails.class, bookInfo);
			record.setBookId(bookInfo.getBookId());
			record.setBookName(bookInfo.getBookName());
			record.setAuthourName(bookInfo.getAuthourName());
			transaction.commit();
			return true;
		} catch (Exception e) {

			throw new LibraryManagementSystemException("Book can't be updated");
		} finally {
			manager.close();
			factory.close();
		}

	}

	// To search a book in library by bookId
	@Override
	public BookDetails searchByBookId(int bookId) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			BookDetails record = manager.find(BookDetails.class, bookId);
			if (record != null) {
				return record;
			} else {
				throw new LibraryManagementSystemException("search book not found!!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			factory.close();
			manager.close();
		}
		return null;
	}

//To show list of books present in library
	@Override
	public List<BookDetails> ListOfBooks() {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from books_details b";
			TypedQuery<BookDetails> query = manager.createQuery(jpql, BookDetails.class);
			List<BookDetails> recordList = query.getResultList();

			if (recordList.isEmpty()) {
				throw new LibraryManagementSystemException("No Books Found");
			} else {
				return recordList;
			}

		} catch (Exception e) {
			throw new LibraryManagementSystemException(e.getMessage());
		} finally {
			manager.close();
			factory.close();
		}

	}

//To show list of user enrolled in library
	@Override
	public List<LibraryDetails> ListOfUsers() {
		EntityManagerFactory factory = null;
		EntityManager manager = null;

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select users from user_details users";
			TypedQuery<LibraryDetails> query = manager.createQuery(jpql, LibraryDetails.class);
			List<LibraryDetails> recordList = query.getResultList();

			if (recordList.isEmpty()) {
				throw new LibraryManagementSystemException("No Users Found");
			} else {
				return recordList;
			}

		} catch (Exception e) {
			throw new LibraryManagementSystemException(e.getMessage());
		} finally {
			manager.close();
			factory.close();
		}

	}

// to show list of request placed by users
	@Override
	public List<RequestDetails> ListOfRequest() {
		EntityManagerFactory factory = null;
		EntityManager manager = null;

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select r from request_details r";
			TypedQuery<RequestDetails> query = manager.createQuery(jpql, RequestDetails.class);
			List<RequestDetails> recordList = query.getResultList();
			if (recordList.isEmpty()) {
				throw new LibraryManagementSystemException("No Requests Found");
			} else {
				return recordList;
			}

		} catch (Exception e) {
			throw new LibraryManagementSystemException(e.getMessage());
		} finally {
			manager.close();
			factory.close();
		}

	}

	// To issue book to user
	@Override
	public boolean isBookCheckedOut(int requestId) {

		int noOfBookCheckedOut = 0;
		int requestedBookId = 0;
		int requestedUserId = 0;

		EntityManager manager = null;
		EntityManagerFactory factory = null;
		EntityTransaction transaction = null;
		Date expectedReturnDate = null;
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 15);
		expectedReturnDate = calendar.getTime();

		RequestDetails bookRequestInfo = new RequestDetails();
		BookDetails bookInfo = new BookDetails();
		LibraryDetails user = new LibraryDetails();

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();

			transaction.begin();
			bookRequestInfo = manager.find(RequestDetails.class, requestId);

			if (bookRequestInfo != null) {
				Date bookIssueDate = bookRequestInfo.getIssuedDate();
				if (bookIssueDate == null) {
					requestedUserId = bookRequestInfo.getUserId();
					requestedBookId = bookRequestInfo.getBookId();

					bookRequestInfo.setIssuedDate(date);
					bookRequestInfo.setExpectedReturnDate(expectedReturnDate);
					transaction.commit();

					transaction.begin();
					user = manager.find(LibraryDetails.class, requestedUserId);
					noOfBookCheckedOut = user.getNoOfBooksBorrowed();
					++noOfBookCheckedOut;
					System.out.println("Number of books borrowed by user are :" + noOfBookCheckedOut);

					user.setNoOfBooksBorrowed(noOfBookCheckedOut);
					transaction.commit();

					transaction.begin();
					bookInfo = manager.find(BookDetails.class, requestedBookId);
					bookInfo.setAvailable(false);
					transaction.commit();
				} else {
					throw new LibraryManagementSystemException("This Book Is Already Issued");
				}

			} else {
				throw new LibraryManagementSystemException("Invalid Request Id");
			}

		} catch (Exception e) {
			transaction.rollback();
			throw new LibraryManagementSystemException(e.getMessage());
		} finally {
			manager.close();
			factory.close();
		}
		return false;
	}

	// To receive book returned by user
	@Override
	public boolean isBookReceived(int requestId) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		Date expectedReturnDate = null;
		Date returnedDate = null;

		int CountOfBooks = 0;
		int requestedBookId = 0;
		int requestedId = 0;
		double fine = 0;

		RequestDetails info = new RequestDetails();
		BookDetails bookInfo = new BookDetails();
		LibraryDetails user = new LibraryDetails();

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();

			transaction.begin();
			info = manager.find(RequestDetails.class, requestId);

			if (info != null) {
				requestedBookId = info.getBookId();
				requestedId = info.getUserId();
				returnedDate = info.getBookReturnedDate();
				expectedReturnDate = info.getExpectedReturnDate();
				transaction.commit();

				if ((returnedDate != null) && (expectedReturnDate != null)) {
					long expectedBookReturnDate = expectedReturnDate.getTime();
					long actualbookReturnDate = returnedDate.getTime();
					long diffDates = actualbookReturnDate - expectedBookReturnDate;
					int noOfDelayed = (int) (diffDates / (24 * 60 * 60 * 1000));

					transaction.begin();
					user = manager.find(LibraryDetails.class, requestedId);
					CountOfBooks = user.getNoOfBooksBorrowed();
					--CountOfBooks;
					user.setNoOfBooksBorrowed(CountOfBooks);
					if (noOfDelayed > 0) {
						fine = user.getFine();
						fine = fine + (noOfDelayed * 2);
						user.setFine(fine);
					}
					transaction.commit();

					transaction.begin();
					bookInfo = manager.find(BookDetails.class, requestedBookId);
					bookInfo.setAvailable(true);
					transaction.commit();

					transaction.begin();
					info = manager.find(RequestDetails.class, requestId);
					manager.remove(info);
					transaction.commit();

				} else {
					throw new LibraryManagementSystemException("still book is not checked in by the user");
				}

			} else {
				throw new LibraryManagementSystemException("you have entered invalid requestId");
			}
		} catch (LibraryManagementSystemException e) {
			transaction.rollback();
			throw new LibraryManagementSystemException(e.getMessage());
		} finally {
			manager.close();
			factory.close();
		}
		return false;
	}

	// User operations
	// To place request for book
	@Override
	public boolean placeBookRequest(int userId, int bookId) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		String jpql = null;
		int countOfBookRequest = 0;

		RequestDetails requestDetails = new RequestDetails();
		BookDetails bookDetails = new BookDetails();

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();

			jpql = "select count(*) from request_details r where r.userId=:uId";
			Query query = manager.createQuery(jpql);
			query.setParameter("uId", userId);
			countOfBookRequest = ((Number) query.getSingleResult()).intValue();
			System.out.println("Count of request placed by user are :" + countOfBookRequest);

			if (countOfBookRequest < 3) {
				bookDetails = manager.find(BookDetails.class, bookId);

				if (bookDetails != null) {
					jpql = "select r from request_details r ";
					TypedQuery<RequestDetails> query2 = manager.createQuery(jpql, RequestDetails.class);
					List<RequestDetails> recorList = query2.getResultList();

					for (RequestDetails requestInfo : recorList) {
						if (requestInfo.getBookId() == bookId) {
							throw new LibraryManagementSystemException(
									"This Book Request is Already Placed By SomeOne ");
						}
					}

					if (bookDetails.isAvailable()) {
						transaction.begin();
						requestDetails.setUserId(userId);
						requestDetails.setBookId(bookId);
						manager.persist(requestDetails);
						transaction.commit();
					} else {
						throw new LibraryManagementSystemException("This book is already checkedOut by the user");
					}

				} else {
					throw new LibraryManagementSystemException("you might have entered worng bookId.please check it");
				}

			} else {
				throw new LibraryManagementSystemException("you have reached your maximum limit of request");
			}

		} catch (LibraryManagementSystemException e) {
			transaction.rollback();
			throw new LibraryManagementSystemException(e.getMessage());
		} finally {
			manager.close();
			factory.close();
		}
		return true;
	}

	// Return book back to librarian
	@Override
	public boolean isBookCheckedIn(int userId, int bookId) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		String jpql = null;
		int requestId = 0;

		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DATE, 20);
		Date returnedDate = calendar2.getTime();

		RequestDetails info = new RequestDetails();

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();

			jpql = "select r from request_details r ";
			TypedQuery<RequestDetails> query = manager.createQuery(jpql, RequestDetails.class);
			List<RequestDetails> recordList = query.getResultList();

			for (RequestDetails requestInfo : recordList) {
				if ((requestInfo.getBookId() == bookId) && (requestInfo.getUserId() == userId)
						&& (requestInfo.getIssuedDate() != null)) {
					requestId = requestInfo.getRequestId();
				}
			}

			if (requestId != 0) {
				transaction.begin();
				info = manager.find(RequestDetails.class, requestId);
				info.setBookReturnedDate(returnedDate);
				transaction.commit();

			} else {
				throw new LibraryManagementSystemException("you might have entered invalid bookId");
			}

		} catch (LibraryManagementSystemException e) {
			throw new LibraryManagementSystemException(e.getMessage());
		} finally {
			manager.close();
			factory.close();
		}

		return true;
	}

	// To remove book from library
	@Override
	public boolean removeBook(int bookId) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;

		BookDetails bookDetails = new BookDetails();
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			bookDetails = manager.find(BookDetails.class, bookId);
			manager.remove(bookDetails);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			throw new LibraryManagementSystemException("This book is not available in library to remove");
		} finally {
			manager.close();
			factory.close();
		}
	}

}
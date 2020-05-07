package com.capgemini.libraryspring.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import com.capgemini.libraryspring.dto.BookDetails;
import com.capgemini.libraryspring.dto.LibraryDetails;
import com.capgemini.libraryspring.dto.RequestDetails;
import com.capgemini.libraryspring.exceptions.LibraryManagementSystemException;

@Repository
public class LibrarySpringDAOImplementation implements LibrarySpringDAO {

	String jpql = null;
	Date returnedDate = null;
	EntityManager manager = null;
	Date expectedReturnDate = null;
	EntityTransaction transaction = null;
	double fine = 0;
	int requestId = 0;
	int requestedId = 0;
	int CountOfBooks = 0;
	int requestedBookId = 0;
	int requestedUserId = 0;
	int countOfBookRequest = 0;
	int noOfBookCheckedOut = 0;

	@PersistenceUnit
	private EntityManagerFactory factory;

	// To login in to Account
	@Override
	public LibraryDetails Login(String email, String password) {
		try {
			manager = factory.createEntityManager();
			String jpql = "select l from LibraryDetails u where u.email=:email and u.password=:password";
			TypedQuery<LibraryDetails> query = manager.createQuery(jpql, LibraryDetails.class);
			query.setParameter("email", email);
			query.setParameter("password", password);
			LibraryDetails details = query.getSingleResult();
			return details;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}

	}

	// To register User
	@Override
	public boolean enrollUser(LibraryDetails user) {

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(user);
			transaction.commit();

			return true;
		} catch (Exception e) {
			transaction.rollback();
			System.err.println(e.getMessage());
			return false;
		} finally {
			manager.close();
			factory.close();
		}
	}

	// To add book in to library
	@Override
	public boolean addBook(BookDetails book) {

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
			System.err.println(e.getMessage());
			return false;

		} finally {
			manager.close();
			factory.close();
		}

	}

	// To update the book details
	public boolean updateBook(BookDetails bookInfo) {

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BookDetails record = manager.find(BookDetails.class, bookInfo.getBookId());
			record.setBookName(bookInfo.getBookName());
			record.setAuthourName(bookInfo.getAuthourName());
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			System.err.println(e.getMessage());
			return false;
		} finally {
			manager.close();
			factory.close();
		}

	}

	// To search a book in library by bookId
	@Override
	public List<BookDetails> searchByBookId(int bookId) {

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			jpql = "select b from BookDetails b where b.bookId=:bookId";
			TypedQuery<BookDetails> query = manager.createQuery(jpql, BookDetails.class);
			query.setParameter("bookId", bookId);
			List<BookDetails> recordList = query.getResultList();
			return recordList;
		} catch (Exception e) {
			transaction.rollback();
			System.err.println(e.getMessage());
			return null;
		} finally {
			factory.close();
			manager.close();
		}

	}

	// To search by bookName
	@Override
	public List<BookDetails> searchByBookName(String bookName) {

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			jpql = "select b from BookDetails b where b.bookName=:bookName";
			TypedQuery<BookDetails> query = manager.createQuery(jpql, BookDetails.class);
			query.setParameter("bookName", bookName);
			List<BookDetails> recordList = query.getResultList();
			return recordList;
		} catch (Exception e) {
			transaction.rollback();
			System.err.println(e.getMessage());
			return null;
		} finally {
			factory.close();
			manager.close();
		}

	}

	// To search book by author name
	@Override
	public List<BookDetails> searchByAuthorName(String authorName) {

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			jpql = "select b from BookDetails b where b.authorName=:authorName";
			TypedQuery<BookDetails> query = manager.createQuery(jpql, BookDetails.class);
			query.setParameter("authorName", authorName);
			List<BookDetails> recordList = query.getResultList();
			return recordList;
		} catch (Exception e) {
			transaction.rollback();
			System.err.println(e.getMessage());
			return null;
		} finally {
			factory.close();
			manager.close();
		}

	}

//To show list of books present in library
	@Override
	public List<BookDetails> ListOfBooks() {

		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select b from BookDetails b";
		TypedQuery<BookDetails> query = manager.createQuery(jpql, BookDetails.class);
		List<BookDetails> recordList = query.getResultList();
		factory.close();
		manager.close();
		return recordList;

	}

//To show list of user enrolled in library
	@Override
	public List<LibraryDetails> ListOfUsers() {

		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select users from LibraryDetails users";
		TypedQuery<LibraryDetails> query = manager.createQuery(jpql, LibraryDetails.class);
		List<LibraryDetails> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;

	}

// to show list of request placed by users
	@Override
	public List<RequestDetails> ListOfRequest() {

		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select r from RequestDetails r";
		TypedQuery<RequestDetails> query = manager.createQuery(jpql, RequestDetails.class);
		List<RequestDetails> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;

	}

	// To issue book to user
	@Override
	public boolean isBookCheckedOut(int requestId) {

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
					;
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

		RequestDetails requestDetails = new RequestDetails();
		BookDetails bookDetails = new BookDetails();

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();

			jpql = "select count(*) from RequestDetails r where r.userId=:userId";
			Query query = manager.createQuery(jpql);
			query.setParameter("userId", userId);
			countOfBookRequest = ((Number) query.getSingleResult()).intValue();
			System.out.println("Count of request placed by user are :" + countOfBookRequest);

			if (countOfBookRequest < 3) {
				bookDetails = manager.find(BookDetails.class, bookId);

				if (bookDetails != null) {
					jpql = "select r from RequestDetails r ";
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

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 25);
		Date returnedDate = calendar.getTime();

		RequestDetails info = new RequestDetails();

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();

			jpql = "select r from RequestDetails r ";
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

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BookDetails record = manager.find(BookDetails.class, bookId);
			manager.remove(record);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			System.err.println(e.getMessage());
			return false;
		} finally {
			manager.close();
			factory.close();
		}
	}

}
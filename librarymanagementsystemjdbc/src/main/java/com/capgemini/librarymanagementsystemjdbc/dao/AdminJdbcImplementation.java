package com.capgemini.librarymanagementsystemjdbc.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import com.capgemini.librarymanagementsystemjdbc.dto.Admin_User_InformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.dto.BookInformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestInformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.exception.LibraryManagementJdbcExceptions;

public class AdminJdbcImplementation implements AdminJdbcDao {

	@Override
	public boolean enrollUser(Admin_User_InformationJdbc userBean) {
		try (FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")) {
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			String dburl = properties.getProperty("dburl");
			try (Connection connection = DriverManager.getConnection(dburl)) {
				String query = properties.getProperty("addUser");
				try (PreparedStatement pstmt = connection.prepareStatement(query)) {
					pstmt.setInt(1, userBean.getId());
					pstmt.setString(2, userBean.getUserName());
					pstmt.setString(3, userBean.getFirstName());
					pstmt.setString(4, userBean.getLastName());
					pstmt.setString(5, userBean.getEmailId());
					pstmt.setString(6, userBean.getPassword());
					pstmt.setString(7, userBean.getRole());

					int isRegistered = pstmt.executeUpdate();
					if (isRegistered != 0) {
						return true;
					} else {
						throw new LibraryManagementJdbcExceptions("User not registered");
					}

				}
			} catch (LibraryManagementJdbcExceptions e) {
				System.err.println("Book is already existing");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Admin_User_InformationJdbc signIn(String emailId, String password) {

		Admin_User_InformationJdbc adminBean = new Admin_User_InformationJdbc();
		try (FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")) {
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();

			String dburl = properties.getProperty("dburl");

			try (Connection conn = DriverManager.getConnection(dburl)) {
				String query = properties.getProperty("login");
				try (PreparedStatement pstmt = conn.prepareStatement(query)) {
					pstmt.setString(1, emailId);
					pstmt.setString(2, password);
					ResultSet rs = pstmt.executeQuery();
					if (rs.next()) {
						adminBean.setEmailId(rs.getString("emailid"));
						adminBean.setPassword(rs.getString("password"));
						return adminBean;
					} else {
						System.out.println();
						return null;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean enrollBook(BookInformationJdbc info) {
		try (FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")) {
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			String dburl = properties.getProperty("dburl");
			try (Connection connection = DriverManager.getConnection(dburl)) {
				String query = properties.getProperty("addBook");
				try (PreparedStatement pstmt = connection.prepareStatement(query)) {
					pstmt.setInt(1, info.getBookId());
					pstmt.setString(2, info.getBookName());
					pstmt.setString(3, info.getAuthorName());
					pstmt.setString(4, info.getPublisher());
					pstmt.setString(5, info.getCategory());

					int isAdded = pstmt.executeUpdate();
					if (isAdded != 0) {
						return true;
					} else {
						throw new LibraryManagementJdbcExceptions("Book is not added");
					}

				}
			} catch (LibraryManagementJdbcExceptions e) {
				System.err.println("Book is already existing");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeBook(int bookId) {
		try (FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")) {
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			String dburl = properties.getProperty("dburl");
			try (Connection connection = DriverManager.getConnection(dburl)) {
				String query = properties.getProperty("removeBook");
				try (PreparedStatement pstmt = connection.prepareStatement(query)) {
					pstmt.setInt(1, bookId);
					int res = pstmt.executeUpdate();
					if (res != 0) {
						return true;
					}
				} catch (LibraryManagementJdbcExceptions lmse) {
					System.err.println("book cannot be removed");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateBook(BookInformationJdbc bookInfo) {
		try (FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")) {
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			String dburl = properties.getProperty("dburl");
			try (Connection connection = DriverManager.getConnection(dburl)) {
				String query = properties.getProperty("updateBook");
				try (PreparedStatement pstmt = connection.prepareStatement(query)) {
					pstmt.setInt(1, bookInfo.getBookId());
					pstmt.setString(2, bookInfo.getBookName());
					int res = pstmt.executeUpdate();
					if (res != 0) {
						return true;
					}
				} catch (LibraryManagementJdbcExceptions lmse) {
					System.err.println("book is not able to update");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public BookInformationJdbc searchBookByBookId(int bookId) {
		BookInformationJdbc bookBean = new BookInformationJdbc();
		try (FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")) {
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			String dburl = properties.getProperty("dburl");
			try (Connection connection = DriverManager.getConnection(dburl)) {
				String query = properties.getProperty("searchBook");
				try (PreparedStatement pstmt = connection.prepareStatement(query)) {
					pstmt.setInt(1, bookId);
					ResultSet rs = pstmt.executeQuery();
					if (rs.next()) {
						bookBean.setBookId(rs.getInt("bookId"));
						bookBean.setBookName(rs.getString("bookName"));
						bookBean.setAuthorName(rs.getString("authorName"));
						return bookBean;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Admin_User_InformationJdbc> listOfUsers() {
		try (FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")) {
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();

			String dburl = properties.getProperty("dburl");

			try (Connection conn = DriverManager.getConnection(dburl)) {
				String query = properties.getProperty("getAllUsers");
				try (Statement pstmt = conn.createStatement()) {

					ResultSet rs = pstmt.executeQuery(query);
					List<Admin_User_InformationJdbc> beans = new LinkedList<Admin_User_InformationJdbc>();
					while (rs.next()) {
						Admin_User_InformationJdbc userBean = new Admin_User_InformationJdbc();

						userBean.setId(rs.getInt("id"));
						userBean.setUserName(rs.getString("username"));
						userBean.setFirstName(rs.getString("firstname"));
						userBean.setLastName(rs.getString("lastname"));
						userBean.setEmailId(rs.getString("emailid"));
						userBean.setPassword(rs.getString("password"));
						userBean.setRole(rs.getString("role"));
						beans.add(userBean);

					}
					return beans;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<BookInformationJdbc> listOfBooks() {
		try (FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")) {
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();

			String dburl = properties.getProperty("dburl");

			try (Connection conn = DriverManager.getConnection(dburl)) {
				String query = properties.getProperty("getAllBookInfo");
				try (Statement pstmt = conn.createStatement()) {

					ResultSet rs = pstmt.executeQuery(query);
					List<BookInformationJdbc> beans = new ArrayList<BookInformationJdbc>();
					while (rs.next()) {
						BookInformationJdbc bookBean = new BookInformationJdbc();
						bookBean.setBookId(rs.getInt("bookId"));
						bookBean.setBookName(rs.getString("bookName"));
						bookBean.setAuthorName(rs.getString("authorName"));
						bookBean.setPublisher(rs.getString("publisher"));
						bookBean.setCategory(rs.getString("category"));
						beans.add(bookBean);

					}
					return beans;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<RequestInformationJdbc> showAllUserRequests() {
		try (FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")) {
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();

			String dburl = properties.getProperty("dburl");

			try (Connection conn = DriverManager.getConnection(dburl)) {
				String query = properties.getProperty("showRequest");
				try (Statement pstmt = conn.createStatement()) {

					ResultSet rs = pstmt.executeQuery(query);
					List<RequestInformationJdbc> beans = new ArrayList<RequestInformationJdbc>();
					while (rs.next()) {
						RequestInformationJdbc requestInfo = new RequestInformationJdbc();
						requestInfo.setRequestId(rs.getInt("requestid"));
						requestInfo.setUserId(rs.getInt("userid"));
						requestInfo.setBookId(rs.getInt("bookid"));
						requestInfo.setBookName(rs.getString("bookName"));
						requestInfo.setIssueDate(rs.getDate("issuedDate"));
						requestInfo.setExpectedReturnDate(rs.getDate("expectedReturnDate"));
						requestInfo.setReturnDate(rs.getDate("returnDate"));

						beans.add(requestInfo);
					}
					return beans;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean bookIssue(int requestId) {
		PreparedStatement pstmt = null;
		try (FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")) {
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			String dburl = properties.getProperty("dburl");
			try (Connection conn = DriverManager.getConnection(dburl)) {
				pstmt = conn.prepareStatement(properties.getProperty("getRequest"));
				pstmt.setInt(1, requestId);
				ResultSet resultSet = pstmt.executeQuery();
				if (resultSet.next()) {
					RequestInformationJdbc info = new RequestInformationJdbc();
					info.setUserId(resultSet.getInt("id"));
					info.setBookId(resultSet.getInt("bookid"));
					int requestUserId = info.getUserId();
					System.out.println("Requested user " + info.getUserId());
					int requestBookId = resultSet.getInt("bookid");
					System.out.println("Requested book " + requestBookId);
					// In Admin setting the no Of Books Borrowed
					if (requestUserId != 0) {
//								String query2 = properties.getProperty("getUserBooks");
						pstmt = conn.prepareStatement(properties.getProperty("getUsersBooks"));
						pstmt.setInt(1, requestUserId);
						resultSet = pstmt.executeQuery();
						if (resultSet.next()) {
							Admin_User_InformationJdbc info2 = new Admin_User_InformationJdbc();
							info2.setNoOfBooksBorrowed(resultSet.getInt("noOfBooksBorrowed"));
							int noOfBooksBorrowed = info2.getNoOfBooksBorrowed();
							System.out.println("no of books Before issue	" + noOfBooksBorrowed);
							if (noOfBooksBorrowed < 3) {
								String query3 = properties.getProperty("issueBookQuery");
								pstmt = conn.prepareStatement(query3);
								pstmt.setInt(1, requestId);
								int updateDate = pstmt.executeUpdate();
								if (updateDate != 0) {
									// Update book availability as false as we are issuing
									String query4 = properties.getProperty("setAvailability");
									pstmt = conn.prepareStatement(query4);
									pstmt.setInt(1, requestUserId);
									pstmt.executeUpdate();
									// Update User no of books borrowed
									noOfBooksBorrowed++;
									String query5 = properties.getProperty("setNoOfBooksBorrowed");
									pstmt = conn.prepareStatement(query5);
									pstmt.setInt(1, noOfBooksBorrowed);
									pstmt.setInt(2, requestUserId);
									pstmt.executeUpdate();

								} // End of if update date!=0

								return true;

							} else {
								return false;// End OF If No of books borrowed <3
							}

						} else {
							return false;// End Of getting User Data
						}

					} else

					{
						System.out.println("");
						return false;
					}

				} else {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("resource")
	@Override
	public boolean isBookReceived(int requestId) {
		PreparedStatement pstmt = null;
		String query = null;
		ResultSet resultSet = null;
		int noOfDaysDelayed = 0;
		int fine = 0;
		int userId = 0;
		int bookId = 0;
		int result = 0;
		try (FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")) {
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			String dburl = properties.getProperty("dburl");
			try (Connection conn = DriverManager.getConnection(dburl)) {

				query = properties.getProperty("receiveBook");
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, requestId);
				resultSet = pstmt.executeQuery();

				while (resultSet.next()) {
					// Date issuedDate = resultSet.getDate("issuedDate");
					Date returnedDate = resultSet.getDate("returnedDate");
					Date expectedReturnedDate = resultSet.getDate("expectedreturnDate");
					userId = resultSet.getInt("id");
					bookId = resultSet.getInt("bookid");

					if (returnedDate != null) {
						query = properties.getProperty("getfine");
						pstmt = conn.prepareStatement(query);
						pstmt.setDate(1, returnedDate);
						pstmt.setDate(2, expectedReturnedDate);
						pstmt.setInt(3, requestId);
						
						resultSet = pstmt.executeQuery();

						while (resultSet.next()) {
							noOfDaysDelayed = resultSet.getInt(1);
						}

						System.out.println("No Of Days Delayed " + noOfDaysDelayed);

						if (noOfDaysDelayed > 0) {
							fine = noOfDaysDelayed * 5;
							query = properties.getProperty("userFine");
							pstmt = conn.prepareStatement(query);
							pstmt.setDouble(1, fine);
							pstmt.setInt(2, userId);

							result = pstmt.executeUpdate();
							if (result != 0) {
								System.out.println("fine updated" + fine);
							}

						}

						// Make available in library books
						query = properties.getProperty("setBookAvailability2");
						pstmt = conn.prepareStatement(query);
						pstmt.setInt(1, bookId);
						result = pstmt.executeUpdate();

						// set No Of Books Borrowed
						query = properties.getProperty("setNoOfBooksBorrowed2");
						pstmt = conn.prepareStatement(query);
						pstmt.setInt(1, userId);
						result = pstmt.executeUpdate();

						query = properties.getProperty("removeRequest");
						pstmt = conn.prepareStatement(query);
						pstmt.setInt(1, requestId);

						result = pstmt.executeUpdate();

						return true;

					}

				} // End Of While Loop
				return false;

			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}

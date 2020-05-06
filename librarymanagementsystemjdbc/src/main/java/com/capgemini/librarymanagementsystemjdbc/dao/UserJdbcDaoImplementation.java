package com.capgemini.librarymanagementsystemjdbc.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import com.capgemini.librarymanagementsystemjdbc.dto.Admin_User_InformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.dto.BookInformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestInformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.exception.LibraryManagementJdbcExceptions;

public class UserJdbcDaoImplementation implements UserJdbcDao {

	@Override
	public Admin_User_InformationJdbc login(String email, String password) {
		Admin_User_InformationJdbc adminBean = new Admin_User_InformationJdbc();
		try(FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")){
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			
			String dburl=properties.getProperty("dburl");
			
			try(Connection conn = DriverManager.getConnection(dburl)){
				String query =properties.getProperty("login") ;
				try(PreparedStatement pstmt = conn.prepareStatement(query)){
					pstmt.setString(1, email);
					pstmt.setString(2, password); 
					ResultSet rs = pstmt.executeQuery();
					if(rs.next()) {
					adminBean.setEmailId(rs.getString("emailid"));
					adminBean.setPassword(rs.getString("password"));
							return adminBean;
						}else {
							System.out.println();
							return null;
					}
					}	
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		
		return null;
		
	}

	@Override
	public BookInformationJdbc searchById(int id) {
		BookInformationJdbc bookBean=new BookInformationJdbc();
		try(FileInputStream fis=new FileInputStream("LibraryManagementSystemDataBase.properties")){
			Properties properties=new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			String dburl=properties.getProperty("dburl");
			try(Connection connection=DriverManager.getConnection(dburl)){
				String query=properties.getProperty("searchBook");
				try(PreparedStatement pstmt=connection.prepareStatement(query)){
					pstmt.setInt(1, id);
					ResultSet rs=pstmt.executeQuery();
					if(rs.next()) {
						bookBean.setBookId(rs.getInt("bookId"));
						bookBean.setBookName(rs.getString("bookName"));
						bookBean.setAuthorName(rs.getString("authorName"));
						return bookBean;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		return null;
		
	}

	@Override
	public RequestInformationJdbc bookRequest(Admin_User_InformationJdbc userBean, BookInformationJdbc bookBean) {
		RequestInformationJdbc requestInfo=new RequestInformationJdbc();
		try(FileInputStream fis=new FileInputStream("LibraryManagementSystemDataBase.properties")){
			Properties properties=new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			String dburl=properties.getProperty("dburl");
			try(Connection connection=DriverManager.getConnection(dburl)){
				String query=properties.getProperty("bookRequest");
				try(PreparedStatement pstmt=connection.prepareStatement(query)){
//					RequestInfo requestInfo=new RequestInfo();
					pstmt.setInt(1 ,userBean.getId());
					pstmt.setInt(2 ,bookBean.getBookId());
					
					int result=pstmt.executeUpdate();
					
					if(result!=0) {
						
						return requestInfo;				
						
					}else {
						return null;
					}	
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
		@Override
		public boolean bookReturn(int userId, int bookId) {
			ResultSet rs=null;
			PreparedStatement pstmt1=null;
			try(FileInputStream fis=new FileInputStream("LibraryManagementSystemDataBase.properties")){
				Properties properties=new Properties();
				properties.load(fis);
				Class.forName(properties.getProperty("path")).newInstance();
				String dburl=properties.getProperty("dburl");
				try(Connection connection=DriverManager.getConnection(dburl)){
					
					try(PreparedStatement pstmt=connection.prepareStatement(properties.getProperty("bookReturn"))){
						pstmt.setInt(1, userId);
						pstmt.setInt(2, bookId);

						rs = pstmt.executeQuery();

						if (rs.next() != false) {
							int requestId = rs.getInt("requestId");
							System.out.println("Request Id....." + requestId);

						
//							query = properties.getProperty("updateReturnDate");
							pstmt1 = connection.prepareStatement(properties.getProperty("updateReturnDate"));
							pstmt1.setInt(1, requestId);

							int count = pstmt1.executeUpdate();
							if (count != 0) {
								return true;
							}
						}
						
					}
				}
			}catch(Exception e) {
				throw new LibraryManagementJdbcExceptions("Book cannot be returned");
			}
		
			return false;
		}

	
}

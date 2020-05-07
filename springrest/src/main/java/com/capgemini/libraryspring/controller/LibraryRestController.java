package com.capgemini.libraryspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.libraryspring.dto.BookDetails;
import com.capgemini.libraryspring.dto.LibraryDetails;
import com.capgemini.libraryspring.dto.LibraryResponse;
import com.capgemini.libraryspring.dto.RequestDetails;
import com.capgemini.libraryspring.service.LibrarySpringService;

@RestController
@RequestMapping("/api")
public class LibraryRestController {

	@Autowired
	private LibrarySpringService service;
	
	@PostMapping(path = "/addUser", 
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse addUser(@RequestBody LibraryDetails userinfo) {
		
		boolean isadded = service.enrollUser(userinfo);
		LibraryResponse response = new LibraryResponse();
		if (isadded) {
			response.setMessage("User Registered sucessfully!!");
		} else {
			response.setError(true);
			response.setMessage("Unable to add user!");
		}
		return response;
	}
	

	@PostMapping(path = "/addbook", 
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse addBook(@RequestBody BookDetails bookinfo) {

		boolean isadded = service.addBook(bookinfo);
		LibraryResponse response = new LibraryResponse();
		if (isadded) {
			response.setMessage("Book added in to account sucessfully!!");
		} else {
			response.setError(true);
			response.setMessage("Unable to add book!");
		}
		return response;
	}
	
	@DeleteMapping(path = "/deletebook/{bookId}", 
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })

	public LibraryResponse deleteBook(@PathVariable(name = "bookId") int bookId) {
		boolean isDeleted = service.removeBook(bookId);

		LibraryResponse response = new LibraryResponse();

		if (isDeleted) {
			response.setMessage("Record of bookId" + bookId + " deleted sucessfully");
		} else {
			response.setError(true);
			response.setMessage("bookId" + bookId + "not found");
		}
		return response;
	}

	
	@PutMapping(path="/updateBook",
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	
	public LibraryResponse updateEmployeeInfo(@RequestBody BookDetails info) {
		
		boolean isupdated = service.updateBook(info);
		LibraryResponse response = new LibraryResponse();
		if(isupdated) {
			response.setMessage("book updated Successfully");
		}
		else {
			response.setError(true);
			response.setMessage("Unable to update book!");
		}
		return response;
	}
	

	@GetMapping(path="/getAllBooks", 
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse getAllBooks() {
		List<BookDetails> recordList =service.ListOfBooks();		
		LibraryResponse response = new LibraryResponse();
		if(recordList !=  null && !recordList.isEmpty()) {
			response.setBook_Details(recordList);
		}else {
		response.setError(true);
		response.setMessage("Books Not Found");
		}
		return response;
	}
	
	
	@GetMapping(path="/searchBookById", 
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	//@ResponseBody{
	public LibraryResponse searchbook(int  bookId){
		List<BookDetails> record =service.searchByBookId(bookId);
			
		LibraryResponse response = new LibraryResponse();
		if(record != null &&  !record.isEmpty()) {
			response.setBook_Details(record);
		}else {
		response.setError(true);
		response.setMessage("search book not found with the bookId you have searched");
		}
		return response;
	}
	
	@GetMapping(path="/searchBookByBookName", 
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	//@ResponseBody{
	public LibraryResponse searchBookByName(String  bookName){
		List<BookDetails> record =service.searchByBookName(bookName);
			
		LibraryResponse response = new LibraryResponse();
		if(record != null &&  !record.isEmpty()) {
			response.setBook_Details(record);
		}else {
		response.setError(true);
		response.setMessage("search book not found with the bookName you have searched");
		}
		return response;
	}
	
	@GetMapping(path="/searchBookByAuthorName", 
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	//@ResponseBody{
	public LibraryResponse searchBookByAuthorName(String  authorName){
		List<BookDetails> record =service.searchByAuthorName(authorName);
			
		LibraryResponse response = new LibraryResponse();
		if(record != null &&  !record.isEmpty()) {
			response.setBook_Details(record);
		}else {
		response.setError(true);
		response.setMessage("search book not found with the Author you have searched");
		}
		return response;
	}
	

	@GetMapping(path="/getAllUsers", produces = { MediaType.APPLICATION_XML_VALUE})
	//@ResponseBody{
	public LibraryResponse getAllUsers() {
		List<LibraryDetails> recordList =service.ListOfUsers();
			
		LibraryResponse response = new LibraryResponse();
		if(recordList !=  null && !recordList.isEmpty()) {
			response.setLibrary_Details(recordList);
		}else {
		response.setError(true);
		response.setMessage("No user Found");
		}
		return response;
	}
	
	@GetMapping(path="/getAllRequest", produces = { MediaType.APPLICATION_XML_VALUE})
	//@ResponseBody{
	public LibraryResponse getAllRequest() {
		List<RequestDetails> recordList =service.ListOfRequest();
			
		LibraryResponse response = new LibraryResponse();
		if(recordList !=  null && !recordList.isEmpty()) {
			response.setRequest_Details(recordList);
		}else {
		response.setError(true);
		response.setMessage("No requests Found");
		}
		return response;
	}
	
	@PostMapping(path = "/requestBook", 
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    //@ResponseBody
	public LibraryResponse requestBook(@RequestBody int userId, int bookId) {
		
		boolean isbookrequested = service.placeBookRequest(userId, bookId);
		LibraryResponse response = new LibraryResponse();
		if (isbookrequested) {
			response.setMessage("request placed sucessfully!!");
		} else {
			response.setError(true);
			response.setMessage("Unable to place a request!");
		}
		return response;
	}
	
	@PostMapping(path = "/issueBook", 
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    //@ResponseBody
	public LibraryResponse issueBook(int requestId) {
		
		boolean isIssued = service.isBookCheckedOut(requestId);
		LibraryResponse response = new LibraryResponse();
		if (isIssued) {
			response.setMessage("book issued sucessfully!!");
		} else {
			response.setError(true);
			response.setMessage("Unable to issue book!");
		}
		return response;
	}
	
	@GetMapping(path="/returnBook", produces = { MediaType.APPLICATION_XML_VALUE})
	//@ResponseBody{
	public LibraryResponse receiveBook(@RequestBody int userId,int bookId) {
		boolean isrecieved =service.isBookCheckedIn(userId, bookId);
			
		LibraryResponse response = new LibraryResponse();
		if (isrecieved) {
			response.setMessage("book returned back to library sucessfully!!");
		} else {
			response.setError(true);
			response.setMessage("Unable to return book!");
		}
		return response;
	}
	
	@GetMapping(path="/receiveBook", produces = { MediaType.APPLICATION_XML_VALUE})
	//@ResponseBody{
	public LibraryResponse receivedBookByLibrary(@RequestBody int requestId) {
		boolean isrecieved =service.isBookReceived(requestId);
			
		LibraryResponse response = new LibraryResponse();
		if (isrecieved) {
			response.setMessage("Book received sucessfully!!");
		} else {
			response.setError(true);
			response.setMessage("Unable to receive book!");
		}
		return response;
	}
	
}//End of class

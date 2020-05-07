package com.capgemini.libraryspring.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LibraryResponse {
	
	private boolean error;
	private String message;
	
	private LibraryDetails libraryDetails;
	private List<LibraryDetails> library_Details;
	
	
	private BookDetails bookDetails;
	private List<BookDetails> book_Details;
	
	private RequestDetails requestDetails;
	private List<RequestDetails> request_Details;
	
	
}
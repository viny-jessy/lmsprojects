package com.capgemini.libraryhibernate.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
@Data
@Table(name = "book_request")
public class RequestDetails implements Serializable {

	@Id
	@Column
	@GeneratedValue
	private int requestId;
	@Column
	private int userId;
	@Column
	private int bookId;
	@Column
	private Date issuedDate;
	@Column
	private Date bookReturnedDate;
	@Column
	private Date expectedReturnDate;

	
}

package com.capgemini.libraryspring.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@SuppressWarnings("serial")
@Entity
@Data
@Table(name = "RequestDetails")
@SequenceGenerator(name="seq1" , initialValue=1 , allocationSize=100)
public class RequestDetails implements Serializable {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "seq1")
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

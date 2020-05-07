package com.capgemini.libraryspring.dto;

import java.io.Serializable;

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
@Table(name = "BookDetails")
@SequenceGenerator(name="seq3" , initialValue=100 , allocationSize=100)
public class BookDetails implements Serializable {
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "seq3")
	private int bookId;
	@Column
	private String bookName;
	@Column
	private String authourName;
	@Column
	private boolean isAvailable;
	
}

package com.capgemini.libraryhibernate.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
@Data
@Table(name = "book_info")
public class BookDetails implements Serializable {
	@Id
	@Column
	private int bookId;
	@Column
	private String bookName;
	@Column
	private String authourName;
	@Column
	private boolean isAvailable;
	
}

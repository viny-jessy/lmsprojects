
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
@Table(name = "LibraryDetails")
@SequenceGenerator(name="seq4" , initialValue=1000 , allocationSize=100)
public class LibraryDetails implements Serializable {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "seq4")
	private int id;
	@Column
	private String name;
	@Column
	private String emailId;
	@Column
	private String password;
	@Column
	private int noOfBooksBorrowed;
	@Column
	private String role;
	@Column
	private double fine;

	

}

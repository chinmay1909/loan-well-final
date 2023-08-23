package com.wellsfargo.training.lms.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 
 * Spring Boot JPA with Hibernate
 * 
 * Spring Boot JPA is a Java specification for managing relational data in Java applications. 
 * It allows us to access and persist data between Java object/ class and relational database. 
 * JPA follows Object-Relation Mapping (ORM). It is a set of interfaces. 
 * It also provides a runtime EntityManager API for processing queries and transactions on the objects 
 * against the database. 
 * It uses a platform-independent object-oriented query language JPQL (Java Persistent Query Language).
 * JPA is implemented with Annotations
 */

/* The @Entity annotation specifies that the class is an entity and is mapped to a database table.*/
@Entity
@Table(name="loan_card_master")
public class Loan {
	
	/*The @Id annotation specifies the primary key of an entity and the @GeneratedValue provides for 
	 * the specification of generation strategies for the values of primary keys. */
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long loan_id;

     private String loan_type;
     private int duration_in_years;
     
     
     
	public Loan() {
		
	}

	public Loan(Long loan_id, String loan_type, int duration_in_years) {
		this.loan_id = loan_id;
		this.loan_type = loan_type;
		this.duration_in_years = duration_in_years;
	}

	public Long getLoan_id() {
		return loan_id;
	}

	public void setLoan_id(Long loan_id) {
		this.loan_id = loan_id;
	}

	public String getLoan_type() {
		return loan_type;
	}

	public void setLoan_type(String loan_type) {
		this.loan_type = loan_type;
	}

	public int getDuration_in_years() {
		return duration_in_years;
	}

	public void setDuration_in_years(int duration_in_years) {
		this.duration_in_years = duration_in_years;
	}
	
	
	
}

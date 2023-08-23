package com.wellsfargo.training.lms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="item_master")
public class Item {
	
	/*The @Id annotation specifies the primary key of an entity and the @GeneratedValue provides for 
	 * the specification of generation strategies for the values of primary keys. */
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long item_id;
	 
	 private String item_description;
	 private String item_make;
	 private String item_category;
	 private int item_valuation;
     
	public Item() {
		
	}

	public Item(Long item_id, String item_description, String item_make, String item_category,
			int item_valuation) {
		this.item_id = item_id;
		this.item_description = item_description;
		this.item_make = item_make;
		this.item_category = item_category;
		this.item_valuation = item_valuation;
	}

	public Long getItem_id() {
		return item_id;
	}

	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}

	public String getItem_description() {
		return item_description;
	}

	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}

	public String getItem_make() {
		return item_make;
	}

	public void setItem_make(String item_make) {
		this.item_make = item_make;
	}

	public String getItem_category() {
		return item_category;
	}

	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}

	public int getItem_valuation() {
		return item_valuation;
	}

	public void setItem_valuation(int item_valuation) {
		this.item_valuation = item_valuation;
	}
	
}

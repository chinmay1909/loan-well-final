package com.wellsfargo.training.lms.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.wellsfargo.training.lms.model.LoanCard;

/**
 * 
 * @author rajgs
 * JPA Repository is mainly used for managing the data in a Spring Boot Application. 
 * JpaRepository is particularly a JPA specific extension for Repository.
 * Jpa Repository contains the APIs for basic CRUD operations, the APIS for 
 * pagination, and the APIs for sorting.
 * This Layer interacts with Database
 */

public interface LoanCardRepository extends JpaRepository<LoanCard, Long> {
	  //String is data type of id field of Loan class
	/*
     * This interface has save(),findAll(),findById(),deleteById(),count()
       etc.. inbuilt methods of jpa repository for various database operations.
       This interface will be implemented by class automatically
    */
	
	// Function to get all items belonging to a user id
//	@Query(value="select * from item_master i where e_id= :userId", nativeQuery=true)
//	public List<Item> getAllItemsByEmployee_id(long userId);
	// Get item_id from item_category and item_make
	
	
}


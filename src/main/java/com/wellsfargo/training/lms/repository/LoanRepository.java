package com.wellsfargo.training.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wellsfargo.training.lms.model.Loan;
import com.wellsfargo.training.lms.model.ViewLoan;

/**
 * 
 * @author rajgs
 * JPA Repository is mainly used for managing the data in a Spring Boot Application. 
 * JpaRepository is particularly a JPA specific extension for Repository.
 * Jpa Repository contains the APIs for basic CRUD operations, the APIS for 
 * pagination, and the APIs for sorting.
 * This Layer interacts with Database
 */

public interface LoanRepository extends JpaRepository<Loan, Long> {
	  //String is data type of id field of Loan class
	/*
     * This interface has save(),findAll(),findById(),deleteById(),count()
       etc.. inbuilt methods of jpa repository for various database operations.
       This interface will be implemented by class automatically
    */
	// function to display all loans belonging to an employee
	
	// function to get loan_id from loan_type and duration_in_years
	@Query("SELECT loan_id FROM Loan WHERE loan_type = :loan_type and duration_in_years = :duration_in_years")
	Long getLoanIdFromTable(String loan_type, int duration_in_years);
}

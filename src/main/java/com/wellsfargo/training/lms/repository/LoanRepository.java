package com.wellsfargo.training.lms.repository;

//-------------------------------------- IMPORTING NECESSARY LIBRARIES ---------------------------------//

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.wellsfargo.training.lms.model.Loan;

//-------------------------------------- INTERFACE FOR LOAN REPO ---------------------------------------//

public interface LoanRepository extends JpaRepository<Loan, Long> {
	
	// function to get loan_id from loan_type and duration_in_years
	@Query("SELECT l.loan_id FROM Loan l WHERE l.loan_type = :loan_type AND l.duration_in_years = :duration_in_years")
	Long getLoanIdFromTable(@Param("loan_type") String loan_type, @Param("duration_in_years") int duration_in_years);
}

//----------------------------------------- END OF LOAN REPO -------------------------------------------//
package com.wellsfargo.training.lms.service;

//----------------------------- IMPORTING NECESSARY LIBRARIES ----------------------------------//

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wellsfargo.training.lms.model.Loan;
import com.wellsfargo.training.lms.repository.LoanRepository;
import jakarta.transaction.Transactional;

//-------------------------- SERVICE LAYER CLASS FOR LOAN  -------------------------------------//

@Service
@Transactional
public class LoanService {
	
	// Repository instance
	@Autowired
	private LoanRepository lrepo;
	
	
	// Service method for admin to be able to create new loans in Loan table (fixed)
	// Not to be confused with loan cards, loans are pre-filled, LoanCards are generated for user on-demand	
	public Loan saveLoan(Loan l) {
		return lrepo.save(l);
	}
	
	// Service method for admin to be able to list all the loans in Loan table (fixed)
	public List<Loan> listAll() {
 		return lrepo.findAll();
 	}
	
	// function to get a single loan from the table by using its id
	public Optional<Loan> getSingleLoan(long id) {
		 return lrepo.findById(id);
	}
	
	// Service method for Admin to be able to delete loan details from Loan table
	public void deleteLoan(long id) {
		lrepo.deleteById(id);
	}
	
	// Function to get the loan id from the table using the other details
	public Long getLoanIdFromTable(String loan_type, int duration_in_years) {
		return lrepo.getLoanIdFromTable(loan_type, duration_in_years);
	}
	
}

//---------------------------------------- END OF LOAN SERVICE CLASS -------------------------//
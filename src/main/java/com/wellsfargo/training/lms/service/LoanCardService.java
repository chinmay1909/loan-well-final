package com.wellsfargo.training.lms.service;

//----------------------------- IMPORTING NECESSARY LIBRARIES ----------------------------------//

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wellsfargo.training.lms.model.LoanCard;
import com.wellsfargo.training.lms.repository.LoanCardRepository;
import jakarta.transaction.Transactional;

//-------------------------- SERVICE LAYER CLASS FOR LOAN CARD ---------------------------------//

@Service
@Transactional
public class LoanCardService {
	
	// Repository instance
	@Autowired
	private LoanCardRepository lcrepo;
	
	// Function to save a loan card in the table
	public LoanCard saveLoanCard(LoanCard lc) {
		return lcrepo.save(lc);
	}
	
	// Function to list all the loan cards in the table
	public List<LoanCard> listAll() {
 		return lcrepo.findAll(); 
 	}
	
	// Function to get a single loan card from its primary key id
	public Optional<LoanCard> getSingleLoanCard(long id) {
		return lcrepo.findById(id);
	}
	
	// Function to delete a loan card from the table by giving its id
	public void deleteLoanCard(long id) {
		lcrepo.deleteById(id);
	}
}

//------------------------------------- END OF LOANCARD SERVICE CLASS ------------------------//
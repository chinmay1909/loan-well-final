package com.wellsfargo.training.lms.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wellsfargo.training.lms.model.Loan;
import com.wellsfargo.training.lms.repository.LoanRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class LoanService {
	
	@Autowired
	private LoanRepository lrepo;
	
	
	// ------------------------ LOAN SERVICE METHODS REQUIRED FOR ADMIN DASHBOARD -------------------- //
	
	
	// Service method for admin to be able to create new loans in Loan table (fixed)
	// Not to be confused with loan cards
	// Loans are pre-filled, LoanCards are generated for user on-demand	
	public Loan saveLoan(Loan l) {
		return lrepo.save(l);   // Invokes save() method predefined in JPA repo
	}
	
	// Service method for admin to be able to list all the loans in Loan table (fixed)
	public List<Loan> listAll(){
 		
 		return lrepo.findAll(); //Define in JPA repo.
 	}
	
	public Optional<Loan> getSingleLoan(long id)
	{
		 return lrepo.findById(id);
	}
	
	// Service method for Admin to be able to delete loan details from Loan table
	public void deleteLoan(long id)
	{
		lrepo.deleteById(id);
	}
	
	// Update loan function is not required for service class, it is in LoanController class
}
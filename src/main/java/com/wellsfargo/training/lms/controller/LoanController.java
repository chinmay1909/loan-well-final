package com.wellsfargo.training.lms.controller;

//--------------------------- IMPORTING  NECESSARY LIBRARIES ----------------------------//

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wellsfargo.training.lms.exception.ResourceNotFoundException;
import com.wellsfargo.training.lms.model.Loan;
import com.wellsfargo.training.lms.service.LoanService;

//--------------------------- CONTROLLER LAYER CLASS FOR LOAN ---------------------------//

@RestController
@RequestMapping(value="/api")
public class LoanController {
	
	// Service instance
	@Autowired
	private LoanService lservice;
	
	// Controller function to save a Loan in the database
	@PostMapping("/loans")
	public Loan saveLoan(@Validated @RequestBody Loan loan) {
		
		Loan p=lservice.saveLoan(loan);
		return p;
	}

	// Controller function to get a list of all loans 
	@GetMapping("/loans")
	public List<Loan> getAllLoans() {
		return lservice.listAll(); 
	}
	
	// Controller function to get a particular Loan by its primary key id
	@GetMapping("/loans/{id}")
	public ResponseEntity<Loan> getLoanById(@PathVariable(value="id") Long lid)
	throws ResourceNotFoundException {
		Loan l = lservice.getSingleLoan(lid).
				orElseThrow(() -> new ResourceNotFoundException("Loan not found for this id: " + lid));
		return ResponseEntity.ok().body(l);
	}
	
	// Controller function to get a loan by its id and update it
	@PutMapping("/loans/{id}")
	public ResponseEntity<Loan> updateLoan(@PathVariable(value="id") Long lid, @Validated @RequestBody Loan l)
	throws ResourceNotFoundException {
		Loan loan = lservice.getSingleLoan(lid).
				orElseThrow(() -> new ResourceNotFoundException("Loan not found for this id: " + lid));
		
		loan.setLoan_type(l.getLoan_type());
		loan.setDuration_in_years(l.getDuration_in_years());
		
		final Loan updatedLoan = lservice.saveLoan(loan);
		return ResponseEntity.ok().body(updatedLoan);
	}

	// Controller function to delete a particular loan using its primary key id
	@DeleteMapping("/loans/{id}")
	public Map<String, Boolean> deleteLoan(@PathVariable(value="id") Long lid)
	throws ResourceNotFoundException {
		lservice.getSingleLoan(lid).
				orElseThrow(() -> new ResourceNotFoundException("Loan not found for this id: " + lid));
		lservice.deleteLoan(lid);
		
		Map<String,Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return response;
	}
	
}

//--------------------------------- END OF LOAN CONTROLLER CLASS -------------------------//
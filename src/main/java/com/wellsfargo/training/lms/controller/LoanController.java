package com.wellsfargo.training.lms.controller;

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

/*Spring RestController annotation is used to create RESTful web services using Spring MVC. 
 * Spring RestController takes care of mapping request data to the defined request handler method. 
 * Once response body is generated from the handler method, it converts it to JSON or XML response. 
 * 
 * @RequestMapping - maps HTTP request with a path to a controller 
 * */

@RestController
@RequestMapping(value="/api")
public class LoanController {
	

	@Autowired
	private LoanService lservice;
	
	/* ResponseEntity represents an HTTP response, including headers, body, and status.
	 *  @RequestBody annotation automatically deserializes the JSON into a Java type
	 *  */
	
	//Open PostMan, make a POST Request - http://localhost:8085/pms/api/loans/
    //Select body -> raw -> JSON 
    //Insert JSON product object.
	@PostMapping("/loans")
	public Loan saveLoan(@Validated @RequestBody Loan loan) {
		
		Loan p=lservice.saveLoan(loan);
		return p;
	}
	
	// Postman/Browser --> Controller -->Service -> Repository -> DataBase
	// All layers will use Model when required
	//Open PostMan, make a GET Request - http://localhost:8085/pms/api/loans/
	@GetMapping("/loans")
	public List<Loan> getAllLoans() {
		return lservice.listAll();   // Invokes service Method user defined listAll()
	}
	
	//Open PostMan, make a GET Request - http://localhost:8085/lms/api/loans/4
	
		@GetMapping("/loans/{id}")
		public ResponseEntity<Loan> getLoanById(@PathVariable(value="id") Long lid)
		throws ResourceNotFoundException
		{
			Loan l = lservice.getSingleLoan(lid).
					orElseThrow(() -> new ResourceNotFoundException("Loan not found for this id: " + lid));
			return ResponseEntity.ok().body(l);
		}
		
		//Open PostMan, make a PUT Request - http://localhost:8085/lms/api/loans/3
	    //Select body -> raw -> JSON 
	    //Update JSON product object with new Values.
	 
		
		@PutMapping("/loans/{id}")
		public ResponseEntity<Loan> updateLoan(@PathVariable(value="id") Long lid, @Validated @RequestBody Loan l)
		throws ResourceNotFoundException
		{
			Loan loan = lservice.getSingleLoan(lid).
					orElseThrow(() -> new ResourceNotFoundException("Loan not found for this id: " + lid));
			
			loan.setLoan_type(l.getLoan_type());
			loan.setDuration_in_years(l.getDuration_in_years());
			
			final Loan updatedLoan = lservice.saveLoan(loan);
			return ResponseEntity.ok().body(updatedLoan);
		}
		
		@DeleteMapping("/loans/{id}")
		public Map<String, Boolean> deleteLoan(@PathVariable(value="id") Long lid)
		throws ResourceNotFoundException
		{
			lservice.getSingleLoan(lid).
					orElseThrow(() -> new ResourceNotFoundException("Loan not found for this id: " + lid));
			lservice.deleteLoan(lid);
			
			Map<String,Boolean> response = new HashMap<>();
			response.put("Deleted", Boolean.TRUE);
			return response;
		}
//		@GetMapping("/loans/{id}")
//		public List<Loan> getLoansByUserID(@PathVariable(value="id") Long uid)
//		throws ResourceNotFoundException
//		{
//			List<Loan> l = lservice.getAllLoansByUserId(uid);
//			return l;
//		}
	
}

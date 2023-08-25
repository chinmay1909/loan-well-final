package com.wellsfargo.training.lms.model;

//----------------------------- IMPORTING NECESSARY LIBRARIES --------------------------------------//

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//--------------- CLASS TO REPRESENT THE DATA MEMBERS AND FUNCTIONS OF A LOAN ----------------------//

@Entity
@Table(name="loan_card_master")
public class Loan
{
	// Primary key of the Loan table 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loan_id;
	 
	// Other data members of the Loan class
    private String loan_type;
    private int duration_in_years;
     
    // Empty constructor
	public Loan() {
		
	}
	
	// Parameterized constructor
	public Loan(Long loan_id, String loan_type, int duration_in_years) {
		this.loan_id = loan_id;
		this.loan_type = loan_type;
		this.duration_in_years = duration_in_years;
	}
	
	// Getters and Setters
	
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

//---------------------------- END OF CLASS LOAN ---------------------------------------//
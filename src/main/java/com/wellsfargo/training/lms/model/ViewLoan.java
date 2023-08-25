package com.wellsfargo.training.lms.model;

//------------------------- IMPORTING NECESSARY LIBRARIES ---------------------------//

import java.sql.Date;

//--------------------- MODEL CLASS FOR EMPLOYEE TO VIEW HIS LOANS -------------------//

public class ViewLoan {
	
	// Data members
	
	private Long loan_id;
	private String loan_type;
	private int duration_in_years;
	private Date card_issue_date;
	
	// Empty constructor
	public ViewLoan()
	{
		
	}
	
	// Parameterized constructor
	public ViewLoan(Long loan_id, String loan_type, int duration_in_years, Date card_issue_date) {
		this.loan_id = loan_id;
		this.loan_type = loan_type;
		this.duration_in_years = duration_in_years;
		this.card_issue_date = card_issue_date;
	}
	
	// Getters and setters
	
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
	public void setDuration_in_years(int duration) {
		this.duration_in_years = duration;
	}
	public Date getCard_issue_date() {
		return card_issue_date;
	}
	public void setCard_issue_date(Date card_issue_date) {
		this.card_issue_date = card_issue_date;
	}
}

// -------------------------------- END OF VIEWLOAN CLASS ------------------------------//
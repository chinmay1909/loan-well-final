package com.wellsfargo.training.lms.model;

//---------------------------------- IMPORTING NECESSARY LIBRARIES -------------------------------------//

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.NonNull;

// ------------------------- MODEL CLASS FOR DATA MEMBERS AND FUNCTIONS OF LOANCARD --------------------//

@Entity
@Table(name="employee_card_details")
public class LoanCard {
	
	// Primary key of loancard table
	@Id   // Primary key /unique
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long lc_issue_id;
	
	// Other data members
	
	@NonNull
	private Long employee_id;
	@NonNull
	private Long loan_id;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date card_issue_date;
	private int issued;
	private int paidback;
	
	// Foreign key connecting employee table
	@ManyToOne
	@JoinColumn(name="e_id")
	private Employee employee;
	
	// Empty constructor
	public LoanCard() {
		
	}
	
	// Parameterized constructor
	public LoanCard(Long lc_issue_id, @NonNull Long employee_id, @NonNull Long loan_id, Date card_issue_date, int issued, int paidback) {
		this.lc_issue_id = lc_issue_id;
		this.employee_id = employee_id;
		this.loan_id = loan_id;
		this.card_issue_date = card_issue_date;
		this.issued = issued;
		this.paidback = paidback;
	}
	
	// Getters and setters
	
	public Long getLc_issue_id() {
		return lc_issue_id;
	}
	public void setLc_issue_id(Long lc_issue_id) {
		this.lc_issue_id = lc_issue_id;
	}
	public Long getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}
	public int getIssued() {
		return issued;
	}
	public void setIssued(int issued) {
		this.issued = issued;
	}	
	public int getPaidback() {
		return paidback;
	}
	public void setPaidback(int paidback) {
		this.paidback = paidback;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Long getLoan_id() {
		return loan_id;
	}
	public void setLoan_id(Long loan_id) {
		this.loan_id = loan_id;
	}
	public Date getCard_issue_date() {
		return card_issue_date;
	}
	public void setCard_issue_date(Date card_issue_date) {
		this.card_issue_date = card_issue_date;
	}

}

// ---------------------------------- END OF LOANCARD CLASS ---------------------------------------- //
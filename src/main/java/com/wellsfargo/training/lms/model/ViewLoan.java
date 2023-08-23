package com.wellsfargo.training.lms.model;

import java.sql.Date;

public class ViewLoan {
	
	private Long loan_id;
	private String loan_type;
	private int duration_in_years;
	private Date card_issue_date;
	private Long employee_id;
	
	public ViewLoan()
	{
		
	}
	
	public ViewLoan(Long loan_id, String loan_type, int duration_in_years, Date card_issue_date, Long employee_id) {
		this.loan_id = loan_id;
		this.loan_type = loan_type;
		this.duration_in_years = duration_in_years;
		this.card_issue_date = card_issue_date;
		this.employee_id = employee_id;
	}
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
	
	public Long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
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

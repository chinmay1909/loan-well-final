package com.wellsfargo.training.lms.model;

// ---------------------------- MODEL CLASS FOR APPLYLOAN FUNCTION ---------------------------//

public class ApplyLoan {
	
	Long employee_id;
	String item_category;
	String item_make;
	String item_description;
	int item_valuation;
	int duration_in_years;

	// Parameterized constructor
	public ApplyLoan(Long employee_id, String item_category, String item_make, String item_description,
			int item_valuation, int duration_in_years) {
		this.employee_id = employee_id;
		this.item_category = item_category;
		this.item_make = item_make;
		this.item_description = item_description;
		this.item_valuation = item_valuation;
		this.duration_in_years = duration_in_years;
	}
	
	// Empty constructor
	public ApplyLoan() {
		// TODO Auto-generated constructor stub
	}
	
	// Getters and setters
	
	public Long getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}
	public String getItem_category() {
		return item_category;
	}
	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}
	public String getItem_make() {
		return item_make;
	}
	public void setItem_make(String item_make) {
		this.item_make = item_make;
	}
	public String getItem_description() {
		return item_description;
	}
	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}
	public int getItem_valuation() {
		return item_valuation;
	}
	public void setItem_valuation(int item_valuation) {
		this.item_valuation = item_valuation;
	}
	public int getDuration_in_years() {
		return duration_in_years;
	}
	public void setDuration_in_years(int duration_in_years) {
		this.duration_in_years = duration_in_years;
	}
	
}

//-------------------------------- END OF MODEL CLASS APPLYLOAN ------------------------//
package com.wellsfargo.training.lms.model;

import java.sql.Date;

public class ViewItem {
	
	private Long issue_id;
	private String item_description;
	private String item_make;
	private String item_category;
	private int item_valuation;
	private Long employee_id;
	private Long item_id;
	
	public ViewItem()
	{
		
	}

	public Long getIssue_id() {
		return issue_id;
	}

	public void setIssue_id(Long issue_id) {
		this.issue_id = issue_id;
	}

	public String getItem_description() {
		return item_description;
	}

	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}

	public String getItem_make() {
		return item_make;
	}

	public Long getItem_id() {
		return item_id;
	}

	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}

	public void setItem_make(String item_make) {
		this.item_make = item_make;
	}

	public String getItem_category() {
		return item_category;
	}

	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}

	public int getItem_valuation() {
		return item_valuation;
	}

	public void setItem_valuation(int item_valuation) {
		this.item_valuation = item_valuation;
	}

	public Long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}

	public ViewItem(Long issue_id, String item_description, String item_make, String item_category, int item_valuation,
			Long employee_id, Long item_id) {
		this.issue_id = issue_id;
		this.item_description = item_description;
		this.item_make = item_make;
		this.item_category = item_category;
		this.item_valuation = item_valuation;
		this.employee_id = employee_id;
		this.item_id = item_id;
	}
	
}

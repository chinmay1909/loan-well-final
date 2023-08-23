package com.wellsfargo.training.lms.model;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


import jakarta.persistence.*;
import lombok.NonNull;

@Entity
@Table(name="employee_issue_details")
public class ItemCard {
	@Id   // Primary key /unique
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="issue_id")
	private Long issue_id;
	
	@NonNull
	@Column(name="employee_id")
	private Long employee_id;
	
	@NonNull
	@Column(name="item_id")
	private Long item_id;

	@NonNull
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="issue_date")
	private Date issue_date;
	
	@NonNull
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="return_date")
	private Date return_date;
	
	@NonNull
	@Column(name="issued?")
	private Boolean issued;
	
	@ManyToOne
	@JoinColumn(name="e_id")
	private Employee employee;

	public ItemCard() {
		
	}

	public Long getIssue_id() {
		return issue_id;
	}

	public void setIssue_id(Long issue_id) {
		this.issue_id = issue_id;
	}

	public Long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}

	public Long getItem_id() {
		return item_id;
	}

	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}

	public Date getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}

	public Date getReturn_date() {
		return return_date;
	}

	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}
	
	
	public Boolean getIssued() {
		return issued;
	}

	public void setIssued(Boolean issued) {
		this.issued = issued;
	}

	public ItemCard(Long issue_id, @NonNull Long employee_id, @NonNull Long item_id,
			@NonNull Date issue_date, @NonNull Date return_date, @NonNull Boolean issued) {
		this.issue_id = issue_id;
		this.employee_id = employee_id;
		this.item_id = item_id;
		this.issue_date = issue_date;
		this.return_date = return_date;
		this.issued = issued;
	}	
}

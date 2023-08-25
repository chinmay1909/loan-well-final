package com.wellsfargo.training.lms.model;

// -------------------------------------- IMPORTING NECESSARY LIBRARIES ---------------------------------//

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.NonNull;

// -------------------------- CLASS TO REPRESENT DATA MEMBERS AND FUNCTIONS OF ITEMCARD --------------- //
@Entity
@Table(name="employee_issue_details")
public class ItemCard {
	@Id   // Primary key /unique
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="issue_id")
	private Long issue_id;
	
	@NonNull
	private Long employee_id;
	@NonNull
	private Long item_id;
	@NonNull
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date issue_date;
	@NonNull
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date return_date;
	private int issued;
	
	// Foreign key connecting employee table
	@ManyToOne
	@JoinColumn(name="e_id")
	private Employee employee;

	// Empty constructor
	public ItemCard() {
		
	}
	
	// Parameterized constructor
	public ItemCard(Long issue_id, @NonNull Long employee_id, @NonNull Long item_id,
			@NonNull Date issue_date, @NonNull Date return_date, int issued) {
		this.issue_id = issue_id;
		this.employee_id = employee_id;
		this.item_id = item_id;
		this.issue_date = issue_date;
		this.return_date = return_date;
		this.issued = issued;
	}	
	
	// Getters and setters
	
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
	public int getIssued() {
		return issued;
	}
	public void setIssued(int issued) {
		this.issued = issued;
	}
	
}

//------------------------------------------- END OF ITEMCARD MODEL --------------------------------------//
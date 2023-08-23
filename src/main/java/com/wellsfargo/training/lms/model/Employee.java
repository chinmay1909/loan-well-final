package com.wellsfargo.training.lms.model;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;


import jakarta.persistence.*;
import lombok.NonNull;

/*
 * Model class for Registration of a Employee
 */
@Entity
@Table(name="employee_master")
public class Employee {
	@Id   // Primary key /unique
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="employee_id")
	private Long employee_id;

	@NonNull
	@Column(name="employee_name")
	private String employee_name;

	
	@Column(name="designation")
	private String designation;
	
	@Column(name="department")
	private String department;
	
	@Column(name="gender")
	private char gender;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dob;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date doj;
	
	private String password;
	
	@OneToMany(mappedBy="employee", cascade=CascadeType.ALL)
	@Column(name = "myLoanCards")
	private List<LoanCard> myLoanCards;
	
	public List<LoanCard> getMyLoanCards()
	{
		return myLoanCards;
	}
	
	@OneToMany(mappedBy="employee", cascade=CascadeType.ALL)
	@Column(name = "myItemCards")
	private List<ItemCard> myItemCards;
	
	
	public List<ItemCard> getMyItemCards()
	{
		return myItemCards;
	}
	
	
//	private Address address;
//
//	public void setMyLoanIds(List<Long> myLoanIds) {
//		this.myLoanIds = myLoanIds;
//	}
	
	
	
	public void setMyLoanCards(List<LoanCard> myLoanCards) {
		this.myLoanCards = myLoanCards;
	}


	public void setMyItemCards(List<ItemCard> myItemCards) {
		this.myItemCards = myItemCards;
	}


	public Employee() {
		
	}
	
	
	
	public Employee(Long employee_id, @NonNull String employee_name, String designation,
			String department, char gender, Date dob, Date doj, String password)
	{
		this.employee_id = employee_id;
		this.employee_name = employee_name;
		this.designation = designation;
		this.department = department;
		this.gender = gender;
		this.dob = dob;
		this.doj = doj;
		this.password = password;
		this.myLoanCards = new LinkedList<LoanCard>();
		this.myItemCards = new LinkedList<ItemCard>();
	}
	
	public Long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDoj() {
		return doj;
	}
	
	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		Base64.Encoder encoder = Base64.getEncoder();  
        String normalString = password;
        String encodedString = encoder.encodeToString(   // encrypt password in database field
        normalString.getBytes(StandardCharsets.UTF_8) );
        this.password = encodedString;
	}
	
	
	
}

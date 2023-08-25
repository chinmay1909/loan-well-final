package com.wellsfargo.training.lms.model;

//------------------------------ IMPORTING NECESSARY LIBRARIES --------------------------------//

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Base64;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.NonNull;

//--------------- CLASS TO REPRESENT THE DATA MEMBERS AND FUNCTIONS OF AN EMPLOYEE ------------//

@Entity
@Table(name="employee_master")
public class Employee {
	
	// Primary key of the Employee table
	@Id   // Primary key /unique
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="employee_id")
	private Long employee_id;

	// Other data members of the Employee class
	@NonNull
	private String employee_name;
	private String designation;
	private String department;
	private char gender;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dob;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date doj;
	private String password;
	
	// Empty constructor
	public Employee() {
		
	}
	
	// Parameterized constructor
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
	}
	
	// Getters and setters
	
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

//---------------------------- END OF CLASS EMPLOYEE ---------------------------------------//
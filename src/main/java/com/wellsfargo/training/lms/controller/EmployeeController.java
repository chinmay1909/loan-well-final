package com.wellsfargo.training.lms.controller;

//--------------------------- IMPORTING  NECESSARY LIBRARIES ----------------------------//

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wellsfargo.training.lms.exception.ResourceNotFoundException;
import com.wellsfargo.training.lms.model.ApplyLoan;
import com.wellsfargo.training.lms.model.Employee;
import com.wellsfargo.training.lms.model.SanctionLoan;
import com.wellsfargo.training.lms.model.ViewItem;
import com.wellsfargo.training.lms.model.ViewLoan;
import com.wellsfargo.training.lms.service.EmployeeService;

//------------------------- CONTROLLER LAYER CLASS FOR EMPLOYEE -------------------------//

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value="/api")
public class EmployeeController {
	
	// Service instances
	@Autowired
	private EmployeeService eservice;
	
	//------- ADMIN RELATED FUNCTIONS -------//
	
	// Controller function to get a list of all employees in the table
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return eservice.listAllEmployees();  
	}

	// Controller function to get an employee by its primary key id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") Long eid)
	throws ResourceNotFoundException {
		Employee e = eservice.getSingleEmployee(eid).
			orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id: " + eid));
		return ResponseEntity.ok().body(e);
	}	

	// Controller function to delete an employee from the employee table
	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteItem(@PathVariable(value="id") Long eid)
	throws ResourceNotFoundException {
		eservice.getSingleEmployee(eid).
				orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id: " + eid));
		eservice.deleteEmployee(eid);
				
		Map<String,Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return response;
	}
	
	// Controller function to update an existing employee using its id
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") Long eid, @Validated @RequestBody Employee e)
	throws ResourceNotFoundException {
		Employee employee = eservice.getSingleEmployee(eid).
			orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id: " + eid));
		employee.setEmployee_name(e.getEmployee_name());
		employee.setDesignation(e.getDesignation());
		employee.setDepartment(e.getDepartment());
		employee.setGender(e.getGender());
		employee.setDob(e.getDob());
		employee.setDoj(e.getDoj());
		employee.setPassword(e.getPassword());
		
		final Employee updatedEmployee = eservice.saveEmployee(employee);
		return ResponseEntity.ok().body(updatedEmployee);
	}
	
	// Controller function to sanction a loan applied for by the employee
	@PutMapping("/employees/{id}/sanctioned")
	public String sanctionLoan(@PathVariable(value="id") Long eid,  @RequestBody SanctionLoan l) throws ResourceNotFoundException {
		return eservice.sanctionLoan(eid, l.getLc_issue_id(), l.getIssue_id());
	}
	
	// Controller function for admin to say that the employee has paid back the loan
	@PutMapping("/employees/{id}/paidback")
	public String paidBackLoan(@PathVariable(value="id") Long eid, @Validated @RequestBody SanctionLoan s) throws ResourceNotFoundException {
		return eservice.paidBackLoan(eid, s.getLc_issue_id(), s.getIssue_id());
	}
	
	// Controller function for admin to declare that the due date is over
	@PutMapping("/employees/{id}/overdue")
	public String declareOverdue(@PathVariable(value="id") Long eid, @Validated @RequestBody SanctionLoan s) throws ResourceNotFoundException {
		return eservice.declareOverdue(eid, s.getLc_issue_id(), s.getIssue_id());
	}
	
	// Controller function for admin to declare that the due date is over
	@PutMapping("/employees/{id}/rejected")
	public String rejectLoan(@PathVariable(value="id") Long eid, @Validated @RequestBody SanctionLoan s) throws ResourceNotFoundException {
		return eservice.rejectLoan(eid, s.getLc_issue_id(), s.getIssue_id());
	}
	
	// Controller function to create an employee in the employee table
	@PostMapping("/register")
	public ResponseEntity<String> createEmployee(@Validated @RequestBody Employee employee) {
		Employee registeredEmployee = eservice.registerEmployee(employee);
	     if (registeredEmployee!= null) {
	    	 return ResponseEntity.ok("Registration successful");
	     }
	     else {
	         return ResponseEntity.badRequest().body("Registration failed");
	     }
	}
	
	//------- EMPLOYEE RELATED FUNCTIONS -------//
	
	// Controller function for Employee to login to the system
	@PostMapping("/login")
	public Boolean loginEmployee(@Validated @RequestBody Employee employee) throws ResourceNotFoundException {
		Boolean a=false;
		Long id =employee.getEmployee_id();
		String password=employee.getPassword();
	
		Employee e = eservice.loginEmployee(id).orElseThrow(() ->
		new ResourceNotFoundException("Employee not found for this id :: "));
		
		if(id.equals(e.getEmployee_id()) && password.equals(e.getPassword())) {
			a=true;
		}
		return a;
	}
		
	// Controller function for Employee to be able to apply for a particular loan
	@PostMapping("/employees/{id}/applyloan")
	public String applyLoan(@PathVariable(value="id")Long employee_id, @Validated @RequestBody ApplyLoan a) {
		System.out.println(a.getItem_category() + a.getDuration_in_years());
		eservice.applyForLoan(employee_id, a.getItem_category(),a.getItem_make(), a.getItem_description(), a.getItem_valuation(), a.getDuration_in_years());
		return new String("Loan Application Successful");
	}
		
	// Controller function for Employee to be able to view his loans
	// Also applicable if admin wants to view the loans of that employee
	@GetMapping("/employees/{id}/viewmyloans")
	public List<ViewLoan> viewMyLoans(@PathVariable(value="id") Long employee_id) {
		return eservice.viewMyLoans(employee_id);
	}
		
	// Controller function for Employee to be able to view his items
	// Also applicable if admin wants to view the items of that employee
	@GetMapping("/employees/{id}/viewmyitems")
	public List<ViewItem> viewMyItems(@PathVariable(value="id") Long employee_id) {
		return eservice.viewMyItems(employee_id);
	}

}

//--------------------------- END OF EMPLOYEE CONTROLLER CLASS ---------------------------//
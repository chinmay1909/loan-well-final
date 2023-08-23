package com.wellsfargo.training.lms.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wellsfargo.training.lms.exception.ResourceNotFoundException;
import com.wellsfargo.training.lms.model.Employee;
import com.wellsfargo.training.lms.model.ViewItem;
import com.wellsfargo.training.lms.model.ViewLoan;
import com.wellsfargo.training.lms.service.EmployeeService;
import com.wellsfargo.training.lms.service.ItemCardService;
import com.wellsfargo.training.lms.service.LoanCardService;


@RestController
@RequestMapping(value="/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeService eservice;
	@Autowired
	private LoanCardService lcservice;
	@Autowired
	private ItemCardService icservice;
	
	/* ResponseEntity represents an HTTP response, including headers, body, and status. */
	
	//Open PostMan, make a GET Request - http://localhost:8085/lms/api/employees/
		@GetMapping("/employees")
		public List<Employee> getAllEmployees() {
			return eservice.listAllEmployees();  
		}
	
	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteItem(@PathVariable(value="id") Long eid)
	throws ResourceNotFoundException
	{
		eservice.getSingleEmployee(eid).
				orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id: " + eid));
		eservice.deleteEmployee(eid);
				
		Map<String,Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return response;
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") Long eid, @Validated @RequestBody Employee e)
	throws ResourceNotFoundException
	{
		Employee employee = eservice.getSingleEmployee(eid).
			orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id: " + eid));
		employee.setEmployee_name(e.getEmployee_name());
		employee.setDesignation(e.getDesignation());
		employee.setDepartment(e.getDepartment());
		employee.setGender(e.getGender());
		employee.setDob(e.getDob());
		employee.setDoj(e.getDoj());
		employee.setPassword(e.getPassword());
		employee.setMyLoanCards(e.getMyLoanCards());
		employee.setMyItemCards(e.getMyItemCards());
		
		final Employee updatedEmployee = eservice.saveEmployee(employee);
		return ResponseEntity.ok().body(updatedEmployee);
	}
	
	@PutMapping("/employees/{id}/sanctioned")
	public ResponseEntity<Employee> sanctionLoan(@PathVariable(value="id") Long eid, Long lc_issue_id, Long issue_id) throws ResourceNotFoundException
	{
		Employee e = eservice.sanctionLoan(eid, lcservice.getSingleLoanCard(lc_issue_id).get(), icservice.getSingleItemCard(issue_id).get()).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id: "+ eid));
		
		return ResponseEntity.ok().body(e);
	}
	
	@PutMapping("/employees/{id}/paidback")
	public ResponseEntity<Employee> paidBackLoan(@PathVariable(value="id") Long eid, Long lc_issue_id, Long issue_id) throws ResourceNotFoundException
	{
		Employee e = eservice.paidBackLoan(eid, lcservice.getSingleLoanCard(lc_issue_id).get(), icservice.getSingleItemCard(issue_id).get()).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id: "+ eid));;
		return ResponseEntity.ok().body(e);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> createEmployee(@Validated @RequestBody Employee employee) {
//		
//		Address address = user.getAddress();
//		
//		 // Establish the bi-directional relationship
//        address.setUser(user);
//        user.setAddress(address);
				
		Employee registeredEmployee = eservice.registerEmployee(employee);
	        if (registeredEmployee!= null) {
	            return ResponseEntity.ok("Registration successful");
	        } else {
	            return ResponseEntity.badRequest().body("Registration failed");
	        }
	}
	
	//Open Postman and make POST request - http://localhost:8085/lms/api/login
		@PostMapping("/login")
		public Boolean loginEmployee(@Validated @RequestBody Employee employee) throws ResourceNotFoundException
		{
			Boolean a=false;
			Long id =employee.getEmployee_id();
			String password=employee.getPassword();
		
			Employee e = eservice.loginEmployee(id).orElseThrow(() ->
			new ResourceNotFoundException("Employee not found for this id :: "));
			
			if(id.equals(e.getEmployee_id()) && password.equals(e.getPassword()))
			{
				a=true;

			}
			return a;
		}
		
	// http://localhost:8085/lms/api/applyloan (POST)
		@PostMapping("/applyloan")
		public void applyLoan(Long employee_id, String item_category, String item_make, String item_description, int item_valuation, int duration_in_year)
		{
			eservice.applyForLoan(employee_id, item_category, item_make, item_description, item_valuation, duration_in_year);
		}
		
	// http://localhost:8085/lms/api/viewmyloans (GET)
		@GetMapping("/viewmyloans")
		public List<ViewLoan> viewMyLoans(Long employee_id)
		{
			return eservice.viewMyLoans(employee_id);
		}
		
	// http://localhost:8085/lms/api/viewmyitems (GET)
		@GetMapping("/viewmyitems")
		public List<ViewItem> viewMyItems(Long employee_id)
		{
			return eservice.viewMyItems(employee_id);
		}

}

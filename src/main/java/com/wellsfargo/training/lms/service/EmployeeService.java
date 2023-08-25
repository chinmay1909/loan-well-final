package com.wellsfargo.training.lms.service;

//----------------------------- IMPORTING NECESSARY LIBRARIES ----------------------------------//

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wellsfargo.training.lms.model.Employee;
import com.wellsfargo.training.lms.model.LoanCard;
import com.wellsfargo.training.lms.model.ViewItem;
import com.wellsfargo.training.lms.model.ViewLoan;
import com.wellsfargo.training.lms.model.ItemCard;
import com.wellsfargo.training.lms.repository.EmployeeRepository;
import com.wellsfargo.training.lms.repository.ItemCardRepository;
import com.wellsfargo.training.lms.repository.ItemRepository;
import com.wellsfargo.training.lms.repository.LoanCardRepository;
import com.wellsfargo.training.lms.repository.LoanRepository;

//--------------------------- SERVICE LAYER CLASS FOR EMPLOYEE --------------------------------//

@Service
public class EmployeeService {
	
	// Repository instances
	@Autowired
	private EmployeeRepository erepo;
	@Autowired
	private LoanRepository lrepo;
	@Autowired
	private ItemCardRepository icrepo;
	@Autowired
	private LoanCardRepository lcrepo;
	@Autowired
	private ItemRepository irepo;
	
	//-------- EMPLOYEEE RELATED SERVICE METHODS --------// 
	
	// Service method to login an existing employee
	public Optional<Employee> loginEmployee(long employee_id) {
		return erepo.findById(employee_id); 
	}
	
	// Service method to view all loans of an employee
	public List<ViewLoan> viewMyLoans(long employee_id) {
		return erepo.viewLoans(employee_id);
	}
	
	// Service method to let the employee apply for a new loan
	public void applyForLoan(Long employee_id, String item_category, String item_make, String item_description, int item_valuation, int duration_in_years) {
		
		// Getting current date
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		
		/* Making new loan card and setting issued to 0 and paidback to 0
		When admin sanctions loan, issued becomes 1, and if rejected then -1
		When admin confirms that user paid back the money, he sets paidback to 1
		Else if due date is over and user did not pay back yet, paidback becomes -1
		If loan card is rejected by admin then paidback becomes -2  */
		
		LoanCard lCard = new LoanCard();
		lCard.setEmployee_id(employee_id);
		lCard.setLoan_id(lrepo.getLoanIdFromTable(item_category, duration_in_years));
		lCard.setCard_issue_date(date);
		lCard.setIssued(0);
		lCard.setPaidback(0);
		
		// Making new item card and setting issued to 0
		// When admin sanctions loan, issued becomes 1, and if rejected then -1
		
		ItemCard iCard = new ItemCard();
		iCard.setEmployee_id(employee_id);
		iCard.setItem_id(irepo.getItemIdFromTable(item_description, item_category, item_make, item_valuation));
		iCard.setIssue_date(date);
		iCard.setIssued(0);
		
		// Setting return date = current date + tenure of loan
		LocalDate x = LocalDate.now().plusYears(duration_in_years);
		iCard.setReturn_date(Date.valueOf(x));
		
		// Saving the loan card and item card in the database 
		lcrepo.save(lCard);
		icrepo.save(iCard);
	}

	// Service method to let the employee view the items he purchased
	public List<ViewItem> viewMyItems(long employee_id)	{
		return erepo.viewItems(employee_id);
	}
	
	
	//-------- ADMIN RELATED SERVICE METHODS ------------//
	
	// Function to save an employee in the employee table
	public Employee saveEmployee(Employee e) {
		return erepo.save(e);  
	}
	
	// Service method to register a new employee
	public Employee registerEmployee(Employee e) {
		return erepo.save(e);
	}
	
	// Service method to list all the existing employees
	public List<Employee> listAllEmployees()
	{	
 		return erepo.findAll(); //Define in JPA repo.
 	}
	
	// Service method to get a single employee by his id
	public Optional<Employee> getSingleEmployee(long id)
	{
		 return erepo.findById(id);
	}
	
	// Service method for admin to be able to create an employee
	public Employee addNewEmployee(Employee e)
	{
		return registerEmployee(e);
	}
	
	// Service method for admin to be able to delete an employee
	public void deleteEmployee(long id)
	{
		erepo.deleteById(id);
	}
	
	// Service method for sanctioning a Loan applied for by an employee
	// function takes employee_id, issue_id of itemcard and lc_issue_id of loancard as parameters
	public String sanctionLoan(long employee_id, Long lc_issue_id, Long issue_id) {
		
		// setting the issued variable to 1 in the cards
		LoanCard lc = lcrepo.findById(lc_issue_id).get();
		lc.setIssued(1);
		ItemCard ic = icrepo.findById(issue_id).get();
		ic.setIssued(1);
		
		// saving the updated cards back in the table
		lcrepo.save(lc);
		icrepo.save(ic);
		
		return new String("Loan Sanctioned");
	}
	
	// Service method for rejecting a loan applied for by an employee
	public String rejectLoan(long employee_id, Long lc_issue_id, Long issue_id) {
		
		// setting paidback as -2, issued as -1
		lcrepo.findById(lc_issue_id).get().setPaidback(-2);
		lcrepo.findById(lc_issue_id).get().setIssued(-1);
		icrepo.findById(issue_id).get().setIssued(-1);
		lcrepo.save(lcrepo.findById(lc_issue_id).get());
		icrepo.save(icrepo.findById(issue_id).get());
		return new String("Loan Rejected");
	}
	
	// Service method for admin to confirm that employee has paid back loan
	public String paidBackLoan(long employee_id, Long lc_issue_id, Long issue_id) {
		
		// setting the paidback variable to 1
		lcrepo.findById(lc_issue_id).get().setPaidback(1);
		lcrepo.save(lcrepo.findById(lc_issue_id).get());
		return new String("Employee has paid back the loan.");
	}

	// Service method for declaring a loan taken by an employee as overdue
	public String declareOverdue(long employee_id, Long lc_issue_id, Long issue_id) {
		// Getting current date
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		int paidback = lcrepo.findById(lc_issue_id).get().getPaidback();
		Date returndate = icrepo.findById(issue_id).get().getReturn_date();
		
		// if current date is more than the return date then declare overdue
		if(date.after(returndate)) {
			if(paidback == 0) lcrepo.findById(lc_issue_id).get().setPaidback(-1);
			lcrepo.save(lcrepo.findById(lc_issue_id).get());
			return new String("Due date is over.");
		}
		else return new String("Due date is not over yet.");
	}
	
}

//------------------------------------- END OF EMPLOYEE SERVICE CLASS ------------------------//
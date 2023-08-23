package com.wellsfargo.training.lms.service;
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

@Service
public class EmployeeService {
	
	
	// ------------------------ UTILITY INSTANCES AND FUNCTIONS ------------ //
	// Creating instances of the repository classes
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
	
	public Employee saveEmployee(Employee e) {
		return erepo.save(e);   // Invokes save() method predefined in JPA repo
	}

	// Service method to assign a new loan card to an employee when he takes loan
	public void addLoanCard(long employee_id, LoanCard lCard)
	{
		erepo.findById(employee_id).get().getMyLoanCards().add(lCard);
	}
	
	// Service method to view all loan cards of an employee
	public List<LoanCard> getMyLoanCards(long employee_id)
	{
		return erepo.findById(employee_id).get().getMyLoanCards();
	}
		
	// Service method to remove a loan card from the employee
	public void removeLoanCard(long employee_id, LoanCard eCard)
	{
		Employee e = erepo.findById(employee_id).get();
		for(int i = 0; i < e.getMyLoanCards().size(); i++)
		{
			if(e.getMyLoanCards().get(i).getLc_issue_id().longValue() == eCard.getLc_issue_id().longValue())
			{
				e.getMyLoanCards().remove(i);
				break;
			}
		}
	}
		
	// Service method to check how many loan cards the employee has
	public int getNumberOfLoanCards(long employee_id)
	{
		return erepo.findById(employee_id).get().getMyLoanCards().size();
	}	
	
	// Service method to return all item cards of an employee
	public List<ItemCard> getMyItemCards(long employee_id)
	{
		return erepo.findById(employee_id).get().getMyItemCards();
	}
	
	// Service method to assign a new item card to an employee when he buys the item 
	public void addItemCard(long employee_id, ItemCard itemCard)
	{
		erepo.findById(employee_id).get().getMyItemCards().add(itemCard);
	}
	
	// Service method to remove an item card from the user
	public void removeItemCard(long employee_id, ItemCard itemCard)
	{
		Employee e = erepo.findById(employee_id).get();
		for(int i = 0; i < e.getMyItemCards().size(); i++)
		{
			if(e.getMyItemCards().get(i).getIssue_id().longValue() == itemCard.getIssue_id().longValue())
			{
				e.getMyItemCards().remove(i);
				break;
			}
		}
	}
	
	// Service method to return the number of item cards the employee has
	public int getNumberOfItemCards(long employee_id)
	{
		return erepo.findById(employee_id).get().getMyItemCards().size();
	}
	
	
	// ------------------------ EMPLOYEE LOGIN AND REGISTER ---------------- //
	
	
	// Service method to register a new employee
	public Employee registerEmployee(Employee e) {
		return erepo.save(e);
	}
	
	// Service method to login an existing employee
	public Optional<Employee> loginEmployee(long employee_id) {

		return erepo.findById(employee_id); 
	}
	
	
	// -------------------------- EMPLOYEE DASHBOARD ---------------------- //
	
	
	// Service method to view all loans of an employee
	public List<ViewLoan> viewMyLoans(long employee_id)
	{
		return erepo.viewLoans(employee_id);
	}
	
	// Service method to let the employee apply for a new loan
	public void applyForLoan(long employee_id, String item_category, String item_make, String item_description, int item_valuation, int duration_in_years)
	{
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		LoanCard lCard = new LoanCard();
		lCard.setEmployee_id(employee_id); 
		lCard.setLoan_id(lrepo.getLoanIdFromTable(item_category, duration_in_years));
		lCard.setCard_issue_date(date);
		lCard.setIssued(false);
		ItemCard iCard = new ItemCard();
		iCard.setEmployee_id(employee_id);
		iCard.setItem_id(irepo.getItemIdFromItemDetails(item_category, item_make, item_description, item_valuation));
		iCard.setIssue_date(date);
		iCard.setIssued(false);
		LocalDate x = LocalDate.now().plusYears(duration_in_years);
		iCard.setReturn_date(Date.valueOf(x));
		lcrepo.save(lCard);
		icrepo.save(iCard);
		addLoanCard(employee_id, lCard);
		addItemCard(employee_id, iCard);
	}

	// Service method to let the employee view the items he purchased
	public List<ViewItem> viewMyItems(long employee_id)
	{
		return erepo.viewItems(employee_id);
	}
	
	
	// ----------------------------- ADMIN DASHBOARD ------------------------- //
	
	
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
	
	// Service method for approving a Loan by Admin
	public Optional<Employee> sanctionLoan(long employee_id, LoanCard lCard, ItemCard iCard)
	{
		// setting the issued boolean to true in the cards
		lcrepo.findById(lCard.getLc_issue_id()).get().setIssued(true);
		icrepo.findById(iCard.getIssue_id()).get().setIssued(true);
		return erepo.findById(employee_id);
	}
	
	// Service method for admin to confirm that employee has paid back loan
	public Optional<Employee> paidBackLoan(long employee_id, LoanCard lCard, ItemCard iCard)
	{
		removeLoanCard(employee_id, lCard);
		removeItemCard(employee_id, iCard);
		if(lcrepo.findById(lCard.getLc_issue_id()) != null)
			lcrepo.deleteById(lCard.getLc_issue_id());
		if(icrepo.findById(iCard.getIssue_id()) != null)
			icrepo.deleteById(iCard.getIssue_id());
		return erepo.findById(employee_id);
	}

	// No need of service method for admin to be able to edit employee details
	// Updating employee details is done in EmployeeController file
	
	// Service method for admin to be able to create new loans in Loan table
	// Written in LoanService file
}

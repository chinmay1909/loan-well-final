package com.wellsfargo.training.lms.repository;

//-------------------------------------- IMPORTING NECESSARY LIBRARIES ---------------------------------//

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.wellsfargo.training.lms.model.Employee;
import com.wellsfargo.training.lms.model.ViewItem;
import com.wellsfargo.training.lms.model.ViewLoan;

//-------------------------------------- INTERFACE FOR EMPLOYEE REPO -----------------------------------//

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	// Query to enable the user to view the loans he has taken
	@Query("SELECT new com.wellsfargo.training.lms.model.ViewLoan" 
			+ "(lc.loan_id, l.loan_type, l.duration_in_years, lc.card_issue_date, lc.issued, lc.paidback)"
			+ " FROM Loan l JOIN LoanCard lc ON l.loan_id = lc.loan_id WHERE lc.employee_id = :employee_id")
	public List<ViewLoan> viewLoans(Long employee_id);
	
	// Query to enable the user to view the items he has taken
	@Query("SELECT new com.wellsfargo.training.lms.model.ViewItem" 
			+ "(ic.issue_id, i.item_description, i.item_make, i.item_category, i.item_valuation, ic.issued)"
			+ " FROM Item i JOIN ItemCard ic ON i.item_id = ic.item_id WHERE ic.employee_id = :employee_id")
	public List<ViewItem> viewItems(Long employee_id);
	
}

//----------------------------------------- END OF EMPLOYEE REPO --------------------------------------//
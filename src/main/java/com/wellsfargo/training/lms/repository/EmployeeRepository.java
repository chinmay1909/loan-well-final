package com.wellsfargo.training.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wellsfargo.training.lms.model.Employee;
import com.wellsfargo.training.lms.model.ViewItem;
import com.wellsfargo.training.lms.model.ViewLoan;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	@Query("SELECT new com.wellsfargo.training.lms.model.ViewLoan" 
			+ "(lc.loan_id, l.loan_type, l.duration_in_years, lc.card_issue_date)"
			+ " FROM Loan l JOIN LoanCard lc ON l.loan_id = lc.loan_id WHERE lc.employee_id = :employee_id")
	public List<ViewLoan> viewLoans(Long employee_id);
	
	@Query("SELECT new com.wellsfargo.training.lms.model.ViewLoan" 
			+ "(ic.issue_id, i.item_description, i.item_make, i.item_category, i.item_valuation)"
			+ " FROM Item i JOIN ItemCard ic ON i.item_id = ic.item_id WHERE ic.employee_id = :employee_id")
	public List<ViewItem> viewItems(Long employee_id);
	
}
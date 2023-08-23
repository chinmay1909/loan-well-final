package com.wellsfargo.training.lms.model;
import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.NonNull;

@Entity
@Table(name="employee_card_details")
public class LoanCard {

	@Id   // Primary key /unique
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long lc_issue_id;
	
	@NonNull
	@Column(name="employee_id")
	private Long employee_id;

	@NonNull
	@Column(name="loan_id")
	private Long loan_id;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date card_issue_date;
	
	@NonNull
	@Column(name="issued")
	private Boolean issued;

	@ManyToOne
	@JoinColumn(name="e_id")
	private Employee employee;
	
	public LoanCard() {
		
	}
	
	public Long getLc_issue_id() {
		return lc_issue_id;
	}

	public void setLc_issue_id(Long lc_issue_id) {
		this.lc_issue_id = lc_issue_id;
	}

	public Long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}
	
	public Boolean getIssued() {
		return issued;
	}

	public void setIssued(Boolean issued) {
		this.issued = issued;
	}
	
	public LoanCard(Long lc_issue_id, @NonNull Long employee_id, @NonNull Long loan_id, Date card_issue_date, @NonNull Boolean issued) {
		this.lc_issue_id = lc_issue_id;
		this.employee_id = employee_id;
		this.loan_id = loan_id;
		this.card_issue_date = card_issue_date;
		this.issued = issued;
	}

	public Long getLoan_id() {
		return loan_id;
	}

	public void setLoan_id(Long loan_id) {
		this.loan_id = loan_id;
	}

	public Date getCard_issue_date() {
		return card_issue_date;
	}

	public void setCard_issue_date(Date card_issue_date) {
		this.card_issue_date = card_issue_date;
	}
	
//	public void addEmployeeId(Long employee_id)
//	{
//		this.employee_ids.add(employee_id);
//	}
//	
//	public void removeEmployeeId(Long employee_id)
//	{
//		for(int i = 0; i < employee_ids.size(); i++)
//		{
//			if(employee_ids.get(i).longValue() == employee_id.longValue())
//			{
//				this.employee_ids.remove(i);
//				break;
//			}
//		}
//	}
//	
//	public int noOfApplicants()
//	{
//		return this.employee_ids.size();
//	}
//	
//	public int noOfDistinctApplicants()
//	{
//		HashSet<Long> S = new HashSet<>();
//		for (int i = 0; i < employee_ids.size(); i++)
//		{
//			S.add(this.employee_ids.get(i).longValue());
//		}
//
//		return S.size();
//	}

}

package com.wellsfargo.training.lms.model;

//--------------------------- MODEL CLASS FOR ADMIN TO SANCTION A LOAN ------------------------//

public class SanctionLoan {
	
	// Data members
	Long eid;
	Long lc_issue_id;
	Long issue_id;
	
	// Parameterized constructor
	public SanctionLoan(Long eid, Long lc_issue_id, Long issue_id) {
		this.eid = eid;
		this.lc_issue_id = lc_issue_id;
		this.issue_id = issue_id;
	}
	
	// Empty constructor
	public SanctionLoan() {
		// TODO Auto-generated constructor stub
	}
	
	// Getters and setters
	
	public Long getEid() {
		return eid;
	}
	public void setEid(Long eid) {
		this.eid = eid;
	}
	public Long getLc_issue_id() {
		return lc_issue_id;
	}
	public void setLc_issue_id(Long lc_issue_id) {
		this.lc_issue_id = lc_issue_id;
	}
	public Long getIssue_id() {
		return issue_id;
	}
	public void setIssue_id(Long issue_id) {
		this.issue_id = issue_id;
	}
	
}

// ----------------------------------- END OF SANCTIONLOAN CLASS ---------------------------------//
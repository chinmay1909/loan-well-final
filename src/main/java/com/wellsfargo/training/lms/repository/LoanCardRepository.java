package com.wellsfargo.training.lms.repository;

//-------------------------------------- IMPORTING NECESSARY LIBRARIES ---------------------------------//

import org.springframework.data.jpa.repository.JpaRepository;
import com.wellsfargo.training.lms.model.LoanCard;

//-------------------------------------- INTERFACE FOR LOANCARD REPO -----------------------------------//

public interface LoanCardRepository extends JpaRepository<LoanCard, Long> {
	
}

//------------------------------------------ END OF LOANCARD REPO --------------------------------------//
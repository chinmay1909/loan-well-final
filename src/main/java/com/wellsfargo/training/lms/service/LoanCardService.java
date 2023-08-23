package com.wellsfargo.training.lms.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wellsfargo.training.lms.model.LoanCard;
import com.wellsfargo.training.lms.repository.LoanCardRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LoanCardService {
	
	@Autowired
	private LoanCardRepository lcrepo;
	
	public LoanCard saveLoanCard(LoanCard lc) {
		return lcrepo.save(lc);   // Invokes save() method predefined in JPA repo
	}
	public List<LoanCard> listAll(){
 		
 		return lcrepo.findAll(); //Define in JPA repo.
 	}
	public Optional<LoanCard> getSingleLoanCard(long id){
		return lcrepo.findById(id);
	}
	public void deleteLoanCard(long id){
		lcrepo.deleteById(id);
	}
}

package com.wellsfargo.training.lms.repository;

//-------------------------------------- IMPORTING NECESSARY LIBRARIES ---------------------------------//

import org.springframework.data.jpa.repository.JpaRepository;
import com.wellsfargo.training.lms.model.ItemCard;

//-------------------------------------- INTERFACE FOR ITEMCARD REPO -----------------------------------//

public interface ItemCardRepository extends JpaRepository<ItemCard, Long> {
	
}

//----------------------------------------- END OF ITEMCARD REPO ---------------------------------------//

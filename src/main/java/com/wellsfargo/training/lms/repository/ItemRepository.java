package com.wellsfargo.training.lms.repository;

//-------------------------------------- IMPORTING NECESSARY LIBRARIES ---------------------------------//

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.wellsfargo.training.lms.model.Item;

//--------------------------------------- INTERFACE FOR ITEM REPO --------------------------------------//

public interface ItemRepository extends JpaRepository<Item, Long> {
	
	// Function to get item id from other details in the item table
	@Query("SELECT item_id FROM Item WHERE item_description = :idesc AND item_category=:icat AND item_make = :imake AND item_valuation = :ival")
	Long getItemIdFromTable(@Param("idesc") String idesc, @Param("icat") String icat,@Param("imake") String imake,@Param("ival") int ival);
	
}

//------------------------------------------- END OF ITEM REPO -----------------------------------------//
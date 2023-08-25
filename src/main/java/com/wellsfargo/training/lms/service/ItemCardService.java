package com.wellsfargo.training.lms.service;

//----------------------------- IMPORTING NECESSARY LIBRARIES ----------------------------------//

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wellsfargo.training.lms.model.ItemCard;
import com.wellsfargo.training.lms.repository.ItemCardRepository;
import jakarta.transaction.Transactional;

//-------------------------- SERVICE LAYER CLASS FOR ITEM CARD ---------------------------------//

@Service
@Transactional
public class ItemCardService {
	
	// Repository instance
	@Autowired
	private ItemCardRepository icrepo;
	
	// Function to save an item card
	public ItemCard saveItemCard(ItemCard i) {
		return icrepo.save(i);
	}
	
	// Function to list all the ItemCards available in the table
	public List<ItemCard> listAll() {	
 		return icrepo.findAll(); 
 	}
	
	// Function to get a single item card from the id of the card
	public Optional<ItemCard> getSingleItemCard(long id) {
		return icrepo.findById(id);
	}
	
	// Function to remove an item card by giving its id
	public void deleteItemCard(long id){
		icrepo.deleteById(id);
	}
}

//------------------------------------- END OF ITEMCARD SERVICE CLASS ------------------------//
package com.wellsfargo.training.lms.service;

//----------------------------- IMPORTING NECESSARY LIBRARIES ----------------------------------//

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wellsfargo.training.lms.model.Item;
import com.wellsfargo.training.lms.repository.ItemRepository;
import jakarta.transaction.Transactional;

//-------------------------- SERVICE LAYER CLASS FOR ITEM  -------------------------------------//

@Service
@Transactional
public class ItemService {
	
	// Repository instance
	@Autowired
	private ItemRepository irepo;
	
	// Function to save an item in the item table
	public Item saveItem(Item i) {
		return irepo.save(i);
	}
	
	// Function to list all the items in the item table
	public List<Item> listAll(){
 		return irepo.findAll();
 	}
	
	// Function to get one item from its primary key id
	public Optional<Item> getSingleItem(long id){
		return irepo.findById(id);
	}
	
	// Function to delete an item from the table by using its id
	public void deleteItem(long id){
		irepo.deleteById(id);
	}
	
	// Function to get ItemId from other details in item table
	public Long getItemIdFromTable(String icat, String imake, String idesc, int ival)
	{
		return irepo.getItemIdFromTable(icat, imake, idesc, ival);
	}
}

//---------------------------------------- END OF ITEM SERVICE CLASS -------------------------//
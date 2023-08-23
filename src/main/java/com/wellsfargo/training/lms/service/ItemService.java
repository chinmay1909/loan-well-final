package com.wellsfargo.training.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.lms.model.Item;
import com.wellsfargo.training.lms.model.Loan;
import com.wellsfargo.training.lms.repository.ItemRepository;
import com.wellsfargo.training.lms.repository.LoanRepository;

import jakarta.transaction.Transactional;

/*
 * A service layer is an additional layer in an MVC application that 
 * mediates communication between a controller and repository layer. 
 * The service layer contains business logic. 
 * In particular, it contains validation logic. */

/* @Service annotation allows developers to add business functionalities.
 *  @Transactional annotation allows to manage Database transactions efficiently */
@Service
@Transactional
public class ItemService {
	

	/*@Autowired - marks a constructor, field, or setter method to be autowired by Spring dependency injection. */
	@Autowired
	private ItemRepository irepo;
	
	public Item saveItem(Item i) {
		return irepo.save(i);   // Invokes save() method predefined in JPA repo
	}
	public List<Item> listAll(){
 		
 		return irepo.findAll(); //Define in JPA repo.
 	}
	public Optional<Item> getSingleItem(long id){
		return irepo.findById(id);
	}
	public void deleteItem(long id){
		irepo.deleteById(id);
	}
//	public List<Item> getAllItemsByUserId(long id)
//	{
//		return irepo.getAllItemsByUserId(id);
//	}
	public Long getItemIdFromItemDetails(String icat, String imake, String idesc, int ival)
	{
		return irepo.getItemIdFromItemDetails(icat, imake, idesc, ival);
	}
}

package com.wellsfargo.training.lms.controller;

//--------------------------- IMPORTING  NECESSARY LIBRARIES ----------------------------//

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wellsfargo.training.lms.exception.ResourceNotFoundException;
import com.wellsfargo.training.lms.model.Item;
import com.wellsfargo.training.lms.service.ItemService;

//--------------------------- CONTROLLER LAYER CLASS FOR ITEM ---------------------------//

@RestController
@RequestMapping(value="/api")
public class ItemController {

	// Service instance
	@Autowired
	private ItemService iservice;
	
	
	//Controller function to save an item in the item table
	@PostMapping("/items")
	public Item saveItem(@Validated @RequestBody Item item) {
		Item i=iservice.saveItem(item);
		return i;
	}
	
	// Controller function to get a list of all items in the item table
	@GetMapping("/items")
	public List<Item> getAllItems() {
		return iservice.listAll();
	}
	
	// Controller function to get an item from the table by its primary key id
	@GetMapping("/items/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable(value="id") Long iid)
	throws ResourceNotFoundException {
		Item i = iservice.getSingleItem(iid).
			orElseThrow(() -> new ResourceNotFoundException("Item not found for this id: " + iid));
		return ResponseEntity.ok().body(i);
	}
	
	// Controller function to update an existing item in the table using its id		
	@PutMapping("/items/{id}")
	public ResponseEntity<Item> updateItem(@PathVariable(value="id") Long iid, @Validated @RequestBody Item i)
	throws ResourceNotFoundException {
		Item item = iservice.getSingleItem(iid).
			orElseThrow(() -> new ResourceNotFoundException("Item not found for this id: " + iid));
		item.setItem_description(i.getItem_description());
		item.setItem_make(i.getItem_make());
		item.setItem_category(i.getItem_category());
		item.setItem_valuation(i.getItem_valuation());
		final Item updatedItem = iservice.saveItem(item);
		return ResponseEntity.ok().body(updatedItem);
	}
			
	// Controller function to delete an item in the table using its id
	@DeleteMapping("/items/{id}")
	public Map<String, Boolean> deleteItem(@PathVariable(value="id") Long iid)
	throws ResourceNotFoundException {
		iservice.getSingleItem(iid).
				orElseThrow(() -> new ResourceNotFoundException("Item not found for this id: " + iid));
		iservice.deleteItem(iid);
				
		Map<String,Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return response;
	}

}

//----------------------------- END OF ITEM CONTROLLER CLASS -----------------------------//
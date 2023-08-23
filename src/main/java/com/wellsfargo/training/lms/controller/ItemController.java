package com.wellsfargo.training.lms.controller;

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

/*Spring RestController annotation is used to create RESTful web services using Spring MVC. 
 * Spring RestController takes care of mapping request data to the defined request handler method. 
 * Once response body is generated from the handler method, it converts it to JSON or XML response. 
 * 
 * @RequestMapping - maps HTTP request with a path to a controller 
 * */

@RestController
@RequestMapping(value="/api")
public class ItemController {
	

	@Autowired
	private ItemService iservice;
	
	/* ResponseEntity represents an HTTP response, including headers, body, and status.
	 *  @RequestBody annotation automatically deserializes the JSON into a Java type
	 *  */
	
	//Open PostMan, make a POST Request - http://localhost:8085/pms/api/loans/
    //Select body -> raw -> JSON 
    //Insert JSON product object.
	@PostMapping("/items")
	public Item saveItem(@Validated @RequestBody Item item) {
		
		Item i=iservice.saveItem(item);
		return i;
	}
	
	// Postman/Browser --> Controller -->Service -> Repository -> DataBase
	// All layers will use Model when required
	//Open PostMan, make a GET Request - http://localhost:8085/pms/api/loans/
	@GetMapping("/items")
	public List<Item> getAllItems() {
		return iservice.listAll();   // Invokes service Method user defined listAll()
	}
	//Open PostMan, make a GET Request - http://localhost:8085/lms/api/items/4
	
	@GetMapping("/items/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable(value="id") Long iid)
	throws ResourceNotFoundException
	{
		Item i = iservice.getSingleItem(iid).
			orElseThrow(() -> new ResourceNotFoundException("Item not found for this id: " + iid));
		return ResponseEntity.ok().body(i);
	}
			
	//Open PostMan, make a PUT Request - http://localhost:8085/lms/api/items/3
	//Select body -> raw -> JSON 
	//Update JSON product object with new Values.
	
			
	@PutMapping("/items/{id}")
	public ResponseEntity<Item> updateItem(@PathVariable(value="id") Long iid, @Validated @RequestBody Item i)
	throws ResourceNotFoundException
	{
		Item item = iservice.getSingleItem(iid).
			orElseThrow(() -> new ResourceNotFoundException("Item not found for this id: " + iid));
			
		item.setItem_description(i.getItem_description());
		item.setItem_make(i.getItem_make());
		item.setItem_category(i.getItem_category());
		item.setItem_valuation(i.getItem_valuation());
				
		final Item updatedItem = iservice.saveItem(item);
		return ResponseEntity.ok().body(updatedItem);
	}
			
	@DeleteMapping("/items/{id}")
	public Map<String, Boolean> deleteItem(@PathVariable(value="id") Long iid)
	throws ResourceNotFoundException
	{
		iservice.getSingleItem(iid).
				orElseThrow(() -> new ResourceNotFoundException("Item not found for this id: " + iid));
		iservice.deleteItem(iid);
				
		Map<String,Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return response;
	}
	

}
;
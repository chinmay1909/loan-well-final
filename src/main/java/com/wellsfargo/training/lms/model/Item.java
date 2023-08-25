package com.wellsfargo.training.lms.model;

//--------------------------IMPORTING NECESSARY LIBRARIES ---------------------------------//

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//--------------- CLASS TO REPRESENT THE DATA MEMBERS AND FUNCTIONS OF AN ITEM ------------//

@Entity
@Table(name="item_master")
public class Item {
	
	// Primary key of the Item table
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long item_id;
	 
	// Other data members of the Item class
	private String item_description;
	private String item_make;
	private String item_category;
	private int item_valuation;
    
	// Empty constructor
	public Item() {
		
	}

	// Parameterized constructor
	public Item(Long item_id, String item_description, String item_make, String item_category,
			int item_valuation) {
		this.item_id = item_id;
		this.item_description = item_description;
		this.item_make = item_make;
		this.item_category = item_category;
		this.item_valuation = item_valuation;
	}
	
	// Getters and setters
	
	public Long getItem_id() {
		return item_id;
	}
	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}
	public String getItem_description() {
		return item_description;
	}
	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}
	public String getItem_make() {
		return item_make;
	}
	public void setItem_make(String item_make) {
		this.item_make = item_make;
	}
	public String getItem_category() {
		return item_category;
	}
	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}
	public int getItem_valuation() {
		return item_valuation;
	}
	public void setItem_valuation(int item_valuation) {
		this.item_valuation = item_valuation;
	}
}

//---------------------------------- END OF ITEM MODEL -------------------------------------//
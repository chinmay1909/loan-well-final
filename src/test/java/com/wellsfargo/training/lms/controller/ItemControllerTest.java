package com.wellsfargo.training.lms.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wellsfargo.training.lms.exception.ResourceNotFoundException;
import com.wellsfargo.training.lms.model.Item;
import com.wellsfargo.training.lms.service.ItemService;


@SpringBootTest
class ItemControllerTest {
	
	@Autowired
	private ItemController itemController;
	
	Item item;
	
	@MockBean
	private ItemService iservice;
	

//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//	}
//
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//	}

	@BeforeEach
	void setUp() throws Exception {
		item = new Item();
		//iservice = new ItemService();
		// irepo = new ItemRepository();
	}

	@AfterEach
	void tearDown() throws Exception {
		item = null;
		//iservice = null;
	}

	@Test
	void testSaveItem() {
		
		item.setItem_category("Furniture");
		item.setItem_id(1L);
		item.setItem_description("180cm x 90cm");
		item.setItem_make("Wooden");
		item.setItem_valuation(1500);
		
		when(iservice.saveItem(any(Item.class))).thenReturn(item);
		
		Item re = itemController.saveItem(item);
		assertEquals("Furniture",re.getItem_category());
		assertEquals(1L,re.getItem_id());
		assertEquals("180cm x 90cm", re.getItem_description());
		assertEquals("Wooden", re.getItem_make());
		assertEquals(1500,re.getItem_valuation());
		
		verify(iservice,times(1)).saveItem(any(Item.class));
	}

	@Test
	void testGetAllItems() {
		
		List<Item> mockItems=new ArrayList<>();
		mockItems.add(new Item(1L,"Coloured, A3 size, Ruled","Paper","Stationary", 1500));
		mockItems.add(new Item(2L,"Bed, Single-Sized, Imported","Wooden","Furniture", 1750));
		
		
		when(iservice.listAll()).thenReturn(mockItems);
		
		List<Item> responseItems = itemController.getAllItems();
		assertEquals(2,responseItems.size());
		assertEquals("Paper",responseItems.get(0).getItem_make());
		assertEquals(1750,responseItems.get(1).getItem_valuation());
		
		verify(iservice,times(1)).listAll();
		
	}

	@Test
	void testGetItemById() throws ResourceNotFoundException{
		
		Item mockItem = new Item(1L,"15cm, Ruler, Transparent","Plastic","Stationary", 10);
		
		when(iservice.getSingleItem(1L)).thenReturn(Optional.of(mockItem));
		
		ResponseEntity<Item> re = itemController.getItemById(1L);
		
		assertEquals(HttpStatus.OK,re.getStatusCode());
		assertEquals("Stationary",re.getBody().getItem_category());
		assertEquals(1L,re.getBody().getItem_id());
		assertEquals("15cm, Ruler, Transparent", re.getBody().getItem_description());
		assertEquals("Plastic", re.getBody().getItem_make());
		assertEquals(10,re.getBody().getItem_valuation());
		
		
		verify(iservice,times(1)).getSingleItem(1L);
		
	}

	@Test
	void testUpdateItem() throws ResourceNotFoundException{
		Item existingItem = new Item(1L,"15cm, Ruler, Transparent","Plastic","Stationary", 10);
		Item updatedItem = new Item(1L,"15cm, Ruler, Transparent","Plastic","Stationary", 15);
		
		when(iservice.getSingleItem(1L)).thenReturn(Optional.of(existingItem));
		when(iservice.saveItem(any(Item.class))).thenReturn(updatedItem);
		
		ResponseEntity<Item> re = itemController.updateItem(1L, updatedItem);
		
		assertEquals(HttpStatus.OK,re.getStatusCode());
		assertEquals("Stationary",re.getBody().getItem_category());
		assertEquals(1L,re.getBody().getItem_id());
		assertEquals("15cm, Ruler, Transparent", re.getBody().getItem_description());
		assertEquals("Plastic", re.getBody().getItem_make());
		assertEquals(15,re.getBody().getItem_valuation());
	
		verify(iservice,times(1)).getSingleItem(1L);
		verify(iservice,times(1)).saveItem(any(Item.class));
	}

	@Test
	void testDeleteItem() throws ResourceNotFoundException {
		
		Item existingItem = new Item(1L,"15cm, Ruler, Transparent","Plastic","Stationary", 10);
		
		when(iservice.getSingleItem(1L)).thenReturn(Optional.of(existingItem));
		doNothing().when(iservice).deleteItem(1L);
		
		Map<String,Boolean> response = itemController.deleteItem(1L);
		
		assertTrue(response.containsKey("Deleted"));
		assertTrue(response.get("Deleted"));
		
		verify(iservice,times(1)).getSingleItem(1L);
		verify(iservice,times(1)).deleteItem(1L);
	}

}

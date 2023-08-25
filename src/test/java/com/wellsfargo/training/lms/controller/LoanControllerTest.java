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
import com.wellsfargo.training.lms.model.Loan;
import com.wellsfargo.training.lms.service.LoanService;


@SpringBootTest
class LoanControllerTest {
	
	@Autowired
	private LoanController loanController;
	
	@MockBean
	private LoanService lservice;
	
	Loan loan;
	
//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//	}
//
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//	}

	@BeforeEach
	void setUp() throws Exception {
		loan = new Loan();
	}

	@AfterEach
	void tearDown() throws Exception {
		loan = null;
	}

	@Test
	void testSaveLoan() {
		
		loan.setDuration_in_years(8);
		loan.setLoan_id(1L);
		loan.setLoan_type("Furniture");
		
		when(lservice.saveLoan(any(Loan.class))).thenReturn(loan);
		
		Loan re = loanController.saveLoan(loan);
		assertEquals("Furniture",re.getLoan_type());
		assertEquals(1L,re.getLoan_id());
		assertEquals(8,re.getDuration_in_years());
		
		
		verify(lservice,times(1)).saveLoan(any(Loan.class));
	}

	@Test
	void testGetAllLoans() {
		
		List<Loan> mockLoans=new ArrayList<>();
		mockLoans.add(new Loan(1L,"Furniture",8));
		mockLoans.add(new Loan(2L,"Stationary",3));
		
		
		when(lservice.listAll()).thenReturn(mockLoans);
		
		List<Loan> responseItems = loanController.getAllLoans();
		assertEquals(2,responseItems.size());
		assertEquals("Furniture",responseItems.get(0).getLoan_type());
		assertEquals(3,responseItems.get(1).getDuration_in_years());
		
		verify(lservice,times(1)).listAll();
	}

	@Test
	void testGetLoanById() throws ResourceNotFoundException {
		
		Loan mockLoan = new Loan(1L,"Furniture",8);
		
		when(lservice.getSingleLoan(1L)).thenReturn(Optional.of(mockLoan));
		
		ResponseEntity<Loan> re = loanController.getLoanById(1L);
		
		assertEquals(HttpStatus.OK,re.getStatusCode());
		assertEquals("Furniture",re.getBody().getLoan_type());
		assertEquals(1L,re.getBody().getLoan_id());
		assertEquals(8 , re.getBody().getDuration_in_years());	
		
		verify(lservice,times(1)).getSingleLoan(1L);
	}

	@Test
	void testUpdateLoan() throws ResourceNotFoundException {
		
		Loan existingLoan = new Loan(1L,"Furniture",8);
		Loan updatedLoan = new Loan(1L,"Furniture",2);
		
		when(lservice.getSingleLoan(1L)).thenReturn(Optional.of(existingLoan));
		when(lservice.saveLoan(any(Loan.class))).thenReturn(updatedLoan);
		
		ResponseEntity<Loan> re = loanController.updateLoan(1L, updatedLoan);
		
		assertEquals(HttpStatus.OK,re.getStatusCode());
		assertEquals("Furniture",re.getBody().getLoan_type());
		assertEquals(1L,re.getBody().getLoan_id());
		assertEquals(2 , re.getBody().getDuration_in_years());
	
		verify(lservice,times(1)).getSingleLoan(1L);
		verify(lservice,times(1)).saveLoan(any(Loan.class));
	}

	@Test
	void testDeleteLoan() throws ResourceNotFoundException {
		Loan existingLoan = new Loan(1L,"Furniture",8);
		
		when(lservice.getSingleLoan(1L)).thenReturn(Optional.of(existingLoan));
		doNothing().when(lservice).deleteLoan(1L);
		
		Map<String,Boolean> response = loanController.deleteLoan(1L);
		
		assertTrue(response.containsKey("Deleted"));
		assertTrue(response.get("Deleted"));
		
		verify(lservice,times(1)).getSingleLoan(1L);
		verify(lservice,times(1)).deleteLoan(1L);
	}

}

package com.wellsfargo.training.lms.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wellsfargo.training.lms.exception.ResourceNotFoundException;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.wellsfargo.training.lms.model.Employee;
import com.wellsfargo.training.lms.repository.EmployeeRepository;
import com.wellsfargo.training.lms.service.EmployeeService;
import com.wellsfargo.training.lms.controller.EmployeeController;

//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);


@SpringBootTest
class EmployeeControllerTest {
	
	@Autowired
	private EmployeeController employeeController;
	
	@MockBean
	private EmployeeService eservice;
	
	Employee employee;

//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//	}
//
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//	}

	@BeforeEach
	void setUp() throws Exception {
		employee = new Employee();
	}

	@AfterEach
	void tearDown() throws Exception {
		employee=null;
	}

	@Test
	void testGetAllEmployees() {
		List<Employee> mockEmployees = new ArrayList<>();
		mockEmployees.add(new Employee(123L, "Chinmay", "PA", "DTI", 'M', Date.valueOf("2002-12-22"), Date.valueOf("2004-11-14"),"pass123"));
//		mockEmployees.add(new Employee());
		
		when(eservice.listAllEmployees()).thenReturn(mockEmployees);
		List<Employee> responseEmployees = employeeController.getAllEmployees();
//		assertEquals()
		assertEquals("Chinmay", responseEmployees.get(0).getEmployee_name());		
		verify(eservice, times(1)).listAllEmployees();
		
	}

	@Test
	void testGetEmployeeById() throws ResourceNotFoundException{
		Employee mockEmployee=new Employee(123L, "Chinmay", "PA", "DTI", 'M', Date.valueOf("2002-12-22"), Date.valueOf("2004-11-14"),"pass123");
		
		when(eservice.getSingleEmployee(123L)).thenReturn(Optional.of(mockEmployee));
		
		ResponseEntity<Employee> re = employeeController.getEmployeeById(123L);
		assertEquals(HttpStatus.OK,re.getStatusCode());
		assertEquals("PA",re.getBody().getDesignation());
		assertEquals('M',re.getBody().getGender());
		assertEquals("Chinmay",re.getBody().getEmployee_name());
		
		verify(eservice,times(1)).getSingleEmployee(123L);
	}

	@Test
	void testDeleteItem() throws ResourceNotFoundException{
		Employee existingEmployee = new Employee(123L, "Chinmay", "PA", "DTI", 'M', Date.valueOf("2002-12-22"), Date.valueOf("2004-11-14"),"pass123");
		
		when(eservice.getSingleEmployee(123L)).thenReturn(Optional.of(existingEmployee));
		doNothing().when(eservice).deleteEmployee(123L);
		
		Map<String,Boolean> response = employeeController.deleteItem(123L);
		
		assertTrue(response.containsKey("Deleted"));
		assertTrue(response.get("Deleted"));
		
		verify(eservice,times(1)).getSingleEmployee(123L);
		verify(eservice,times(1)).deleteEmployee(123L);
	}

	@Test
	void testUpdateEmployee() throws ResourceNotFoundException{
		Employee existingEmployee = new Employee(123L, "Chinmay", "PA", "DTI", 'M', Date.valueOf("2002-12-22"), Date.valueOf("2004-11-14"),"pass123");
		Employee updatedEmployee = new Employee(123L, "Shaurya", "PA", "COO", 'M', Date.valueOf("2012-12-22"), Date.valueOf("2014-11-14"),"pass123");
		
		when(eservice.getSingleEmployee(123L)).thenReturn(Optional.of(existingEmployee));
		when(eservice.saveEmployee(any(Employee.class))).thenReturn(updatedEmployee);
		
		ResponseEntity<Employee> re = employeeController.updateEmployee(123L, updatedEmployee);
		
		assertEquals("Shaurya",re.getBody().getEmployee_name());
		assertEquals("COO",re.getBody().getDepartment());
		assertEquals(Date.valueOf("2012-12-22"),re.getBody().getDob());
	
		verify(eservice,times(1)).getSingleEmployee(123L);
		verify(eservice,times(1)).saveEmployee(any(Employee.class));
	}

	@Test
	void testCreateEmployee() {
		employee.setEmployee_id(123L);
		employee.setDesignation("PA");
		employee.setDepartment("DTI");
		employee.setEmployee_name("Mike");
		employee.setPassword("password123");
	
		employee.setDob(Date.valueOf("1990-01-01"));
		employee.setDoj(Date.valueOf("1999-02-02"));
		
		employee.setGender('M');
		/*
		 * Matchers are like regex or wildcards where instead of a specific input (and or output), 
		 * you specify a range/type of input/output based on which stubs/spies can be rest and calls to stubs can be verified.
		 * Matchers are a powerful tool, which enables a shorthand way of setting up stubs as well as verifying invocations on 
		 * the stubs by mentioning argument inputs as generic types to specific values depending on the use-case or scenario.
		 * 
		 * any(java language class) –

		Example: any(ClassUnderTest.class) – This is a more specific variant of any() 
		and will accept only objects of the class type that’s mentioned as the template parameter.
		 * */
		when(eservice.registerEmployee(any(Employee.class))).thenReturn(employee);
		ResponseEntity<String> re=employeeController.createEmployee(employee);
		
		assertEquals(HttpStatus.OK,re.getStatusCode());
		assertEquals("Registration successful",re.getBody());
		
		verify(eservice,times(1)).registerEmployee(any(Employee.class));
	}

	@Test
	void testLoginEmployee() throws ResourceNotFoundException {
		employee.setEmployee_id(123L);
		employee.setPassword("password123");
		
		/*when() Then() method
		It enables stubbing methods. It should be used when we want to mock to return specific values when 
		particular methods are called. In simple terms, "When the XYZ() method is called, 
		then return ABC." It is mostly used when there is some condition to execute.
		 * 
		 * */
		when(eservice.loginEmployee(123L)).thenReturn(Optional.of(employee));
	
		Employee x = eservice.loginEmployee(123L).get();
		
		assertEquals(x.getEmployee_id(),employee.getEmployee_id());
		assertEquals(x.getPassword(),employee.getPassword());
		
//		assertEquals(x.getPassword(),"password");

		
		Boolean result=employeeController.loginEmployee(employee);
		assertTrue(result);
		
		/*
		 * The verify() method is used to check whether some specified methods are called or not. 
		 * In simple terms, it validates the certain behavior that happened once in a test. 
		 * It is used at the bottom of the testing code to assure that the defined methods are called.
		 * */
		
		verify(eservice,times(2)).loginEmployee(123L);
	}
}

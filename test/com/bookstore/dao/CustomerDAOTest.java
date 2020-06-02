package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Customer;

public class CustomerDAOTest {
	private static CustomerDAO customerDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		customerDAO = new CustomerDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		customerDAO.close();
	}

	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setEmail("tanvi@gmail.com");
		customer.setFirstname("Tanvi");
		customer.setLastname("Sonavane");
		customer.setCity("Mumbai");
		customer.setState("Maharashtra");
		customer.setCountry("India");
		customer.setAddressLine1("100 North");
		customer.setAddressLine2("Thorat's Apartment");
		customer.setPassword("tanvi");
		customer.setPhone("9619179717");
		customer.setZipcode("4000027");
		
		Customer savedCustomer = customerDAO.create(customer);
		
		assertTrue(savedCustomer.getCustomerId() > 0);
	}

	@Test
	public void testGet() {
		Integer customerId = 24;
		Customer customer = customerDAO.get(customerId);
		
		assertNotNull(customer);
	}
	
	@Test
	public void testUpdateCustomer() {
		Customer customer = customerDAO.get(24);
		String firstname = "Devashri";
		customer.setFirstname(firstname);
		Customer updatedCustomer = customerDAO.update(customer);
		
		assertTrue(updatedCustomer.getFirstname().equals(firstname));
	}

	@Test
	public void testDeleteCustomer() {
		Integer customerId = 24;
		customerDAO.delete(customerId);
		Customer customer = customerDAO.get(12);
		
		assertNull(customer);
	}
	
	@Test
	public void testListAllCustomer() {
		List<Customer> listCustomer = customerDAO.listAll();
		
		for(Customer customer : listCustomer) {
			System.out.println(customer.getEmail());
		}
		
		assertFalse(listCustomer.isEmpty());
	}
	
	@Test
	public void testCount() {
		long totalCustomer = customerDAO.count();
		
		assertEquals(10, totalCustomer);
	}
	
	@Test
	public void testFindByEmail() {
		String email = "niesh@gmail.com";
		Customer customer = customerDAO.findByEmail(email);
		
		assertNotNull(customer);
	}
	
	@Test
	public void testCheckLoginSuccess() {
		String email = "nilesh@gmail.com";
		String password = "nilesh";
		
		Customer customer = customerDAO.checkLogin(email, password);
		
		assertNotNull(customer);
	}
	
	@Test
	public void testCheckLoginFail() {
		String email = "nilesh@gmail.com";
		String password = "vicky";
		
		Customer customer = customerDAO.checkLogin(email, password);
		
		assertNull(customer);
	}
}

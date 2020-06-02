package com.bookstore.dao;

import static org.junit.Assert.*;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest {
	private static UserDAO userDAO;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		userDAO= new UserDAO();
	}

	@Test
	public void testCreateUsers() {
		Users user1= new Users();
		user1.setEmail("nilesh@gmail.com");
		user1.setFullName("Nilesh Thorat");
		user1.setPassword("nilesh");
		
		user1= userDAO.create(user1);
		
		assertTrue(user1.getUserId() > 0);		
	}
	
	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		Users user1= new Users();
		user1 = userDAO.create(user1);
		
		assertTrue(user1.getUserId() > 0);
	}
	
	public void testUpdateUsers() {
		Users user= new Users();
		user.setUserId(2);
		user.setEmail("nilesh@gmail.com");
		user.setFullName("Nilesh M Thorat");
		user.setPassword("nilesh");
		
		user= userDAO.update(user);
		String expected = "abcdef";
		String actual = user.getPassword();
		
		assertEquals(expected, actual);		
	}
		
	@Test
	public void testGetUsersFound() {
		Integer userId = 1;
		Users user= userDAO.get(userId);
		
		if(user != null) {
			System.out.println(user.getEmail());
		}
		assertNotNull(user);
	}
	
	@Test
	public void getUsersNotFound() {
		Integer userId = 99;
		Users user = userDAO.get(userId);
		
		assertNull(user);
	}
	
	@Test
	public void testDeleteUsers() {
		Integer userId = 6;
		userDAO.delete(userId);
		
		Users user= userDAO.get(userId);
		
		assertNull(user);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteNonExistUsers() {
		Integer userId = 66;
		userDAO.delete(userId);
	}
	
	@Test
	public void testListAll() {
		List<Users> listusers = userDAO.listAll();
		
		for(Users user : listusers) {
			System.out.println(user.getEmail());
		}
		
		assertTrue(listusers.size() > 0);
	}
	
	@Test
	public void testCount() {
		long totalUsers = userDAO.count();
		
		//assertEquals(6, totalUsers);
		assertTrue(totalUsers > 0);
	}
	
	@Test
	public void testCheckLoginSuccess() {
		String email = "thoratnilesh5@gmail.com";
		String password = "helloworld";
		boolean loginResult = userDAO.checkLogin(email, password);
		
		assertTrue(loginResult);
	}
	
	@Test
	public void testCheckLoginFailed() {
		String email = "thoratnilesh5nilesh";
		String password = "helorld";
		boolean loginResult = userDAO.checkLogin(email, password);
		
		assertFalse(loginResult);
	}
	
	@Test
	public void testFindByEmail() {
		String email = "nilesh@gmail.com";
		Users user = userDAO.findByEmail(email);
		
		assertNotNull(user);
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		userDAO.close();
	}
}

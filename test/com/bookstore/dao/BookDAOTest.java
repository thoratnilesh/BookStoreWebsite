package com.bookstore.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest {
	private static BookDAO bookDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bookDAO = new BookDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		bookDAO.close();
	}

	@Test
	public void testCount() {
		long totalBooks = bookDAO.count();
		
		assertEquals(2, totalBooks);
	}

	@Test
	public void testCreate2ndBook() throws IOException, ParseException {
		
		Book newBook = new Book();
		
		Category category = new Category("Advanced Java");
		category.setCategoryId(2);
		newBook.setCategory(category );
		
		newBook.setTitle("Java 8 In Action");
		newBook.setAuthor("Alan Mycroft");
		newBook.setDescription("Java 8 In Action is clearly written guide to the new feature of Java 8");
		newBook.setPrice(388.87f);
		newBook.setIsbn("1617291994");
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date publishDate = dateFormat.parse("08/28/2014");
		newBook.setPublishDate(publishDate);
		
		String imagePath = "D:/Projects/BookStore/Images/BookImage/Java8InAction.png";
		
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);
		
		Book createdBook = bookDAO.create(newBook);
		
		assertTrue(createdBook.getBookId() > 0);
	}
	
	@Test
	public void testCreateBook() throws IOException, ParseException {
		
		Book newBook = new Book();
		
		Category category = new Category("Advanced Java");
		category.setCategoryId(2);
		newBook.setCategory(category );
		
		newBook.setTitle("Effective Java (2nd Edition)");
		newBook.setAuthor("Joshua Bloch");
		newBook.setDescription("New coverage of generics, enums, annotations, autoboxing");
		newBook.setPrice(38.87f);
		newBook.setIsbn("0321356683");
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date publishDate = dateFormat.parse("05/28/2008");
		newBook.setPublishDate(publishDate);
		
		String imagePath = "D:/Projects/BookStore/Images/BookImage/EffectiveJava.png";
		
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);
		
		Book createdBook = bookDAO.create(newBook);
		
		assertTrue(createdBook.getBookId() > 0);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteBookFail() {
		Integer bookId = 100;
		bookDAO.delete(bookId);	
	}
	
	@Test
	public void testDeleteBookSuccess() {
		Integer bookId = 1;
		bookDAO.delete(bookId);
		
		assertTrue(true);
	}
	
	@Test
	public void testFindByTitleExist() {
		String title = "Java 8 In Action";
		Book book = bookDAO.findByTitle(title);
		
		System.out.println(book.getAuthor());
		System.out.println(book.getPrice());
		
		assertNotNull(book);
	}
	
	@Test
	public void testFindByTitleNotExist() {
		String title = "Thinking in Java";
		Book book = bookDAO.findByTitle(title);
		
		assertNull(book);
	}
	
	@Test
	public void testGetBookSuccess() {
		Integer bookId = 11;
		Book book = bookDAO.get(bookId);
		
		assertNotNull(book);
	}
	
	@Test
	public void testGetBookFail() {
		Integer bookId = 99;
		Book book = bookDAO.get(bookId);
		
		assertNull(book);
	}
	
	@Test
	public void testListAll() {
		List<Book> listBook = bookDAO.listAll();
		
		for(Book aBook : listBook) {
			System.out.println(aBook.getTitle() + " - " + aBook.getAuthor());
		}
		
		assertFalse(listBook.isEmpty());
	}
	
	@Test
	public void testListNewBook() {
		List<Book> listNewBook = bookDAO.listNewBook();
		for(Book aBook : listNewBook) {
			System.out.println(aBook.getTitle() + " - " + aBook.getPublishDate());
		}
		assertEquals(listNewBook.size(), 4);
	}
	
	@Test
	public void testSearchBookInTitle() {
		String keyword = "Java";
		List<Book> result = bookDAO.search(keyword);
		
		for(Book aBook : result) {
			System.out.println(aBook.getTitle());
		}
		
		assertEquals(7, result.size());
	}
	
	@Test
	public void testSearchBookInAuthor() {
		String keyword1 = "Brian";
		List<Book> result = bookDAO.search(keyword1);
		
		for(Book aBook : result) {
			System.out.println(aBook.getTitle());
		}
		
		assertEquals(1, result.size());
	}
	
	@Test
	public void testSearchBookInDescription() {
		String keyword1 = "Java Concurrency in Practice";
		List<Book> result = bookDAO.search(keyword1);
		
		for(Book aBook : result) {
			System.out.println(aBook.getTitle());
		}
		
		assertEquals(1, result.size());
	}
	
	@Test
	public void testUpdateBook() throws IOException, ParseException {
		
		Book existBook = new Book();
		existBook.setBookId(1);
		
		Category category = new Category("Core Java");
		category.setCategoryId(1);
		existBook.setCategory(category);
		
		existBook.setTitle("Effective Java (3rd Edition)");
		existBook.setAuthor("Joshua Bloch");
		existBook.setDescription("New coverage of generics, enums, annotations, autoboxing");
		existBook.setPrice(442.42f);
		existBook.setIsbn("0321356683");
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date publishDate = dateFormat.parse("05/28/2008");
		existBook.setPublishDate(publishDate);
		
		String imagePath = "D:/Projects/BookStore/Images/BookImage/EffectiveJava.png";
		
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		existBook.setImage(imageBytes);
		
		Book updatedBook = bookDAO.update(existBook);
		
		assertEquals(updatedBook.getTitle(), "Effective Java (3rd Edition)");
	}
	
	@Test
	public void testListByCategory() {
		int categoryId = 1;
		
		List<Book> listBooks = bookDAO.listByCategory(categoryId);
		
		assertTrue(listBooks.size() > 0);
	}
	
	@Test
	public void testCountByCategory() {
		int categoryId = 2;
		long numOfBooks = bookDAO.countByCategory(categoryId);
		
		assertTrue(numOfBooks == 7);
	}
	
	@Test
	public void testListBestSellingBooks() {
		List<Book> topBestSellingBooks = bookDAO.listBestSellingBooks();
		
		for(Book book : topBestSellingBooks) {
			System.out.println(book.getTitle());
		}
		
		assertEquals(4, topBestSellingBooks.size());
	}
	
	@Test
	public void testListMostFavoredBooks() {
		List<Book> topFavoredBooks = bookDAO.listMostFavoredBooks();
		
		for(Book book : topFavoredBooks) {
			System.out.println(book.getTitle());
		}
		
		assertEquals(4, topFavoredBooks.size());
	}
}

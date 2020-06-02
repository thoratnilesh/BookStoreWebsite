package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;
import com.bookstore.entity.OrderDetailId;

public class OrderDAOTest {
	
	private static OrderDAO orderDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		orderDAO = new OrderDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		orderDAO.close();
	}

	@Test
	public void testCreateBookOrder1() {
		BookOrder order = new BookOrder();
		Customer customer = new Customer();
		customer.setCustomerId(15);
		
		order.setCustomer(customer);
		order.setFirstname("Nilesh");
		order.setPhone("1234567");
		order.setAddressLine1("Welspun House, Mumbai, India");
		
		Set<OrderDetail> orderDetails = new HashSet<>();
		OrderDetail orderDetail1 = new OrderDetail();
		
		Book book1 = new Book(6);
		orderDetail1.setBook(book1);
		orderDetail1.setQuantity(2);
		orderDetail1.setSubtotal(500f);
		orderDetail1.setBookOrder(order);
				
		orderDetails.add(orderDetail1);
		
		Book book2 = new Book(7);
		OrderDetail orderDetail2 = new OrderDetail();
		orderDetail2.setBook(book2);
		orderDetail2.setQuantity(1);
		orderDetail2.setSubtotal(400f);
		orderDetail2.setBookOrder(order);
				
		
		order.setOrderDetails(orderDetails);
		
		orderDAO.create(order);
		
		assertTrue(order.getOrderId() > 0);
	}
	
	@Test
	public void testCreateBookOrder() {
		BookOrder order = new BookOrder();
		Customer customer = new Customer();
		customer.setCustomerId(19);
		
		order.setCustomer(customer);
		order.setFirstname("Savitri");
		order.setLastname("Mhatre");
		order.setPhone("879789654");
		order.setAddressLine1("100 South Street");
		order.setAddressLine2("Clifton Park");
		order.setCity("Mumbai");
		order.setState("Maharashtra");
		order.setCountry("IN");
		order.setPaymentMethod("paypal");
		order.setZipcode("600054");
		
		Set<OrderDetail> orderDetails = new HashSet<>();
		OrderDetail orderDetail = new OrderDetail();
		
		Book book = new Book(18);
		orderDetail.setBook(book);
		orderDetail.setQuantity(2);
		orderDetail.setSubtotal(1111.1f);
		orderDetail.setBookOrder(order);
				
		orderDetails.add(orderDetail);
		
		order.setOrderDetails(orderDetails);
		order.setSubtotal(1111.1f);
		order.setTax(111.11f);
		order.setShippingFee(10.0f);
		order.setTotal(1232.21f);
		
		BookOrder savedOrder = orderDAO.create(order);
		
		assertTrue(savedOrder != null && savedOrder.getOrderDetails().size() > 0);
	}

	@Test
	public void testUpdateBookOrder() {
		Integer orderId = 26;
		BookOrder order = orderDAO.get(orderId);
		order.setAddressLine1("New shipping address");
		
		orderDAO.update(order);
		
		BookOrder updateOrder = orderDAO.get(orderId);
		
		assertEquals(order.getAddressLine1(), updateOrder.getAddressLine1());
	}
	
	@Test
	public void testUpdateBookOrderDetail() {
		Integer orderId = 39;
		BookOrder order = orderDAO.get(orderId);
		
		Iterator<OrderDetail> iterator = order.getOrderDetails().iterator();
		
		while(iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			if (orderDetail.getBook().getBookId() == 18) {
				orderDetail.setQuantity(3);
				orderDetail.setSubtotal(1666.65f);
			}
		}
		
		orderDAO.update(order);
		
		BookOrder updateOrder = orderDAO.get(orderId);
		
		iterator = order.getOrderDetails().iterator();
		
		int expectedQuantity = 3;
		float expectedSubtotal = 1666.65f;
		int actualQuantity = 0;
		float actualSubtotal = 0;
		
		while(iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			if (orderDetail.getBook().getBookId() == 18) {
				actualQuantity = orderDetail.getQuantity();
				actualSubtotal = orderDetail.getSubtotal();
			}
		}
		
		assertEquals(expectedQuantity, actualQuantity);
		assertEquals(expectedSubtotal, actualSubtotal, 0.0f);
	}

	@Test
	public void testGet() {
		Integer orderId = 40;
		BookOrder order = orderDAO.get(orderId);
		System.out.println(order.getFirstname());
		System.out.println(order.getLastname());
		System.out.println(order.getPhone());
		System.out.println(order.getAddressLine1());
		System.out.println(order.getAddressLine2());
		System.out.println(order.getCity());
		System.out.println(order.getState());
		System.out.println(order.getCountry());
		System.out.println(order.getZipcode());
		System.out.println(order.getStatus());
		System.out.println(order.getSubtotal());
		System.out.println(order.getShippingFee());
		System.out.println(order.getTax());
		System.out.println(order.getTotal());
		System.out.println(order.getPaymentMethod());
		
		assertEquals(1, order.getOrderDetails().size());
	}

	@Test
	public void testGetByIdAndCustomerNull() {
		Integer orderId = 10;
		Integer customerId = 99;
		
		BookOrder order = orderDAO.get(orderId, customerId);
		
		assertNull(order);
	}
	
	@Test
	public void testGetByIdAndCustomerNotNull() {
		Integer orderId = 26;
		Integer customerId = 13;
		
		BookOrder order = orderDAO.get(orderId, customerId);
		
		assertNotNull(order);
	}
	
	@Test
	public void testDeleteObject() {
		int orderId = 27;
		orderDAO.delete(orderId);
		
		BookOrder order = orderDAO.get(orderId);
		
		assertNull(order);
	}

	@Test
	public void testListAll() {
		List<BookOrder> listOrder = orderDAO.listAll();
		
		for(BookOrder order : listOrder) {
			System.out.println(order.getOrderId() + " - "+ order.getCustomer().getFirstname() + " - "
					+ order.getTotal() + " - "+ order.getStatus());
			for(OrderDetail detail : order.getOrderDetails()) {
				Book book = detail.getBook();
				int quantity = detail.getQuantity();
				float subtotal = detail.getSubtotal();
				System.out.println("\t"+ book.getTitle()+ " - "+ quantity +" - "+ subtotal);
			}
		}
		
		assertTrue(listOrder.size() > 0);
	}

	@Test
	public void testListByCustomerNoOrders() {
		Integer customerId = 99;
		List<BookOrder> listOrders = orderDAO.listByCustomer(customerId);
		
		assertTrue(listOrders.isEmpty());
	}
	
	@Test
	public void testListByCustomerHaveOrders() {
		Integer customerId = 13;
		List<BookOrder> listOrders = orderDAO.listByCustomer(customerId);
		
		assertTrue(listOrders.size() > 0);
	}
	
	@Test
	public void testCount() {
		long totalOrders = orderDAO.count();
		
		assertEquals(3, totalOrders);
	}
	
	@Test
	public void testListMostRecentSales() {
		List<BookOrder> recentOrders = orderDAO.listMostRecentSales();
		assertEquals(3, recentOrders.size());
	}

}

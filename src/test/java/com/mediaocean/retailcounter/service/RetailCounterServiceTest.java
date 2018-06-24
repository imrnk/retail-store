package com.mediaocean.retailcounter.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mediaocean.retailcounter.entity.ProductEntity;
import com.mediaocean.retailcounter.model.Bill;
import com.mediaocean.retailcounter.model.LineItem;
import com.mediaocean.retailcounter.model.Product;
import com.mediaocean.retailcounter.repository.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:applicationContext.xml"})
public class RetailCounterServiceTest {

	List<LineItem> lineItems;
	List<Integer> prodIds;
	
	Integer orderId;
	
	@Autowired
	private ProductRepository prodRepo;
	
	@Autowired
	private RetailCounterService service;
	
	@Before
	public void setUp() throws Exception {
		Product productA = new Product("A");
		Product productB = new Product("B");
		Product productC = new Product("C");
		
		productA.setName("Apple");
		productB.setName("Biscuit");
		productC.setName("Band-Aid");
		
		productA.setPrice(3.5);
		productB.setPrice(4.5);
		productC.setPrice(1.5);
		
		List<Product> products = new ArrayList<>();
		products.add(productA);products.add(productB);products.add(productC);   

		service.createProducts(products);
		
		prodIds = prodRepo.findAllProducts()
					.stream()
					.map(ProductEntity::getProductId)
						.collect(Collectors.toList());
		
		lineItems = prodIds.stream().map(pId -> {
			LineItem li = new LineItem();
			li.setProductId(pId);
			li.setItemQuantity(2);
			
			return li;
		}).collect(Collectors.toList());
		
		orderId = service.createOrder(lineItems);
		
	}

	@Test
	public void testCreateOrder() {
		int orderId = service.createOrder(lineItems);
		assertTrue(orderId > 0);
	}


	@Test
	public void testFindBill() {
		Bill bill = service.findBill(orderId);
		assertNotNull(bill);
		
		
	}

	@Test
	public void testOrderById() {
		assertNotNull(service.orderById(orderId));
	}


	@Test
	public void testCreateProducts() {
		Product productA = new Product("A");
		Product productB = new Product("B");
		Product productC = new Product("C");
		
		
		productA.setName("Apple");
		productB.setName("Biscuit");
		productC.setName("Band-Aid");
		
		productA.setPrice(3.5);
		productB.setPrice(4.5);
		productC.setPrice(1.5);
		
		List<Product> products = new ArrayList<>();
		products.add(productA);products.add(productB);products.add(productC);
		assertTrue(service.createProducts(products));
	}

	@Test
	public void testProductById() {
		assertNotNull(service.orderById(prodIds.get(0)));
	}

	@Test
	public void testFindAllOrders() {
		assertTrue(service.findAllOrders().size() > 0);
	}

	@Test
	public void testFindAllProducts() {
		assertTrue(((service.findAllProducts()).size()== prodIds.size()));
	}

}

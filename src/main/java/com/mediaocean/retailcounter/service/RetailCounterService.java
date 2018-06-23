package com.mediaocean.retailcounter.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mediaocean.retailcounter.entity.LineItemEntity;
import com.mediaocean.retailcounter.entity.OrderEntity;
import com.mediaocean.retailcounter.entity.ProductEntity;
import com.mediaocean.retailcounter.model.Bill;
import com.mediaocean.retailcounter.model.LineItem;
import com.mediaocean.retailcounter.model.Order;
import com.mediaocean.retailcounter.model.Product;
import com.mediaocean.retailcounter.repository.OrderRepository;
import com.mediaocean.retailcounter.repository.ProductRepository;
import com.mediaocean.retailcounter.util.MapperUtil;

@Service
public class RetailCounterService {

	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private MapperUtil mapper;
	
	public Integer createOrder(List<LineItem> items) {
		OrderEntity orderEntity = mapper.toOrderEntity(items);
		populateTaxAndTotalAmounts(orderEntity);
		return orderRepo.createOrder(orderEntity);
	} 
	
	public Bill findBill(Integer orderId) {
		OrderEntity orderEntity = orderEntityById(orderId);
		if(orderEntity != null)
			return mapper.fromOrderEntity(orderEntity);
		return null;
		
	}
	
	private void populateTaxAndTotalAmounts(OrderEntity orderEntity) {
		List<LineItemEntity> lineItems = orderEntity.getLineItems();
		lineItems.stream().forEach(le -> {
			calculateTaxAndTotalAmountForLineItem(le);
		});
	}

	private void calculateTaxAndTotalAmountForLineItem(LineItemEntity le) {
		ProductEntity product = productRepo.findProductById(le.getProductId());
		Double taxRate = product.getTaxRate();
		le.setItemPrice(product.getProductPrice());
		le.setItemTaxAmount((product.getProductPrice() * taxRate)/100);
		le.setItemTotalAmount(le.getItemPrice() + le.getItemTaxAmount());
	}
	
	private OrderEntity orderEntityById(Integer orderId) {
		return orderRepo.findOrder(orderId);
	}
	
	public Order orderById(Integer orderId) {
		OrderEntity orderEntity = orderEntityById(orderId);
		if(orderEntity != null) {
			return mapper.toOrder(orderEntity);
		}
		return null;
	}
	
	public Integer createProduct(Product product) {
		return productRepo.createProduct(mapper.toProductEntity(product));
	}
	
	public boolean createProducts(List<Product> products) {
		List<ProductEntity> prodEntities = products.stream().map(mapper::toProductEntity).collect(Collectors.toList());
		return productRepo.createProducts(prodEntities);
	}
	
	public Product productById(Integer productId) {
		ProductEntity prodEntity = productRepo.findProductById(productId);
		if(prodEntity != null) {
			return mapper.toProduct(prodEntity);
		}
		return null;
	}
	
	public List<Order> findAllOrders() {
		List<OrderEntity> orderEntities = orderRepo.findAllOrders();
		if(!CollectionUtils.isEmpty(orderEntities)) {
			return orderEntities.stream().map(mapper::toOrder).collect(Collectors.toList());
		}
		return null;
	}
	
	public List<Product> findAllProducts() {
		List<ProductEntity> productEntities = productRepo.findAllProducts();
		if(!CollectionUtils.isEmpty(productEntities)) {
			return productEntities.stream().map(mapper::toProduct).collect(Collectors.toList());
		}
		return null;
	}
	
}

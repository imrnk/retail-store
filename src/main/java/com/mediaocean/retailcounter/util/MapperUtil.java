package com.mediaocean.retailcounter.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mediaocean.retailcounter.entity.LineItemEntity;
import com.mediaocean.retailcounter.entity.OrderEntity;
import com.mediaocean.retailcounter.entity.ProductEntity;
import com.mediaocean.retailcounter.model.Bill;
import com.mediaocean.retailcounter.model.LineItem;
import com.mediaocean.retailcounter.model.Order;
import com.mediaocean.retailcounter.model.Product;
import com.mediaocean.retailcounter.service.RetailCounterService;

@Component
public class MapperUtil {

	@Autowired
	private RetailCounterService service;
	
	public LineItemEntity toLineItemEntity(LineItem item) {
		LineItemEntity itemEntity = new LineItemEntity();
		itemEntity.setItemQuantity(item.getItemQuantity());
		itemEntity.setLineId(item.getLineId());
		itemEntity.setProductId(item.getProductId());
		
		return itemEntity;
	}
	
	
	public OrderEntity toOrderEntity(List<LineItem> items) {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setLineItems(items.
				stream().
				map(this::toLineItemEntity).collect(Collectors.toList()));
		
		//orderEntity.getLineItems().forEach(item -> item.setOrder(orderEntity));
		
		return orderEntity;
	}
	
	public OrderEntity toOrderEntity(Order order) {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setLineItems(order.getItems().
				stream().
				map(this::toLineItemEntity).collect(Collectors.toList()));
		
		orderEntity.getLineItems().forEach(item -> item.setOrder(orderEntity));
		
		return orderEntity;
		
	}
	
	public ProductEntity toProductEntity(Product product) {
		ProductEntity prodEntity = new ProductEntity();
		prodEntity.setProductCategory(product.getCategory().categoryType());
		prodEntity.setProductPrice(product.getPrice());
		prodEntity.setTaxRate(product.getCategory().taxRate);
		prodEntity.setProductName(product.getName());
		prodEntity.setProductDescription(product.getDescription());
		return prodEntity;
	}
	
	public Order toOrder(OrderEntity orderEntity) {
		Order order = new Order();
		order.setOrderId(orderEntity.getOrderId());
		order.setItems(orderEntity.getLineItems()
				.stream()
				.map(this::toLineItem)
				.collect(Collectors.toList()));
		
		return order;
	}
	
	public LineItem toLineItem(LineItemEntity leEntity) {
		LineItem item = new LineItem();
		item.setLineId(leEntity.getLineId());
		item.setItemQuantity(leEntity.getItemQuantity());
		item.setItemAmount(leEntity.getItemPrice());
		item.setProductId(leEntity.getProductId());
		item.setTaxAmount(leEntity.getItemTaxAmount());
		item.setTotalLineItemAmount(leEntity.getItemTotalAmount());
		
		Product product = service.productById(leEntity.getProductId());
		if(product != null) {
			item.setTaxRate(product.getTaxRate());
			item.setProductPrice(product.getPrice());
		}
		
		return item;
	}
	
	public Product toProduct(ProductEntity prodEntity ) {
		Product prod = new Product(prodEntity.getProductCategory());
		prod.setProductId(prodEntity.getProductId());
		prod.setName(prodEntity.getProductName());
		prod.setPrice(prodEntity.getProductPrice());
		prod.setDescription(prodEntity.getProductDescription());
		return prod;
	}
	
	
	public Bill fromOrderEntity(OrderEntity orderEntity) {
		Bill bill = new Bill();
		List<LineItemEntity> lineItems = orderEntity.getLineItems();
		bill.setTotalLineItemAmount(lineItems
				.stream()
				.collect(Collectors
						.summingDouble(LineItemEntity::getItemPrice)));
		
		bill.setTotalTaxAmount(lineItems
				.stream()
				.collect(Collectors
						.summingDouble(LineItemEntity::getItemTaxAmount)));
		
		bill.setTotalBillAmount(lineItems
				.stream()
				.collect(Collectors
						.summingDouble(LineItemEntity::getItemTotalAmount)));
		
		bill.setLineItems(lineItems
				.stream()
				.map(li -> toLineItem(li))
					.collect(Collectors.toList()));
		
		return bill;
	}
	
	public LineItemEntity updateLineItem(LineItemEntity oldItem, LineItemEntity newItem) {
		oldItem = newItem;
		return oldItem;
	}
	
}

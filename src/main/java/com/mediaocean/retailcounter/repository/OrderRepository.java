package com.mediaocean.retailcounter.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mediaocean.retailcounter.entity.LineItemEntity;
import com.mediaocean.retailcounter.entity.OrderEntity;

@Component
public class OrderRepository {

	@Autowired
	private EntityManager em;
	
	
	public Integer createOrder(OrderEntity orderEntity) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		OrderEntity savedOrder = em.merge(orderEntity);
		transaction.commit();
		return savedOrder.getOrderId();
	}
	
	public Integer updateOrder(Integer orderId, List<LineItemEntity> lineItems) {
		
		lineItems.forEach(l -> {updateOrder(orderId, l);});
		return orderId;
	}
	
	private Integer updateOrder(Integer orderId, LineItemEntity lineItem) {
		OrderEntity foundOrder = findOrder(orderId);
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		foundOrder.addLineItem(lineItem);
		em.merge(foundOrder);
		transaction.commit();
		return foundOrder.getOrderId();
	}

	public OrderEntity findOrder(Integer orderId) {
		OrderEntity foundOrder = em.find(OrderEntity.class, orderId);
		return foundOrder;
	}
	
	public List<LineItemEntity> findAllItems(Integer orderId) {
		OrderEntity foundOrder = findOrder(orderId);
		if(foundOrder != null)
			return findOrder(orderId).getLineItems();
		return null;
	}
	
	public List<OrderEntity> findAllOrders(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<OrderEntity> cq = cb.createQuery(OrderEntity.class);
		Root<OrderEntity> root = cq.from(OrderEntity.class);
		cq.select(root);
		List<OrderEntity> allOrders = em.createQuery(cq).getResultList();
		return allOrders;
	}
}

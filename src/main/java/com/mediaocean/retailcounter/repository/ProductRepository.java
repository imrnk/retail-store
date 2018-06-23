package com.mediaocean.retailcounter.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mediaocean.retailcounter.entity.ProductEntity;

@Component
public class ProductRepository {

	@Autowired
	private EntityManager em;
	
	
	public Integer createProduct(ProductEntity productEntity) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		ProductEntity savedProduct = em.merge(productEntity);
		transaction.commit();
		return savedProduct.getProductId();
	}
	
	public boolean createProducts(List<ProductEntity> productEntities) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		productEntities.forEach(em::merge);
		transaction.commit();
		return true;
	}
	
	public ProductEntity findProductById(Integer productId) {
		return em.find(ProductEntity.class, productId);
		
	}
	
	public List<ProductEntity> findAllProducts(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ProductEntity> cq = cb.createQuery(ProductEntity.class);
		Root<ProductEntity> root = cq.from(ProductEntity.class);
		cq.select(root);
		List<ProductEntity> allProducts = em.createQuery(cq).getResultList();
		return allProducts;
	}
}

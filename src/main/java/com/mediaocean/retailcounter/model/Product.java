package com.mediaocean.retailcounter.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.mediaocean.retailcounter.util.CategoryFactory;

public class Product {

	private Integer productId;
	
	@JsonIgnore
	private ProductCategory category;
	
	private String prodCategory;
	
	private String name;
	
	private String description;
	
	private Double price;
	
	public Product() {}
	
	public Product(String category) {
		this.category = CategoryFactory.getProductCategory(category);
		this.prodCategory = category;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProdCategory() {
		return prodCategory;
	}

	public void setProdCategory(String prodCategory) {
		this.category = CategoryFactory.getProductCategory(prodCategory);
		this.prodCategory = prodCategory;
	}
	
	public Double getTaxRate() {
		return this.category.getTaxRate();
	}
}

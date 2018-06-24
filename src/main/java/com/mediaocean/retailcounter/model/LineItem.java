package com.mediaocean.retailcounter.model;

public class LineItem {

	
	private Integer lineId;
	
	private Integer productId;
	
	private int itemQuantity;
	
	private Double itemAmount;
	
	private Double taxRate;
	
	private Double productPrice;
	
	private Double taxAmount;
	
	private Double totalLineItemAmount;

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Double getItemAmount() {
		return itemAmount;
	}

	public void setItemAmount(Double itemAmount) {
		this.itemAmount = itemAmount;
	}

	public Double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Double getTotalLineItemAmount() {
		return totalLineItemAmount;
	}

	public void setTotalLineItemAmount(Double totalLineItemAmount) {
		this.totalLineItemAmount = totalLineItemAmount;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	
	
	
	
}

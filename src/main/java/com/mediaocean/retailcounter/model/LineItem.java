package com.mediaocean.retailcounter.model;

public class LineItem {

	
	private Integer lineId;
	
	private Integer productId;
	
	private int itemQuantity;
	
	private Double itemPrice;
	
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

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
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
	
	
	
	
}

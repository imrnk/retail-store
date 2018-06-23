package com.mediaocean.retailcounter.model;

import java.util.List;

public class Bill {

	private Integer billId;
	
	private Double totalBillAmount;
	
	private Double totalLineItemAmount;
	
	private Double totalTaxAmount;
	
	private List<LineItem> lineItems;

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public Double getTotalBillAmount() {
		return totalBillAmount;
	}

	public void setTotalBillAmount(Double totalBillAmount) {
		this.totalBillAmount = totalBillAmount;
	}

	public Double getTotalTaxAmount() {
		return totalTaxAmount;
	}

	public void setTotalTaxAmount(Double totalTaxAmount) {
		this.totalTaxAmount = totalTaxAmount;
	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public Double getTotalLineItemAmount() {
		return totalLineItemAmount;
	}

	public void setTotalLineItemAmount(Double totalLineItemAmount) {
		this.totalLineItemAmount = totalLineItemAmount;
	}
	
	
}

package com.mediaocean.retailcounter.model;

public abstract class ProductCategory {
	
	public Double taxRate;
	
	public abstract void setTaxRate(Double taxRate) ;
	
	public abstract String categoryType();

	public Double getTaxRate() {
		return taxRate;
	}
}

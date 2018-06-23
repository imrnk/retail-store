package com.mediaocean.retailcounter.model;

public class CategoryC extends ProductCategory{

	public CategoryC() {
		taxRate = CategoryTaxRate.C.getRate();
	}
	
	@Override
	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}
	
	@Override
	public String categoryType() {
		return CategoryTaxRate.C.getName();
	}
	

}

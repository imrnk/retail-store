package com.mediaocean.retailcounter.model;

public class CategoryA extends ProductCategory {
	
	public CategoryA(){
		taxRate = CategoryTaxRate.A.getRate();
	}

	@Override
	public String categoryType() {
		return CategoryTaxRate.A.getName();
	}


	@Override
	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

}

package com.mediaocean.retailcounter.model;

public class CategoryB extends ProductCategory {

	public CategoryB(){
		taxRate = CategoryTaxRate.B.getRate();
	}
	@Override
	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;

	}

	@Override
	public String categoryType() {
		return CategoryTaxRate.B.getName();
	}

}

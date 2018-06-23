package com.mediaocean.retailcounter.model;

public enum CategoryTaxRate {

	A("A", 10.0), B("B", 20.0), C("C", 0.0);
	
	private Double rate;
	private String name;
	
	CategoryTaxRate(String name, Double rate){
		this.name = name;
		this.rate = rate;
	}
	
	public Double getRate() {
		return this.rate;
	}

	public String getName() {
		return name;
	}
	
	
}

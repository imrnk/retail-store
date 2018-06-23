package com.mediaocean.retailcounter.util;

import com.mediaocean.retailcounter.model.CategoryA;
import com.mediaocean.retailcounter.model.CategoryB;
import com.mediaocean.retailcounter.model.CategoryC;
import com.mediaocean.retailcounter.model.ProductCategory;

public final class CategoryFactory {

	
	public static ProductCategory getProductCategory(String category) {
		if(category.equalsIgnoreCase("A"))
		{
			return new CategoryA();
		} else if(category.equalsIgnoreCase("B"))
		{
			return new CategoryB();
		} else if(category.equalsIgnoreCase("C"))
		{
			return new CategoryC();
		} else {
			throw new RuntimeException("Not a valid product category: " + category);
		}
		
	}
}

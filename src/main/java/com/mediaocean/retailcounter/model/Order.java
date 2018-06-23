package com.mediaocean.retailcounter.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.common.util.CollectionUtils;

public class Order {

	private Integer orderId;
	
	private List<LineItem> items;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public List<LineItem> getItems() {
		return items;
	}

	public void setItems(List<LineItem> items) {
		this.items = items;
	}
	
	public void addItem(LineItem item) {
		if(CollectionUtils.isEmpty(this.items))
			this.items = new ArrayList<>();
		
		this.items.add(item);
	}
	
}

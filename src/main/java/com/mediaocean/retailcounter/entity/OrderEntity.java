package com.mediaocean.retailcounter.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.cxf.common.util.CollectionUtils;

@Entity
@Table(name="order_items")
public class OrderEntity implements Serializable{
	
	private static final long serialVersionUID = 980033327532577242L;
	
	@Id
	@Column(name="order_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer orderId;
	
	@OneToMany(mappedBy="order")
	private List<LineItemEntity> lineItems;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public List<LineItemEntity> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItemEntity> lineItems) {
		this.lineItems = lineItems;
	}
	
	public void addLineItem(LineItemEntity lineItem) {
		if(CollectionUtils.isEmpty(this.lineItems)) 
			this.lineItems = new ArrayList<> ();
		this.lineItems.add(lineItem);
	}
	
	
}

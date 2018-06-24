package com.mediaocean.retailcounter.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	@OneToMany(mappedBy="order", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
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
		this.lineItems.forEach(le -> le.setOrder(this));
	}
	
	public void addLineItem(LineItemEntity lineItem) {
		if(CollectionUtils.isEmpty(this.lineItems)) 
			this.lineItems = new ArrayList<> ();
		lineItem.setOrder(this);
		this.lineItems.add(lineItem);
		
	}

	@Override
	public String toString() {
		return "OrderEntity [orderId=" + orderId + ", lineItems=" + lineItems + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderEntity other = (OrderEntity) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		return true;
	}
	
	
}

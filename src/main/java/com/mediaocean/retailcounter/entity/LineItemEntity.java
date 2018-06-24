package com.mediaocean.retailcounter.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="line_item")
public class LineItemEntity implements Serializable{

	private static final long serialVersionUID = 8566312509353905685L;
	
	@Id
	@Column(name="line_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer lineId;
	
	@Column(name="product_id")
	private Integer productId;
	
	@Column(name="order_id")
	private Integer orderId;
	
	@Column(name="quantity")
	private Integer itemQuantity;
	
	@Column(name="item_price")
	private Double itemPrice;
	
	@Column(name="item_tax_amount")
	private Double itemTaxAmount;
	
	@Column
	private Double itemTotalAmount;
	
	@ManyToOne
	@JoinColumn(name="order_id", insertable=false, updatable=false)
	private OrderEntity order;

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}

	public Integer getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Double getItemTaxAmount() {
		return itemTaxAmount;
	}

	public void setItemTaxAmount(Double itemTaxAmount) {
		this.itemTaxAmount = itemTaxAmount;
	}

	public Double getItemTotalAmount() {
		return itemTotalAmount;
	}

	public void setItemTotalAmount(Double itemTotalAmount) {
		this.itemTotalAmount = itemTotalAmount;
	}

	@Override
	public String toString() {
		return "LineItemEntity [lineId=" + lineId + ", productId=" + productId + ", itemQuantity=" + itemQuantity
				+ ", itemPrice=" + itemPrice + ", itemTaxAmount=" + itemTaxAmount + ", itemTotalAmount="
				+ itemTotalAmount + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lineId == null) ? 0 : lineId.hashCode());
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
		LineItemEntity other = (LineItemEntity) obj;
		if (lineId == null) {
			if (other.lineId != null)
				return false;
		} else if (!lineId.equals(other.lineId))
			return false;
		return true;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	
}

package com.cognizant.RestoraApp.bean;

import java.time.LocalDate;

public class OrderBean {

	private Integer orderId;
	private LocalDate orderDate;
	private Double orderTotal;
	private String orderPayment;
	private Integer orderItemCount;

	public OrderBean() {
		super();
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public Double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getOrderPayment() {
		return orderPayment;
	}

	public void setOrderPayment(String orderPayment) {
		this.orderPayment = orderPayment;
	}

	public Integer getOrderItemCount() {
		return orderItemCount;
	}

	public void setOrderItemCount(Integer orderItemCount) {
		this.orderItemCount = orderItemCount;
	}

	@Override
	public String toString() {
		return "OrderBean [orderId=" + orderId + ", orderDate=" + orderDate + ", orderTotal=" + orderTotal
				+ ", orderPayment=" + orderPayment + ", orderItemCount=" + orderItemCount + "]";
	}

}

package com.cognizant.RestoraApp.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "RESTORA_ORDER")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_ID_GEN")
	@SequenceGenerator(name = "ORDER_ID_GEN", sequenceName = "ORDER_ID_SEQ", allocationSize = 1, initialValue = 100)
	@Column(name = "ORDER_ID")
	private Integer orderId;

	@Column(name = "ORDER_DATE")
	private LocalDate orderDate;

	@Column(name = "ORDER_TOTAL")
	private Double orderTotal;

	@Column(name = "ORDER_PAYMENT")
	private String orderPayment;

	@Column(name = "ORDER_ITEMCOUNT")
	private Integer orderItemCount;

	public Order() {
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

	// Mapping (User-Order)

	@ManyToOne
	@JoinColumn(name = "USERID")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// Mapping (Order-Item)

	@ManyToMany
	@JoinTable(name = "RESTORA_ORDER_ITEM", joinColumns = @JoinColumn(name = "ORDER_ID"), inverseJoinColumns = @JoinColumn(name = "FOOD_ITEM_ID"))
	private Set<Item> item = new HashSet<>();

	public Set<Item> getItem() {
		return item;
	}

	public void setItem(Set<Item> item) {
		this.item = item;
	}

}

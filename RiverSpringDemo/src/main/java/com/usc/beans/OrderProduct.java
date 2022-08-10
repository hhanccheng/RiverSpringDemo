package com.usc.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // jpa or mapping
@Table(name = "usc_order_product")
public class OrderProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ORDER_PRODUCT_SEQ")
	@SequenceGenerator(name = "ORDER_PRODUCT_SEQ",sequenceName = "USC_ORDER_PRODUCT_SEQ",allocationSize = 1)
	private int id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id")
	@JsonIgnore
	Order order;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	@JsonIgnore
	Product product;
	@Column
	private int amount;
	
	@Column
	private int productid;
	


	public OrderProduct() {
		super();
	}

	
	public int getProductid() {
		return productid;
	}


	public void setProductid(int productid) {
		this.productid = productid;
	}


	public OrderProduct(int id, Order order, Product product, int amount, int productid) {
		super();
		this.id = id;
		this.order = order;
		this.product = product;
		this.amount = amount;
		this.productid = productid;
	}

	

	public OrderProduct(int amount, int productid) {
		super();
		this.amount = amount;
		this.productid = productid;
	}


	public OrderProduct(int id, Order order, Product product, int amount) {
		super();
		this.id = id;
		this.order = order;
		this.product = product;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "OrderProduct [id=" + id + ", order=" + order + ", product=" + product + ", amount=" + amount + "]";
	}
	
}

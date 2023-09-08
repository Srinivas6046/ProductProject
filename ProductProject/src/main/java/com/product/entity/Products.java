package com.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="products_dtls")
public class Products {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="prod_id")
	private long id;
	@Column(name="prod_Name")
	private String productName;
	@Column(name="prod_description")
	private String description;
	@Column(name="prod_price")
	private String price;
	@Column(name="prod_quantity")
	private String quantity;
	public Products() {
		super();
	}
	public Products(long id, String productName, String description, String price, String quantity) {
		super();
		this.id = id;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Products [id=" + id + ", productName=" + productName + ", description=" + description + ", price="
				+ price + ", quantity=" + quantity + ", getId()=" + getId() + ", getProductName()=" + getProductName()
				+ ", getDescription()=" + getDescription() + ", getPrice()=" + getPrice() + ", getQuantity()="
				+ getQuantity() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}

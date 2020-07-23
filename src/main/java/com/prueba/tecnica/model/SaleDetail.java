package com.prueba.tecnica.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class SaleDetail {

	private Integer quantity;
	private Integer amount;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}

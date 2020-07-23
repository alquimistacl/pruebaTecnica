package com.prueba.tecnica.model;

import java.util.Date;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Sale {

	private Date date;
	private Integer totalAmount;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

}

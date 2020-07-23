package com.prueba.tecnica.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Product {

	private String name;
	private Integer cost;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

}

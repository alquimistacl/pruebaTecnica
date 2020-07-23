package com.prueba.tecnica.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class ProductType {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

package com.prueba.tecnica.model;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

@MappedSuperclass
public class Product {

	@ApiModelProperty("Nombre del producto")
	@NotBlank(message = "El nombre es obligatorio")
	private String name;

	@ApiModelProperty("Costo del producto")
	@NotBlank(message = "El costo del producto es obligatorio")
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

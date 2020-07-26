package com.prueba.tecnica.model;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

@MappedSuperclass
public class ProductType {

	@ApiModelProperty("Nombre del tipo de producto")
	@NotBlank(message = "El nombre del tipo de producto es obligatorio")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

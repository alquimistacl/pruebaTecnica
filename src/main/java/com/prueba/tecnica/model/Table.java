package com.prueba.tecnica.model;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

@MappedSuperclass
public class Table {

	@ApiModelProperty("Ubicacion de la mesa ")
	@NotBlank(message = "La ubicacion de la mesa es obligatoria")
	private String ubicacionMesa;

	public String getUbicacionMesa() {
		return ubicacionMesa;
	}

	public void setUbicacionMesa(String ubicacionMesa) {
		this.ubicacionMesa = ubicacionMesa;
	}

}

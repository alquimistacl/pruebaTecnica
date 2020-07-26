package com.prueba.tecnica.model;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

@MappedSuperclass
public class AppUser {

	@ApiModelProperty("Nombre del usuario")
	@NotBlank(message = "El nombre es obligatorio")
	private String name;

	@ApiModelProperty("Contraseña del usuario")
	@NotBlank(message = "La contraseña es obligatoria")
	private String pass;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}

package com.prueba.tecnica.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Table {

	private String ubicacionMesa;

	public String getUbicacionMesa() {
		return ubicacionMesa;
	}

	public void setUbicacionMesa(String ubicacionMesa) {
		this.ubicacionMesa = ubicacionMesa;
	}

}

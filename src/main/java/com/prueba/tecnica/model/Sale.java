package com.prueba.tecnica.model;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

@MappedSuperclass
public class Sale {

	@ApiModelProperty("Fecha de la venta")
	@NotBlank(message = "La fecha es obligatoria")
	private Date date;

	@ApiModelProperty("Monto total de la venta")
	@NotBlank(message = "El monto total de la venta es obligatorio")
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

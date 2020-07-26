package com.prueba.tecnica.model;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Información del detalle de la venta")
@MappedSuperclass
public class SaleDetail {

	@ApiModelProperty("Cantidad del producto")
	@NotBlank(message = "La cantidad del producto es obligatoria")
	private Integer quantity;
	
	@ApiModelProperty("Monto de los productos vendidos")
	@NotBlank(message = "El monto es obligatorio")
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

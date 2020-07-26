package com.prueba.tecnica.dto;

/**
 * Allows to input new detail sales
 * @author Luis San Martin
 *
 */
public class SaleDetailDto {
	private Integer quantity;
	private Integer amount;

	private Long productId;

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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}

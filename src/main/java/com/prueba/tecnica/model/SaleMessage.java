package com.prueba.tecnica.model;

public class SaleMessage {

	private Long tableId;
	private Sale sale;

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

}

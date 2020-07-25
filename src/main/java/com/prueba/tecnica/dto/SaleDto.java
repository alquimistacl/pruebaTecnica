package com.prueba.tecnica.dto;

import java.util.Date;
import java.util.List;

public class SaleDto {

	private Date date;
	private Integer totalAmount;
	private Long tableId;

	private List<SaleDetailDto> saleDetail;

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

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public List<SaleDetailDto> getSaleDetail() {
		return saleDetail;
	}

	public void setSaleDetail(List<SaleDetailDto> saleDetail) {
		this.saleDetail = saleDetail;
	}

}

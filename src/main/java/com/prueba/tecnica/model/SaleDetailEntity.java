package com.prueba.tecnica.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "sale_detail")
@Entity
public class SaleDetailEntity extends SaleDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JoinColumn(name = "sale_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private SaleEntity sale;

	@JoinColumn(name = "product_id")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private ProductEntity product;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SaleEntity getSale() {
		return sale;
	}

	public void setSale(SaleEntity sale) {
		this.sale = sale;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

}

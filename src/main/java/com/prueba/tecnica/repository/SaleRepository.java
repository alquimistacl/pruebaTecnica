package com.prueba.tecnica.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prueba.tecnica.model.SaleEntity;

public interface SaleRepository extends CrudRepository<SaleEntity, Long> {

	List<SaleEntity> findByDate(Date saleDate);
	
}

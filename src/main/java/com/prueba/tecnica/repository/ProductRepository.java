package com.prueba.tecnica.repository;

import org.springframework.data.repository.CrudRepository;

import com.prueba.tecnica.model.ProductEntity;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

}

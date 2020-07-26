package com.prueba.tecnica.repository;

import org.springframework.data.repository.CrudRepository;

import com.prueba.tecnica.model.TableEntity;

public interface TableRepository extends CrudRepository<TableEntity, Long> {

}

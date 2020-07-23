package com.prueba.tecnica.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prueba.tecnica.model.AppUserEntity;

@Repository
public interface AppUserRepository extends CrudRepository<AppUserEntity, Long> {
	AppUserEntity findByName(String username);
}

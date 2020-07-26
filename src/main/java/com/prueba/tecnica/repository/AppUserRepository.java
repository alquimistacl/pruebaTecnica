package com.prueba.tecnica.repository;

import org.springframework.data.repository.CrudRepository;

import com.prueba.tecnica.model.AppUserEntity;

public interface AppUserRepository extends CrudRepository<AppUserEntity, Long> {
	AppUserEntity findByName(String username);
}

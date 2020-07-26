package com.prueba.tecnica.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tecnica.dto.SaleDto;
import com.prueba.tecnica.model.SaleEntity;
import com.prueba.tecnica.service.SalesService;

import io.swagger.annotations.ApiImplicitParam;

/**
 * Controller to handle the sale operations
 * @author Luis San Martin
 *
 */
@RestController
public class SaleController {

	@Autowired
	private SalesService service;

	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
	@PostMapping("/sales")
	public ResponseEntity<String> addSale(@Valid @RequestBody SaleDto saleDto) {
		service.registerSale(saleDto);
		return ResponseEntity.ok("Venta registrada");
	}

	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
	@GetMapping(path = "/sales", params = { "saleDate" }, produces = { "application/json" })
	public ResponseEntity<List<SaleEntity>> getSales(@Valid @RequestParam("saleDate") Date saleDate) {
		List<SaleEntity> salesByDay = service.listSalesByDay(saleDate);
		return ResponseEntity.ok(salesByDay);
	}

}

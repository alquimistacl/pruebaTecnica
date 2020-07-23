package com.prueba.tecnica.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tecnica.model.Sale;
import com.prueba.tecnica.model.SaleEntity;
import com.prueba.tecnica.service.SalesService;

import io.swagger.annotations.ApiImplicitParam;

@RestController
public class SaleController {

	@Autowired
	private SalesService service;

	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
	@PostMapping("/sale/table/${tableId}")
	public ResponseEntity<String> addSale(Sale sale, Long tableId) {
		Long registerSale = service.registerSale(sale, tableId);
		return ResponseEntity.ok("Venta id " + registerSale + " registrada");
	}

	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
	@GetMapping("/sale/${saleDate}")
	public ResponseEntity<List<SaleEntity>> getSales(Date saleDate) {
		List<SaleEntity> salesByDay = service.listSalesByDay(saleDate);
		return ResponseEntity.ok(salesByDay);
	}

}

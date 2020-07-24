package com.prueba.tecnica.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.tecnica.model.Sale;
import com.prueba.tecnica.model.SaleEntity;
import com.prueba.tecnica.model.TableEntity;
import com.prueba.tecnica.repository.SaleRepository;
import com.prueba.tecnica.repository.TableRepository;

@Service
public class SalesService {

	Logger logger = LogManager.getLogger(SalesService.class);

	@Autowired
	private TableRepository tableRepository;

	@Autowired
	private SaleRepository saleRepository;

	public Long registerSale(Sale sale, Long tableId) {

		SaleEntity saleEntity = new SaleEntity();

		Optional<TableEntity> tableEntityOptional = tableRepository.findById(tableId);
		if (tableEntityOptional.isPresent()) {
			saleEntity.setTable(tableEntityOptional.get());
		} else {
			logger.error("Table not found");
			// generates exception
		}

		saleEntity.setDate(sale.getDate());
		saleEntity.setTotalAmount(sale.getTotalAmount());
		SaleEntity savedSale = saleRepository.save(saleEntity);

		if (null == savedSale.getId()) {
			logger.error("There was a problem saving table data");
			// generates exception
		}

		return savedSale.getId();
	}

	public List<SaleEntity> listSalesByDay(Date saleDate) {
		List<SaleEntity> salesByDate = saleRepository.findByDate(saleDate);

		if (salesByDate.isEmpty()) {
			logger.error("Date {} not found", saleDate);
			// generates exception
		}

		return salesByDate;
	}
}

package com.prueba.tecnica.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.prueba.tecnica.model.Sale;
import com.prueba.tecnica.model.SaleEntity;
import com.prueba.tecnica.model.SaleMessage;
import com.prueba.tecnica.model.TableEntity;
import com.prueba.tecnica.repository.SaleRepository;
import com.prueba.tecnica.repository.TableRepository;

@Service
public class SalesService {

	private static final String QUEUE_REGISTERED = "Sale registered in the queue";

	Logger logger = LogManager.getLogger(SalesService.class);

	@Autowired
	private TableRepository tableRepository;

	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	public Boolean registerSale(Sale sale, Long tableId) {

		SaleMessage message = new SaleMessage();
		message.setSale(sale);
		message.setTableId(tableId);

		Boolean response = MockQueue.putMessage(message);

		QueueEvent queueEvent = new QueueEvent(QUEUE_REGISTERED);
		publisher.publishEvent(queueEvent);

		return response;
	}

	@EventListener
	public void registerSaleDB(QueueEvent event) {

		SaleMessage message = MockQueue.getMessage();

		SaleEntity saleEntity = new SaleEntity();

		Optional<TableEntity> tableEntityOptional = tableRepository.findById(message.getTableId());
		if (tableEntityOptional.isPresent()) {
			saleEntity.setTable(tableEntityOptional.get());
		} else {
			logger.error("Table not found");
			return;
			// generates exception
		}

		Sale sale = message.getSale();
		saleEntity.setDate(sale.getDate());
		saleEntity.setTotalAmount(sale.getTotalAmount());
		SaleEntity savedSale = saleRepository.save(saleEntity);

		if (null == savedSale.getId()) {
			logger.error("There was a problem saving table data");
			return;
			// generates exception
		}

		logger.info("Sale registered in the database");
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

package com.prueba.tecnica.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.prueba.tecnica.dto.SaleDetailDto;
import com.prueba.tecnica.dto.SaleDto;
import com.prueba.tecnica.exception.DateNotFoundException;
import com.prueba.tecnica.exception.TableNotFoundException;
import com.prueba.tecnica.model.ProductEntity;
import com.prueba.tecnica.model.SaleDetailEntity;
import com.prueba.tecnica.model.SaleEntity;
import com.prueba.tecnica.model.TableEntity;
import com.prueba.tecnica.repository.ProductRepository;
import com.prueba.tecnica.repository.SaleDetailRepository;
import com.prueba.tecnica.repository.SaleRepository;
import com.prueba.tecnica.repository.TableRepository;

/**
 * Allows to handle the sale operations
 * 
 * @author Luis San Martin
 *
 */
@Service
public class SalesService {

	private static final String QUEUE_REGISTERED = "Sale registered in the queue";

	Logger logger = LogManager.getLogger(SalesService.class);

	@Autowired
	private TableRepository tableRepository;

	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private SaleDetailRepository saleDetailRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	/**
	 * Register a sale in the MockQueue
	 * 
	 * @param saleDto
	 * @return
	 */
	public Boolean registerSale(SaleDto saleDto) {
		Boolean response = MockQueue.putMessage(saleDto);

		QueueEvent queueEvent = new QueueEvent(QUEUE_REGISTERED);
		publisher.publishEvent(queueEvent);

		return response;
	}

	/**
	 * Listens when a new Queue event is triggered
	 * 
	 * @param event
	 */
	@EventListener
	public void registerSaleDB(QueueEvent event) {

		SaleDto saleDto = getMessage();

		SaleEntity saleEntity = new SaleEntity();

		Long tableId = saleDto.getTableId();
		Optional<TableEntity> tableEntityOptional = tableRepository.findById(tableId);
		if (tableEntityOptional.isPresent()) {
			saleEntity.setTable(tableEntityOptional.get());
		} else {
			logger.error("Board id {} not found", tableId);
			throw new TableNotFoundException("El id de la mesa " + tableId + " no fue encontrado");
		}

		Date date = saleDto.getDate();
		logger.info(date);
		saleEntity.setDate(date);

		saleEntity.setTotalAmount(saleDto.getTotalAmount());
		SaleEntity savedSale = saleRepository.save(saleEntity);

		List<SaleDetailDto> saleDetailList = saleDto.getSaleDetail();

		List<SaleDetailEntity> detailEntityList = new ArrayList<SaleDetailEntity>();
		for (SaleDetailDto saleDetailDto : saleDetailList) {
			SaleDetailEntity saleDetailEntity = new SaleDetailEntity();

			Long productId = saleDetailDto.getProductId();
			Optional<ProductEntity> optionalProductEntity = productRepository.findById(productId);
			if (optionalProductEntity.isPresent()) {
				saleDetailEntity.setProduct(optionalProductEntity.get());
			} else {
				logger.error("product id {} not found", productId);
				continue;
			}

			saleDetailEntity.setAmount(saleDetailDto.getAmount());
			saleDetailEntity.setQuantity(saleDetailDto.getQuantity());
			saleDetailEntity.setSale(savedSale);

			detailEntityList.add(saleDetailEntity);
		}

		saleDetailRepository.saveAll(detailEntityList);
		logger.info("Sale registered in the database");
	}

	protected SaleDto getMessage() {
		return MockQueue.getMessage();
	}

	/**
	 * Get the sales by date
	 * 
	 * @param saleDate
	 * @return
	 */
	public List<SaleEntity> listSalesByDay(Date saleDate) {
		List<SaleEntity> salesByDate = saleRepository.findByDate(saleDate);

		if (salesByDate.isEmpty()) {
			logger.error("Date {} not found", saleDate);
			throw new DateNotFoundException("No hay datos para la fecha " + saleDate + " ingresada");
		}

		return salesByDate;
	}
}

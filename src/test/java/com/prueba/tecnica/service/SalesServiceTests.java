package com.prueba.tecnica.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ContextConfiguration;

import com.prueba.tecnica.dto.SaleDetailDto;
import com.prueba.tecnica.dto.SaleDto;
import com.prueba.tecnica.exception.DateNotFoundException;
import com.prueba.tecnica.exception.TableNotFoundException;
import com.prueba.tecnica.model.ProductEntity;
import com.prueba.tecnica.model.ProductTypeEntity;
import com.prueba.tecnica.model.SaleEntity;
import com.prueba.tecnica.model.TableEntity;
import com.prueba.tecnica.repository.ProductRepository;
import com.prueba.tecnica.repository.SaleDetailRepository;
import com.prueba.tecnica.repository.SaleRepository;
import com.prueba.tecnica.repository.TableRepository;
import com.prueba.tecnica.util.TestUtil;

@RunWith(MockitoJUnitRunner.Silent.class)
@ContextConfiguration
public class SalesServiceTests {
	private static final String UBICACION_MESA = "en la terraza a la derecha de la entrada";

	private static final String QUEUE_REGISTERED = "Sale registered in the queue";

	private Date actualDate = new Date();

	@Mock
	private TableRepository tableRepository;

	@Mock
	private ApplicationEventPublisher publisher;

	@Mock
	private SaleRepository saleRepository;

	@Mock
	private SaleDetailRepository saleDetailRepository;

	@Mock
	private ProductRepository productRepository;

	@Autowired
	@Spy
	@InjectMocks
	private SalesService service;

	@Test
	public void shouldRegisterSale() {
		doNothing().when(publisher).publishEvent(any());
		assertTrue(service.registerSale(TestUtil.getSaleDto()));
	}

	@Test
	public void shouldRegisterSaleDB() {
		QueueEvent event = new QueueEvent(QUEUE_REGISTERED);

		SaleDto saleDto = TestUtil.getSaleDto();
		given(service.getMessage()).willReturn(saleDto);

		TableEntity tableEntity = setTableEntity(saleDto);

		SaleEntity savedSaleEntity = new SaleEntity();
		savedSaleEntity.setDate(actualDate);
		savedSaleEntity.setId(1l);
		savedSaleEntity.setTable(tableEntity);
		savedSaleEntity.setTotalAmount(10000);
		given(saleRepository.save(any())).willReturn(savedSaleEntity);

		List<SaleDetailDto> saleDetail = saleDto.getSaleDetail();

		ProductEntity productEntity = new ProductEntity();
		productEntity.setCost(5000);
		productEntity.setId(1l);
		productEntity.setName("lasaña boloñesa");
		ProductTypeEntity type = new ProductTypeEntity();
		type.setId(1l);
		type.setName("plato de fondo");
		productEntity.setType(type);
		Optional<ProductEntity> optionalProduct = Optional.ofNullable(productEntity);
		given(productRepository.findById(saleDetail.get(0).getProductId())).willReturn(optionalProduct);

		service.registerSaleDB(event);
	}


	@Test(expected = TableNotFoundException.class)
	public void shouldNotRegisterSaleDBTableNotFound() {
		QueueEvent event = new QueueEvent(QUEUE_REGISTERED);

		SaleDto saleDto = TestUtil.getSaleDto();
		given(service.getMessage()).willReturn(saleDto);

		service.registerSaleDB(event);
	}

	@Test
	public void shouldNotRegisterSaleDBProductNotPresent() {
		QueueEvent event = new QueueEvent(QUEUE_REGISTERED);

		SaleDto saleDto = TestUtil.getSaleDto();
		given(service.getMessage()).willReturn(saleDto);

		TableEntity tableEntity = setTableEntity(saleDto);
		
		service.registerSaleDB(event);

	}

	@Test
	public void shouldListSalesByDay() {
		List<SaleEntity> saleEntityList = new ArrayList<>();
		SaleEntity saleEntity = new SaleEntity();
		saleEntity.setDate(actualDate);
		saleEntity.setId(1l);
		TableEntity table = new TableEntity();
		table.setId(1l);
		table.setUbicacionMesa(UBICACION_MESA);
		saleEntity.setTable(table);
		saleEntity.setTotalAmount(10000);
		saleEntityList.add(saleEntity);
		given(saleRepository.findByDate(actualDate)).willReturn(saleEntityList);
		List<SaleEntity> listSalesByDay = service.listSalesByDay(actualDate);
		assertEquals(1, listSalesByDay.size());
	}

	@Test(expected = DateNotFoundException.class)
	public void shouldNotListSalesByDay() {
		service.listSalesByDay(actualDate);
	}

	
	private TableEntity setTableEntity(SaleDto saleDto) {
		TableEntity tableEntity = new TableEntity();
		tableEntity.setId(1l);
		tableEntity.setUbicacionMesa(UBICACION_MESA);
		Optional<TableEntity> optionalTableEntity = Optional.ofNullable(tableEntity);
		given(tableRepository.findById(saleDto.getTableId())).willReturn(optionalTableEntity);
		return tableEntity;
	}

}

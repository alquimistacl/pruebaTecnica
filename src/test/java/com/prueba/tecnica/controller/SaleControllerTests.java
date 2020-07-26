package com.prueba.tecnica.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.tecnica.config.JwtAuthenticationEntryPoint;
import com.prueba.tecnica.dto.SaleDto;
import com.prueba.tecnica.model.SaleEntity;
import com.prueba.tecnica.model.TableEntity;
import com.prueba.tecnica.service.JwtTokenService;
import com.prueba.tecnica.service.JwtUserDetailsService;
import com.prueba.tecnica.service.SalesService;
import com.prueba.tecnica.util.JwtTokenUtil;
import com.prueba.tecnica.util.TestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest // (SaleController.class)
public class SaleControllerTests {

	private static final String FAIL_MESSAGE = "Should not generate an exception";

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private JwtUserDetailsService jwtUserService;

	@MockBean
	private JwtTokenUtil jwtTokenUtil;

	@MockBean
	private JwtTokenService jwtTokenService;

	@MockBean
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@MockBean
	private SalesService service;

	@Autowired
	private MockMvc mockMvc;

	private Date actualDate = new Date();

	@WithMockUser
	@Test
	public void shouldAddSale() {

		SaleDto saleDto = TestUtil.getSaleDto();

		String jsonBody;

		try {
			jsonBody = mapper.writeValueAsString(saleDto);
			MvcResult mvcResult = mockMvc.perform(post("/sales").content(jsonBody).accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andReturn();
			assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			fail(FAIL_MESSAGE);
		}
	}

	@WithMockUser
	@Test
	public void shouldGetSales() {
		try {

			List<SaleEntity> saleEntityList = new ArrayList<>();
			saleEntityList.addAll(getSaleEntityList());
			given(service.listSalesByDay(any())).willReturn(saleEntityList);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			String date = dateFormat.format(actualDate);

			MvcResult mvcResult = mockMvc.perform(get("/sales?saleDate=" + date).accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andReturn();
			assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
			assertNotNull(mvcResult.getResponse().getContentAsString());
		} catch (Exception e) {
			fail(FAIL_MESSAGE);
		}

	}

	private List<SaleEntity> getSaleEntityList() {
		List<SaleEntity> list = new ArrayList<>();
		SaleEntity entity = new SaleEntity();
		entity.setDate(actualDate);
		entity.setId(1l);
		TableEntity table = new TableEntity();
		table.setId(1l);
		table.setUbicacionMesa("a la izquierda de la entrada");
		entity.setTable(table);
		entity.setTotalAmount(15000);
		list.add(entity);
		return list;
	}
}

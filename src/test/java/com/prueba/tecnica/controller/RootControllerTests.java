package com.prueba.tecnica.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.tecnica.config.JwtAuthenticationEntryPoint;
import com.prueba.tecnica.model.AppUser;
import com.prueba.tecnica.service.JwtTokenService;
import com.prueba.tecnica.service.JwtUserDetailsService;
import com.prueba.tecnica.service.SalesService;
import com.prueba.tecnica.util.JwtTokenUtil;

@RunWith(SpringRunner.class)
@WebMvcTest // (SaleController.class)
public class RootControllerTests {

	private static final String FAIL_MESSAGE = "Should not generate an exception";

	@MockBean
	private JwtUserDetailsService jwtUserService;

	@MockBean
	private JwtTokenUtil jwtTokenUtil;

	@MockBean
	private JwtTokenService jwtTokenService;

	@MockBean
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SalesService service;

	@Test
	public void shouldLogin() {
		String userJson;

		try {
			AppUser appUser = new AppUser();
			appUser.setName("usuario");
			appUser.setPass("secreto");

			String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c3VhcmlvIiwiZXhwIjoxNTk1NzQ4MzU2LCJpYXQiOjE1OTU3MzAzNTZ9.GU-AflnAcFUWhdl4db24tJdil2mVDQnYX0Gu2a20tjU2FvsgX2vz-uaHV5khoUkxVwU7spHmLerzJPK5VDDhyA";
			given(jwtTokenService.getJwtToken(any())).willReturn(token);

			userJson = mapper.writeValueAsString(appUser);
			MvcResult mvcResult = mockMvc.perform(post("/login").content(userJson).accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andReturn();
			assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
			assertNotNull(mvcResult.getResponse().getContentAsString());
		} catch (Exception e) {
			fail(FAIL_MESSAGE);
		}
	}
}

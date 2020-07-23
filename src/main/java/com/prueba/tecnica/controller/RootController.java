package com.prueba.tecnica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prueba.tecnica.model.AppUser;
import com.prueba.tecnica.service.JwtTokenService;

/**
 * Helper controller to get the token and expose the swagger documentation
 * 
 * @author Luis San Martin
 *
 */
@Controller
@RequestMapping
public class RootController {

	@Autowired
	private JwtTokenService service;

	@GetMapping("/swagger")
	public String swaggerUi() {
		return "redirect:/swagger-ui.html";
	}

	@PostMapping("/login")
	public ResponseEntity<String> processLogin(@RequestBody AppUser appUser) {
		return ResponseEntity.ok(service.getJwtToken(appUser));
	}
}
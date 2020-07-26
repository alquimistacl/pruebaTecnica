package com.prueba.tecnica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.prueba.tecnica.model.AppUser;
import com.prueba.tecnica.util.JwtTokenUtil;

/**
 * Allows to handle jwt operations
 * @author Luis San Martin
 *
 */
@Service
public class JwtTokenService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	public String getJwtToken(AppUser appUser) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(appUser.getName(), appUser.getPass()));

		final UserDetails userDetails = userDetailsService.loadUserByUsername(appUser.getName());

		return jwtTokenUtil.generateToken(userDetails);
	}
}

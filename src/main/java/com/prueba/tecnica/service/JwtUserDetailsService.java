package com.prueba.tecnica.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prueba.tecnica.model.AppUser;
import com.prueba.tecnica.repository.AppUserRepository;

/**
 * Allows to handle jwt user details
 * @author Luis San Martin
 *
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private AppUserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) {
		AppUser appUser = repo.findByName(username);

		if (appUser == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		return new org.springframework.security.core.userdetails.User(appUser.getName(), appUser.getPass(),
				new ArrayList<>());
	}
}
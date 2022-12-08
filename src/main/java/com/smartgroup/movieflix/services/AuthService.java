package com.smartgroup.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartgroup.movieflix.entities.User;
import com.smartgroup.movieflix.repositories.UserRepository;
import com.smartgroup.movieflix.services.exceptions.ForbiddenException;
import com.smartgroup.movieflix.services.exceptions.UnauthorizedException;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public User authenticated() {
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			return userRepository.findByEmail(username);
		} catch (Exception e) {
			throw new UnauthorizedException("Invalid user!");
		}
	}
	
	public void validateSelfOrAdmin(Long id) {
		User user = authenticated();
		if(!user.getId().equals(id) && !user.hasRole("ROLE_MEMBER")) {
			throw new ForbiddenException("Access denied!");
		}
	}
	
}

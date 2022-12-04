package com.smartgroup.movieflix.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartgroup.movieflix.dto.UserDTO;
import com.smartgroup.movieflix.entities.User;
import com.smartgroup.movieflix.repositories.UserRepository;
import com.smartgroup.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		User user = userOptional.orElseThrow(
				() -> new ResourceNotFoundException("Entity not found!"));
		return new UserDTO(user);
	}
	
}

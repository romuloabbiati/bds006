package com.smartgroup.movieflix.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.smartgroup.movieflix.dto.ReviewDTO;
import com.smartgroup.movieflix.services.ReviewService;

@RestController
@RequestMapping(path = "/reviews")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@PreAuthorize("hasAnyRole('MEMBER')")
	@PostMapping
	public ResponseEntity<ReviewDTO> insert(@Valid @RequestBody ReviewDTO reviewDTO) {
		reviewDTO = reviewService.insert(reviewDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(reviewDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(reviewDTO);
	}
	
}

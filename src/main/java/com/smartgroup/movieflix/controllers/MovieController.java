package com.smartgroup.movieflix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartgroup.movieflix.dto.MovieAllFieldsDTO;
import com.smartgroup.movieflix.dto.MovieDTO;
import com.smartgroup.movieflix.services.MovieService;

@RestController
@RequestMapping(path = "/movies")
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<MovieAllFieldsDTO> findById(@PathVariable Long id) {
		MovieAllFieldsDTO movieDTO = movieService.findById(id);
		return ResponseEntity.ok(movieDTO);
	}
	
	@GetMapping
	public ResponseEntity<Page<MovieDTO>> findByGenre(
			@RequestParam(value = "genreId", defaultValue = "0") Long genreId,
			Pageable pageable) {
		Page<MovieDTO> pageDTO = movieService.findByGenre(genreId, pageable);
		return ResponseEntity.ok(pageDTO);
	}
	
}
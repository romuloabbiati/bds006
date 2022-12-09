package com.smartgroup.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartgroup.movieflix.dto.MovieDTO;
import com.smartgroup.movieflix.entities.Movie;
import com.smartgroup.movieflix.repositories.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Transactional(readOnly = true)
	public Page<MovieDTO> findByGenre(Long genreId, Pageable pageable) {
		Page<Movie> page = null;
		if(genreId == 0) {
			page = movieRepository.findAll(pageable);
		} else {
			page = movieRepository.findByGenre(genreId, pageable);
			
		}
		return page.map(movie -> new MovieDTO(movie));
	}
	
}

package com.smartgroup.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartgroup.movieflix.dto.ReviewDTO;
import com.smartgroup.movieflix.entities.Movie;
import com.smartgroup.movieflix.entities.Review;
import com.smartgroup.movieflix.entities.User;
import com.smartgroup.movieflix.repositories.MovieRepository;
import com.smartgroup.movieflix.repositories.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private AuthService authService;
	
	@Transactional(readOnly = true)
	public List<ReviewDTO> findReviewsByMovieId(Long movieId) {
		Movie movie = movieRepository.getOne(movieId);
		return movie.getReviews().stream()
			.map(review -> new ReviewDTO(review))
			.collect(Collectors.toList());
	}
	
	@Transactional
	public ReviewDTO insert(ReviewDTO reviewDTO) {
		User user = authService.authenticated();
		
			Review review = new Review();
			review.setText(reviewDTO.getText());
			review.setMovie(movieRepository.getOne(reviewDTO.getMovieId()));
			review.setUser(user);
			
			reviewRepository.save(review);
			return new ReviewDTO(review);
	}
	
}

package com.smartgroup.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartgroup.movieflix.dto.ReviewDTO;
import com.smartgroup.movieflix.entities.Review;
import com.smartgroup.movieflix.repositories.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Transactional
	public ReviewDTO insert(ReviewDTO reviewDTO) {
		Review review = new Review();
		review.setText(reviewDTO.getText());
		review.getMovie().setId(reviewDTO.getMovieId());
		
		review = reviewRepository.save(review);
		return new ReviewDTO(review);
	}
	
}

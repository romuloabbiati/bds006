package com.smartgroup.movieflix.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smartgroup.movieflix.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query("SELECT DISTINCT movie FROM Movie movie "
			+ "WHERE movie.genre.id = :genreId "
			+ "ORDER BY movie.title")
	Page<Movie> findByGenre(Long genreId, Pageable pageable);
	
	@Query("SELECT movie "
			+ "FROM Movie movie "
			+ "ORDER BY movie.title")
	Page<Movie> findWhenGenreNullOrZero(Pageable pageable);
	
//	@Query(nativeQuery = true, value = "SELECT DISTINCT tb_movie.title FROM tb_movie "
//			+ "INNER JOIN tb_genre ON tb_movie.genre_id = tb_genre.id "
//			+ "WHERE tb_genre.id = :genreId "
//			+ "ORDER BY tb_movie.title")
//	Page<Movie> findByGenre(Long genreId, Pageable pageable);
		
}

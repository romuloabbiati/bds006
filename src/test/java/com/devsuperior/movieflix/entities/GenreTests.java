package com.devsuperior.movieflix.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GenreTests {

	@Test
	public void genreShouldHaveCorrectStructure() {
	
		GenreService entity = new GenreService();
		entity.setId(1L);
		entity.setName("Maria");
	
		Assertions.assertNotNull(entity.getId());
		Assertions.assertNotNull(entity.getName());
		Assertions.assertEquals(0, entity.getMovies().size());
	}
}

package com.service;

import java.util.List;

import com.entities.Genre;

public interface GenreService {

	List<Genre> getAll();

	Genre create(Genre genre) throws Exception;
	
	Genre getByGenreId(int genreId);
	
	Genre getByName(String name);
	
	Genre getByDescription(String description);

}
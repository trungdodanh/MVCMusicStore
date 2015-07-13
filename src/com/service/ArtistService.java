package com.service;

import java.util.List;

import com.entities.Artist;

public interface ArtistService {
	
	List<Artist> getAll();
	
	Artist create(Artist genre);
	
	Artist getByArtistId(int artistId);
	
	Artist getByName(String name);
	
}

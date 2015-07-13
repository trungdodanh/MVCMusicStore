package com.service;

import java.util.List;

import com.entities.Album;
import com.entities.Artist;
import com.entities.Genre;

public interface AlbumService {

	List<Album> getAll();

	Album create(Album album) throws Exception;

	Album getAlbumById(int id);
	
	Album getByTitle(String title);
	
	Album getByPrice(Double price);
	
	Album getByArtUrl(String albumArtUrl);
	
	Genre getGenreByIdFromAlbumModel(int genreId) throws NullPointerException;
	
	Artist getArtistByIdFromAlbumModel(int artistId) throws NullPointerException;
	
	List<Album> getTop6();

	void edit(Album album);
	
	void delete(Album album) throws IndexOutOfBoundsException;
}
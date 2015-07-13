package com.service.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Album;
import com.entities.Artist;
import com.entities.Genre;
import com.repository.AlbumRepository;
import com.service.AlbumService;

@Service("albumService")
@Transactional(readOnly = true)
public class AlbumServiceImpl implements AlbumService {

	@PersistenceContext
	EntityManager em;
	
	@Autowired
    private AlbumRepository albumRepository;
	
	private GenreServiceImpl genreService;
	
	private ArtistServiceImpl artistService;
    
    public List<Album> getAll() {
        return albumRepository.getAll();
    }
    
    @Transactional
    public Album create(Album album) throws Exception {
        return albumRepository.persist(album);
    }

	public Album getAlbumById(int albumId) {
		return albumRepository.getAlbumById(albumId);
	}

	public Album getByTitle(String title) {
		return albumRepository.getByTitle(title);
	}

	public Album getByPrice(Double price) {
		return albumRepository.getByPrice(price);
	}
	
	public Album getByArtUrl(String AlbumArtUrl) {
		return albumRepository.getByAlbumArtUrl(AlbumArtUrl);
	}
	
	public List<Album> getTop6() {
		return albumRepository.getTop6();
	}

	/**
	 * Find a genre when we know its ID
	 * @param genreId
	 * @return
	 */
	public Genre getGenreByIdFromAlbumModel(int genreId) throws NullPointerException {
		return (Genre) genreService.getByGenreId(genreId);
	}
	
	/**
	 * Find a artist when we know its ID
	 * @param artistId
	 * @return
	 */
	public Artist getArtistByIdFromAlbumModel(int artistId) throws NullPointerException {
		return (Artist) artistService.getByArtistId(artistId);
	}
	
	@Transactional
	public void edit(Album album) {
		Album newAlbum = getAlbumById(album.getAlbumId());
		newAlbum.setGenreId(album.getGenreId());
		newAlbum.setAlbumId(album.getAlbumId());
		newAlbum.setTitle(album.getTitle());
		newAlbum.setPrice(album.getPrice());
		newAlbum.setAlbumArtUrl(album.getAlbumArtUrl());
		
		em.merge(album);
	}
	
	@Transactional
	public void delete(Album album) throws IndexOutOfBoundsException {
		Album myAlbum = getAlbumById(album.getAlbumId());
		em.remove(myAlbum);
	}
}

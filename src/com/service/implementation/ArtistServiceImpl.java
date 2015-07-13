package com.service.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Artist;
import com.repository.ArtistRepository;
import com.service.ArtistService;

@Service("artistService")
@Transactional(readOnly = true)
public class ArtistServiceImpl implements ArtistService {

	@Autowired
    private ArtistRepository artistRepository;
	
	@PersistenceContext
	protected EntityManager entityManager;
     
    public List<Artist> getAll() {
        return artistRepository.getAll();
    }
     
    @Transactional(readOnly = false)
    public Artist create(Artist artist) {
        return artistRepository.merge(artist);
    }

	public Artist getByArtistId(int artistId) {
		return artistRepository.getByArtistId(artistId);
	}

	public Artist getByName(String name) {
		return artistRepository.getByName(name);
	}

}

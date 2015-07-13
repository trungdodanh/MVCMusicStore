package com.service.implementation;

import java.text.ParseException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Genre;
import com.repository.GenreRepository;
import com.service.GenreService;

@Service("genreService")
@Transactional(readOnly = true)
public class GenreServiceImpl implements GenreService {

	@Autowired
    private GenreRepository genreRepository;
	
	@PersistenceContext
	protected EntityManager entityManager;
     
    public List<Genre> getAll() {
        return genreRepository.getAll();
    }
     
    @Transactional(readOnly = false)
    public Genre create(Genre genre) throws ParseException {
        return genreRepository.merge(genre);
    }

	public Genre getByGenreId(int genreId) {
		return genreRepository.getByGenreId(genreId);
	}

	public Genre getByName(String name) {
		return genreRepository.getByName(name);
	}

	public Genre getByDescription(String description) {
		return genreRepository.getByDescription(description);
	}


}

package com.repository;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.entities.Genre;

@Repository("genreRepository")
public class GenreRepository extends AbstractRepository<Genre> {
	
	@SuppressWarnings("unchecked")
    public List<Genre> getAll() {
        return entityManager.createNamedQuery("Genre.findAll").getResultList();
    }
	
	public Genre getByGenreId(int genreId) {
		Query q = entityManager.createNamedQuery("Genre.findByGenreId");
		q.setParameter("genreId", genreId);
		return (Genre) q.getResultList().get(0);
	}
	
	public Genre getByName(String name) {
		Query q = entityManager.createNamedQuery("Genre.findByName");
		q.setParameter("name", name);
		return (Genre) q.getResultList().get(0);
	}
	
	public Genre getByDescription(String description) {
		Query q = entityManager.createNamedQuery("Genre.findByDescription");
		q.setParameter("description", description);
		return (Genre) q.getResultList().get(0);
	}
	
}

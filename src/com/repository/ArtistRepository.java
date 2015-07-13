package com.repository;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.entities.Artist;

@Repository("artistRepository")
public class ArtistRepository extends AbstractRepository<Artist> {
	
	@SuppressWarnings("unchecked")
    public List<Artist> getAll() {
        return entityManager.createNamedQuery("Artist.findAll").getResultList();
    }
	
	public Artist getByArtistId(int artistId) {
		Query q = entityManager.createNamedQuery("Artist.findByArtistId");
		q.setParameter("artistId", artistId);
		return (Artist) q.getResultList().get(0);
	}
	
	public Artist getByName(String name) {
		Query q = entityManager.createNamedQuery("Artist.findByName");
		q.setParameter("name", name);
		return (Artist) q.getResultList().get(0);
	}
	
}

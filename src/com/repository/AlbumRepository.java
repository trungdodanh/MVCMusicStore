package com.repository;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.entities.*;
import com.repository.AbstractRepository;

@Repository("albumRepository")
public class AlbumRepository extends AbstractRepository<Album> {

	@SuppressWarnings("unchecked")
	public List<Album> getAll() {
		return entityManager.createNamedQuery("Album.findAll").getResultList();
	}
	
	public Album getAlbumById(int albumId) {
		Query q = entityManager.createNamedQuery("Album.findByAlbumId");
		q.setParameter("albumId", albumId);
		return (Album) q.getResultList().get(0);
	}
	
	public Album getByTitle(String title) {
		Query q = entityManager.createNamedQuery("Album.findByTitle");
		q.setParameter("title", title);
		return (Album) q.getResultList().get(0);
	}
	
	public Album getByPrice(Double price) {
		Query q = entityManager.createQuery("Album.findByPrice");
		q.setParameter("price", price);
		return (Album) q.getResultList().get(0);
	}
	
	public Album getByAlbumArtUrl(String albumArtUrl) {
		Query q = entityManager.createQuery("Album.findByAlbumArtUrl");
		q.setParameter("albumArtUrl", albumArtUrl);
		return (Album) q.getResultList().get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<Album> getTop6() {
		return entityManager.createQuery("select a from Album a").setMaxResults(5).getResultList();
	}
	
	
	
}

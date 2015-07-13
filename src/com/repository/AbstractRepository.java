package com.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AbstractRepository<T> {
	@PersistenceContext
	protected EntityManager entityManager;

	public T persist(T t) {
		entityManager.persist(t);
		return t;
	}

	public T merge(T t) {
		return entityManager.merge(t);
	}

	public void remove(T t) {
		entityManager.remove(t);
	}
}

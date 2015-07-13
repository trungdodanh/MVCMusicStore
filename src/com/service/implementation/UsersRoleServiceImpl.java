package com.service.implementation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.entities.UsersRole;
import com.service.UsersRoleService;

@Service("usersroleService")
public class UsersRoleServiceImpl implements UsersRoleService {

	@PersistenceContext
	private EntityManager em;
	
	public UsersRole findUsersroleByUserroleId(int userRole) {
		
		Query q = em.createNamedQuery("UsersRole.findByRoleId");
		q.setParameter("roleId", userRole);
		
		return (UsersRole) q.getResultList().get(0);
	}
	
	
	
}

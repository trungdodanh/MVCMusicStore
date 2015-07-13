package com.service.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Users;
import com.service.UsersService;

@Service("usersService")
public class UsersServiceImpl implements UsersService {

	protected static Logger logger = Logger.getLogger("Controller");
	
	// Injected database connection
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Create a user object
	 * 
	 * @param user
	 */
	@Transactional
	public void create(Users user) {
		em.persist(user);
	}
	
	/**
	 * Find all users in the database
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Users> findAllUsers() {
		List<Users> usersList = new ArrayList<Users>();
		try {
			Query q = em.createNamedQuery("Users.findAll");
			usersList = q.getResultList();
		} catch (Exception e) {
			
		}
		return usersList;
	}
	
	/**
	 * Find all of user who has this username
	 * @param username
	 * @return
	 */
	public Users findUserByUsername(String usersname) {
		
		Query q = em.createNamedQuery("Users.findByUsersName");
		q.setParameter("usersName", usersname);
		return  (Users) q.getResultList().get(0);
		
	}
	
	
	/**
	 * Verify username and password that user put in
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean verifyLoginUser(String usersName, String passWord) {
		
		boolean result = false;
		List<Users> usersList = null;
		
		// Get the whole list of users
		try 
		{
			usersList = findAllUsers();
		} 
		catch (Exception e) 
		{
			System.out.println("Sorry, Out system does not have any user");
		}
		for (Users user : usersList) 
		{
			if (user.getUsersName().equals(usersName) && user.getPassWord().equals(passWord)) { 
				result = true;
			}
		}
		return result;
	}

}

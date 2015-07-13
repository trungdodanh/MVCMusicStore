package com.service;

import java.util.List;

import com.entities.Users;

public interface UsersService {

	void create(Users user);
	
	List<Users> findAllUsers();
	
	Users findUserByUsername(String username);
	
	boolean verifyLoginUser(String usersName, String passWord);
	
	
	
}

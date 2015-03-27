package com.clathrop.hero.dao;

import java.util.List;

import com.clathrop.hero.model.User;

public class UserDaoTest {

	//very simple test to make sure the dao is working and communicating with local db
	public static void main(String[] args) {
		UserDao userDao = new UserDaoImpl();
		
		List<User> userList = userDao.getAllUsers();
		
		for(User user : userList){
			System.out.println(user.getFirstName());
		}

	}

}

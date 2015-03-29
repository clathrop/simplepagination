package com.clathrop.hero.dao;

import java.util.List;

import com.clathrop.hero.model.User;

public interface UserDao {

	public List<User> getAllUsers();
	public Integer getUserCount();
	public List<User> getAllUsersInRange(int startPageIndex, int recordsPerPage);
	public void addUser(User user);
	public Integer getLastRowId();

}

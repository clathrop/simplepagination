package dao;

import java.util.List;

import model.User;

public interface UserDao {

	public List<User> getAllUsers();
	public Integer getUserCount();

}

package dao;

import java.util.List;

import model.User;

public class UserDaoTest {

	public static void main(String[] args) {
		UserDao userDao = new UserDaoImpl();
		
		List<User> userList = userDao.getAllUsers();
		
		for(User user : userList){
			System.out.println(user.getFirstName());
		}

	}

}

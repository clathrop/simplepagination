package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDaoImpl {
	
	String sUrlString = "jdbc:sqlite:hero.db";
	MyDatabase mydb = new MyDatabase("org.sqlite.JDBC", sUrlString);
	Connection conn = mydb.getConnection();
	
	public List<User> getAllUsers(){
		
		String selectQuery = "SELECT * FROM users LIMIT 10";
		List<User> userList = new ArrayList<User>();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(selectQuery);

			while (rs.next()) {
				String name = rs.getString("name");
				String userName = rs.getString("user_name");
				String email = rs.getString("email");
				String numPosts = rs.getString("number_of_posts");

				User user = new User(name, userName, email, Integer.parseInt(numPosts));
				userList.add(user);
			}

			rs.close();
			stmt.close();
			//conn.close();
			return userList;

		} catch (Exception e) {
			System.out.print(e);
			return null;
		}
	}

}

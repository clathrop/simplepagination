package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import model.User;

public class UserDaoImpl implements UserDao {
	private Connection conn = null;
	Logger LOG = Logger.getLogger(UserDaoImpl.class);
	
	public UserDaoImpl(){
		
		try {
			Context ctx = new InitialContext();
			
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/sqlite");
			conn = ds.getConnection();
			
			LOG.info("Client info from UserDaoImpl" + conn.getClientInfo());
			System.out.print("Client info from UserDaoImpl" + conn.getClientInfo());
			
//			String sUrlString = "jdbc:sqlite:hero.db";
//			MyDatabase mydb = new MyDatabase("org.sqlite.JDBC", sUrlString);
//			Connection conn = mydb.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error(e);
			e.printStackTrace();
		}
	}
	

	public List<User> getAllUsers() {

		String selectQuery = "SELECT * FROM users";
		List<User> userList = new ArrayList<User>();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(selectQuery);

			while (rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String userName = rs.getString("user_name");
				String email = rs.getString("email");
				String age = rs.getString("age");

				User user = new User(firstName, lastName, userName, email, Integer.parseInt(age));
				userList.add(user);
			}

			rs.close();
			stmt.close();
			conn.close();
			return userList;

		} catch (Exception e) {
			System.out.print(e);
			return null;
		}
	}
	
	public List<User> getAllUsersInRange(int startPageIndex, int recordsPerPage) {

		String selectQuery = "SELECT * FROM users LIMIT " + Integer.toString(startPageIndex) + "," + Integer.toString(recordsPerPage);
		List<User> userList = new ArrayList<User>();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(selectQuery);

			while (rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String userName = rs.getString("user_name");
				String email = rs.getString("email");
				String age = rs.getString("age");

				User user = new User(firstName, lastName, userName, email, Integer.parseInt(age));
				userList.add(user);
			}

			rs.close();
			stmt.close();
		    conn.close();
			return userList;

		} catch (Exception e) {
			System.out.print(e);
			return null;
		}
	}

	public Integer getUserCount() {

		String selectQuery = "SELECT COUNT(*) FROM users";

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(selectQuery);
			// get count
			int tableCount = rs.getInt(1);

			rs.close();
			stmt.close();
			return tableCount;

		} catch (Exception e) {
			System.out.print(e);
			return null;
		}
	}

}

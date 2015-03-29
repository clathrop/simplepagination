package com.clathrop.hero.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.clathrop.hero.db.MyDatabase;
import com.clathrop.hero.model.User;

public class UserDaoImpl implements UserDao {

	private static final String LOCAL_SQLITE_JDBC_DRIVER = "org.sqlite.JDBC";
	private static final String LOCAL_DB_URL = "jdbc:sqlite:hero.db";
	private static final String REMOTE_DB_URL = "java:comp/env/jdbc/sqlite";
	private static final String USER_ID = "user_id";
	private static final String FIRST_NAME = "first_name";
	private static final String LAST_NAME = "last_name";
	private static final String USER_NAME = "user_name";
	private static final String EMAIL = "email";
	private static final String AGE = "age";

	private Connection conn = null;
	private MyDatabase mydb = null;
	private DataSource ds = null;
	boolean LOCAL_DB = false;

	public UserDaoImpl() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(REMOTE_DB_URL);

		} catch (Exception e) {
			try {
				mydb = new MyDatabase(LOCAL_SQLITE_JDBC_DRIVER, LOCAL_DB_URL);
				LOCAL_DB = true;
			} catch (Exception f) {
				f.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public List<User> getAllUsers() {

		String selectQuery = "SELECT * FROM users";
		List<User> userList = new ArrayList<User>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = LOCAL_DB ? mydb.getConnection() : ds.getConnection();

			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectQuery);

			while (rs.next()) {
				String userId = rs.getString(USER_ID);
				String firstName = rs.getString(FIRST_NAME);
				String lastName = rs.getString(LAST_NAME);
				String userName = rs.getString(USER_NAME);
				String email = rs.getString(EMAIL);
				String age = rs.getString(AGE);

				User user = new User(Integer.parseInt(userId), firstName,
						lastName, userName, email, Integer.parseInt(age));
				userList.add(user);
			}

			return userList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				rs.close();
			} catch (SQLException e1) {
			}
			try {
				stmt.close();
			} catch (SQLException e2) {
			}
			try {
				conn.close();
			} catch (SQLException e3) {
			}
		}
	}

	public List<User> getAllUsersInRange(int startPageIndex, int recordsPerPage) {

		String selectQuery = "SELECT * FROM users LIMIT "
				+ Integer.toString(startPageIndex) + ","
				+ Integer.toString(recordsPerPage);
		List<User> userList = new ArrayList<User>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = LOCAL_DB ? mydb.getConnection() : ds.getConnection();

			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectQuery);

			while (rs.next()) {
				String userId = rs.getString(USER_ID);
				String firstName = rs.getString(FIRST_NAME);
				String lastName = rs.getString(LAST_NAME);
				String userName = rs.getString(USER_NAME);
				String email = rs.getString(EMAIL);
				String age = rs.getString(AGE);

				User user = new User(Integer.parseInt(userId), firstName,
						lastName, userName, email, Integer.parseInt(age));
				userList.add(user);
			}

			return userList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				rs.close();
			} catch (SQLException e1) {
			}
			try {
				stmt.close();
			} catch (SQLException e2) {
			}
			try {
				conn.close();
			} catch (SQLException e3) {
			}
		}
	}

	public void addUser(User user) {
		String insertQuery = "INSERT INTO users (user_id, first_name, last_name, user_name, email, age) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = null;

		try {
			conn = LOCAL_DB ? mydb.getConnection() : ds.getConnection();

			stmt = conn.prepareStatement(insertQuery);
			stmt.setInt(1, user.getUserId());
			stmt.setString(2, user.getFirstName());
			stmt.setString(3, user.getLastName());
			stmt.setString(4, user.getUserName());
			stmt.setString(5, user.getEmail());
			stmt.setInt(6, user.getAge());
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
			}
			try {
				conn.close();
			} catch (SQLException e3) {
			}
		}
	}

	public Integer getUserCount() {

		String selectQuery = "SELECT COUNT(*) FROM users";
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = LOCAL_DB ? mydb.getConnection() : ds.getConnection();

			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectQuery);
			// get count
			int tableCount = rs.getInt(1);

			return tableCount;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				rs.close();
			} catch (SQLException e1) {
			}
			try {
				stmt.close();
			} catch (SQLException e2) {
			}
			try {
				conn.close();
			} catch (SQLException e3) {
			}
		}
	}
	
	public Integer getLastRowId(){
		String lastIdQry = "SELECT user_id from users order by ROWID DESC limit 1";
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = LOCAL_DB ? mydb.getConnection() : ds.getConnection();

			stmt = conn.createStatement();
			rs = stmt.executeQuery(lastIdQry);
			// get last rowid
			int rowId = rs.getInt(USER_ID);

			return rowId;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				rs.close();
			} catch (SQLException e1) {
			}
			try {
				stmt.close();
			} catch (SQLException e2) {
			}
			try {
				conn.close();
			} catch (SQLException e3) {
			}
		}
	}

}

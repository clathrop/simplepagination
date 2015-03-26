package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	private static final String LOCAL_SQLITE_JDBC_DRIVER = "org.sqlite.JDBC";
	private static final String LOCAL_DB_URL = "jdbc:sqlite:hero.db";
	private static final String REMOTE_DB_URL = "java:comp/env/jdbc/sqlite";
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
			if(LOCAL_DB == true){
				conn = mydb.getConnection();
			} else {
				conn = ds.getConnection();
			}
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectQuery);

			while (rs.next()) {
				String firstName = rs.getString(FIRST_NAME);
				String lastName = rs.getString(LAST_NAME);
				String userName = rs.getString(USER_NAME);
				String email = rs.getString(EMAIL);
				String age = rs.getString(AGE);

				User user = new User(firstName, lastName, userName, email,
						Integer.parseInt(age));
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
			} catch (SQLException e) {
			}
			try {
				conn.close();
			} catch (SQLException e) {
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
			if(LOCAL_DB == true){
				conn = mydb.getConnection();
			} else {
				conn = ds.getConnection();
			}
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectQuery);

			while (rs.next()) {
				String firstName = rs.getString(FIRST_NAME);
				String lastName = rs.getString(LAST_NAME);
				String userName = rs.getString(USER_NAME);
				String email = rs.getString(EMAIL);
				String age = rs.getString(AGE);

				User user = new User(firstName, lastName, userName, email,
						Integer.parseInt(age));
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
			} catch (SQLException e) {
			}
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	public Integer getUserCount() {

		String selectQuery = "SELECT COUNT(*) FROM users";
		Statement stmt = null;
		ResultSet rs = null;

		try {
			if(LOCAL_DB == true){
				conn = mydb.getConnection();
			} else {
				conn = ds.getConnection();
			}
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
			} catch (SQLException e) {
			}
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

}

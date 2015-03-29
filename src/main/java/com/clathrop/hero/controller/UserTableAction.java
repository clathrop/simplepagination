package com.clathrop.hero.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.clathrop.hero.dao.UserDao;
import com.clathrop.hero.dao.UserDaoImpl;
import com.clathrop.hero.model.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class UserTableAction extends ActionSupport {
	Logger LOG = Logger.getLogger(UserTableAction.class);
	private static final long serialVersionUID = 1L;
	private static final String OK = "OK";
	private static final String ERROR = "ERROR";

	private String result;
	private User record;
	private String message;

	private List<User> records = new ArrayList<User>();
	private int jtStartIndex;
	private int jtPageSize;
	private Integer totalRecordCount;

	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private int age;

	public String listUsers() {
		try {
			UserDao userDao = new UserDaoImpl();
			records = userDao.getAllUsersInRange(jtStartIndex, jtPageSize);
			totalRecordCount = userDao.getUserCount();
			setResult(OK);
			return Action.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			setResult(ERROR);
			return Action.ERROR;
		}
	}

	public String createUser() {
		UserDao userDao = new UserDaoImpl();

		record = new User();
		record.setUserId(userDao.getLastRowId()+1);
		record.setFirstName(firstName);
		record.setLastName(lastName);
		record.setUserName(userName);
		record.setEmail(email);
		record.setAge(age);

		try {
			if (!record.getFirstName().isEmpty() || record.getFirstName() != null) {
				userDao.addUser(record);
			} else {
				throw new Exception("Form contents are empty, not inserting to DB");
			}
			setResult(OK);
			return Action.SUCCESS;
		} catch (Exception e) {
			setResult(ERROR);
			setMessage(e.getMessage());
			System.out.println(e.getMessage());
			return Action.ERROR;
		}

	}

	public List<User> getRecords() {
		return records;
	}

	public void setRecords(List<User> userList) {
		this.records = userList;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getJtStartIndex() {
		return jtStartIndex;
	}

	public void setJtStartIndex(int jtStartIndex) {
		this.jtStartIndex = jtStartIndex;
	}

	public int getJtPageSize() {
		return jtPageSize;
	}

	public void setJtPageSize(int jtPageSize) {
		this.jtPageSize = jtPageSize;
	}

	public Integer getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(Integer totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public User getRecord() {
		return record;
	}

	public void setRecord(User record) {
		this.record = record;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

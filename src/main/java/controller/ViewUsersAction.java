package controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import model.User;

import com.opensymphony.xwork2.ActionSupport;

import dao.UserDao;
import dao.UserDaoImpl;

public class ViewUsersAction extends ActionSupport {
	Logger LOG = Logger.getLogger(ViewUsersAction.class);

	private static final long serialVersionUID = 1L;
	private List<User> records = new ArrayList<User>();
	private int jtStartIndex;
	private int jtPageSize;
	private String result;
	private Integer totalRecordCount;
	
	public String execute(){
		UserDao userDao = new UserDaoImpl();
		//records = userDao.getAllUsers();
		records = userDao.getAllUsersInRange(jtStartIndex, jtPageSize);
		totalRecordCount = userDao.getUserCount();
		
		setResult("OK");
		return SUCCESS;
	}
	
	public List<User> getRecords(){
		return records;
	}
	
	public void setRecords(List<User> userList){
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

}

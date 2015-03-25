package controller;

import java.util.ArrayList;
import java.util.List;

import model.User;

import com.opensymphony.xwork2.ActionSupport;

public class ViewUsersAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private List<User> records = new ArrayList<User>();
	private int jtStartIndex;
	private int jtPageSize;
	private String result;
	
	
	
	public String execute(){
		
		for(int i = 0;i<20;i++){
			records.add(new User("name"+Integer.toString(i), "username"+Integer.toString(i), "email", i));
		}
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

}

package com.maven.bank.pojo;

public class EmployeePojo {
	
	// Employee variables
	
	private String employeeID;
	private String employeePwd;
	
	public EmployeePojo() {
		super();

	}

	public EmployeePojo(String employeeID, String employeePwd) { 
		super();
		this.setEmployeeID(employeeID);
		this.setEmployeePwd(employeePwd);

	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeePwd() {
		return employeePwd;
	}

	public void setEmployeePwd(String employeePwd) {
		this.employeePwd = employeePwd;
	}
}

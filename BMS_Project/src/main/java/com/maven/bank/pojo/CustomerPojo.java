package com.maven.bank.pojo;

public class CustomerPojo {

	// Customer variables

	private String customerID;
	private String customerPwd;
	private String firstname;
	private String lastname;
	private int generateChecking;
	private int generateSavings;



	public CustomerPojo() {
		super();

	}

	public CustomerPojo(String customerID, String customerPwd, String firstname, String lastname) { 
		super();
		this.setCustomerID(customerID);
		this.setCustomerPwd(customerPwd);
		this.setFirstname(firstname);
		this.setLastname(lastname);

	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getCustomerPwd() {
		return customerPwd;
	}

	public void setCustomerPwd(String customerPwd) {
		this.customerPwd = customerPwd;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public int getGenerateChecking() {
		generateChecking = (int)((Math.random() * 9000)+1000); 
		return generateChecking;

	}

	public void setGenerateChecking(int generateChecking) {
		this.generateChecking = generateChecking;
	}

	public int getGenerateSavings() {
		generateSavings = (int)((Math.random() * 9000)+1000);
		return generateSavings; 
	}

	public void setGenerateSavings(int generateSavings) {
		this.generateSavings = generateSavings;
	}

}
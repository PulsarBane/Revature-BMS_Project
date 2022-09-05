package com.maven.bank.pojo;

public class AccountPojo {
	
	// Account Variables
	
	private String originID;
	private int checkingNum;
	private int savingsNum;
	private int toCheckingNum;
	private int toSavingsNum;
	private int checkingBalance;
	private int savingsBalance;
	private int transferAmount;
	private int transID;
	
	public AccountPojo() {
		super();

	}

	public AccountPojo(int checkingNum, int savingsNum, int checkingBalance, int savingsBalance) { 
		super();
		this.setCheckingNum(checkingNum);
		this.setSavingsNum(savingsNum);
		this.setCheckingBalance(checkingBalance);
		this.setSavingsBalance(savingsBalance);
		
	}

	public String getOriginID() {
		return originID;
	}

	public void setOriginID(String originID) {
		this.originID = originID;
	}

	public int getCheckingNum() {
		return checkingNum;
	}

	public void setCheckingNum(int checkingNum) {
		this.checkingNum = checkingNum;
	}

	public int getSavingsNum() {
		return savingsNum;
	}

	public void setSavingsNum(int savingsNum) {
		this.savingsNum = savingsNum;
	}

	public int getToCheckingNum() {
		return toCheckingNum;
	}

	public void setToCheckingNum(int toCheckingNum) {
		this.toCheckingNum = toCheckingNum;
	}

	public int getToSavingsNum() {
		return toSavingsNum;
	}

	public void setToSavingsNum(int toSavingsNum) {
		this.toSavingsNum = toSavingsNum;
	}

	public int getCheckingBalance() {
		return checkingBalance;
	}

	public void setCheckingBalance(int checkingBalance) {
		this.checkingBalance = checkingBalance;
	}

	public int getSavingsBalance() {
		return savingsBalance;
	}

	public void setSavingsBalance(int savingsBalance) {
		this.savingsBalance = savingsBalance;
	}

	public int getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(int transferAmount) {
		this.transferAmount = transferAmount;
	}

	public int getTransID() {
		return transID;
	}

	public void setTransID(int transID) {
		this.transID = transID;
	}

}

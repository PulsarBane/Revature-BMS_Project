package com.maven.bank.service;

import javax.swing.JTable;

import com.maven.bank.dao.BankDao;
import com.maven.bank.dao.BankDaoJDBC;
import com.maven.bank.exception.ApplicationException;
import com.maven.bank.pojo.AccountPojo;
import com.maven.bank.pojo.CustomerPojo;
import com.maven.bank.pojo.EmployeePojo;

public class BankServiceImplement implements BankService {

	BankDao bankDao;

	public BankServiceImplement() {
		bankDao = new BankDaoJDBC();
	}

	@Override
	public CustomerPojo getCustomer(String customerID, String password) throws ApplicationException {
		return bankDao.getCustomer(customerID, password);
	}
	
	@Override
	public CustomerPojo addUser(CustomerPojo customerPojo) throws ApplicationException {
		return bankDao.addUser(customerPojo);
	}


	@Override
	public EmployeePojo getEmployee(String employeeID, String employeepwd) throws ApplicationException {
		return bankDao.getEmployee(employeeID, employeepwd);
	}
	
	@Override
	public JTable getAllCustomers() throws ApplicationException {
		return bankDao.getAllCustomers();
	}

	@Override
	public JTable getBalance(String customerID) throws ApplicationException {
		return bankDao.getBalance(customerID);
	}

	@Override
	public JTable getHistory(String customerID) throws ApplicationException {
		return bankDao.getHistory(customerID);
	}
	
	@Override
	public AccountPojo getCheckingNum(int accountNum) throws ApplicationException {
		return bankDao.getCheckingNum(accountNum);
	}
	
	@Override
	public AccountPojo getSavingsNum(int accountNum) throws ApplicationException {
		return bankDao.getSavingsNum(accountNum);
	}

	@Override
	public AccountPojo withdrawChecking(AccountPojo accountPojo) throws ApplicationException {
		return bankDao.withdrawChecking(accountPojo);
	}

	@Override
	public AccountPojo withdrawSavings(AccountPojo accountPojo) throws ApplicationException {
		return bankDao.withdrawSavings(accountPojo);
	}

	@Override
	public AccountPojo depositChecking(AccountPojo accountPojo) throws ApplicationException {
		return bankDao.depositChecking(accountPojo);
	}

	@Override
	public AccountPojo depositSavings(AccountPojo accountPojo) throws ApplicationException {
		return bankDao.depositSavings(accountPojo);
	}

	@Override
	public AccountPojo checkingToChecking(AccountPojo accountPojo) throws ApplicationException {
		return bankDao.checkingToChecking(accountPojo);
	}

	@Override
	public AccountPojo checkingToSavings(AccountPojo accountPojo) throws ApplicationException {
		return bankDao.checkingToSavings(accountPojo);
	}

	@Override
	public AccountPojo savingstoChecking(AccountPojo accountPojo) throws ApplicationException {
		return bankDao.savingstoChecking(accountPojo);
	}

	@Override
	public AccountPojo savingsToSavings(AccountPojo accountPojo) throws ApplicationException {
		return bankDao.savingsToSavings(accountPojo);
	}



}

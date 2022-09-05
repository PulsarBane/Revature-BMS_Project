package com.maven.bank.dao;

import javax.swing.JTable;

import com.maven.bank.exception.ApplicationException;
import com.maven.bank.pojo.AccountPojo;
import com.maven.bank.pojo.CustomerPojo;
import com.maven.bank.pojo.EmployeePojo;


public interface BankDao {

	// Customer functions

	CustomerPojo addUser(CustomerPojo customerPojo) throws ApplicationException;

	CustomerPojo getCustomer(String customerID, String password) throws ApplicationException;

	JTable getBalance(String customerID) throws ApplicationException;

	JTable getHistory(String customerID) throws ApplicationException;


	// Employee functions

	EmployeePojo getEmployee(String employeeID, String employeepwd) throws ApplicationException;

	JTable getAllCustomers() throws ApplicationException;

	// Account functions

	AccountPojo withdrawChecking(AccountPojo accountPojo) throws ApplicationException; 

	AccountPojo withdrawSavings(AccountPojo accountPojo) throws ApplicationException;

	AccountPojo depositChecking(AccountPojo accountPojo) throws ApplicationException;

	AccountPojo depositSavings(AccountPojo accountPojo) throws ApplicationException;

	AccountPojo checkingToChecking(AccountPojo accountPojo) throws ApplicationException; 

	AccountPojo checkingToSavings(AccountPojo accountPojo) throws ApplicationException;

	AccountPojo savingstoChecking(AccountPojo accountPojo) throws ApplicationException;

	AccountPojo savingsToSavings(AccountPojo accountPojo) throws ApplicationException;

	AccountPojo getCheckingNum(int checkingNum) throws ApplicationException;

	AccountPojo getSavingsNum(int savingsNum) throws ApplicationException;

}

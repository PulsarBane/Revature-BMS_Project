package com.maven.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;

import com.maven.bank.exception.ApplicationException;
import com.maven.bank.pojo.AccountPojo;
import com.maven.bank.pojo.CustomerPojo;
import com.maven.bank.pojo.EmployeePojo;
import com.maven.bank.presentation.BankPresentation;


public class BankDaoJDBC implements BankDao {

	private static final Logger logger = LoggerFactory.getLogger(BankDaoJDBC.class);

	@Override
	public CustomerPojo getCustomer(String customerID, String customerPwd) throws ApplicationException {
		logger.info("Entered loginCustomer() of dao layer...");
		Connection connect = BankDBConnectionUtil.establishConnection(); 
		Statement stmt = null;
		CustomerPojo customerPojo = null;
		try {

			stmt = connect.createStatement();
			String query = "SELECT * FROM customer_login WHERE customer_id='" + customerID + "' AND password='" + customerPwd + "'";
			ResultSet rs = stmt.executeQuery(query);

			while(rs.next()) {
				customerPojo = new CustomerPojo();
				customerPojo.setCustomerID(rs.getString(1));
				customerPojo.setCustomerPwd(rs.getString(2));
			}
		} catch (SQLException e) {
			throw new ApplicationException();
		}
		return customerPojo;
	}

	@Override
	public EmployeePojo getEmployee(String employeeID, String employeepwd) throws ApplicationException {
		logger.info("Entered loginEmployee() of BankDao");
		Connection connect = BankDBConnectionUtil.establishConnection(); 
		Statement stmt = null;
		EmployeePojo employeePojo = null;
		try {

			stmt = connect.createStatement();
			String query = "SELECT * FROM employee_login WHERE employee_id='" + employeeID + "' AND password='" + employeepwd + "'";
			ResultSet rs = stmt.executeQuery(query);

			while(rs.next()) {
				employeePojo = new EmployeePojo();
				employeePojo.setEmployeeID(rs.getString(1));
				employeePojo.setEmployeePwd(rs.getString(2));
			}
		} catch (SQLException e) {
			throw new ApplicationException();
		}
		return employeePojo;
	}

	@Override
	public CustomerPojo addUser(CustomerPojo customerPojo) throws ApplicationException {
		logger.info("Entered addUser() of BankDao");
		Connection connect = BankDBConnectionUtil.establishConnection(); 

		String query1 = "INSERT INTO customer_login (customer_id, password) VALUES ('" + customerPojo.getCustomerID() + "','" + customerPojo.getCustomerPwd() + "')";
		String query2 = "INSERT INTO customer_info (first_name, last_name, customer_info_id) VALUES ('" + customerPojo.getFirstname() + "','" + customerPojo.getLastname() + "','" + customerPojo.getCustomerID() + "')";
		String query3 = "INSERT INTO bank_account (user_id, checking_no, savings_no) VALUES ('" + customerPojo.getCustomerID() + "','" + customerPojo.getGenerateChecking() + "','" + customerPojo.getGenerateSavings() + "')";

		try {

			Statement stmt = connect.createStatement();
			stmt.executeUpdate(query1);
			stmt.executeUpdate(query2);
			stmt.executeUpdate(query3);

		} catch (SQLException e) {
			throw new ApplicationException();
		}

		return customerPojo;
	}

	@Override
	public JTable getAllCustomers() throws ApplicationException { 
		logger.info("Entered getAllCustomers() of BankDao");
		Connection connect = BankDBConnectionUtil.establishConnection();
		Statement stmt;
		JTable table = null;
		try {

			stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM customer_info");
			table = new JTable(BankPresentation.buildTableModel(rs));

		} catch (SQLException e) {
			throw new ApplicationException();
		}

		return table;

	}

	@Override
	public JTable getBalance(String customerID) throws ApplicationException {
		logger.info("Entered getBalance() of BankDao");
		Connection connect = BankDBConnectionUtil.establishConnection();
		JTable table = null;
		try {

			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM bank_account WHERE user_id='" + customerID + "'");
			table = new JTable(BankPresentation.buildTableModel(rs));

		} catch (SQLException e) {
			throw new ApplicationException();
		}

		return table;
	}

	@Override
	public JTable getHistory(String customerID) throws ApplicationException {
		logger.info("Entered getHistory of BankDao");
		Connection connect = BankDBConnectionUtil.establishConnection();
		JTable table = null;
		try {

			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM trans_history WHERE origin_user_id='" + customerID + "'");
			table = new JTable(BankPresentation.buildTableModel(rs));

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException();
		}

		return table;
	}

	@Override
	public AccountPojo withdrawChecking(AccountPojo accountPojo) throws ApplicationException {
		logger.info("Entered withdrawChecking() of BankDao");
		Connection connect = BankDBConnectionUtil.establishConnection();
		String query = "UPDATE bank_account SET checking_balance = checking_balance - ? WHERE checking_no=?";

		try {

			PreparedStatement pstmt = connect.prepareStatement(query);
			pstmt.setInt(1, accountPojo.getTransferAmount());
			pstmt.setInt(2, accountPojo.getCheckingNum());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new ApplicationException();
		}
		return accountPojo;
	}

	@Override
	public AccountPojo withdrawSavings(AccountPojo accountPojo) throws ApplicationException {
		logger.info("Entered withdrawSavings() of BankDao");
		Connection connect = BankDBConnectionUtil.establishConnection();
		String query = "UPDATE bank_account SET savings_balance = savings_balance - ? WHERE savings_no=?";

		try {

			PreparedStatement pstmt = connect.prepareStatement(query);
			pstmt.setInt(1, accountPojo.getTransferAmount());
			pstmt.setInt(2, accountPojo.getSavingsNum());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new ApplicationException();
		}
		return accountPojo;
	}

	@Override
	public AccountPojo depositChecking(AccountPojo accountPojo) throws ApplicationException {
		logger.info("Entered despoitChecking() of BankDao");
		Connection connect = BankDBConnectionUtil.establishConnection();
		String query = "UPDATE bank_account SET checking_balance = checking_balance + ? WHERE checking_no=?";

		try {

			PreparedStatement pstmt = connect.prepareStatement(query);
			pstmt.setInt(1, accountPojo.getTransferAmount());
			pstmt.setInt(2, accountPojo.getCheckingNum());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new ApplicationException();
		}
		return accountPojo;
	}

	@Override
	public AccountPojo depositSavings(AccountPojo accountPojo) throws ApplicationException {
		logger.info("Entered despoitSaving of BankDao");
		Connection connect = BankDBConnectionUtil.establishConnection();
		String query = "UPDATE bank_account SET savings_balance = savings_balance + ? WHERE savings_no=?";

		try {

			PreparedStatement pstmt = connect.prepareStatement(query);
			pstmt.setInt(1, accountPojo.getTransferAmount());
			pstmt.setInt(2, accountPojo.getSavingsNum());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new ApplicationException();
		}
		return accountPojo;
	}

	@Override
	public AccountPojo checkingToChecking(AccountPojo accountPojo) throws ApplicationException {
		logger.info("Entered checkingtoChecking() of BankDao");
		Connection connect = BankDBConnectionUtil.establishConnection();

		try {

			Statement stmt = connect.createStatement();
			String query = "UPDATE bank_account SET checking_balance = checking_balance - '" + accountPojo.getTransferAmount() + "' WHERE checking_no='" + accountPojo.getCheckingNum() + "'";
			String query1 = "UPDATE bank_account SET checking_balance = checking_balance + '" + accountPojo.getTransferAmount() + "' WHERE checking_no='" + accountPojo.getToCheckingNum() + "'";
			String query3 = "INSERT INTO trans_history(origin_user_id, from_acct_no, to_acct_no, trans_amount) VALUES ('" + accountPojo.getOriginID() + "','" + accountPojo.getCheckingNum() + "','" + accountPojo.getToCheckingNum() + "','" + accountPojo.getTransferAmount() + "') RETURNING trans_id";

			stmt.executeUpdate(query);
			stmt.executeUpdate(query1);

			ResultSet rs = stmt.executeQuery(query3);
			rs.next();
			int autoGeneratedTransId = rs.getInt(1);
			accountPojo.setTransID(autoGeneratedTransId);

		} catch (SQLException e) {
			throw new ApplicationException();
		}
		return accountPojo;
	}

	@Override
	public AccountPojo checkingToSavings(AccountPojo accountPojo) throws ApplicationException {
		logger.info("Entered checkingToSavings() of BankDao");
		Connection connect = BankDBConnectionUtil.establishConnection();

		try {

			Statement stmt = connect.createStatement();
			String query = "UPDATE bank_account SET checking_balance = checking_balance - '" + accountPojo.getTransferAmount() + "' WHERE checking_no='" + accountPojo.getCheckingNum() + "'";
			String query1 = "UPDATE bank_account SET savings_balance = savings_balance + '" + accountPojo.getTransferAmount() + "' WHERE savings_no='" + accountPojo.getToSavingsNum() + "'";
			String query3 = "INSERT INTO trans_history(origin_user_id, from_acct_no, to_acct_no, trans_amount) VALUES ('"+ accountPojo.getOriginID() + "','" + + accountPojo.getCheckingNum() + "','" + accountPojo.getToSavingsNum() + "','" + accountPojo.getTransferAmount() + "') RETURNING trans_id";

			stmt.executeUpdate(query);
			stmt.executeUpdate(query1);

			ResultSet rs = stmt.executeQuery(query3);
			rs.next();
			int autoGeneratedTransId = rs.getInt(1);
			accountPojo.setTransID(autoGeneratedTransId);

		} catch (SQLException e) {
			throw new ApplicationException();
		}
		return accountPojo;
	}

	@Override
	public AccountPojo savingstoChecking(AccountPojo accountPojo) throws ApplicationException {
		logger.info("Entered savingstoChecking() of BankDao");
		Connection connect = BankDBConnectionUtil.establishConnection();

		try {

			Statement stmt = connect.createStatement();
			String query = "UPDATE bank_account SET savings_balance = savings_balance - '" + accountPojo.getTransferAmount() + "' WHERE savings_no='" + accountPojo.getSavingsNum() + "'";
			String query1 = "UPDATE bank_account SET checking_balance = checking_balance + '" + accountPojo.getTransferAmount() + "' WHERE checking_no='" + accountPojo.getToCheckingNum() + "'";
			String query3 = "INSERT INTO trans_history(origin_user_id, from_acct_no, to_acct_no, trans_amount) VALUES ('"+ accountPojo.getOriginID() + "','" + + accountPojo.getSavingsNum() + "','" + accountPojo.getToCheckingNum() + "','" + accountPojo.getTransferAmount() + "') RETURNING trans_id";

			stmt.executeUpdate(query);
			stmt.executeUpdate(query1);

			ResultSet rs = stmt.executeQuery(query3);
			rs.next();
			int autoGeneratedTransId = rs.getInt(1);
			accountPojo.setTransID(autoGeneratedTransId);

		} catch (SQLException e) {
			throw new ApplicationException();
		}
		return accountPojo;
	}

	@Override
	public AccountPojo savingsToSavings(AccountPojo accountPojo) throws ApplicationException {
		logger.info("Entered savingstoChecking() of BankDao");
		Connection connect = BankDBConnectionUtil.establishConnection();

		try {

			Statement stmt = connect.createStatement();
			String query = "UPDATE bank_account SET savings_balance = savings_balance - '" + accountPojo.getTransferAmount() + "' WHERE savings_no='" + accountPojo.getCheckingNum() + "'";
			String query1 = "UPDATE bank_account SET savings_balance = savings_balance + '" + accountPojo.getTransferAmount() + "' WHERE savings_no='" + accountPojo.getToSavingsNum() + "'";
			String query3 = "INSERT INTO trans_history(origin_user_id, from_acct_no, to_acct_no, trans_amount) VALUES ('"+ accountPojo.getOriginID() + "','" + + accountPojo.getCheckingNum() + "','" + accountPojo.getToSavingsNum() + "','" + accountPojo.getTransferAmount() + "') RETURNING trans_id";

			stmt.executeUpdate(query);
			stmt.executeUpdate(query1);

			ResultSet rs = stmt.executeQuery(query3);
			rs.next();
			int autoGeneratedTransId = rs.getInt(1);
			accountPojo.setTransID(autoGeneratedTransId);

		} catch (SQLException e) {
			throw new ApplicationException();
		}
		return accountPojo;
	}

	@Override
	public AccountPojo getCheckingNum(int accountNum) throws ApplicationException {
		logger.info("Entered getAccountNum() of BankDao");
		Connection connect = BankDBConnectionUtil.establishConnection();
		AccountPojo accountPojo = null;
		
		try {
			Statement stmt = connect.createStatement();
			String query = "SELECT * FROM bank_account WHERE checking_no=" + accountNum;
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				accountPojo = new AccountPojo();
				accountPojo.setOriginID(rs.getString(1));
				accountPojo.setCheckingNum(rs.getInt(2));
				accountPojo.setSavingsNum(rs.getInt(3));
				accountPojo.setCheckingBalance(rs.getInt(4));
				accountPojo.setSavingsBalance(rs.getInt(5));

			}
		} catch (SQLException e) {
			throw new ApplicationException();
		}
		return accountPojo;
	}

	@Override
	public AccountPojo getSavingsNum(int accountNum) throws ApplicationException {
		logger.info("Entered getAccountNum() of BankDao");
		Connection connect = BankDBConnectionUtil.establishConnection();
		AccountPojo accountPojo = null;

		try {
			Statement stmt = connect.createStatement();
			String query = "SELECT * FROM bank_account WHERE savings_no=" + accountNum;
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				accountPojo = new AccountPojo();
				accountPojo.setOriginID(rs.getString(1));
				accountPojo.setCheckingNum(rs.getInt(2));
				accountPojo.setSavingsNum(rs.getInt(3));
				accountPojo.setCheckingBalance(rs.getInt(4));
				accountPojo.setSavingsBalance(rs.getInt(5));

			}
		} catch (SQLException e) {
			throw new ApplicationException();
		}
		return accountPojo;
	}


}
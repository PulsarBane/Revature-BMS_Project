package com.maven.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.maven.bank.exception.ApplicationException;

public class BankDBConnectionUtil {

	static Connection connect;
	public static Connection establishConnection() throws ApplicationException {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String connectionurl = "jdbc:postgresql://localhost:5432/loanley_bank";
		String userName = "postgres";
		String pasword = "#Umbrella415";	
		try {
			if(connect == null) {
				connect = DriverManager.getConnection(connectionurl, userName, pasword);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connect;
	}

}

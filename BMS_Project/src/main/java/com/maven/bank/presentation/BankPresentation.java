package com.maven.bank.presentation;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.maven.bank.exception.ApplicationException;
import com.maven.bank.pojo.AccountPojo;
import com.maven.bank.pojo.CustomerPojo;
import com.maven.bank.pojo.EmployeePojo;
import com.maven.bank.service.BankService;
import com.maven.bank.service.BankServiceImplement;

/** 
 * BankPresentation --- Program to mimic/facilitate simple functions of a Bank Application.
 * Follows a three layered architecture - Presentation Layer, Service Layer, Dao(Data Access Object) Layer.
 * Makes use of a user defined object entity (pojo) that encapsulates the "Bank" logic.
 * Achieves 10 functionalities:
 * 1. Login as Employee
 * 2. Login as Customer
 * 3. Exit Employee Menu
 * 4. Exit Customer Menu
 * 5. Register/Create Customer
 * 6. List all Customers
 * 7. View Account Details
 * 8. Transfer Money
 * 9. View Transaction History
 * 10.Logout
 * 
 * @author  Nathnael Legesse
 * @version 1.0
 * @since   09/01/2022 
 */

public class BankPresentation implements ActionListener {

	private static final Logger logger = LoggerFactory.getLogger(BankPresentation.class);

	BankFrame bankFrame;
	BankService bankService = new BankServiceImplement();

	public static void main(String[] args) {
		new BankFrame();

	}


	public BankPresentation(BankFrame bankframe) {
		this.bankFrame = bankframe;

	}


	/* Cite Third Party Tool
	 * https://github.com/IVP4C2/IVP4C2/blob/master/src/main/java/nl/edu/avans/ivp4c2/presentation/BuildTableModel.java
	 * moverboom & IVP4C2
	 * 8/27/2022
	 * JTable Program: DefaultTableModel		 
	 */

	/*								JTabel Model
	 *------------------------------------------------------------------------------*/

	public static DefaultTableModel buildTableModel(ResultSet rs)
			throws SQLException {

		ResultSetMetaData metaData = rs.getMetaData();

		// names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}

		// data of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}

		return new DefaultTableModel(data, columnNames);

	}

	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e) {


		/*							PAGE TRANSITIONS
		 *------------------------------------------------------------------------------*/
		// Customer Login chosen...
		if(e.getSource() == bankFrame.customerLogin)  {
			bankFrame.getLoginCustomer();
		}
		// Employee Login Chosen...
		if(e.getSource() == bankFrame.employeeLogin) {
			bankFrame.getLoginEmployee();
		}
		// Create Account Chosen...	
		if(e.getSource() == bankFrame.createAccount){
			bankFrame.getCreate();
		}
		// Internal Transfer (Deposit/Withdraw) Chosen...
		if(e.getSource() == bankFrame.transferInternal) {
			bankFrame.getInternalTransfer();
		}
		// External Transfer (Checking/Saving) Chosen...
		if(e.getSource() == bankFrame.transferExternal) {
			bankFrame.getExternalTransfer();
		}



		/*								LOGIN FEATURE
		 *------------------------------------------------------------------------------*/

		/*--------CUSTOMER LOGIN-----------*/

		// Calling loginCustomer method through bankService
		String fetchCustomerID = bankFrame.userIDField.getText();
		String fetchCustomerPassword = String.valueOf(bankFrame.userPasswordField.getPassword());
		CustomerPojo customerCredentials = null;

		try {
			customerCredentials = bankService.getCustomer(fetchCustomerID, fetchCustomerPassword);
		} catch (ApplicationException e1) {
			logger.error("Exception occurred : " + e1.getMessage());
			JOptionPane.showMessageDialog(bankFrame, e1.getMessage(),"Alert",JOptionPane.WARNING_MESSAGE); 
		}

		// Assessing customer input with database info
		// If login is found
		if(fetchCustomerID.length() != 0 && fetchCustomerPassword.length() != 0 && customerCredentials != null && e.getSource() == bankFrame.custLogin) {
			JOptionPane.showMessageDialog(bankFrame,"Login Succesful!","Message",JOptionPane.INFORMATION_MESSAGE);
			bankFrame.userPasswordField.setText("");
			bankFrame.getAccountCustomer();
		}
		// If login fails
		else if (fetchCustomerID.length() != 0 && fetchCustomerPassword.length() != 0 && customerCredentials == null && e.getSource() == bankFrame.custLogin) {
			JOptionPane.showMessageDialog(bankFrame,"Login Failed!","Alert",JOptionPane.WARNING_MESSAGE);     
			bankFrame.userPasswordField.setText("");
		}
		// If field is missing
		else if ((fetchCustomerID.length() == 0 || fetchCustomerPassword.length() == 0) && e.getSource() == bankFrame.custLogin) {
			JOptionPane.showMessageDialog(bankFrame,"Please fill all required fields!","Alert",JOptionPane.WARNING_MESSAGE);     
		}

		/*--------EMPLOYEE LOGIN-----------*/

		// Calling loginEmployee method through bankService
		String fetchEmployeeID = bankFrame.employeeIDField.getText();
		String fetchEmployeePassword = String.valueOf(bankFrame.employeePasswordField.getPassword());
		EmployeePojo employeeCredentials = null;
		try {
			employeeCredentials = bankService.getEmployee(fetchEmployeeID, fetchEmployeePassword);
		} catch (ApplicationException e1) {
			logger.error("Exception occurred : " + e1.getMessage());
			JOptionPane.showMessageDialog(bankFrame, e1.getMessage(),"Alert",JOptionPane.WARNING_MESSAGE); 
		}

		// Assessing user input with database info
		// If login is found
		if(fetchEmployeeID.length() != 0 && fetchEmployeePassword.length() != 0 && employeeCredentials != null && e.getSource() == bankFrame.empLogin ) {
			JOptionPane.showMessageDialog(bankFrame,"Login Succesful!","Message",JOptionPane.INFORMATION_MESSAGE);
			bankFrame.employeeIDField.setText("");	
			bankFrame.employeePasswordField.setText("");
			bankFrame.getAccountEmployee();		
		}
		// If login fails
		else if (fetchEmployeeID.length() != 0 && fetchEmployeePassword.length() != 0 && employeeCredentials == null && e.getSource() == bankFrame.empLogin) {
			JOptionPane.showMessageDialog(bankFrame,"Login Failed!","Alert",JOptionPane.WARNING_MESSAGE);
			bankFrame.employeePasswordField.setText("");
		}
		// If field is missing
		else if ((fetchEmployeeID.length() == 0 || fetchEmployeePassword.length() == 0) && e.getSource() == bankFrame.empLogin) {
			JOptionPane.showMessageDialog(bankFrame,"Please fill all required fields!","Alert",JOptionPane.WARNING_MESSAGE);
		}

		/*								CREATE ACCOUNT
		 *------------------------------------------------------------------------------*/

		// Receiving/Assigning new user info through text field
		String firstName =  bankFrame.firstnameField.getText();
		String lastName = bankFrame.lastnameField.getText();
		String customerID = bankFrame.customerIDField.getText();
		String customerPassword = String.valueOf(bankFrame.customerPasswordField.getPassword());
		String confirmPassword = String.valueOf(bankFrame.confirmPasswordField.getPassword());

		CustomerPojo newUser = new CustomerPojo();
		newUser.setFirstname(firstName);
		newUser.setLastname(lastName);
		newUser.setCustomerID(customerID);
		newUser.setCustomerPwd(customerPassword);

		// Assessing if new user creation is confirmed
		if (firstName.length() != 0 && lastName.length() != 0 && customerID.length() != 0 && customerPassword.length() != 0 && confirmPassword.length() != 0 && customerPassword.equals(confirmPassword) && e.getSource() == bankFrame.completeCreate) {
			try {
				CustomerPojo addnewUser = bankService.addUser(newUser);
			} catch (ApplicationException e1) {
				logger.error("Exception occurred : " + e1.getMessage());
				JOptionPane.showMessageDialog(bankFrame, e1.getMessage(),"Alert",JOptionPane.WARNING_MESSAGE); 
			}

			JOptionPane.showMessageDialog(bankFrame,"New Account Created!","Message",JOptionPane.INFORMATION_MESSAGE);
			bankFrame.firstnameField.setText("");
			bankFrame.lastnameField.setText("");
			bankFrame.customerIDField.setText("");
			bankFrame.customerPasswordField.setText("");	
			bankFrame.confirmPasswordField.setText("");
			bankFrame.getExitHome();

		}
		// In case new password does not match
		else if (firstName.length() != 0 && lastName.length() != 0 && customerID.length() != 0 && customerPassword.length() != 0 && confirmPassword.length() != 0 && !customerPassword.equals(confirmPassword) && e.getSource() == bankFrame.completeCreate) {
			JOptionPane.showMessageDialog(bankFrame,"Passwords don't match!","Alert",JOptionPane.WARNING_MESSAGE);	
			bankFrame.customerPasswordField.setText("");	
			bankFrame.confirmPasswordField.setText("");
		}
		// In case a required filled is missing
		else if ((firstName.length() == 0 || lastName.length() == 0 || customerID.length() == 0 || customerPassword.length() == 0 || confirmPassword.length() == 0) && e.getSource() == bankFrame.completeCreate) {
			JOptionPane.showMessageDialog(bankFrame,"Please fill all required fields!","Alert",JOptionPane.WARNING_MESSAGE);	
		}

		/*								MONEY TRANSFER Option
		 *------------------------------------------------------------------------------*/

		/*--------INTERNAL TRANSFER (WITHDRAW/DEPOSIT)----------*/

		// Storing Withdraw Input
		String accountType = (String) bankFrame.accountTypeComboBox.getSelectedItem();
		String transferType = (String) bankFrame.transferTypeComboBox.getSelectedItem();
		String accountNumber =  bankFrame.accountNumberField.getText();
		String transferAmount = bankFrame.transferAmountField.getText();
		AccountPojo fetchAccountPojo;

		// [WITHDRAW - CHECKING]: Processing withdraws from checking
		if(accountType == "Checking" && transferType == "Withdraw" && accountNumber.length() != 0 && transferAmount.length() != 0 && e.getSource() == bankFrame.confirmIntTransfer) {
			int accountNum = Integer.parseInt(accountNumber);
			int amount = Integer.parseInt(transferAmount);

			try {
				fetchAccountPojo = bankService.getCheckingNum(accountNum);
				fetchAccountPojo.setTransferAmount(amount);
				AccountPojo updatedAccountPojo = bankService.withdrawChecking(fetchAccountPojo);
				JOptionPane.showMessageDialog(bankFrame,"Withdraw of $" + amount + " from "+ accountType +" has been processed!","Message",JOptionPane.INFORMATION_MESSAGE);
				bankFrame.transferAmountField.setText("");
			} catch (ApplicationException e1) {
				logger.error("Exception occurred : " + e1.getMessage());
				JOptionPane.showMessageDialog(bankFrame, e1.getMessage(),"Alert",JOptionPane.WARNING_MESSAGE); 
			}	
		}

		// [WITHDRAW - SAVINGS]: Processing withdraws from savings
		if(accountType == "Savings" && transferType == "Withdraw" && accountNumber.length() != 0 && transferAmount.length() != 0 && e.getSource() == bankFrame.confirmIntTransfer){
			int accountNum = Integer.parseInt(accountNumber);
			int amount = Integer.parseInt(transferAmount);

			try {
				fetchAccountPojo = bankService.getSavingsNum(accountNum);
				fetchAccountPojo.setTransferAmount(amount);
				AccountPojo updatedAccountPojo = bankService.withdrawSavings(fetchAccountPojo);
				JOptionPane.showMessageDialog(bankFrame,"Withdraw of $" + amount + " from "+ accountType +" has been processed!","Message",JOptionPane.INFORMATION_MESSAGE);
				bankFrame.transferAmountField.setText("");
			} catch (ApplicationException e1) {
				logger.error("Exception occurred : " + e1.getMessage());
				JOptionPane.showMessageDialog(bankFrame, e1.getMessage(),"Alert",JOptionPane.WARNING_MESSAGE); 
			}	
		}

		// [DEPOSIT - CHECKING]: Processing deposit from checking
		if(accountType == "Checking" && transferType == "Deposit" && accountNumber.length() != 0 && transferAmount.length() != 0 && e.getSource() == bankFrame.confirmIntTransfer) {
			int accountNum = Integer.parseInt(accountNumber);
			int amount = Integer.parseInt(transferAmount);

			try {
				fetchAccountPojo = bankService.getCheckingNum(accountNum);
				fetchAccountPojo.setTransferAmount(amount);
				AccountPojo updatedAccountPojo = bankService.depositChecking(fetchAccountPojo);
				JOptionPane.showMessageDialog(bankFrame,"Deposit of $" + amount + " to "+ accountType +" has been processed!","Message",JOptionPane.INFORMATION_MESSAGE);
				bankFrame.transferAmountField.setText("");
			} catch (ApplicationException e1) {
				logger.error("Exception occurred : " + e1.getMessage());
				JOptionPane.showMessageDialog(bankFrame, e1.getMessage(),"Alert",JOptionPane.WARNING_MESSAGE); 
			}	
		}

		// [DEPOSIT - SAVINGS]: Processing deposit to savings
		if(accountType == "Savings" && transferType == "Deposit" && accountNumber.length() != 0 && transferAmount.length() != 0 && e.getSource() == bankFrame.confirmIntTransfer) {
			int accountNum = Integer.parseInt(accountNumber);
			int amount = Integer.parseInt(transferAmount);

			try {
				fetchAccountPojo = bankService.getSavingsNum(accountNum);
				fetchAccountPojo.setTransferAmount(amount);
				AccountPojo updatedAccountPojo = bankService.depositSavings(fetchAccountPojo);
				JOptionPane.showMessageDialog(bankFrame,"Deposit of $" + amount + " to "+ accountType +" has been processed!","Message",JOptionPane.INFORMATION_MESSAGE);
				bankFrame.transferAmountField.setText("");
			} catch (ApplicationException e1) {
				logger.error("Exception occurred : " + e1.getMessage());
				JOptionPane.showMessageDialog(bankFrame, e1.getMessage(),"Alert",JOptionPane.WARNING_MESSAGE); 
			}	
		}

		// Responding to missing fields
		else if((accountType == "" || transferType == "" || accountNumber.length() == 0 ||transferAmount.length() == 0) && e.getSource() == bankFrame.confirmIntTransfer) {
			JOptionPane.showMessageDialog(bankFrame,"Please fill all required fields!","Alert",JOptionPane.WARNING_MESSAGE);	
		}

		/*--------EXTERNAL TRANSFER (SAVING/CHECKING)----------*/

		// Storing Chosen Account Type Input
		String originType = (String) bankFrame.fromComboBox.getSelectedItem();
		String destinationType = (String) bankFrame.toComboBox.getSelectedItem();

		// Storing Checking Transfer Input
		String fromAccountNum = bankFrame.fromExtTransferField.getText();
		String toAccountNum = bankFrame.toExtTransferField.getText();
		String transferTotal = bankFrame.amountExtTransferField.getText();

		// [CHECKING-TO-CHECKING]: Processing transfer from checking account to checking account
		if(originType == "Checking" && destinationType == "Checking" && fromAccountNum.length() != 0 && toAccountNum.length() != 0 && transferTotal.length() != 0 && e.getSource() == bankFrame.confirmExtTransfer) {
			int fromAccount = Integer.parseInt(fromAccountNum);
			int toAccount = Integer.parseInt(toAccountNum);
			int total = Integer.parseInt(transferTotal);

			try { 
				fetchAccountPojo = bankService.getCheckingNum(fromAccount);
				fetchAccountPojo.setToCheckingNum(toAccount);
				fetchAccountPojo.setTransferAmount(total);
				AccountPojo updatedAccountPojo = bankService.checkingToChecking(fetchAccountPojo);
				JOptionPane.showMessageDialog(bankFrame,"Transfer of $" + total + " to External Account " + toAccount + " has been processed!","Message",JOptionPane.INFORMATION_MESSAGE);
				bankFrame.amountExtTransferField.setText("");
			} catch (ApplicationException e1) {
				logger.error("Exception occurred : " + e1.getMessage());
				JOptionPane.showMessageDialog(bankFrame, e1.getMessage(),"Alert",JOptionPane.WARNING_MESSAGE); 
			}

		}

		// [CHECKING-TO-SAVINGS]: Processing transfer from checking account to savings account
		if(originType == "Checking" && destinationType == "Savings" && fromAccountNum.length() != 0 && toAccountNum.length() != 0 && transferTotal.length() != 0 && e.getSource() == bankFrame.confirmExtTransfer) {
			int fromAccount = Integer.parseInt(fromAccountNum);
			int toAccount = Integer.parseInt(toAccountNum);
			int total = Integer.parseInt(transferTotal);

			try { 
				fetchAccountPojo = bankService.getCheckingNum(fromAccount);
				fetchAccountPojo.setToSavingsNum(toAccount);
				fetchAccountPojo.setTransferAmount(total);
				AccountPojo updatedAccountPojo = bankService.checkingToSavings(fetchAccountPojo);
				JOptionPane.showMessageDialog(bankFrame,"Transfer of $" + total + " to External Account " + toAccount + " has been processed!","Message",JOptionPane.INFORMATION_MESSAGE);
				bankFrame.amountExtTransferField.setText("");
			} catch (ApplicationException e1) {
				logger.error("Exception occurred : " + e1.getMessage());
				JOptionPane.showMessageDialog(bankFrame, e1.getMessage(),"Alert",JOptionPane.WARNING_MESSAGE); 
			}

		}

		// [SAVINGS-TO-SAVINGS]: Processing transfer from savings account to savings account
		if(originType == "Savings" && destinationType == "Savings" && fromAccountNum.length() != 0 && toAccountNum.length() != 0 && transferTotal.length() != 0 && e.getSource() == bankFrame.confirmExtTransfer) {
			int fromAccount = Integer.parseInt(fromAccountNum);
			int toAccount = Integer.parseInt(toAccountNum);
			int total = Integer.parseInt(transferTotal);

			try { 
				fetchAccountPojo = bankService.getSavingsNum(fromAccount);
				fetchAccountPojo.setToSavingsNum(toAccount);
				fetchAccountPojo.setTransferAmount(total);
				AccountPojo updatedAccountPojo = bankService.savingsToSavings(fetchAccountPojo);
				JOptionPane.showMessageDialog(bankFrame,"Transfer of $" + total + " to External Account " + toAccount + " has been processed!","Message",JOptionPane.INFORMATION_MESSAGE);
				bankFrame.amountExtTransferField.setText("");
			} catch (ApplicationException e1) {
				logger.error("Exception occurred : " + e1.getMessage());
				JOptionPane.showMessageDialog(bankFrame, e1.getMessage(),"Alert",JOptionPane.WARNING_MESSAGE); 
			}

		}

		// [SAVINGS-TO-CHECKING]: Processing transfer from savings account to checking account
		if(originType == "Savings" && destinationType == "Checking" && fromAccountNum.length() != 0 && toAccountNum.length() != 0 && transferTotal.length() != 0 && e.getSource() == bankFrame.confirmExtTransfer) {
			int fromAccount = Integer.parseInt(fromAccountNum);
			int toAccount = Integer.parseInt(toAccountNum);
			int total = Integer.parseInt(transferTotal);

			try { 
				fetchAccountPojo = bankService.getSavingsNum(fromAccount);
				fetchAccountPojo.setToCheckingNum(toAccount);
				fetchAccountPojo.setTransferAmount(total);
				AccountPojo updatedAccountPojo = bankService.savingstoChecking(fetchAccountPojo);
				JOptionPane.showMessageDialog(bankFrame,"Transfer of $" + total + " to External Account " + toAccount + " has been processed!","Message",JOptionPane.INFORMATION_MESSAGE);
				bankFrame.amountExtTransferField.setText("");
			} catch (ApplicationException e1) {
				logger.error("Exception occurred : " + e1.getMessage());
				JOptionPane.showMessageDialog(bankFrame, e1.getMessage(),"Alert",JOptionPane.WARNING_MESSAGE); 
			}
		}

		// Responding to missing fields
		if((originType == "" || destinationType == "" || fromAccountNum.length() == 0 || toAccountNum.length() == 0 || transferTotal.length() == 0) && e.getSource() == bankFrame.confirmExtTransfer) {
			JOptionPane.showMessageDialog(bankFrame,"Please fill all required fields!","Alert",JOptionPane.WARNING_MESSAGE);	
		}




		/*								EMPLOYEE ACCOUNT PAGE
		 *------------------------------------------------------------------------------*/
		// All customers
		if(e.getSource() == bankFrame.listAll) {
			BankService service = new BankServiceImplement();
			try {
				JOptionPane.showMessageDialog(bankFrame, new JScrollPane(service.getAllCustomers()));
			} catch (HeadlessException | ApplicationException e1) {
				logger.error("Exception occurred : " + e1.getMessage());
				JOptionPane.showMessageDialog(bankFrame, e1.getMessage(),"Alert",JOptionPane.WARNING_MESSAGE); 

			}
		}

		/*								VIEW ACCOUNT BALANCE
		 *------------------------------------------------------------------------------*/
		//	Overall Balance
		if(e.getSource() == bankFrame.showBalance) {
			BankService service = new BankServiceImplement();
			try {
				JOptionPane.showMessageDialog(bankFrame, new JScrollPane(service.getBalance(fetchCustomerID)));
			} catch (ApplicationException e1) {
				logger.error("Exception occurred : " + e1.getMessage());
				JOptionPane.showMessageDialog(bankFrame, e1.getMessage(),"Alert",JOptionPane.WARNING_MESSAGE); 
			}

		}

		/*								VIEW TRANSACTION HISTORY
		 *------------------------------------------------------------------------------*/
		if(e.getSource() == bankFrame.showHistory) {
			BankService service = new BankServiceImplement();
			try {
				JOptionPane.showMessageDialog(bankFrame, new JScrollPane(service.getHistory(fetchCustomerID)));
			} catch (ApplicationException e1) {
				logger.error("Exception occurred : " + e1.getMessage());
				JOptionPane.showMessageDialog(bankFrame, e1.getMessage(),"Alert",JOptionPane.WARNING_MESSAGE); 
			}
		}


		/*								EXIT OPTIONS
		 *------------------------------------------------------------------------------*/

		// Exit customer login page...
		if(e.getSource() == bankFrame.exitCustomer) {
			bankFrame.userIDField.setText("");	
			bankFrame.userPasswordField.setText("");
			bankFrame.getExitHome();
		}

		// Exit employee login page...
		if(e.getSource() == bankFrame.exitEmployee) {
			bankFrame.employeeIDField.setText("");	
			bankFrame.employeePasswordField.setText("");
			bankFrame.getExitHome();
		}

		// Exit create account page...
		if(e.getSource() == bankFrame.exitCreate) {
			bankFrame.firstnameField.setText("");
			bankFrame.lastnameField.setText("");
			bankFrame.customerIDField.setText("");
			bankFrame.customerPasswordField.setText("");	
			bankFrame.confirmPasswordField.setText("");
			bankFrame.getExit_Create();
		}

		// Exit internal transfer (withdraw/deposit) page...
		if (e.getSource() == bankFrame.exitIntTransfer) {
			bankFrame.accountTypeComboBox.setSelectedIndex(-1);
			bankFrame.transferTypeComboBox.setSelectedIndex(-1);
			bankFrame.accountNumberField.setText("");	
			bankFrame.transferAmountField.setText("");
			bankFrame.getAccountCustomer();
		}

		// Exit external transfer (Checking/Savings) page...
		if (e.getSource() == bankFrame.exitExtTransfer) {
			bankFrame.fromComboBox.setSelectedIndex(-1);
			bankFrame.toComboBox.setSelectedIndex(-1);
			bankFrame.fromExtTransferField.setText("");	
			bankFrame.toExtTransferField.setText("");
			bankFrame.amountExtTransferField.setText("");
			bankFrame.getAccountCustomer();
		}

		// Logout out of customer account...
		if(e.getSource() == bankFrame.customerLogout) {
			bankFrame.getExitCustomer();
		}

		// Logout out of employee account...
		if(e.getSource() == bankFrame.employeeLogout) {
			bankFrame.getExitEmployee();
		}

		// Logout...
		if(e.getSource() == bankFrame.logout) {
			bankFrame.getLogout();
		}
	}
}




package com.maven.bank.presentation;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;




public class BankFrame extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*								COMPONENT(JPANELS)
	 *------------------------------------------------------------------------------*/


	// JPanel Layout
	GridBagLayout panelLayout = new GridBagLayout();
	// Welcome Page 
	JPanel welcomePanel = new JPanel(panelLayout);
	JPanel customerLoginPanel = new JPanel(panelLayout);
	JPanel employeeLoginPanel = new JPanel(panelLayout);
	JPanel createAccountPanel = new JPanel(panelLayout);
	// Account Page
	JPanel customerAccountPanel = new JPanel(panelLayout);
	JPanel employeeAccountPanel = new JPanel(panelLayout);
	// Transfer Page
	JPanel internalTransferPanel = new JPanel(panelLayout);
	JPanel externalTransferPanel = new JPanel(panelLayout);
	
	/*							COMPONENT(JLABELS)
	 *------------------------------------------------------------------------------*/
	// Welcome page label
	JLabel welcomelabel = new JLabel("Welcome to Loanley Banking!");
	// User-Login label
	JLabel userIDLabel = new JLabel("UserID: ");
	JLabel userPasswordLabel = new JLabel("Password: ");
	// Employee-login label
	JLabel employeeIDLabel = new JLabel("EmployeeID: ");
	JLabel employeePasswordLabel = new JLabel("Password: ");
	// Login message label
	JLabel messageLabel = new JLabel("Invalid Credentials!");
	// Create account label
	JLabel firstnameLabel = new JLabel("Firstname: ");
	JLabel lastnameLabel = new JLabel("Lastname: ");
	JLabel customerIDLabel = new JLabel("ID: ");
	JLabel customerPasswordLabel = new JLabel("New Password: ");
	JLabel confirmPasswordLabel = new JLabel("Confirm New Password: ");
	// Internal Transfer(Withdraw/Deposit) Label
	JLabel accountNumberLabel  = new JLabel("Account Number: ");
	JLabel transferAmountLabel = new JLabel("Amount: ");
	// External Transfer(Checking/Savings) Label
	JLabel fromExtTransferLabel = new JLabel("From account number: ");
	JLabel toExtTransferLabel = new JLabel("To account number: ");
	JLabel amountExtTransferLabel = new JLabel("Amount: ");

	/*							COMPONENT(JTEXTFIELD)
	 *------------------------------------------------------------------------------*/
	// User Text Fields
	JTextField userIDField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	// Employee Text Fields
	JTextField employeeIDField = new JTextField();
	JPasswordField employeePasswordField = new JPasswordField();
	// Create Account Fields
	JTextField firstnameField = new JTextField();
	JTextField lastnameField = new JTextField();
	JTextField customerIDField = new JTextField();
	JPasswordField customerPasswordField = new JPasswordField();
	JPasswordField confirmPasswordField = new JPasswordField();
	// Internal Transfer(Withdraw/Deposit) Fields
	JTextField accountNumberField = new JTextField();
	JTextField transferAmountField = new JTextField();
	// External Transfer(Checking/Savings) Fields
	JTextField fromExtTransferField = new JTextField();
	JTextField toExtTransferField = new JTextField();
	JTextField amountExtTransferField = new JTextField();


	/*							COMPONENT(JBUTTONS)
	 *------------------------------------------------------------------------------*/
	// Welcome page options
	JButton customerLogin = new JButton("Customer Login");
	JButton employeeLogin = new JButton("Employee Login");
	JButton createAccount = new JButton("Create Account");
	// User login page options
	JButton custLogin = new JButton("Submit");
	// Employee login options
	JButton empLogin = new JButton("Submit");
	// User account page options
	JButton transferExternal = new JButton("Transfer(External)");
	JButton transferInternal = new JButton("Transfer(Internal)");
	JButton showBalance = new JButton("View Balance");
	JButton showHistory = new JButton("View History");
	JButton customerLogout = new JButton("Logout");
	// Employee account page options
	JButton listAll = new JButton("List all customers");
	JButton employeeLogout = new JButton("Logout");
	// Create option 
	JButton completeCreate = new JButton("Create Account");
	// Internal Transfer Option
	JButton confirmIntTransfer = new JButton("Confirm");
	// External Transfer Option
	JButton confirmExtTransfer = new JButton("Confirm");
	// Universal options
	JButton exitCustomer = new JButton("Cancel");
	JButton exitEmployee = new JButton("Cancel");
	JButton exitCreate = new JButton("Cancel");
	JButton exitExtTransfer = new JButton("Cancel");
	JButton exitIntTransfer = new JButton("Cancel");
	JButton logout = new JButton("Logout");
	
	/*								COMPONENT(JCOMBOComboBox)
	 *------------------------------------------------------------------------------*/
	
	String[] transferTypeOption = {"Withdraw", "Deposit"};
	String[] accountTypeOption = {"Checking", "Savings"};
	String[] fromOption = {"Checking", "Savings"};
	String[] toOption = {"Checking", "Savings"};
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox transferTypeComboBox = new JComboBox(transferTypeOption);	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox accountTypeComboBox = new JComboBox(accountTypeOption);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox fromComboBox = new JComboBox(fromOption);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox toComboBox = new JComboBox(toOption);
	
	/*								GRIDBAG CONSTRAINT(VARIABLES)
	 *------------------------------------------------------------------------------*/
	private int top = 3, left = 3, bottom = 3, right = 3;
	private Insets i = new Insets(top, left, bottom, right);


	BankFrame() {


		/*							CONSTRUCTING JFRAME
		 *------------------------------------------------------------------------------*/
		setTitle("Loanley Bank"); // Establishes a given title.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closes when 'x' is clicked.  
		setLayout(new GridBagLayout());
		add(welcomePanel);
		setSize(740, 420); // Establishes the dimension of the this .setSize(x,y).
		setVisible(true); // Frame visibility.
		setResizable(false);


		/*							REMOVING FOCUS(ME DON'T LIKE)
		 *------------------------------------------------------------------------------*/
		customerLogin.setFocusPainted(false);
		employeeLogin.setFocusPainted(false);
		createAccount.setFocusPainted(false);
		custLogin.setFocusPainted(false);
		empLogin.setFocusPainted(false);
		customerLogout.setFocusPainted(false);
		employeeLogout.setFocusPainted(false);
		transferExternal.setFocusPainted(false);
		transferInternal.setFocusPainted(false);
		showBalance.setFocusPainted(false);
		showHistory.setFocusPainted(false);
		listAll.setFocusPainted(false);
		completeCreate.setFocusPainted(false);
		confirmIntTransfer.setFocusPainted(false);
		confirmExtTransfer.setFocusPainted(false);
		exitCustomer.setFocusPainted(false);
		exitEmployee.setFocusPainted(false);
		exitCreate.setFocusPainted(false);
		exitIntTransfer.setFocusPainted(false);

		exitIntTransfer.setFocusPainted(false);
		exitExtTransfer.setFocusPainted(false);
		logout.setFocusPainted(false);


		/*							LAYOUT CONSTRAINTS
		 *------------------------------------------------------------------------------*/
		GridBagConstraints c = new GridBagConstraints();

		// c constraints
		c.anchor = GridBagConstraints.EAST;
		c.insets = i;
		c.weightx=-1; 
		c.gridx=-1;
		c.gridx=-1;
		
		/*							JCOMBOComboBox INDEX
		 *------------------------------------------------------------------------------*/
		transferTypeComboBox.setSelectedIndex(-1);	
		accountTypeComboBox.setSelectedIndex(-1);	
		fromComboBox.setSelectedIndex(-1);
		toComboBox.setSelectedIndex(-1);


		/*							USER INPUT/ACTION LISTENER
		 *------------------------------------------------------------------------------*/

		// Anticipating welcome page options...
		customerLogin.addActionListener(new BankPresentation(this));
		employeeLogin.addActionListener(new BankPresentation(this));
		createAccount.addActionListener(new BankPresentation(this));

		// Anticipating User login page buttons
		custLogin.addActionListener(new BankPresentation(this));
		empLogin.addActionListener(new BankPresentation(this));

		// Anticipating customer options...
		transferInternal.addActionListener(new BankPresentation(this));
		transferExternal.addActionListener(new BankPresentation(this));
		transferTypeComboBox.addActionListener(new BankPresentation(this));
		accountTypeComboBox.addActionListener(new BankPresentation(this));
		fromComboBox.addActionListener(new BankPresentation(this));
		toComboBox.addActionListener(new BankPresentation(this));
		confirmIntTransfer.addActionListener(new BankPresentation(this));
		confirmExtTransfer.addActionListener(new BankPresentation(this));
		showBalance.addActionListener(new BankPresentation(this));
		showHistory.addActionListener(new BankPresentation(this));

		// Anticipating employee options...
		listAll.addActionListener(new BankPresentation(this));

		// Anticipating exit options
		exitCustomer.addActionListener(new BankPresentation(this));
		exitEmployee.addActionListener(new BankPresentation(this));
		exitCreate.addActionListener(new BankPresentation(this));
		exitIntTransfer.addActionListener(new BankPresentation(this));
		exitExtTransfer.addActionListener(new BankPresentation(this));
		customerLogout.addActionListener(new BankPresentation(this));
		employeeLogout.addActionListener(new BankPresentation(this));
		completeCreate.addActionListener(new BankPresentation(this));
		logout.addActionListener(new BankPresentation(this));


		/*					ADDING COMPONENTS TO RESPECTIVE PANELS
		 *------------------------------------------------------------------------------*/

		// Adding Welcome page components...
		welcomePanel.add(welcomelabel, c);
		welcomePanel.add(customerLogin, c);
		welcomePanel.add(employeeLogin, c);
		welcomePanel.add(createAccount, c);
		welcomePanel.add(logout, c);
		welcomePanel.revalidate();

		// Setting preferred component size
		userIDField.setPreferredSize(new Dimension(100, 25));
		userPasswordField.setPreferredSize(new Dimension(100, 25));
		employeeIDField.setPreferredSize(new Dimension(100, 25));
		employeePasswordField.setPreferredSize(new Dimension(100, 25));
		firstnameField.setPreferredSize(new Dimension(100, 25));
		lastnameField.setPreferredSize(new Dimension(100, 25));
		customerIDField.setPreferredSize(new Dimension(100, 25));
		customerPasswordField.setPreferredSize(new Dimension(100, 25));
		confirmPasswordField.setPreferredSize(new Dimension(100, 25));
		accountNumberField.setPreferredSize(new Dimension(100, 25));
		transferAmountField.setPreferredSize(new Dimension(100, 25));
		fromExtTransferField.setPreferredSize(new Dimension(100, 25));
		toExtTransferField.setPreferredSize(new Dimension(100, 25));
		amountExtTransferField.setPreferredSize(new Dimension(100, 25));


		// Login(Customer) Panel
		customerLoginPanel.add(userIDLabel, c);
		customerLoginPanel.add(userIDField, c);
		customerLoginPanel.add(userPasswordLabel, c);
		customerLoginPanel.add(userPasswordField, c);
		customerLoginPanel.add(custLogin, c);
		customerLoginPanel.add(exitCustomer, c);

		// Login(Employee) Panel
		employeeLoginPanel.add(employeeIDLabel, c);
		employeeLoginPanel.add(employeeIDField, c);

		employeeLoginPanel.add(employeePasswordLabel, c);
		employeeLoginPanel.add(employeePasswordField, c);

		employeeLoginPanel.add(empLogin, c);
		employeeLoginPanel.add(exitEmployee, c);

		// Create Account Panel
		c.gridx= -1;
		createAccountPanel.add(customerIDLabel, c);
		c.gridy = 0;
		createAccountPanel.add(customerIDField, c);


		c.gridy = 1;
		createAccountPanel.add(firstnameLabel, c);
		createAccountPanel.add(firstnameField, c);

		c.gridy = 2;
		createAccountPanel.add(lastnameLabel, c);
		createAccountPanel.add(lastnameField, c);


		c.gridy = 3;
		createAccountPanel.add(customerPasswordLabel, c);
		createAccountPanel.add(customerPasswordField, c);


		c.gridy = 4;
		createAccountPanel.add(confirmPasswordLabel, c);
		createAccountPanel.add(confirmPasswordField, c);

		c.gridy = 2;
		createAccountPanel.add(exitCreate, c);
		createAccountPanel.add(completeCreate, c);

		// Customer Account Panel/options
		customerAccountPanel.add(transferInternal, c);
		customerAccountPanel.add(transferExternal, c);	
		customerAccountPanel.add(showBalance, c);
		customerAccountPanel.add(showHistory, c);
		customerAccountPanel.add(customerLogout, c);

		// Employee Account Panel/options
		employeeAccountPanel.add(listAll,c);
		employeeAccountPanel.add(employeeLogout, c);

		// Internal Transfer Panel
		c.gridy = 0;
		internalTransferPanel.add(accountTypeComboBox, c);
		internalTransferPanel.add(accountNumberLabel, c);
		internalTransferPanel.add(accountNumberField, c);
		c.gridy = 1;
		internalTransferPanel.add(transferTypeComboBox, c);
		internalTransferPanel.add(transferAmountLabel, c);
		internalTransferPanel.add(transferAmountField, c);
		c.gridy = 2;
		internalTransferPanel.add(confirmIntTransfer, c);
		internalTransferPanel.add(exitIntTransfer, c);

		// External Transfer Panel
		c.gridy = 0;
		externalTransferPanel.add(fromComboBox, c);
		externalTransferPanel.add(toComboBox, c);
		c.gridy = 1;
		externalTransferPanel.add(fromExtTransferLabel, c);
		externalTransferPanel.add(fromExtTransferField, c);
		c.gridy = 2;
		externalTransferPanel.add(toExtTransferLabel, c);
		externalTransferPanel.add(toExtTransferField, c);
		c.gridy = 3;
		externalTransferPanel.add(amountExtTransferLabel, c);
		externalTransferPanel.add(amountExtTransferField, c);
		c.gridy = 4;
		externalTransferPanel.add(confirmExtTransfer, c);
		externalTransferPanel.add(exitExtTransfer, c);

	}


	/*						PANEL TRANSITIONING
	 *------------------------------------------------------------------------------*/

	public void getCreate() {
		this.add(createAccountPanel);
		this.setContentPane(createAccountPanel);
		this.revalidate();
	}

	public void getLoginCustomer() {
		this.add(customerLoginPanel);
		this.setContentPane(customerLoginPanel);
		this.revalidate();
	}

	public void getAccountCustomer() {
		this.add(customerAccountPanel);
		this.setContentPane(customerAccountPanel);
		this.revalidate();	
	}

	public void getLoginEmployee() {
		this.add(employeeLoginPanel);
		this.setContentPane(employeeLoginPanel);
		this.revalidate();
	}

	public void getAccountEmployee() {
		this.add(employeeAccountPanel);
		this.setContentPane(employeeAccountPanel);
		this.revalidate();
	}

	public void getInternalTransfer() {
		this.add(internalTransferPanel);
		this.setContentPane(internalTransferPanel);
		this.revalidate();
	}

	public void getExternalTransfer() {
		this.add(externalTransferPanel);
		this.setContentPane(externalTransferPanel);
		this.revalidate();
	}

	/*								     EXIT
	 *------------------------------------------------------------------------------*/

	public void getExitHome() {
		this.setContentPane(welcomePanel);
		this.revalidate();
	}

	public void getExit_Create() {
		this.setContentPane(welcomePanel);
		this.revalidate();
	}

	/*								EXIT WITH DIALOUGE
	 *------------------------------------------------------------------------------*/

	public void getExitCustomer() {
		int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION)
			this.setContentPane(customerLoginPanel);
		this.revalidate();
	}

	public void getExitEmployee() {
		int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION)
			this.setContentPane(employeeLoginPanel);
		this.revalidate();
	}

	public void getLogout() {
		int result = JOptionPane.showConfirmDialog(null, "Thank you for using Loanely Banking!", "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION)
			System.exit(0);
		this.revalidate();
	}

}


























































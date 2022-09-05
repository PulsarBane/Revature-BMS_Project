package com.maven.bank.exception;

public class ApplicationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4927957479496983860L;

	@Override
	public String getMessage() {
		return "Internal Database Server Error!! Please try again later!"; 
	}
}


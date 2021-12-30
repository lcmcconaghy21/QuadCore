package com.lcmcconaghy.java.quadcore.util;

public class QuadException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	///////////
	// FIELD //
	///////////
	
	protected String errorMessage;
	
	/////////////////
	// CONSTRUCTOR //
	/////////////////
	
	public QuadException(String arg0)
	{
		this.errorMessage = arg0;
	}
	
	/////////////
	// MESSAGE //
	/////////////
	
	public String getErrorMessage()
	{
		return this.errorMessage;
	}
	
	@Override
	public String getLocalizedMessage()
	{
		return this.errorMessage;
	}
	
}

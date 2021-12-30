package com.lcmcconaghy.java.quadcore.cmd.argument;

public abstract class Argument<T> implements Arg<T>
{
	
	///////////////
	// PARAMETER //
	///////////////
	
	protected String parameterName;
	protected T defaultValue;
	protected String defaultValueName;
	protected boolean concatinating = false;
	
	/////////////////
	// CONSTRUCTOR //
	/////////////////
	
	public Argument(String arg0)
	{
		this.parameterName = arg0;
	}
	
	public Argument(String arg0, T arg1, String arg2, boolean arg3)
	{
		this.parameterName = arg0;
		this.defaultValue = arg1;
		this.defaultValueName = arg2;
		this.concatinating = arg3;
	}
	
	public Argument(String arg0, boolean arg1)
	{
		this.parameterName = arg0;
		this.concatinating = arg1;
	}
	
	///////////////
	// PARAMETER //
	///////////////
	
	protected String getParamterName()
	{
		return this.parameterName;
	}
	
	public T getDefaultValue()
	{
		return this.defaultValue;
	}
	
	public String getDescription()
	{
		String desc = "<b>";
		
		if (this.defaultValue == null)
		{
			desc += "<"+this.parameterName+">";
		}
		else
		{
			desc += "["+this.parameterName+"="+this.defaultValueName+"]";
		}
		
		return desc;
	}
	
	public boolean isConcatinating()
	{
		return this.concatinating;
	}
	
}

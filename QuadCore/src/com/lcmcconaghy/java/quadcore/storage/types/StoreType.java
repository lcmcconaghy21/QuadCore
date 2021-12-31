package com.lcmcconaghy.java.quadcore.storage.types;

import java.io.File;

public enum StoreType 
{
	JSON(0, "json"),
	YAML(1, "yml"),
	TXT(2, "txt");
	
	private int token;
	private String ext;
	
	StoreType(int arg0, String arg1)
	{
		this.token = arg0;
		this.ext = arg1;
	}
	
	///////////
	// TOKEN //
	///////////
	
	public int getToken()
	{
		return this.token;
	}
	
	public StoreType getFromFile(File arg0)
	{
		String extension = arg0.getName().substring(arg0.getName().indexOf("."));
		
		if (extension.equalsIgnoreCase("yml")) return YAML;
		if (extension.equalsIgnoreCase("txt")) return TXT;
		
		return JSON;
	}
	
	public StoreType getFromToken(int arg0)
	{
		if (0>arg0 && arg0>2) return YAML;
		
		if (arg0 == 1) return YAML;
		if (arg0 == 2) return TXT;
		
		return JSON;
	}
	
	///////////////
	// EXTENSION //
	///////////////
	
	public String getExtension()
	{
		return this.ext;
	}
}

package com.lcmcconaghy.java.quadcore;

import com.lcmcconaghy.java.quadcore.storage.QuadConfig;

public class ConfQuad extends QuadConfig
{
	
	/////////////////
	// CONSTRUCTOR //
	/////////////////
	
	public ConfQuad()
	{
		super(QuadCore.get(), ConfQuad.class);
	}
	
	//////////////
	// COMMANDS //
	//////////////
	
	private int maxTabComplete = 50;
	
	public int getMaxTabComplete() { return this.maxTabComplete; }
	
	
}

package com.lcmcconaghy.java.quadcore.storage;

import com.lcmcconaghy.java.quadcore.QuadPlugin;
import com.lcmcconaghy.java.quadcore.storage.types.FileYaml;

public class QuadConfig extends FileYaml<QuadConfig>
{
	
	/////////////////
	// CONSTRUCTOR //
	/////////////////
	
	@SuppressWarnings("unchecked")
	public QuadConfig(QuadPlugin arg0, Class<? extends QuadConfig> arg1)
	{
		super(arg0, "config", (Class<QuadConfig>) arg1);
	}
	
}
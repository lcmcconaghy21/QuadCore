package com.lcmcconaghy.java.quadcore.storage.types;

import java.io.File;
import java.io.IOException;

import com.lcmcconaghy.java.quadcore.QuadPlugin;

public abstract class QuadFile<T> implements StoreFile<T>
{
	
	public QuadFile(QuadPlugin arg0, String arg1, StoreType arg2)
	{
		this.plugin = arg0;
		this.sourceFile = new File(arg0.getDataFolder().getAbsolutePath()+File.separator+arg1+"."+arg2.getExtension());
	}
	
	//////////
	// PATH //
	//////////
	
	protected QuadPlugin plugin;
	protected File sourceFile;
	
	public File getSourceFile()
	{
		return this.sourceFile;
	}
	
	public File getDirectory()
	{
		return this.sourceFile.getParentFile();
	}
	
	///////////
	// FILES //
	///////////
	
	public void ensureFile() throws IOException
	{
		if (!this.sourceFile.exists())
		{
			if (!this.sourceFile.getParentFile().exists()) this.sourceFile.getParentFile().mkdirs();
			
			this.sourceFile.createNewFile();
		}
	}
	
}

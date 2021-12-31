package com.lcmcconaghy.java.quadcore.storage.types;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.lcmcconaghy.java.quadcore.QuadPlugin;

public class FileJson<T> extends QuadFile<T>
{
	
	public FileJson(QuadPlugin arg0, String arg1, Class<T> arg2)
	{
		super(arg0, arg1, StoreType.JSON);
	}
	
	//////////
	// JSON //
	//////////
	
	protected Gson gson = plugin.getGsonBuilder().create();
	
	public void save(T arg0) throws IOException
	{
		ensureFile();
		
		gson.toJson(arg0, new FileWriter(this.sourceFile.getAbsolutePath()));
	}
	
	///////////////////
	// INSTANTIATION //
	///////////////////
	
	protected Class<T> sourceClass;
	
	public T instantiate() 
	{
		T ret = null;
		
		try
		{
			ret = gson.fromJson(new FileReader(this.sourceFile.getAbsolutePath()), sourceClass);
		}
		catch (JsonSyntaxException | JsonIOException | FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		return ret;
	}
	
}

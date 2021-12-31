package com.lcmcconaghy.java.quadcore.storage.types;

import java.io.IOException;
import java.lang.reflect.Field;

import org.bukkit.configuration.file.YamlConfiguration;

import com.lcmcconaghy.java.quadcore.QuadPlugin;

public class FileYaml<T> extends QuadFile<T>
{
	
	/////////////////
	// CONSTRUCTOR //
	/////////////////
	
	protected Class<T> sourceClass;
	
	public FileYaml(QuadPlugin arg0, String arg1, Class<T> arg2)
	{
		super(arg0, arg1, StoreType.YAML);
	}
	
	///////////
	// FILES //
	///////////
	
	protected YamlConfiguration config;
	
	@Override
	public void save(T arg0) throws IOException
	{
		ensureFile();
		
		config = new YamlConfiguration();
		
		for (Field declaredField : this.sourceClass.getDeclaredFields())
		{
			declaredField.setAccessible(true);
			
			Object res = null;
			try
			{
				res = declaredField.get(arg0);
			}
			catch (IllegalArgumentException | IllegalAccessException e)
			{
				e.printStackTrace();
			}
			
			config.set(declaredField.getName(), res);
		}
		
		config.save(this.sourceFile);
	}
	
	///////////////////
	// INSTANTIATION //
	///////////////////

	@SuppressWarnings("deprecation")
	@Override
	public T instantiate()
	{
		config = YamlConfiguration.loadConfiguration(this.sourceFile);
		
		// Instantiate T object...
		T ret = null;
		try
		{
			ret = this.sourceClass.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
		
		// ...for every declared field, search YAML for that field...
		for (Field declaredField : this.sourceClass.getDeclaredFields())
		{
			declaredField.setAccessible(true);
			// ...set declared field in YAML to that field in the class.
			try
			{
				declaredField.set(ret, config.get(declaredField.getName()));
			}
			catch (IllegalArgumentException | IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
		
		return ret;
	}
	
}

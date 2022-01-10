package com.lcmcconaghy.java.quadcore;

import java.lang.reflect.Field;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;

import com.lcmcconaghy.java.quadcore.command.QuadCommand;
import com.lcmcconaghy.java.quadcore.command.defaults.CmdQuad;
import com.lcmcconaghy.java.quadcore.util.QUtil;

public class QuadCore extends QuadPlugin
{
	
	///////////////
	// SINGLETON //
	///////////////
	
	private static QuadCore i;
	public static QuadCore get() { return QuadCore.i; }
	
	/////////////////
	// CONSTRUCTOR //
	/////////////////
	
	public QuadCore()
	{
		QuadCore.i = this;
	}
	
	////////////
	// FIELDS //
	////////////
	
	protected SimpleCommandMap commandMap;
	protected static int debug = 0;
	
	//////////////
	// COMMANDS //
	//////////////
	
	public SimpleCommandMap getCommandMap()
	{
		if (this.commandMap != null) return this.commandMap;
		
		try
		{
			Field fieldCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
			fieldCommandMap.setAccessible(true);
			this.commandMap = (SimpleCommandMap) fieldCommandMap.get(Bukkit.getServer());
		}
		catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
		
		return this.commandMap;
	}
	
	@Override
	public List<QuadCommand> getRegistrableCommands()
	{
		return QUtil.list(new CmdQuad());
	}
	
	////////////
	// ENABLE //
	////////////
	
	public void onStartup()
	{
		this.setConfig(new ConfQuad());
	}
	
	///////////
	// DEBUG //
	///////////
	
	@Deprecated
	public static void debug()
	{
		get().log("Debugging path <d>"+debug);
		
		debug++;
	}
}

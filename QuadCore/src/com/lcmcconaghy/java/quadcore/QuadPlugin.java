package com.lcmcconaghy.java.quadcore;

import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.lcmcconaghy.java.quadcore.command.IQuadCommand;
import com.lcmcconaghy.java.quadcore.command.QuadCommand;
import com.lcmcconaghy.java.quadcore.engine.Engine;
import com.lcmcconaghy.java.quadcore.storage.QuadConfig;
import com.lcmcconaghy.java.quadcore.util.QUtil;
import com.lcmcconaghy.java.quadcore.util.QuadException;

public abstract class QuadPlugin extends JavaPlugin
{
	
	/////////////
	// STARTUP //
	/////////////
	
	@Override
	public void onEnable()
	{
		log("Starting <d>"+this.getName()+"<i>...");
		
		if (this.hasRegistrableCommands())
		{
			log("Registering <d>"+this.getRegistrableCommands().size()+" <i>commands...");
			registerCommands( this.getRegistrableCommands() );
			log("Finished registering commands!");
		}
		
		if (this.containsEngines())
		{
			log("Registering <d>"+this.getEngines().size()+" <i>engines...");
			listen( this.getEngines() );
			log("Finished registering engines!");
		}
		
		this.onStartup();
	}
	
	public void onStartup()
	{
		
	}
	
	//////////////
	// COMMANDS //
	//////////////
	
	private boolean registerCommands(List<QuadCommand> arg0)
	{
		if (getRegistrableCommands()==null || getRegistrableCommands().isEmpty()) return true;
		
		boolean ret = true;
		
		for (QuadCommand item : arg0)
		{
			if (QuadCore.get().getCommandMap().getCommand(item.getLabel()) != null)
			{
				ret = false;
				
				continue;
			}
			
			try
			{
				item.register();
			}
			catch (QuadException e)
			{
				log(Level.WARNING, "Failed to register command <d>"+item.getLabel()+"<c>.");
				e.printStackTrace();
			}
			
			QuadCore.get().getCommandMap().register(item.getLabel(), getName(), new IQuadCommand(item));
			
			log("<i>...registered command <d>"+item.getLabel()+" <i>successfully...");
		}
		
		return ret;
	}
	
	public List<QuadCommand> getRegistrableCommands()
	{
		return null;
	}
	
	public boolean hasRegistrableCommands()
	{
		if (this.getRegistrableCommands() == null || this.getRegistrableCommands().isEmpty()) return false;
		
		return true;
	}
	
	/////////////
	// CONSOLE //
	/////////////
	
	protected static final ConsoleCommandSender console = Bukkit.getConsoleSender();
	
	public ConsoleCommandSender getConsole()
	{
		return QuadPlugin.console;
	}
	
	////////////
	// ENGINE //
	////////////
	
	public void listen(List<Engine> engines)
	{
		if (engines == null || engines.isEmpty()) return;
		
		for (Engine arg : engines)
		{
			arg.listen(this);
		}
	}
	
	public List<Engine> getEngines()
	{
		return null;
	}
	
	public boolean containsEngines()
	{
		if ( this.getEngines() == null ) return false;
		
		return this.getEngines().size()>0;
	}
	
	//////////
	// GSON //
	//////////
	
	protected static GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
	
	public GsonBuilder getGsonBuilder()
	{
		return QuadPlugin.gsonBuilder;
	}
	
	public <T> GsonBuilder registerTypeAdapter(Class<T> arg0, TypeAdapter<T> arg1)
	{
		getGsonBuilder().registerTypeAdapter(arg0, arg1);
		
		return getGsonBuilder();
	}
	
	////////////
	// LOGGER //
	////////////
	
	public void log(Level arg0, String... args)
	{
		String pref = "<a>[INFO]: <b>";
		if (arg0 == Level.SEVERE)
		{
			pref = "<4>[SEVERE]: <c>";
		}

		else if (arg0 == Level.WARNING)
		{
			pref = "<6>[WARNING]: <i>";
		}
		
		for (String arg : args)
		{
			console.sendMessage(QUtil.parse(pref+arg));
		}
	}
	
	public void log(String...args)
	{
		log(Level.INFO, args);
	}
	
	//////////
	// FILE //
	//////////
	
	protected QuadConfig iConfig;
	
	public void setConfig(QuadConfig arg0)
	{
		arg0.instantiate();
		
		this.iConfig = arg0;
	}
	
	public QuadConfig getIConfig() 
	{
		return this.iConfig;
	}
}

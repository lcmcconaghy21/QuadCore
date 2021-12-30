package com.lcmcconaghy.java.quadcore.engine;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class Engine implements Listener
{
	
	//////////////////
	// REGISTRATION //
	//////////////////
	
	public void listen(Plugin arg0)
	{
		Bukkit.getPluginManager().registerEvents(this, arg0);
	}
	
}

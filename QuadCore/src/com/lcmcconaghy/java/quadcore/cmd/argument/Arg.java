package com.lcmcconaghy.java.quadcore.cmd.argument;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.lcmcconaghy.java.quadcore.util.QuadException;

public interface Arg<T>
{
	
	public T read(String arg0, CommandSender arg1) throws QuadException;
	
	public List<String> getTabList();
	
}

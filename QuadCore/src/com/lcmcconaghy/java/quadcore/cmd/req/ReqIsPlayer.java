package com.lcmcconaghy.java.quadcore.cmd.req;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReqIsPlayer implements Req
{
	
	/////////////////
	// CONSTRUCTOR //
	/////////////////
	
	private static ReqIsPlayer i = new ReqIsPlayer();
	public static ReqIsPlayer get() { return ReqIsPlayer.i; }
	
	
	/////////////////
	// REQUIREMENT //
	/////////////////
	
	@Override
	public boolean check(CommandSender arg0)
	{	
		return (arg0 instanceof Player);
	}
}

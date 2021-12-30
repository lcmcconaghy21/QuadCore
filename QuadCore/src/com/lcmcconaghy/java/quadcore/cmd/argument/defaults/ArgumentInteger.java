package com.lcmcconaghy.java.quadcore.cmd.argument.defaults;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.lcmcconaghy.java.quadcore.cmd.argument.Argument;
import com.lcmcconaghy.java.quadcore.util.QuadException;

public class ArgumentInteger extends Argument<Integer>
{

	public ArgumentInteger(String arg0, Integer arg1, String arg2, boolean arg3)
	{
		super(arg0, arg1, arg2, arg3);
	}
	
	public ArgumentInteger(String arg0, boolean arg1)
	{
		super(arg0, arg1);
	}
	
	public ArgumentInteger(String arg0)
	{
		super(arg0);
	}
	
	///////////////
	// EXECUTION //
	///////////////
	
	@Override
	public Integer read(String arg0, CommandSender arg1) throws QuadException
	{
		int ret = 0;
		
		try
		{
			ret = Integer.parseInt(arg0);
		}
		catch (NumberFormatException e)
		{
			throw new QuadException("Unable to parse <d>"+arg0+" <c>as a number.");
		}
		
		return ret;
	}

	@Override
	public List<String> getTabList()
	{
		return Arrays.asList("0", "1", "2", "3", "4", "5");
	}
}

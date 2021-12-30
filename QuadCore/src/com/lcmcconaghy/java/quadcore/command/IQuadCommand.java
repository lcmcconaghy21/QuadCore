package com.lcmcconaghy.java.quadcore.command;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import com.lcmcconaghy.java.quadcore.util.QUtil;
import com.lcmcconaghy.java.quadcore.util.QuadException;

public class IQuadCommand extends BukkitCommand
{
	
	////////////
	// SOURCE //
	////////////
	
	protected QuadCommand source;
	
	public IQuadCommand(QuadCommand arg0)
	{
		super(arg0.getLabel());
		
		this.setAliases(arg0.aliases);
		this.setDescription(arg0.description);
		
		this.source = arg0;
	}

	@Override
	public boolean execute(CommandSender arg0, String arg1, String[] args)
	{
		QuadCommand source = this.source;
		
		if (source.isParent() && args.length == 0)
		{
			QuadCommand cmdHelp = source.getChild("help");
			
			if (cmdHelp == null) return false;
			cmdHelp.parent = source;
			
			cmdHelp.fixSender(arg0);
			
			try
			{
				cmdHelp.perform();
			}
			catch (QuadException e)
			{
				message(arg0, "<4>Error: <c>"+e.getErrorMessage());
			}
			
			return false;
		}
		
		while (source.isParent() && source.containsChild(args[0]))
		{
			QuadCommand child = source.getChild(args[0]);
			child.parent = source;
			source = child;
			
			if (args.length == 1)
			{
				args = new String[0];
				
				break;
			}
			
			args = (String[]) ArrayUtils.remove(args, 0);
		}
		
		if (source.isParent() && !source.containsChild(args[0]))
		{
			message(arg0, "<c>Subcommand <d>"+args[0]+" <c>does not exist!");
			return false;
		}
		source.fixArgs(args);
		source.fixSender(arg0);
		
		try
		{
			source.perform();
		}
		catch (QuadException e)
		{
			message(arg0, "<c>"+e.getErrorMessage());
		}
		
		return false;
	}
	
	@Override
	public List<String> tabComplete(CommandSender arg0, String arg1, String[] args, Location arg2)
	{
		return this.tabComplete(arg0, arg1, args);
	}
	
	@Override
	public List<String> tabComplete(CommandSender arg0, String arg1, String[] args)
	{
		List<String> tabCompleteList = new ArrayList<String>();
		
		QuadCommand source = this.source;
		
		while (source.isParent())
		{
			if (args.length <= 0) break;
			if (args[0].isBlank() || args[0].isEmpty() || args[0].equalsIgnoreCase(" ")) break;
			
			source = source.getChild(args[0]);
			args = (String[]) ArrayUtils.remove(args, 0);
		}
		
		if (source.isParent())
		{
			for (QuadCommand cmd : source.getChildren())
			{
				for (String alias : cmd.getAlaises())
				{
					tabCompleteList.add(alias);
				}
			}
			
			return tabCompleteList;
		}
		
		if (source.argumentsExpected.size() <= args.length-1)
		{
			message(arg0, "<c>No more expected arguments!");
		}
		
		try
		{
			tabCompleteList = source.argumentsExpected.get(0).getTabList();
			if (args.length > 0) tabCompleteList = source.argumentsExpected.get(args.length).getTabList();
		}
		catch (IndexOutOfBoundsException e)
		{
			tabCompleteList = new ArrayList<String>();
			message(arg0, "<4>Error: <c>Unexpected arguments at this position.");
		}
		
		return tabCompleteList;
	}
	
	protected void message(CommandSender arg0, String arg1)
	{
		arg0.sendMessage(QUtil.parse(arg1));
	}
}

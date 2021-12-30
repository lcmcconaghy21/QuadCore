package com.lcmcconaghy.java.quadcore.command.defaults;

import java.util.Iterator;

import org.bukkit.command.Command;

import com.lcmcconaghy.java.quadcore.QuadCore;
import com.lcmcconaghy.java.quadcore.command.QuadCommand;
import com.lcmcconaghy.java.quadcore.util.QUtil;
import com.lcmcconaghy.java.quadcore.util.QuadException;

public class HelpCommand extends QuadCommand
{
	
	public HelpCommand()
	{
		this.addAlias("help");
		this.addAlias("h");
		
		this.setDescription("list all subcommands");
	}
	
	@Override
	public void perform() throws QuadException
	{
		if (this.parent == null)
		{
			StringBuilder builder = new StringBuilder();
			Iterator<Command> itCommand = QuadCore.get().getCommandMap().getCommands().iterator();
			
			while (itCommand.hasNext())
			{
				Command cmd = itCommand.next();
				builder.append("<b>"+QUtil.stringify(cmd.getAliases(), ",")+"<f>: "+cmd.getDescription());
				
				if (!itCommand.hasNext()) break;
				builder.append("\n");
			}
			
			message(builder.toString());
			
			return;
		}
		
		StringBuilder list = new StringBuilder();
		Iterator<QuadCommand> itCommand = this.parent.getChildren().iterator();
		list.append(QUtil.center(this.parent.getLabel()+" Help")+"\n");
		
		while (itCommand.hasNext())
		{
			QuadCommand cmd = itCommand.next();
			
			list.append("<b>/"+QUtil.stringify(this.getParents()," ")+" "+QUtil.stringify(cmd.getAlaises(), ","));
			list.append("<i>: "+cmd.getDescription());
			
			if (!itCommand.hasNext()) break;
			
			list.append("\n");
		}
		
		message(list.toString());
	}
	
}

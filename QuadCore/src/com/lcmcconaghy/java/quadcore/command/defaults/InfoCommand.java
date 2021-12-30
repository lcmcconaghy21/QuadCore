package com.lcmcconaghy.java.quadcore.command.defaults;

import org.bukkit.plugin.Plugin;

import com.lcmcconaghy.java.quadcore.command.QuadCommand;
import com.lcmcconaghy.java.quadcore.util.InfoBox;
import com.lcmcconaghy.java.quadcore.util.QUtil;

public class InfoCommand extends QuadCommand
{
	
	protected Plugin plugin;
	
	public InfoCommand(Plugin arg0)
	{
		this.addAliases("info", "i");
		this.setDescription("get all plugin information");
		
		this.plugin = arg0;
	}
	
	@Override
	public void perform()
	{
		InfoBox info = new InfoBox(plugin.getName()+" Help")
				       .append("Name", plugin.getName())
				       .append("Version", plugin.getDescription().getVersion())
				       .append("Authors", QUtil.stringify( plugin.getDescription().getAuthors() ));
		
		message(info.toString());
	}
}

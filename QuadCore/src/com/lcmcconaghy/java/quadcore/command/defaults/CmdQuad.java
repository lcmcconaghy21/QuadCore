package com.lcmcconaghy.java.quadcore.command.defaults;

import com.lcmcconaghy.java.quadcore.QuadCore;
import com.lcmcconaghy.java.quadcore.command.QuadCommand;

public class CmdQuad extends QuadCommand
{
	
	public CmdQuad()
	{
		this.addAlias("quad");
		this.setDescription("manage quadcore dependencies");
		
		this.addChild(new HelpCommand());
		this.addChild(new InfoCommand(QuadCore.get()));
	}
}

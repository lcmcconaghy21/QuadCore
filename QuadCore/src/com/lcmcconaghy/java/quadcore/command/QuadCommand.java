package com.lcmcconaghy.java.quadcore.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.lcmcconaghy.java.quadcore.cmd.argument.Argument;
import com.lcmcconaghy.java.quadcore.util.QUtil;
import com.lcmcconaghy.java.quadcore.util.QuadException;

public class QuadCommand
{
	
	/////////////
	// ALIASES //
	/////////////
	
	protected List<String> aliases = new ArrayList<String>();
	
	public boolean addAlias(String arg0)
	{
		if (containsAlias(arg0)) return false;
		
		this.aliases.add(arg0);
		
		return true;
	}
	
	public boolean addAliases(String... args)
	{
		for (String arg : args)
		{
			this.addAlias(arg);
		}
		
		return true;
	}
	
	public boolean removeAlias(String arg0)
	{
		if (!containsAlias(arg0)) return false;
		
		this.aliases.remove(arg0);
		
		return true;
	}
	
	public boolean containsAlias(String arg0)
	{
		return this.aliases.contains(arg0);
	}
	
	public List<String> getAlaises()
	{
		return this.aliases;
	}
	
	public String getLabel()
	{
		String label = "";
		int maxLength = 0;
		
		for (String alias : this.aliases)
		{
			if (alias.length()<=maxLength) continue;
			
			maxLength = alias.length();
			label = alias;
		}
		
		return label;
	}
	
	public String getStringifiedAliases()
	{
		return QUtil.stringify(aliases, ",");
	}
	
	///////////////
	// ARGUMENTS //
	///////////////
	
	protected ArrayList<Argument<?>> argumentsExpected = new ArrayList<Argument<?>>();
	protected LinkedList<String> arguments = new LinkedList<String>();
	protected int tracer = -1;
	
	public void addArgument(Argument<?> arg0)
	{
		this.argumentsExpected.add(arg0);
	}
	
	public QuadCommand fixArgs(String[] args)
	{
		this.tracer = -1;
		this.arguments.clear();
		this.arguments = new LinkedList<String>(Arrays.asList(args));
		
		return this;
	}
	
	public int expectedArgLength()
	{
		return this.argumentsExpected.size();
	}
	
	@SuppressWarnings("unchecked")
	public <T> T readArgumentAt(int arg0) throws QuadException
	{
		if (this.arguments.get(arg0) == null && this.argumentsExpected.get(arg0) != null)
			throw new QuadException("Expected argument <d>"+this.argumentsExpected.get(arg0).getDescription()+"<c>, received no such argument.");
		
		return (T) this.argumentsExpected.get(arg0).read(arguments.get(arg0), this.sender);
	}
	
	public <T> T readArgument() throws QuadException
	{
		this.tracer++;
		
		int argumentSize = this.argumentsExpected.size();
		
		if (this.tracer >= argumentSize &&
			!this.argumentsExpected.get( argumentSize-1 ).isConcatinating()) 
			throw new QuadException("Cannot read argument that does not exist! Reading <d>"+this.tracer+" <c>arguments when expecting <d>"+argumentSize+"<c>.");
		
		
		
		return readArgumentAt(this.tracer);
	}
	
	//////////////
	// COMMANDS //
	//////////////
	
	protected ArrayList<QuadCommand> commandChildren = new ArrayList<QuadCommand>();
	protected QuadCommand parent;
	protected CommandSender sender;
	
	public void addChild(QuadCommand arg0)
	{
		this.commandChildren.add(arg0);
	}
	
	public boolean containsChild(QuadCommand arg0)
	{
		return this.commandChildren.contains(arg0);
	}
	
	public boolean containsChild(String arg0)
	{
		if (getChild(arg0) != null) return true;
		
		return false;
	}
	
	public QuadCommand getChild(String arg0)
	{
		QuadCommand ret = null;
		
		for (QuadCommand cmds : this.commandChildren)
		{
			for (String alias : cmds.aliases)
			{
				if (!alias.equalsIgnoreCase(arg0)) continue;
				
				ret = cmds;
				
				break;
			}
		}
		
		return ret;
	}
	
	public List<QuadCommand> getChildren()
	{
		return this.commandChildren;
	}
	
	public boolean isParent()
	{
		return this.commandChildren.size()>0;
	}
	
	public boolean hasParent()
	{
		if (this.parent == null) return false;
		
		return true;
	}
	
	public List<QuadCommand> getParents()
	{
		QuadCommand target = this;
		List<QuadCommand> path = new ArrayList<QuadCommand>();
		
		while (target.hasParent())
		{
			path.add(parent);
			
			target = target.parent;
		}
		
		return path;
	}
	
	public CommandSender fixSender(CommandSender arg0)
	{
		this.sender = arg0;
		
		return this.sender;
	}
	
	/////////////////
	// DESCRIPTION //
	/////////////////
	
	protected String description = "lorem ipsum";
	
	public void setDescription(String arg0)
	{
		this.description = arg0;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	///////////////
	// EXECUTION //
	///////////////
	
	public void setup(CommandSender arg0, String[] args)
	{
		this.fixSender(arg0);
		this.fixArgs(args);
	}
	
	public void perform() throws QuadException
	{
		//TODO write perform by-command
		
		return;
	}
	
	/////////////
	// MESSAGE //
	/////////////
	
	protected void message(String...args)
	{
		for (String msg : args)
		{
			sender.sendMessage(QUtil.parse(msg));
		}
	}
	
	//////////////////
	// REGISTRATION //
	//////////////////
	
	public void register() throws QuadException
	{
		if (this.arguments.size()>0 && this.commandChildren.size()>0)
			throw new QuadException("Error registering command <i>"+this.getLabel()+"<c>. Command can either be parent or executor.");
		
		boolean willConcat = false;
		boolean argAfterConcat = false;
		
		for (Argument<?> arg : this.argumentsExpected)
		{
			if (willConcat) throw new QuadException("Error registering command <i>"+this.getLabel()+"<c>. Cannot add argument after concatinating arg.");
			
			if (arg.isConcatinating()) willConcat = true;
		}
		
		if (argAfterConcat) throw new QuadException("Error registering command <i>"+this.getLabel()+"<c>. Command can only have one concatinating argument.");
		
		
	}
	
	////////////
	// OBJECT //
	////////////
	
	@Override
	public String toString()
	{
		return this.getLabel();
	}
	
}

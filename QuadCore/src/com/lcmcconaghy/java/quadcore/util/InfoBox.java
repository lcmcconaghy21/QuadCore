package com.lcmcconaghy.java.quadcore.util;

import java.util.HashMap;
import java.util.Iterator;

public class InfoBox extends HashMap<String, String>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/////////////////
	// CONSTRUCTOR //
	/////////////////
	
	public InfoBox(String arg0)
	{
		this.title = arg0;
	}
	
	///////////
	// TITLE //
	///////////
	
	protected String title;
	
	public InfoBox setTitle(String arg0)
	{
		this.title = arg0;
		
		return this;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	/////////////
	// STORAGE //
	/////////////
	
	/**
	 * @param arg0 String key
	 * @param arg1 String value
	 * @return
	 */
	public InfoBox append(String arg0, String arg1)
	{
		this.put(arg0, arg1);
		
		return this;
	}
	
	@Deprecated
	@Override
	public String put(String arg0, String arg1)
	{
		return super.put(arg0, arg1);
	}
	
	///////////////
	// STRINGIFY //
	///////////////
	
	@Override
	public String toString()
	{
		StringBuilder infoBuilder = new StringBuilder(QUtil.center(this.title));
		Iterator<String> keyIterator = this.keySet().iterator();
		
		while (keyIterator.hasNext())
		{
			infoBuilder.append("\n");
			String key = keyIterator.next();
			
			infoBuilder.append("<a>"+key+": <i>"+this.get(key));
		}
		
		return infoBuilder.toString();
	}
}

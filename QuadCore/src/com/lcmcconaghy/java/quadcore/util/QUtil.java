package com.lcmcconaghy.java.quadcore.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QUtil
{
	
	////////////
	// FIELDS //
	////////////
	
	private static HashMap<String, String> colors = new HashMap<String, String>();
	
	static
	{
		colors.put("a", "§a");
		colors.put("b", "§b");
		colors.put("c", "§c");
		colors.put("d", "§d");
		colors.put("e", "§e");
		colors.put("i", "§e");
		colors.put("f", "§f");
		colors.put("0", "§0");
		colors.put("1", "§1");
		colors.put("2", "§2");
		colors.put("3", "§3");
		colors.put("4", "§4");
		colors.put("5", "§5");
		colors.put("6", "§6");
		colors.put("7", "§7");
		colors.put("8", "§8");
		colors.put("9", "§9");
		colors.put("italic", "§o");
		colors.put("o", "§o");
		colors.put("bold", "§l");
		colors.put("l", "§l");
		colors.put("u", "§m");
		colors.put("crazy", "§k");
		colors.put("k", "§k");
	}
	
	///////////
	// PARSE //
	///////////
	
	public static String parse(String arg0)
	{
		for (String key : colors.keySet())
		{
			if (!arg0.contains("<"+key+">")) continue;
			
			arg0 = arg0.replaceAll("<"+key+">", colors.get(key));
		}
		
		return arg0;
	}
	
	public static String center(String arg0)
	{
		int totalCharSize = 42-arg0.length();
		int sides = totalCharSize/2;
		
		StringBuilder builder = new StringBuilder("{ <a>"+arg0+" <i>}");
		
		for (int i = 0; i<sides; i++)
		{
			builder.append("=").insert(0, "=");
			builder.insert(0, "<i>");
		}
		
		return parse(builder.toString());
	}
	
	//////////
	// LIST //
	//////////
	
	public static <T> List<T> list(@SuppressWarnings("unchecked") T...args)
	{
		List<T> ret = new ArrayList<T>();
		
		for (T part : args)
		{
			ret.add(part);
		}
		
		return ret;
	}
	
	public static String stringify(List<?> objects)
	{
		return stringify(objects, " ");
	}
	
	public static String stringify(List<?> objects, String separator)
	{
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i<objects.size(); i++)
		{
			Object item = objects.get(i);
			builder.append(item.toString());
			
			if (i>=objects.size()-1) break;
			
			builder.append(separator);
		}
		
		return builder.toString();
	}
	
}

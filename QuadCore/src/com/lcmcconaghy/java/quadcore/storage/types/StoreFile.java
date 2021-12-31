package com.lcmcconaghy.java.quadcore.storage.types;

import java.io.IOException;

public interface StoreFile<T>
{
	
	public void save(T arg0) throws IOException;
	
	public T instantiate();
	
}

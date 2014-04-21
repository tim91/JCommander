package org.jcommander.util;

import junit.framework.Assert;

import org.junit.Test;

public class ParamterizedStringTest {

	@Test
	public void paramterizedString(){
		String filename = "temp.txt";
		float ff = 4.543f;
		Object [] ob = new Object[]{filename,ff};
		String message = String.format("File %s could not be deleted, %f", ob);
		System.out.println(message);
	}
	
	@Test public void finnalyTest(){
		Assert.assertEquals(false, finnaly());
	}
	
	public boolean finnaly(){
		
		try{
			return true;
		}finally{
			return false;
		}
		
	}
}

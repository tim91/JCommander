package org.jcommander.core.path;

import junit.framework.Assert;

import org.jcommander.model.BasePath;
import org.jcommander.model.Path;
import org.junit.Test;

public class BasePathTest {

	@Test
	public void constructorTest()
	{
		Path p = new BasePath("c:\\",null);
		Assert.assertEquals("c:\\", p.toString());
		p = new BasePath("c:",null);
		Assert.assertEquals("c:\\", p.toString());
		
		p = new BasePath("c:\\dsfsdf",null);
		Assert.assertEquals("c:\\dsfsdf\\", p.toString());
		
		p = new BasePath("c:\\dsfsdf\\",null);
		Assert.assertEquals("c:\\dsfsdf\\", p.toString());
	}
	
	@Test
	public void leafTest(){
		
		String p = "c:\\*.*";
		
		Path pp = new BasePath(p,null);
		
		Assert.assertEquals("c:", pp.getLeafInLowerCase());
		Assert.assertEquals("c:\\*.*", pp.getFullPath());
		
		
		p = "c:\\Ala ma kota\\*.*";
		
		pp = new BasePath(p,null);
		
		Assert.assertEquals("Ala ma kota", pp.getLeafInLowerCase());
		Assert.assertEquals("c:\\Ala ma kota\\*.*", pp.getFullPath());
		
		p = "c:\\Ala ma kota\\kot ma ale\\*.*";
		
		pp = new BasePath(p,null);
		
		Assert.assertEquals("kot ma ale", pp.getLeafInLowerCase());
		Assert.assertEquals("c:\\Ala ma kota\\kot ma ale\\*.*", pp.getFullPath());
		
	}
	
	@Test
	public void isRootTest()
	{
		Path p = new BasePath("c:\\",null);
		
		Assert.assertEquals(true, p.isRoot());
		p = new BasePath("c:",null);
		Assert.assertEquals(true, p.isRoot());
		
		p = new BasePath("c:\\dsfsdf",null);
		Assert.assertEquals(false, p.isRoot());
		p = new BasePath("c:\\dsfsdf\\",null);
		Assert.assertEquals(false, p.isRoot());
		p = new BasePath("c:\\dsfsdf\\sdsdfdsf",null);
		Assert.assertEquals(false, p.isRoot());
	}
	
}

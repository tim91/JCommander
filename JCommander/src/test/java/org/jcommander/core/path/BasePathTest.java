package org.jcommander.core.path;

import junit.framework.Assert;

import org.junit.Test;

public class BasePathTest {

	@Test
	public void leafTest(){
		
		String p = "c:\\*.*";
		
		Path pp = new BasePath(p);
		
		Assert.assertEquals("c:", pp.getLeaf());
		Assert.assertEquals("c:\\*.*", pp.getFullPath());
		
		
		p = "c:\\Ala ma kota\\*.*";
		
		pp = new BasePath(p);
		
		Assert.assertEquals("Ala ma kota", pp.getLeaf());
		Assert.assertEquals("c:\\Ala ma kota\\*.*", pp.getFullPath());
		
		p = "c:\\Ala ma kota\\kot ma ale\\*.*";
		
		pp = new BasePath(p);
		
		Assert.assertEquals("kot ma ale", pp.getLeaf());
		Assert.assertEquals("c:\\Ala ma kota\\kot ma ale\\*.*", pp.getFullPath());
		
	}
}

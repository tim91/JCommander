package org.jcommander.util;

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
}

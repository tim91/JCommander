package org.jcommander.model.column;

public class FileAttributeColumn implements AttributeColumn  {

	private String attr;
	
	public FileAttributeColumn(String attribiute) {
		this.attr = attribiute;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.attr;
	}

	
}

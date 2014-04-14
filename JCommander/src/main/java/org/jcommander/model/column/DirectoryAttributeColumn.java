package org.jcommander.model.column;

public class DirectoryAttributeColumn implements AttributeColumn {

	private String attribiute = null;
	
	public DirectoryAttributeColumn(String attribiute) {
		
		this.attribiute = attribiute;
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.attribiute;
	}
}

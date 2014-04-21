package org.jcommander.model.column;

import java.util.Comparator;

public class BaseExtensionColumn implements ExtensionColumn {

	private String ext;
	
	public BaseExtensionColumn(String ext) {
		super();
		this.ext = ext;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.ext;
	}

}

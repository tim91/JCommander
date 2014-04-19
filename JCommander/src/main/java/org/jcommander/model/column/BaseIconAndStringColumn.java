package org.jcommander.model.column;

import javax.swing.Icon;

public class BaseIconAndStringColumn implements IconAndStringColumn {

	private String text;
	private Icon icon;
	
	public BaseIconAndStringColumn(String text, Icon icon) {
		super();
		this.text = text;
		this.icon = icon;
	}

	public String getText() {
		// TODO Auto-generated method stub
		return this.text;
	}

	public Icon getIcon() {
		// TODO Auto-generated method stub
		return this.icon;
	}

}

package org.jcommander.model.column;

import java.util.Comparator;

import javax.swing.Icon;

public class BaseIconAndStringColumn extends Column implements IconAndStringColumn,Comparator<BaseIconAndStringColumn>{

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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.text;
	}

	public boolean rowIsDirectory() {
		// TODO Auto-generated method stub
		return rowIsDirectory;
	}

	public void setIsRowDirectory(boolean b) {
		// TODO Auto-generated method stub
		rowIsDirectory = b;
	}

	public int compare(BaseIconAndStringColumn o1, BaseIconAndStringColumn o2) {
		// TODO Auto-generated method stub
		if(o1.rowIsDirectory() && o2.rowIsDirectory()){
			String v1 = o1.getText();
			String v2 = o2.getText();
			
			return v1.compareTo(v2);
//			return 0;
		}
		else if(o1.rowIsDirectory() && !o2.rowIsDirectory()){
			return 1;
		}
		else if(o2.rowIsDirectory() && !o1.rowIsDirectory()){
			return -1;
		}
		else{
			String v1 = o1.getText();
			String v2 = o2.getText();
			return v1.compareTo(v2);
			
		}
	}

}

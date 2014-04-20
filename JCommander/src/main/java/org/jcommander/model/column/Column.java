package org.jcommander.model.column;

public abstract class Column {

	protected boolean rowIsDirectory;
	
	public abstract boolean rowIsDirectory();
	public abstract void setIsRowDirectory(boolean b);
}

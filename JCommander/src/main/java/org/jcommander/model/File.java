package org.jcommander.model;

import javax.swing.Icon;

public interface File extends Comparable<File> {

	public Object getValueByColumnIndex(int columnIndex);
	public Path getPath();
	public Icon getIcon();
	public boolean isDirectory();
}

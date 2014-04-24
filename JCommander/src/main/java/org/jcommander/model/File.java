package org.jcommander.model;

import javax.swing.Icon;

public interface File {

	public Object getValueByColumnIndex(int columnIndex);
	public Path getPath();
	public Icon getIcon();
	public long getSize();
	public boolean isDirectory();
}

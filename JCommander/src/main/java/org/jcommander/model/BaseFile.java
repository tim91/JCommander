package org.jcommander.model;

import javax.swing.Icon;

import org.jcommander.model.column.BaseExtensionColumn;
import org.jcommander.model.column.BaseIconAndStringColumn;
import org.jcommander.model.column.DirectorySizeColumn;
import org.jcommander.model.column.FileSizeColumn;
import org.jcommander.model.column.LocaleDateColumn;

public class BaseFile implements File{

	protected String name;
	protected String extension;
	protected long size;
	protected long lastModifiedDate;
	protected String attribiute;
	protected Path path;
	protected Icon icon;

	protected Object[] valuesTotable = null;
	
	public BaseFile(String name, String extension, long size,
			long lastModifiedDate, String attribiute, Path path,Icon icon) {
		super();
		this.name = name;
		this.extension = extension;
		this.size = size;
		this.lastModifiedDate = lastModifiedDate;
		this.attribiute = attribiute;
		this.path = path;
		this.icon = icon;
		
		valuesTotable = new Object[]{new BaseIconAndStringColumn(name, icon),new BaseExtensionColumn(extension),new FileSizeColumn(size),new LocaleDateColumn(lastModifiedDate)};
	}

	public Object getValueByColumnIndex(int columnIndex) {
		// TODO Auto-generated method stub
		return this.valuesTotable[columnIndex];
	}

	public Path getPath() {
		// TODO Auto-generated method stub
		return this.path;
	}

	public Icon getIcon() {
		// TODO Auto-generated method stub
		return icon;
	}

	public boolean isDirectory() {
		// TODO Auto-generated method stub
		return false;
	}

	public long getSize() {
		// TODO Auto-generated method stub
		return this.size;
	}
}

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
		
//		valuesTotable = new Object[]{new BaseIconAndStringColumn(name, icon),extension,new FileSizeColumn(size),new LocaleDateColumn(lastModifiedDate)};
		
		
		BaseIconAndStringColumn n = new BaseIconAndStringColumn(name, icon);
		n.setIsRowDirectory(isDirectory());
		
		BaseExtensionColumn ext = new BaseExtensionColumn(extension);
		ext.setIsRowDirectory(isDirectory());
		
		FileSizeColumn dsc = new FileSizeColumn(size);
		dsc.setIsRowDirectory(isDirectory());
		
		LocaleDateColumn ldc = new LocaleDateColumn(lastModifiedDate);
		ldc.setIsRowDirectory(isDirectory());
		
		valuesTotable = new Object[]{n,
				ext,
				dsc,
				ldc};
		
		
	}

//
//
//	public DirectoryTableRow getTableRow() {
//		// TODO Auto-generated method stub
//		DirectoryTableRow dtr = new DirectoryTableRow();
//		
//		dtr.name = name;
//		dtr.extension = extension;
//		dtr.date = new LocaleDateColumn(lastModifiedDate);
//		dtr.size = new FileSizeColumn(size);
//		dtr.attribiute = new FileAttributeColumn(attribiute);
//		
//		return dtr;
//	}



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

	public int compareTo(File f2) {
		
		if(this instanceof Directory)
			return 1;
		else if((this instanceof File) && (f2 instanceof Directory)){
			return -1;
		}
		else
			return 0;
	}

	public boolean isDirectory() {
		return false;
	}

	
}

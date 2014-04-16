package org.jcommander.model;

import org.jcommander.model.column.FileAttributeColumn;
import org.jcommander.model.column.FileSizeColumn;
import org.jcommander.model.column.LocaleDateColumn;

public class BaseFile implements File{

	
	protected String name;
	protected String extension;
	protected long size;
	protected long lastModifiedDate;
	protected String attribiute;
	protected Path path;

	protected Object[] valuesTotable = null;
	
	public BaseFile(String name, String extension, long size,
			long lastModifiedDate, String attribiute, Path path) {
		super();
		this.name = name;
		this.extension = extension;
		this.size = size;
		this.lastModifiedDate = lastModifiedDate;
		this.attribiute = attribiute;
		this.path = path;
		
		valuesTotable = new Object[]{name,extension,new FileSizeColumn(size),new LocaleDateColumn(lastModifiedDate)};
		
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

}

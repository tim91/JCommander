package org.jcommander.model;

import java.util.ArrayList;
import java.util.List;

import org.jcommander.model.column.DirectoryAttributeColumn;
import org.jcommander.model.column.DirectorySizeColumn;
import org.jcommander.model.column.FileAttributeColumn;
import org.jcommander.model.column.FileSizeColumn;
import org.jcommander.model.column.LocaleDateColumn;

public class BaseDirectory extends BaseFile implements Directory {
	
	public BaseDirectory(String name,
			long lastModifiedDate, String attribiute,Path path) {
		super(name, "", 0, lastModifiedDate, attribiute,path);
		
		valuesTotable = new Object[]{name,extension,new DirectorySizeColumn(),new LocaleDateColumn(lastModifiedDate)};
		
	}

	private List<File> files = new ArrayList<File>();
	
	public int getDirectoriesNum() {
		return 0;
	}

	public int getElements() {
		// TODO Auto-generated method stub
		return this.files.size();
	}

	public List<File> getFiles() {
		// TODO Auto-generated method stub
		return files;
	}

	public void addFile(File f) {
		this.files.add(f);
		
	}

	@Override
	public Object getValueByColumnIndex(int columnIndex) {
		// TODO Auto-generated method stub
		return valuesTotable[columnIndex];
	}
//	
//	public DirectoryTableRow getTableRow() {
//		// TODO Auto-generated method stub
//		DirectoryTableRow dtr = new DirectoryTableRow();
//		
//		dtr.name = name;
//		dtr.extension = extension;
//		dtr.date = new LocaleDateColumn(lastModifiedDate);
//		dtr.size = new DirectorySizeColumn();
//		dtr.attribiute = new DirectoryAttributeColumn(attribiute);
//		
//		return dtr;
//	}

	public File getFile(int idx) {
		return this.files.get(idx);
	}


}

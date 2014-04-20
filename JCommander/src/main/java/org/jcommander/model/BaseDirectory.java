package org.jcommander.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.jcommander.core.image.ImageService;
import org.jcommander.model.column.BaseExtensionColumn;
import org.jcommander.model.column.BaseIconAndStringColumn;
import org.jcommander.model.column.DirectorySizeColumn;
import org.jcommander.model.column.LocaleDateColumn;

public class BaseDirectory extends BaseFile implements Directory {
	
	public BaseDirectory(String name,
			long lastModifiedDate, String attribiute,Path path) {
		super(name, "", 0, lastModifiedDate, attribiute,path,
				new ImageIcon(ImageService.getInstance().DIRECTORY_ICON));
		
		boolean aa = true;
		
		BaseIconAndStringColumn n = new BaseIconAndStringColumn(name, icon);
		n.setIsRowDirectory(aa);
		
		BaseExtensionColumn ext = new BaseExtensionColumn(extension);
		ext.setIsRowDirectory(aa);
		
		DirectorySizeColumn dsc = new DirectorySizeColumn();
		dsc.setIsRowDirectory(aa);
		
		LocaleDateColumn ldc = new LocaleDateColumn(lastModifiedDate);
		ldc.setIsRowDirectory(aa);
		
		valuesTotable = new Object[]{n,
				ext,
				dsc,
				ldc};
		
		
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
	
	public void addFile(File f,int idx) {
		this.files.add(idx,f);
		
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

	@Override
	public boolean isDirectory() {
		return true;
	}
	
}

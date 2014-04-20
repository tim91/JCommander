package org.jcommander.model;

import java.util.List;

import javax.swing.ImageIcon;

import org.jcommander.core.image.ImageService;
import org.jcommander.model.column.BaseExtensionColumn;
import org.jcommander.model.column.BaseIconAndStringColumn;
import org.jcommander.model.column.DirectorySizeColumn;
import org.jcommander.model.column.LocaleDateColumn;

public class ParentDirectory extends BaseFile implements Directory {

	public ParentDirectory(long lastModifiedDate, String attribiute,Path path) {
		super("..", "", 0, lastModifiedDate, attribiute, path, 
				new ImageIcon(ImageService.getInstance().PARENT_DIRECTORY_ICON));
		
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

	/*
	 * We dont have childs here
	 */
	
	public int getDirectoriesNum() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	public int getElements() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	public List<File> getFiles() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	public void addFile(File f) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	public void addFile(File f, int idx) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	public File getFile(int idx) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isDirectory() {
		return true;
	}

}

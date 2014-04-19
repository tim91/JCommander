package org.jcommander.model;

import java.util.List;

import javax.swing.ImageIcon;

import org.jcommander.core.image.ImageService;
import org.jcommander.model.column.BaseIconAndStringColumn;
import org.jcommander.model.column.DirectorySizeColumn;
import org.jcommander.model.column.LocaleDateColumn;

public class ParentDirectory extends BaseFile implements Directory {

	public ParentDirectory(long lastModifiedDate, String attribiute,Path path) {
		super("..", "", 0, lastModifiedDate, attribiute, path, 
				new ImageIcon(ImageService.getInstance().PARENT_DIRECTORY_ICON));
		
		valuesTotable = new Object[]{new BaseIconAndStringColumn(name, icon),extension,new DirectorySizeColumn(),new LocaleDateColumn(lastModifiedDate)};
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


}

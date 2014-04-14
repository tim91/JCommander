package org.jcommander.model;

import java.util.List;

public interface Directory extends File {
	
	public int getDirectoriesNum();
	public int getFilesNum();
	public List<File> getFiles();
	public void addFile(File f);
	public File getFile(int idx);

}

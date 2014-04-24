package org.jcommander.model;

import java.util.List;

public interface Directory extends File {
	
	public int getDirectoriesNum();
	public int getOnlyFilesNum();
	public long getFilesSize();
	public int getElements();
	public List<File> getFiles();
	public void addFile(File f);
	public void addFile(File f,int idx);
	public File getFile(int idx);
	public void setFiles(List<File> fs);

}

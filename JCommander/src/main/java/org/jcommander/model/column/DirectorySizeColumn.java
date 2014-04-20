package org.jcommander.model.column;

import java.util.Comparator;

import org.jcommander.model.DirectoryTableModel;

public class DirectorySizeColumn extends Column implements SizeColumn,Comparator<String>{

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return DirectoryTableModel.DIRECTORY_SIZE;
	}

	public int compare(String o1, String o2) {
		return 0;
	}

	public long getValue() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException(); 
	}
	
	@Override
	public boolean rowIsDirectory() {
		// TODO Auto-generated method stub
		return rowIsDirectory;
	}

	@Override
	public void setIsRowDirectory(boolean b) {
		// TODO Auto-generated method stub
		rowIsDirectory = b;
	}
}

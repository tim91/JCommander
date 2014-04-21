package org.jcommander.model.column;

import java.util.Comparator;

import org.jcommander.model.DirectoryTableModel;

public class DirectorySizeColumn implements SizeColumn{

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return DirectoryTableModel.DIRECTORY_SIZE;
	}

	public long getValue() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException(); 
	}
}

package org.jcommander.core.comparator;

import org.jcommander.model.File;
import org.jcommander.model.column.DirectorySizeColumn;
import org.jcommander.model.column.IconAndStringColumn;

public class FileExtensionColumnComparator extends AbstractColumnComparator implements
		DirectoryTableRowComparator {

	
	
	public FileExtensionColumnComparator(int idx) {
		super(idx);
	}

public int compare(File o1, File o2) {
		
		String col1 = (String) o1.getValueByColumnIndex(idx);
		String col2 = (String) o2.getValueByColumnIndex(idx);
		
		if(o1.isDirectory() && o2.isDirectory()){
			return 0;
		}
		else if(o1.isDirectory() && !(o2.isDirectory())){
			return 1;
		}
		else if(o2.isDirectory() && !(o1.isDirectory())){
			return -1;
		}
		else{
			return col1.compareTo(col2);
			
		}
	}

}

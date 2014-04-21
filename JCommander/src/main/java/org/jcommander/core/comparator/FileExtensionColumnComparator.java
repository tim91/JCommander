package org.jcommander.core.comparator;

import org.jcommander.model.File;
import org.jcommander.model.column.ExtensionColumn;

public class FileExtensionColumnComparator extends AbstractColumnComparator implements
		DirectoryTableRowComparator {

	
	
	public FileExtensionColumnComparator(int idx) {
		super(idx);
	}

public int compare(File o1, File o2) {
		
		ExtensionColumn col1 = (ExtensionColumn) o1.getValueByColumnIndex(idx);
		ExtensionColumn col2 = (ExtensionColumn) o2.getValueByColumnIndex(idx);
		
		if(o1.isDirectory() && o2.isDirectory()){
			return 0;
		}
		else if(o1.isDirectory() && !(o2.isDirectory())){
			return -1;
		}
		else if(o2.isDirectory() && !(o1.isDirectory())){
			return 1;
		}
		else{
			String v1 = col1.toString();
			String v2 = col2.toString();
			if(direction){
				return v2.compareTo(v1);
			}else{
				return v1.compareTo(v2);
			}
			
		}
	}

}

package org.jcommander.core.comparator;

import org.jcommander.model.File;
import org.jcommander.model.column.DirectorySizeColumn;
import org.jcommander.model.column.SizeColumn;

public class FileSizeColumnComparator extends AbstractColumnComparator implements DirectoryTableRowComparator {

	public FileSizeColumnComparator(int idx) {
		super(idx);
	}
	
	public int compare(File o1, File o2) {
		
		SizeColumn col1 = (SizeColumn) o1.getValueByColumnIndex(idx);
		SizeColumn col2 = (SizeColumn) o2.getValueByColumnIndex(idx);
		
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
			Long v1 = col1.getValue();
			Long v2 = col2.getValue();
			if(direction){
				return v2.compareTo(v1);
			}else{
				return v1.compareTo(v2);
			}
			
		}
	}

}

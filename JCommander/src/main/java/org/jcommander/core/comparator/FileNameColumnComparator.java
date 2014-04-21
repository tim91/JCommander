package org.jcommander.core.comparator;

import org.jcommander.model.File;
import org.jcommander.model.column.DirectorySizeColumn;
import org.jcommander.model.column.IconAndStringColumn;
import org.jcommander.model.column.SizeColumn;

public class FileNameColumnComparator extends AbstractColumnComparator implements DirectoryTableRowComparator {

	public FileNameColumnComparator(int idx) {
		super(idx);
	}
	
	
	public int compare(File o1, File o2) {
		
		IconAndStringColumn col1 = (IconAndStringColumn) o1.getValueByColumnIndex(idx);
		IconAndStringColumn col2 = (IconAndStringColumn) o2.getValueByColumnIndex(idx);
		
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
			String v1 = col1.getText();
			String v2 = col2.getText();
			if(direction){
				return v2.compareTo(v1);
			}else{
				return v1.compareTo(v2);
			}
		}
	}

}

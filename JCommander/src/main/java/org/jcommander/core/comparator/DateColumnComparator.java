package org.jcommander.core.comparator;

import org.jcommander.model.File;
import org.jcommander.model.column.DateColumn;

public class DateColumnComparator extends AbstractColumnComparator implements DirectoryTableRowComparator {

	public DateColumnComparator(int idx) {
		super(idx);
		// TODO Auto-generated constructor stub
	}

	public int compare(File o1, File o2) {
		// TODO Auto-generated method stub
		DateColumn col1 = (DateColumn) o1.getValueByColumnIndex(idx);
		DateColumn col2 = (DateColumn) o2.getValueByColumnIndex(idx);
		
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

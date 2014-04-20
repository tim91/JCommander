package org.jcommander.model.column;

import java.util.Comparator;

public class BaseExtensionColumn extends Column implements ExtensionColumn,Comparator<BaseExtensionColumn> {

	private String ext;
	
	public BaseExtensionColumn(String ext) {
		super();
		this.ext = ext;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.ext;
	}

	public int compare(BaseExtensionColumn o1, BaseExtensionColumn o2) {
		// TODO Auto-generated method stub
		if(o1.rowIsDirectory() && o2.rowIsDirectory()){
			return 0;
		}
		else if(o1.rowIsDirectory() && !(o2.rowIsDirectory())){
			return 1;
		}
		else if(o2.rowIsDirectory() && !(o1.rowIsDirectory())){
			return -1;
		}
		else{
			String v1 = o1.toString();
			String v2 = o2.toString();
			return v1.compareTo(v2);
			
		}
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

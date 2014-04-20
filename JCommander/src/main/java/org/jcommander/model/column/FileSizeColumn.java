package org.jcommander.model.column;

import java.text.NumberFormat;
import java.util.Comparator;
import java.util.Locale;

import org.jcommander.gui.locale.LocaleContext;

public class FileSizeColumn extends Column implements SizeColumn, Comparator<SizeColumn>{

	long size;
	
	public FileSizeColumn(long size) {
		
		this.size = size;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		NumberFormat nf = NumberFormat.getInstance(LocaleContext.getContext().getLocale());
		String number = nf.format(this.size);
		
		return number;
	}


	public long getValue() {
		// TODO Auto-generated method stub
		return this.size;
	}

//	public int compare(FileSizeColumn o1, FileSizeColumn o2) {
//		
//		if(o1.rowIsDirectory() && o2.rowIsDirectory()){
//			return 0;
//		}
//		else if(o1.rowIsDirectory() && !(o2.rowIsDirectory())){
//			return 1;
//		}
//		else if(o2.rowIsDirectory() && !(o1.rowIsDirectory())){
//			return -1;
//		}
//		else{
//			Long v1 = o1.getValue();
//			Long v2 = o2.getValue();
//			return v1.compareTo(v2);
//			
//		}
//	}

	public int compare(SizeColumn o1, SizeColumn o2) {
		if(o1 instanceof DirectorySizeColumn && o2 instanceof DirectorySizeColumn){
			return 0;
		}
		else if(o1 instanceof DirectorySizeColumn && !(o2 instanceof DirectorySizeColumn)){
			return 1;
		}
		else if(o2 instanceof DirectorySizeColumn && !(o1 instanceof DirectorySizeColumn)){
			return -1;
		}
		else{
			Long v1 = o1.getValue();
			Long v2 = o2.getValue();
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

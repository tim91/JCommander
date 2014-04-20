package org.jcommander.model.column;

import java.text.DateFormat;
import java.util.Comparator;

import org.apache.log4j.Logger;
import org.jcommander.gui.locale.LocaleContext;

public class LocaleDateColumn extends Column implements DateColumn, Comparator<LocaleDateColumn> {

	static Logger logger = Logger
			.getLogger("org.jcommander.model.column.LocaleDateColumn");
	
	private long date;

	public LocaleDateColumn(long lastModifiedDate) {
		this.date = lastModifiedDate;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,
				LocaleContext.getContext().getLocale());

		String toRet = df.format(this.date);

//		logger.debug("Zwracam nowa date");
		
		return toRet;
	}


	public long getValue() {
		// TODO Auto-generated method stub
		return this.date;
	}

	public int compare(LocaleDateColumn o1, LocaleDateColumn o2) {
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

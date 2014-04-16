package org.jcommander.model.column;

import java.text.DateFormat;

import org.apache.log4j.Logger;
import org.jcommander.gui.locale.LocaleContext;

public class LocaleDateColumn implements DateColumn {

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

}

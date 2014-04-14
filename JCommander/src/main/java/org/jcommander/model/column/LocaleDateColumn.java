package org.jcommander.model.column;

import java.text.DateFormat;

import org.jcommander.gui.locale.LocaleContext;

public class LocaleDateColumn implements DateColumn {

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

		return toRet;
	}

}

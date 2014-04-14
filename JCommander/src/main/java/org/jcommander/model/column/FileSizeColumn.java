package org.jcommander.model.column;

import java.text.NumberFormat;
import java.util.Locale;

import org.jcommander.gui.locale.LocaleContext;

public class FileSizeColumn implements SizeColumn {

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

}

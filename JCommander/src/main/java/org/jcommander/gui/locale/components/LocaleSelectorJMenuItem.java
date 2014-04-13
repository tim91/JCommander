package org.jcommander.gui.locale.components;

import java.util.Locale;

import javax.swing.JMenuItem;

import org.apache.log4j.Logger;
import org.jcommander.gui.locale.LocaleChangeListener;
import org.jcommander.gui.locale.LocaleContext;

public class LocaleSelectorJMenuItem extends JMenuItem implements LocaleChangeListener {

	static Logger logger = Logger.getLogger("org.jcommander.gui.locale.LocaleSelectorJMenuItem");
	
	private Locale locale = null;
	
	public LocaleSelectorJMenuItem(Locale locale) {
		super(locale.getDisplayName(LocaleContext.getContext().getLocale()).substring(0, 1).toUpperCase() + 
				locale.getDisplayName(LocaleContext.getContext().getLocale()).substring(1));
		this.locale = locale;
		LocaleContext.getContext().addContextChangeListener(this);
	}

	public void localeChanged() {
		
		this.setText(locale.getDisplayName(LocaleContext.getContext().getLocale()).substring(0, 1).toUpperCase() + 
				locale.getDisplayName(LocaleContext.getContext().getLocale()).substring(1));
	}

	public Locale getLocale() {
		return locale;
	}
}

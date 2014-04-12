package org.jcommander.gui.components;

import javax.swing.JMenu;

import org.jcommander.gui.locale.LocaleChangeListener;
import org.jcommander.gui.locale.LocaleContext;

public class LocaleJMenu extends JMenu implements LocaleChangeListener {

	
	public LocaleJMenu(String key) {
		super(LocaleContext.getContext().getBundle().getString(key));
		
		LocaleContext.getContext().addContextChangeListener(this);
	}
	
	public void localeChanged() {
	}

}

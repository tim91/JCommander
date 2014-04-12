package org.jcommander.gui.components;

import javax.swing.JMenuItem;

import org.jcommander.gui.locale.LocaleChangeListener;
import org.jcommander.gui.locale.LocaleContext;

public class LocaleJMenuItem extends JMenuItem implements LocaleChangeListener {

	public LocaleJMenuItem(String key) {
		super(LocaleContext.getContext().getBundle().getString(key));
		
		LocaleContext.getContext().addContextChangeListener(this);
	}

	public void localeChanged() {
		// TODO Auto-generated method stub
		
	}

}

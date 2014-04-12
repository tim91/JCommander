package org.jcommander.gui.locale.components;

import javax.swing.JMenu;

import org.jcommander.gui.locale.LocaleChangeListener;
import org.jcommander.gui.locale.LocaleContext;

public class LocaleJMenu extends JMenu implements LocaleChangeListener {


	String key = null;
	
	public LocaleJMenu(String key) {
		super(LocaleContext.getContext().getBundle().getString(key));
		this.key = key;
		LocaleContext.getContext().addContextChangeListener(this);
	}
	
	public void localeChanged() {
		this.setText(LocaleContext.getContext().getBundle().getString(this.key));
	}

}

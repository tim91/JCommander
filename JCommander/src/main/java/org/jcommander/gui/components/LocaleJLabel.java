package org.jcommander.gui.components;

import javax.swing.JLabel;

import org.jcommander.gui.locale.LocaleChangeListener;
import org.jcommander.gui.locale.LocaleContext;

public class LocaleJLabel extends JLabel implements LocaleChangeListener {

	
	public LocaleJLabel(String key) {
		super(LocaleContext.getContext().getBundle().getString(key));
		
		LocaleContext.getContext().addContextChangeListener(this);
	}
	
	public void localeChanged() {
		// TODO Auto-generated method stub
		
	}

}

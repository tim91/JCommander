package org.jcommander.gui.components;

import javax.swing.JButton;

import org.jcommander.gui.locale.LocaleChangeListener;
import org.jcommander.gui.locale.LocaleContext;

public class LocaleJButton extends JButton implements LocaleChangeListener {

	
	public LocaleJButton(String key) {
		super(LocaleContext.getContext().getBundle().getString(key));
		
		LocaleContext.getContext().addContextChangeListener(this);
	}
	
	public void localeChanged() {
		// TODO Auto-generated method stub
		
	}

}

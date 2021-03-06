package org.jcommander.gui.locale.components;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.jcommander.gui.locale.LocaleChangeListener;
import org.jcommander.gui.locale.LocaleContext;

public class LocaleJLabel extends JLabel implements LocaleChangeListener {

	String key = null;
	
	public LocaleJLabel(String key) {
		super(LocaleContext.getContext().getBundle().getString(key));
		this.key = key;
		LocaleContext.getContext().addContextChangeListener(this);
	}
	
	public void localeChanged() {
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				setText(LocaleContext.getContext().getBundle().getString(key));
			}
		});
		
		
	}

}

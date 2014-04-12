package org.jcommander.gui.locale.components;

import javax.swing.JButton;

import org.jcommander.gui.locale.LocaleChangeListener;
import org.jcommander.gui.locale.LocaleContext;

public class LocaleJButton extends JButton implements LocaleChangeListener {

	private String key = null;
	
	public LocaleJButton(String key) {
		super(LocaleContext.getContext().getBundle().getString(key));
		this.key = key;
		LocaleContext.getContext().addContextChangeListener(this);
	}
	
	public void localeChanged() {
		// TODO Auto-generated method stub
		this.setText(LocaleContext.getContext().getBundle().getString(this.key));
	}

}

package org.jcommander.gui.locale.components;

import javax.swing.JLabel;

import org.jcommander.gui.locale.LocaleChangeListener;
import org.jcommander.gui.locale.LocaleContext;


public class LocaleParametrizedJLabel extends JLabel implements
		LocaleChangeListener {

	private Object [] values = null;
	private String key = null;
	
	public LocaleParametrizedJLabel(String key) {
		super();
		this.key = key;
		
		LocaleContext.getContext().addContextChangeListener(this);
	}

	public void localeChanged() {
		String pattern = LocaleContext.getContext().getBundle().getString(key);
		String labelValue = String.format(LocaleContext.getContext().getLocale(),pattern,this.values);
		this.setText(labelValue);
	}

	public void setValues(Object[] values) {
		this.values = values;
		String pattern = LocaleContext.getContext().getBundle().getString(key);
		String labelValue = String.format(LocaleContext.getContext().getLocale(),pattern,this.values);
		this.setText(labelValue);
	}
}

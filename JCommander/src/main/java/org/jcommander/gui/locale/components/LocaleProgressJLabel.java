package org.jcommander.gui.locale.components;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.jcommander.gui.locale.LocaleChangeListener;
import org.jcommander.gui.locale.LocaleContext;

public class LocaleProgressJLabel extends JLabel implements
		LocaleChangeListener {

	String key = null;

	String baseVal = null;
	
	public LocaleProgressJLabel(String key) {
		super(LocaleContext.getContext().getBundle().getString(key));
		this.key = key;
		LocaleContext.getContext().addContextChangeListener(this);
		baseVal = LocaleContext.getContext().getBundle().getString(key);
	}

	public void localeChanged() {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				String prev = getText();
				prev = prev.replace(baseVal, "");
				baseVal = LocaleContext.getContext().getBundle().getString(key);
				setText(baseVal + prev);
			}
		});

	}
}

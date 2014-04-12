package org.jcommander.gui.locale.components;

import javax.swing.JButton;

import org.jcommander.gui.locale.LocaleChangeListener;
import org.jcommander.gui.locale.LocaleContext;

public class LocaleJButtonForBottomPanel extends JButton implements LocaleChangeListener {

	String key = null;

	public LocaleJButtonForBottomPanel(String key) {
		super(LocaleContext.getContext().getBundle().getString(key));
		this.key = key;
		LocaleContext.getContext().addContextChangeListener(this);
	}

	public void localeChanged() {
		this.setText(LocaleContext.getContext().getBundle().getString(this.key));

	}

}

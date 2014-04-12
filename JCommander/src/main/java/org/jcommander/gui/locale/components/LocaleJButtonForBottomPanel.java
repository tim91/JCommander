package org.jcommander.gui.locale.components;

import java.awt.Font;

import javax.swing.JButton;

import org.jcommander.gui.locale.LocaleChangeListener;
import org.jcommander.gui.locale.LocaleContext;

public class LocaleJButtonForBottomPanel extends JButton implements LocaleChangeListener {

	String key = null;

	public LocaleJButtonForBottomPanel(String key) {
		super(LocaleContext.getContext().getBundle().getString(key));
		Font f = new Font("Microsoft Sans Serif", Font.BOLD, 12);
		this.setFont(f);
		this.key = key;
		LocaleContext.getContext().addContextChangeListener(this);
	}

	public void localeChanged() {
		this.setText(LocaleContext.getContext().getBundle().getString(this.key));

	}

}

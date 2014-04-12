package org.jcommander.gui.locale.components;

import javax.swing.JButton;

import org.jcommander.gui.locale.LocaleChangeListener;
import org.jcommander.gui.locale.LocaleContext;

public class LocaleJButtonForTopPanel extends JButton implements LocaleChangeListener {

	private String key = null;
	
	public LocaleJButtonForTopPanel(String key) {
		super();
		
		this.key = key;
		this.setText("a");
		this.setToolTipText(LocaleContext.getContext().getBundle().getString(this.key));
		
		LocaleContext.getContext().addContextChangeListener(this);
	}
	
	public void localeChanged() {
		// TODO Auto-generated method stub
		this.setToolTipText(LocaleContext.getContext().getBundle().getString(this.key));
		
	}

}

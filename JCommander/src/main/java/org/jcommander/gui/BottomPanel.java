package org.jcommander.gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jcommander.gui.locale.components.LocaleJButtonForBottomPanel;


public class BottomPanel extends JPanel {

	
	public BottomPanel() {
		super();
		
		this.setLayout(new GridLayout(2, 1));
		
		JPanel top = new JPanel();
		top.setLayout(new GridLayout(1, 3));
		top.add(new JLabel("sdfsdf"));
		
		
		/*
		 * Buttons on the buttom
		 */
		JPanel bottom = new JPanel();
		bottom.setLayout(new GridLayout(1,7));
		
		JButton viewButton = new LocaleJButtonForBottomPanel("button.view");
		JButton editButton = new LocaleJButtonForBottomPanel("button.edit");
		JButton copyButton = new LocaleJButtonForBottomPanel("button.copy");
		JButton moveButton = new LocaleJButtonForBottomPanel("button.move");
		JButton newFolderButton = new LocaleJButtonForBottomPanel("button.newFolder");
		JButton deleteButton = new LocaleJButtonForBottomPanel("button.delete");
		JButton exitButton = new LocaleJButtonForBottomPanel("button.exit");
		
		
//		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
//        Dimension d = separator.getPreferredSize();
//        d.height = viewButton.getPreferredSize().height;
//        separator.setPreferredSize(d);
		
		bottom.add(viewButton);
		bottom.add(editButton);
		bottom.add(copyButton);
		bottom.add(moveButton);
		bottom.add(newFolderButton);
		bottom.add(deleteButton);
		bottom.add(exitButton);
		
		
		this.add(top);
		this.add(bottom);
		
		
	}
	
}

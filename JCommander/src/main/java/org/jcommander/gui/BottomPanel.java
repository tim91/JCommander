package org.jcommander.gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.lang3.tuple.Pair;
import org.jcommander.core.ApplicationContext;
import org.jcommander.core.action.Action;
import org.jcommander.core.action.ActionService;
import org.jcommander.core.action.CopyAction;
import org.jcommander.core.util.JCommanderUtils;
import org.jcommander.gui.locale.components.LocaleJButtonForBottomPanel;
import org.jcommander.model.Directory;
import org.jcommander.model.File;


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
		copyButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Pair<Component, Component> panels = ApplicationContext.getInstance().getSelectedPanels();
				
				Pair<List<File>, Directory> fromTo = JCommanderUtils.extractFromAndToLocation(panels);
				
				Action copyAction = new CopyAction(fromTo.getLeft(), fromTo.getRight().getPath());
				
				ActionService.getInstance().getActionExecuter().executeAction(copyAction);
				
			}
		});
		
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

package org.jcommander.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.apache.commons.lang3.tuple.Pair;
import org.jcommander.core.ApplicationContext;
import org.jcommander.core.action.AbstractAction;
import org.jcommander.core.action.Action;
import org.jcommander.core.action.ActionService;
import org.jcommander.core.action.CopyAction;
import org.jcommander.core.action.DeleteAction;
import org.jcommander.core.action.MoveAction;
import org.jcommander.core.util.JCommanderUtils;
import org.jcommander.gui.locale.components.LocaleJButtonForBottomPanel;
import org.jcommander.model.Directory;
import org.jcommander.model.DirectoryTableModel;
import org.jcommander.model.File;


public class BottomPanel extends JPanel {

	
	public BottomPanel() {
		super();
		
		this.setLayout(new GridLayout(2, 1));
		
		JPanel top = new JPanel();
		top.setLayout(new GridLayout());
//		top.add(Box.createHorizontalGlue(),BorderLayout.);
		JLabel ll = new JLabel("c:\\>");
//		JPanel ppp = new JPanel(new BorderLayout());
//		ppp.add(ll,BorderLayout.EAST);
		
//		ll.setPreferredSize(new Dimension(15,top.getHeight()));
//		ll.setAlignmentX(RIGHT_ALIGNMENT);
		top.add(ll,BorderLayout.EAST);
		JComboBox<String> jcb = new JComboBox<String>();
		jcb.setEditable(true);
		jcb.setPreferredSize(new Dimension(300,top.getHeight()));
		top.add(jcb,BorderLayout.EAST);
		/*
		 * Buttons on the buttom
		 */
		JPanel bottom = new JPanel();
		bottom.setLayout(new GridLayout(1,7));
		
		JButton viewButton = new LocaleJButtonForBottomPanel("button.view");
		JButton editButton = new LocaleJButtonForBottomPanel("button.edit");
		JButton copyButton = new LocaleJButtonForBottomPanel("button.copy");
		final ActionListener onCopyActionListsner = new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Pair<Component, Component> panels = ApplicationContext.getInstance().getSelectedPanels();
				
				Pair<List<File>, Directory> fromTo = JCommanderUtils.extractFromAndToLocation(panels);
				
				final AbstractAction copyAction = new CopyAction(fromTo.getLeft(), fromTo.getRight().getPath());
				
//				copyAction.initDialogWindow();
				
//				SwingUtilities.invokeLater(new Runnable() {
//					
//					public void run() {
//						copyAction.executeTask();
//					}
//				});
				
				ActionService.getInstance().getActionExecuter().executeAction(copyAction);
				
			}
		};
		copyButton.addActionListener(onCopyActionListsner);
		ApplicationContext.getInstance().setOnCopyActionListsner(onCopyActionListsner);
		
		JButton moveButton = new LocaleJButtonForBottomPanel("button.move");
		final ActionListener onMoveActionListsner = new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Pair<Component, Component> panels = ApplicationContext.getInstance().getSelectedPanels();
				
				Pair<List<File>, Directory> fromTo = JCommanderUtils.extractFromAndToLocation(panels);
				
				AbstractAction copyAction = new MoveAction(fromTo.getLeft(), fromTo.getRight().getPath());
				
				ActionService.getInstance().getActionExecuter().executeAction(copyAction);
				
			}
		};
		moveButton.addActionListener(onMoveActionListsner);
		ApplicationContext.getInstance().setOnMoveActionListsner(onMoveActionListsner);
		
		JButton newFolderButton = new LocaleJButtonForBottomPanel("button.newFolder");
		JButton deleteButton = new LocaleJButtonForBottomPanel("button.delete");
		final ActionListener onDeleteActionListener = new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Container component = (Container) ApplicationContext.getInstance().getLastFocusedDirectoryComponent();
				
				DirectoryViewJTable table = (DirectoryViewJTable) JCommanderUtils.getSpecifiedComponentInContainer(component, "sd0");
				
				int [] selectedRows = table.getSelectedRows();
				DirectoryTableModel model = (DirectoryTableModel) table.getModel();
				List<File> filesToDelete = new ArrayList<File>();
				for (int row : selectedRows) {
					File f = model.getRowComponent(row);
					filesToDelete.add(f);
				}
				
				AbstractAction deleteAction = new DeleteAction(filesToDelete);
				
				ActionService.getInstance().getActionExecuter().executeAction(deleteAction);
				
			}
		};
		deleteButton.addActionListener(onDeleteActionListener);
		ApplicationContext.getInstance().setOnDeleteActionListsner(onDeleteActionListener);

		
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

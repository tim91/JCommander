package org.jcommander.gui;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.apache.log4j.Logger;
import org.jcommander.core.action.Action;
import org.jcommander.core.action.ActionExecuter;
import org.jcommander.core.action.ActionService;
import org.jcommander.core.action.ChangeDirectoryAction;
import org.jcommander.core.action.OpenFileAction;
import org.jcommander.model.Directory;
import org.jcommander.model.DirectoryTableModel;
import org.jcommander.model.File;
import org.jcommander.model.column.IconAndStringColumn;

public class DirectoryViewJTable extends JTable {

	static Logger logger = Logger
			.getLogger("org.jcommander.gui.DirectoryViewJTable");


	ActionService actionService = ActionService.getInstance();
	
	private DirectoryTableModel tableModel = null;

	private DirectoryViewJTable directoryViewJTable = null;

	public DirectoryViewJTable(DirectoryTableModel model) {
		
		this.tableModel = model;
		this.setModel(tableModel);
		this.addMouseListener(new TableMouseClickListener());
		this.addKeyListener(new KeyboardListener());
		
		TableColumnModel tcm = this.getColumnModel();
		
		tcm.getColumn(0).setCellRenderer(new IconTextCellRenderer());
		
		
		/*
		 * Unbind default enter behavior
		 */
		this.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
	    this.getActionMap().put("Enter", new AbstractAction() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				/*
				 * Do nothing
				 */
			}
	        
	    });
		
		
		directoryViewJTable = this;
	}

	public Object[] getDirectoryInformations() {
		// TODO Auto-generated method stub
		return new Object[] { 0, 781, 0, 3, 0, 26 };
	}

	
	
	private class TableMouseClickListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {

			if (e.getClickCount() == 2) {
				int row = directoryViewJTable.rowAtPoint(e.getPoint());
				executeRow(row);
			}

		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
	
	private void executeRow(int row){
		logger.debug("Wiersz : " + row);

		DirectoryTableModel dtm = (DirectoryTableModel) directoryViewJTable
				.getModel();

		File fileSelected = dtm.getRowComponent(row);

		logger.debug("Podwojnie klknalem : " + fileSelected.getPath());

		ActionExecuter ae = actionService.getActionExecuter();

		Action action = null;

		if (fileSelected instanceof Directory) {
			action = new ChangeDirectoryAction(dtm.getDirectory()
					.getPath(), fileSelected.getPath(), dtm);
		} else {
			action = new OpenFileAction(fileSelected.getPath());
		}

		ae.executeAction(action);

		// logger.debug(dtm.getRowComponent(row));
		// logger.debug(message);
	}
	
	private class KeyboardListener implements KeyListener
	{

		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void keyTyped(KeyEvent e) {
			if(e.getKeyChar() == e.VK_ENTER){
				DirectoryViewJTable table  = (DirectoryViewJTable) e.getSource();
				int [] rowsIds = table.getSelectedRows();
				
				/*
				 * Perform execute action on last element in the list
				 */
				int row = rowsIds[rowsIds.length - 1];
				
				executeRow(row);
			}
			
			if(e.getKeyChar() == e.VK_BACK_SPACE){
				
				DirectoryViewJTable table  = (DirectoryViewJTable) e.getSource();
				
				DirectoryTableModel model = (DirectoryTableModel) table.getModel();
				
				if(model.isPosibleGoToTheParent()){
					/*
					 * Emulate clicking of first row with up arrow
					 */
					executeRow(0);
				}
					
			}
			
		}
		
	}
	
	public class IconTextCellRenderer extends DefaultTableCellRenderer {
	    public Component getTableCellRendererComponent(JTable table,
	                                  Object value,
	                                  boolean isSelected,
	                                  boolean hasFocus,
	                                  int row,
	                                  int column) {
	        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

	        
	        DirectoryTableModel dtm = (DirectoryTableModel) table.getModel();
	        
	        IconAndStringColumn col = (IconAndStringColumn) dtm.getValueAt(row, column);
	        
	        ImageIcon ii = (ImageIcon) col.getIcon();
	        Image img = ii.getImage();  
	        Image newimg = img.getScaledInstance(16, 16,  java.awt.Image.SCALE_SMOOTH);  
	        Icon newIcon = new ImageIcon(newimg); 
	        
	        setIcon(newIcon);
	        setText(col.getText());
	        
	        return this;
	    }
	}
}

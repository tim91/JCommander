package org.jcommander.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import org.apache.log4j.Logger;
import org.jcommander.core.ApplicationContext;
import org.jcommander.core.action.Action;
import org.jcommander.core.action.ActionExecuter;
import org.jcommander.core.action.ActionService;
import org.jcommander.core.action.ChangeDirectoryAction;
import org.jcommander.core.action.OpenFileAction;
import org.jcommander.core.comparator.DirectoryTableRowComparatorService;
import org.jcommander.gui.locale.LocaleContext;
import org.jcommander.model.Directory;
import org.jcommander.model.DirectoryRowSorter;
import org.jcommander.model.DirectoryTableModel;
import org.jcommander.model.File;
import org.jcommander.model.column.BaseExtensionColumn;
import org.jcommander.model.column.BaseIconAndStringColumn;
import org.jcommander.model.column.Column;
import org.jcommander.model.column.FileSizeColumn;
import org.jcommander.model.column.IconAndStringColumn;
import org.jcommander.model.column.LocaleDateColumn;

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
		
		DirectoryRowSorter sorter = new DirectoryRowSorter(this.tableModel);
		this.setRowSorter(sorter);
		this.tableModel.registerDirectoryContentChangeListener(sorter);
//		this.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer());
		
		
		
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
		
	    this.addFocusListener(new FocusListener() {
			
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				logger.debug("panel focused : " + arg0.getSource());
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

		org.jcommander.core.action.AbstractAction action = null;

		if (fileSelected instanceof Directory) {
			action = new ChangeDirectoryAction(dtm.getDirectory().getPath(), fileSelected.getPath(), dtm);
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
			
			if(e.getKeyChar() == KeyEvent.VK_F6){
				ApplicationContext.getInstance().getOnMoveActionListsner().actionPerformed(null);
			}
			
			if(e.getKeyChar() == KeyEvent.VK_F8 || e.getKeyChar() == KeyEvent.VK_DELETE){
				ApplicationContext.getInstance().getOnDeleteActionListsner().actionPerformed(null);
			}
			
			if(e.getKeyChar() == KeyEvent.VK_F5){
				ApplicationContext.getInstance().getOnCopyActionListsner().actionPerformed(null);
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
	
	@Override
	public Component prepareRenderer(
	        TableCellRenderer renderer, int row, int column)
	    {
	        Component c = super.prepareRenderer(renderer, row, column);
	        logger.debug("row: " + row);
	        int row1 = row;
	        try{
	        	row1 = convertRowIndexToView(row);
	        }catch(Exception e){
	        	String info = LocaleContext.getContext().getBundle().getString("dialog.directory.problem.info");
				JOptionPane.showMessageDialog(ApplicationContext.getInstance().getMainApplicationWindow(),
						info);
//				return c ;
	        }
	        
	        
	        DirectoryTableModel dtm = (DirectoryTableModel) getModel();
	        Object o =  dtm.getValueAt(row1, column);
	        Column col = (Column) o;
	        if(column == DirectoryTableRowComparatorService.FILE_NAME_COLUMN_INDEX){
	        	
	        	IconAndStringColumn col1 = (IconAndStringColumn) col;
		        
		        ImageIcon ii = (ImageIcon) col1.getIcon();
		        Image img = ii.getImage();  
		        Image newimg = img.getScaledInstance(16, 16,  java.awt.Image.SCALE_SMOOTH);  
		        Icon newIcon = new ImageIcon(newimg); 
		        
		        ((DefaultTableCellRenderer) c).setIcon(newIcon);
		        ((DefaultTableCellRenderer) c).setText(col1.getText());
	        }
	        else{
	        	((DefaultTableCellRenderer) c).setIcon(null);
	        	((DefaultTableCellRenderer) c).setText(col.toString());
	        }
	        
	        return c;
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

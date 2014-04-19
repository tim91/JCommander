package org.jcommander.gui;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.apache.log4j.Logger;
import org.jcommander.core.action.Action;
import org.jcommander.core.action.ActionExecuter;
import org.jcommander.core.action.ActionService;
import org.jcommander.core.action.ChangeDirectoryAction;
import org.jcommander.core.action.OpenFileAction;
import org.jcommander.gui.locale.components.LocaleParametrizedJLabel;
import org.jcommander.model.Directory;
import org.jcommander.model.DirectoryTableModel;
import org.jcommander.model.File;
import org.jcommander.model.column.IconAndStringColumn;

public class DirectoryViewJTable extends JTable {

	static Logger logger = Logger
			.getLogger("org.jcommander.gui.DirectoryViewJTable");

	private LocaleParametrizedJLabel descriptionLabel = null;

	private DirectoryTableModel tableModel = null;

	private DirectoryViewJTable directoryViewJTable = null;

	public DirectoryViewJTable(DirectoryTableModel model) {
		
		this.tableModel = model;
		this.setModel(tableModel);
		this.addMouseListener(new TableMouseClickListener());
		
		TableColumnModel tcm = this.getColumnModel();
		
		tcm.getColumn(0).setCellRenderer(new IconTextCellRenderer());
		
		directoryViewJTable = this;
	}

	public Object[] getDirectoryInformations() {
		// TODO Auto-generated method stub
		return new Object[] { 0, 781, 0, 3, 0, 26 };
	}

	private class TableMouseClickListener implements MouseListener {

		ActionService actionService = ActionService.getInstance();

		public void mouseClicked(MouseEvent e) {

			if (e.getClickCount() == 2) {
				int row = directoryViewJTable.rowAtPoint(e.getPoint());
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

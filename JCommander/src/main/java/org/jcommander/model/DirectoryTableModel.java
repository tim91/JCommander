package org.jcommander.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;
import org.jcommander.core.DeviceChangeListener;
import org.jcommander.core.DirectoryChangeListener;
import org.jcommander.core.action.Action;
import org.jcommander.core.action.ActionExecuter;
import org.jcommander.core.action.ActionService;
import org.jcommander.core.action.ChangeDirectoryAction;
import org.jcommander.core.system.SystemService;
import org.jcommander.gui.locale.LocaleChangeListener;
import org.jcommander.gui.locale.LocaleContext;
import org.jcommander.model.column.AttributeColumn;
import org.jcommander.model.column.LocaleDateColumn;
import org.jcommander.model.column.SizeColumn;
import org.jcommander.util.exception.InvalidDirectoryPathException;

public class DirectoryTableModel extends AbstractTableModel implements LocaleChangeListener,DeviceChangeListener {
	
	static Logger logger = Logger
			.getLogger("org.jcommander.model.DirectoryTableModel");
	
	public static String [] COLUMNS = new String [] {"table.column.name","table.column.extension","table.column.size",
		"table.column.date"};
	
	public static String DIRECTORY_SIZE = "<DIR>";
	
	private List<DirectoryChangeListener> directoryChangeListeners = new ArrayList<DirectoryChangeListener>();
	
	private Directory directory = null;
	
	private JTextField directoryPathTextBox = null;
	
	public static Map<Integer,Boolean> COLUMNS_EDITABLE = new HashMap<Integer,Boolean>(){
        {
            put(0, false);
            put(1, false);
            put(2, false);
            put(3, false);
            put(4, false);
        }
    };

    private static Map<Integer,Class<?>> colIndexToClass = new HashMap<Integer, Class<?>>();
    
    static
    {
    	colIndexToClass.put(0, String.class);
    	colIndexToClass.put(1, String.class);
    	colIndexToClass.put(2, SizeColumn.class);
    	colIndexToClass.put(3, LocaleDateColumn.class);
    	colIndexToClass.put(4, AttributeColumn.class);
    }
    
//    public DirectoryTableModel() {
//		// TODO Auto-generated constructor stub
//	}
	
	public DirectoryTableModel(Path path,JTextField directoryPathTextBox) {
		
		LocaleContext.getContext().addContextChangeListener(this);
		
		this.directoryPathTextBox = directoryPathTextBox;
		
		try {
			this.directory  = SystemService.getInstance().getDirectory(path);
			this.directoryPathTextBox.setText(path.getInTotalCommanderStyle());
		} catch (InvalidDirectoryPathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    
	
	
    public Directory getDirectory() {
		return directory;
	}

    public void fireTableDataChanged(){
    	Path p = this.directory.getPath();
    	
    	this.directoryPathTextBox.setText(p.getInTotalCommanderStyle());
    	
    	
    	super.fireTableDataChanged();
    }
    
	public void setDirectory(Directory directory) {
		//TODO teraz powinien byc refresh
		this.directory = directory;
		
		
		/*
		 * UPDATE
		 */
		fireTableDataChanged();
		fireTableStructureChanged();
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		return this.colIndexToClass.get(arg0);
		
	}
    
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return COLUMNS.length;
	}

	public File getRowComponent(int rowIdx){
		return this.directory.getFile(rowIdx);
	}
	
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.directory.getElements();
	}

	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		File f = this.directory.getFile(arg0);
		return f.getValueByColumnIndex(arg1);
	}
	
    @Override
    public String getColumnName(int column) {
    	logger.debug("Pobieram kolumne : " + column);
    	return LocaleContext.getContext().getBundle().getString(COLUMNS[column]);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return COLUMNS_EDITABLE.get(columnIndex);
    }

	public void localeChanged() {
		
//		int cols = this.getColumnCount();
//		for(int i=0; i< cols; i++){
//			String val = this.getColumnName(i);
//			this.getColumnModel().getColumn(i).setHeaderValue(val);
//		}
		fireTableDataChanged();
		
	}
    
	
	public void notifyAllDirectoryChangedListsners(){
		for (DirectoryChangeListener el : this.directoryChangeListeners) {
			el.onDirectoryChangeAction(this.directory.getPath());
		}
	}
	
	public void registerDirectoryChangeListener(DirectoryChangeListener dcl){
		this.directoryChangeListeners.add(dcl);
	}

	public void onDeviceChangeAction(Path path) {
		/*
		 * We are changing path to the root of device
		 */
		
		if(!this.directory.getPath().equals(path)){
			Action a = new ChangeDirectoryAction(this.directory.getPath(), path, this);
			ActionExecuter aex = ActionService.getInstance().getActionExecuter(this.getDirectory());
			aex.executeAction(a);
			
		}
		
	}
	
	
//	public void insertInformationToTable(){
//		logger.debug("Dodaje informacje do tabelki z widokiem folderu");
//		
//		Directory directory = null;
//		try {
//			directory = SystemService.getInstance().getDirectory(this.path);
//		} catch (InvalidDirectoryPathException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		this.tableModel.setDirectory(directory);
//	}
	
	
	private class DirectoryTableColumnModelListener implements TableColumnModelListener
	{

		public void columnAdded(TableColumnModelEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void columnMarginChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void columnMoved(TableColumnModelEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void columnRemoved(TableColumnModelEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void columnSelectionChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}

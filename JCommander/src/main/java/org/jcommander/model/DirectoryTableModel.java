package org.jcommander.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;
import org.jcommander.core.action.ActionExecuter;
import org.jcommander.core.action.ActionService;
import org.jcommander.core.action.ChangeDirectoryAction;
import org.jcommander.core.listener.DeviceChangeListener;
import org.jcommander.core.listener.DirectoryChangeListener;
import org.jcommander.core.listener.DirectoryContentChangeListener;
import org.jcommander.core.system.SystemService;
import org.jcommander.gui.locale.LocaleChangeListener;
import org.jcommander.gui.locale.LocaleContext;
import org.jcommander.gui.locale.components.LocaleParametrizedJLabel;
import org.jcommander.model.column.AttributeColumn;
import org.jcommander.model.column.ExtensionColumn;
import org.jcommander.model.column.IconAndStringColumn;
import org.jcommander.model.column.LocaleDateColumn;
import org.jcommander.model.column.SizeColumn;
import org.jcommander.util.exception.InvalidDirectoryPathException;

public class DirectoryTableModel extends AbstractTableModel implements LocaleChangeListener,DeviceChangeListener {
	
	static Logger logger = Logger
			.getLogger("org.jcommander.model.DirectoryTableModel");
	
	public static String [] COLUMNS = new String [] {"table.column.name","table.column.extension","table.column.size",
		"table.column.date"};
	
	public static String DIRECTORY_SIZE = "<DIR>";
	
	private LocaleParametrizedJLabel folderInfo;
	
	private List<DirectoryContentChangeListener> directoryContentChangeListeners = new ArrayList<DirectoryContentChangeListener>();
	
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
    	colIndexToClass.put(0, IconAndStringColumn.class);
    	colIndexToClass.put(1, ExtensionColumn.class);
    	colIndexToClass.put(2, SizeColumn.class);
    	colIndexToClass.put(3, LocaleDateColumn.class);
    	colIndexToClass.put(4, AttributeColumn.class);
    }
	
	public DirectoryTableModel(Path path,JTextField directoryPathTextBox,LocaleParametrizedJLabel folderInfo) {
		
		LocaleContext.getContext().addContextChangeListener(this);
		this.folderInfo = folderInfo;
		this.directoryPathTextBox = directoryPathTextBox;
		
		try {
			setDirectory(SystemService.getInstance().getDirectory(path));
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
    	this.folderInfo.setValues(getDirInfo());
    	
    	super.fireTableDataChanged();
    }
    
    private Object [] getDirInfo(){
    	Object[] o = new Object[6];
    	o[0] = 0;
    	o[1] = this.directory.getFilesSize();
    	o[2] = 0;
    	o[3] = this.directory.getOnlyFilesNum();
    	o[4] = 0;
    	o[5] = this.directory.getDirectoriesNum();
    	return o;
    }
    
    /*
     * Check if we can go to the parent of current dir
     */
    public boolean isPosibleGoToTheParent(){
    	
    	return this.directory.getFile(0) instanceof ParentDirectory;
    }
    
    
	public void setDirectory(Directory directory) {
		//TODO teraz powinien byc refresh
		this.directory = directory;
		
		if(!this.directory.getPath().isRoot()){
			/*
			 * Insert line on the top
			 */
			Path pp = this.directory.getPath();
			Directory d = null;
			try {
				d = SystemService.getInstance().getParentDirectory(pp.getParentPath());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidDirectoryPathException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.directory.addFile(d,0);
			
		}
		/*
		 * UPDATE
		 */
		fireTableDataChanged();
		fireTableStructureChanged();
		notifyDirectoryContentChangeListeners();
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		return this.colIndexToClass.get(arg0);
		
	}
    
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return COLUMNS.length;
	}

	public List<File> getFiles(){
		return this.directory.getFiles();
	}
	
	public void setFiles(List<File> fs){
		this.directory.setFiles(fs);
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
    	String name = LocaleContext.getContext().getBundle().getString(COLUMNS[column]);
    	logger.debug("Pobieram kolumne : " + column + " nazwa : " + name);
    	return name;
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
		fireTableStructureChanged();
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
			org.jcommander.core.action.AbstractAction a = new ChangeDirectoryAction(this.directory.getPath(), path, this);
			ActionExecuter aex = ActionService.getInstance().getActionExecuter();
			aex.executeAction(a);
		}
		
	}

	public void refreshDataSource() {
		try {
			Directory d = SystemService.getInstance().getDirectory(this.directory.getPath());
			setDirectory(d);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidDirectoryPathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void registerDirectoryContentChangeListener(DirectoryContentChangeListener li){
		this.directoryContentChangeListeners.add(li);
	}
	
	private void notifyDirectoryContentChangeListeners(){
		for(DirectoryContentChangeListener l :directoryContentChangeListeners ){
			l.onDirectorycontentChange();
		}
	}
}

package org.jcommander.model;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import org.jcommander.gui.locale.LocaleContext;
import org.jcommander.model.column.AttributeColumn;
import org.jcommander.model.column.LocaleDateColumn;
import org.jcommander.model.column.SizeColumn;
import org.jcommander.util.exception.InvalidDirectoryPathException;
import org.jcommander.core.system.SystemService;

public class DirectoryTableModel extends AbstractTableModel {
	
	public static String [] COLUMNS = new String [] {"table.column.name","table.column.extension","table.column.size",
		"table.column.date","table.column.attribute"};
	
	public static String DIRECTORY_SIZE = "<DIR>";
	
	
	
	Directory directory = null;
	
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
    
    public DirectoryTableModel() {
		// TODO Auto-generated constructor stub
	}
	
	public DirectoryTableModel(Path path) {
		
		try {
			this.directory  = SystemService.getInstance().getDirectory(path);
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

	public void setDirectory(Directory directory) {
		//TODO teraz powinien byc refresh
		this.directory = directory;
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
		return this.directory.getFilesNum();
	}

	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		File f = this.directory.getFile(arg0);
		return f.getValueByColumnIndex(arg1);
	}
	
    @Override
    public String getColumnName(int column) {
    	return LocaleContext.getContext().getBundle().getString(COLUMNS[column]);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return COLUMNS_EDITABLE.get(columnIndex);
    }
    
}

package org.jcommander.model;

import java.util.HashMap;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import org.jcommander.gui.locale.LocaleContext;
import org.jcommander.model.column.AttributeColumn;
import org.jcommander.model.column.DateColumn;
import org.jcommander.model.column.SizeColumn;

public class DirectoryTableModel extends AbstractTableModel {
	
	public static String [] COLUMNS = new String [] {"table.column.name","table.column.extension","table.column.size",
		"table.column.date","table.column.attribute"};
	
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
    	colIndexToClass.put(3, DateColumn.class);
    	colIndexToClass.put(4, AttributeColumn.class);
    }
    
    @Override
	public Class<?> getColumnClass(int arg0) {
		return this.colIndexToClass.get(arg0);
		
	}
    
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return COLUMNS.length;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
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

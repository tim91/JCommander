package org.jcommander.gui;

import javax.swing.JTable;

import org.jcommander.gui.locale.LocaleChangeListener;
import org.jcommander.gui.locale.LocaleContext;
import org.jcommander.model.DirectoryTableModel;

public class DirectoryJTable extends JTable implements LocaleChangeListener {
	
	public DirectoryJTable(String path) {
	
		this.setModel(new DirectoryTableModel());
		LocaleContext.getContext().addContextChangeListener(this);
	}

	public Object[] getDirectoryInformations() {
		// TODO Auto-generated method stub
		return new Object[]{0,781,0,3,0,26};
	}

	public void localeChanged() {
		
		int cols = this.getModel().getColumnCount();
		for(int i=0; i< cols; i++){
			String val = this.getModel().getColumnName(i);
			this.getTableHeader().getColumnModel().getColumn(i).setHeaderValue(val);
		}
	}

}

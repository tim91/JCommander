package org.jcommander.gui;

import javax.swing.JTable;

import org.apache.log4j.Logger;
import org.jcommander.core.DirectoryChangeListener;
import org.jcommander.core.DeviceChangeListener;
import org.jcommander.core.path.Path;
import org.jcommander.gui.locale.LocaleChangeListener;
import org.jcommander.gui.locale.LocaleContext;
import org.jcommander.gui.locale.components.LocaleParametrizedJLabel;
import org.jcommander.model.Device;
import org.jcommander.model.DirectoryTableModel;

public class DirectoryViewJTable extends JTable implements LocaleChangeListener,DeviceChangeListener{
	
	static Logger logger = Logger
			.getLogger("org.jcommander.gui.DirectoryViewJTable");
	
	private Path path = null;
	
	private LocaleParametrizedJLabel descriptionLabel = null;
	
	private DirectoryChangeListener directoryChangeListener = null;
	
	
	public DirectoryViewJTable(Path path,LocaleParametrizedJLabel descriptionLabel) {
	
		this.setModel(new DirectoryTableModel());
		LocaleContext.getContext().addContextChangeListener(this);
		this.path = path;
		this.descriptionLabel = descriptionLabel;
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
	
	public void setDeviceInComboBox(){
		this.directoryChangeListener.changeDeviceOnList(this.path);
	}
	
	public void registerDirectoryChangeListener(DirectoryChangeListener dcl){
		this.directoryChangeListener = dcl;
	}

	public void changeElementsInTabPanel(Path path) {
		/*
		 * We are changing path to the root of device
		 */
		this.path = path;
		initializeTable();
	}
	
	public void initializeTable(){
		logger.debug(super.getParent());
	}

}

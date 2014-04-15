package org.jcommander.gui;

import java.io.FileNotFoundException;

import javax.swing.JTable;

import org.apache.log4j.Logger;
import org.jcommander.core.DirectoryChangeListener;
import org.jcommander.core.DeviceChangeListener;
import org.jcommander.gui.locale.LocaleChangeListener;
import org.jcommander.gui.locale.LocaleContext;
import org.jcommander.gui.locale.components.LocaleParametrizedJLabel;
import org.jcommander.model.Device;
import org.jcommander.model.Directory;
import org.jcommander.model.DirectoryTableModel;
import org.jcommander.model.Path;
import org.jcommander.util.exception.InvalidDirectoryPathException;
import org.jcommander.core.system.SystemService;

public class DirectoryViewJTable extends JTable implements LocaleChangeListener,DeviceChangeListener{
	
	static Logger logger = Logger
			.getLogger("org.jcommander.gui.DirectoryViewJTable");
	
	private Path path = null;
	
	private LocaleParametrizedJLabel descriptionLabel = null;
	
	private DirectoryChangeListener directoryChangeListener = null;
	
	private DirectoryTableModel tableModel = null;
	
	public DirectoryViewJTable(Path path,LocaleParametrizedJLabel descriptionLabel) {
	
		this.tableModel = new DirectoryTableModel(path);
		this.setModel(tableModel);
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
		
		if(!this.path.equals(path)){
			this.path = path;
			insertInformationToTable();
		}
		
	}
	
	public void insertInformationToTable(){
		logger.debug("Dodaje informacje do tabelki z widokiem folderu");
		
		Directory directory = null;
		try {
			directory = SystemService.getInstance().getDirectory(this.path);
		} catch (InvalidDirectoryPathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.tableModel.setDirectory(directory);
	}

}

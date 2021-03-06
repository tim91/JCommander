package org.jcommander.gui;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;
import org.jcommander.core.ApplicationContext;
import org.jcommander.core.listener.ChildFocusedListener;
import org.jcommander.core.listener.DeviceChangeListener;
import org.jcommander.core.listener.DirectoryChangeListener;
import org.jcommander.core.util.JCommanderUtils;
import org.jcommander.model.DirectoryTableModel;
import org.jcommander.model.Path;

public class CustomJTabbedPane extends JTabbedPane implements DeviceChangeListener, DirectoryChangeListener, ChildFocusedListener {

	static Logger logger = Logger.getLogger("org.jcommander.gui.CustomJTabbedPane");
	
	public CustomJTabbedPane() {
		
		super();
		
		this.addChangeListener(new TabChangedListener());
		
		/*
		 * Fire virtual tab changed to set Device in comboBox
		 */
		
		this.setSelectedIndex(this.getSelectedIndex());
	}
	
	private class TabChangedListener implements ChangeListener
	{
		
		public void stateChanged(ChangeEvent arg0) {
			JTabbedPane sourceTabbedPane = (JTabbedPane) arg0.getSource();
			logger.debug("Wybrano zakladke : " + sourceTabbedPane.getSelectedIndex());
			int selectedIndex = sourceTabbedPane.getSelectedIndex();
			JPanel panel = (JPanel)sourceTabbedPane.getComponent(selectedIndex);
			
			/*
			 * We must find our directoryTable
			 */
			
			Component comp = (Component) JCommanderUtils.getSpecifiedComponentInContainer(panel, "DirectoryViewJTable");
			
			if(comp != null){
				logger.debug("Znalazlem komponent : " + comp);
				
				DirectoryViewJTable dwt = (DirectoryViewJTable)comp;
				DirectoryTableModel dtm = (DirectoryTableModel) dwt.getModel();
				dtm.notifyAllDirectoryChangedListsners();
				
			}
			
			
			
//	        System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
			
		}
		
	}
	
	public void onDeviceChangeAction(Path path) {
		int selected = this.getSelectedIndex();
		
		/*
		 * 
		 */
		DirectoryViewJTable selectedPanel = (DirectoryViewJTable) JCommanderUtils.getSpecifiedComponentInContainer((Container) this.getSelectedComponent(), "Sdf");
		DirectoryTableModel dtm = (DirectoryTableModel) selectedPanel.getModel();
		dtm.onDeviceChangeAction(path);
		
		this.setTitleAt(selected, path.getLeafInLowerCase());
	}

	public void onDirectoryChangeAction(Path path) {
		int selected = this.getSelectedIndex();
		this.setTitleAt(selected, path.getLeafInLowerCase());
	}

	public void onChildFocus() {
		// TODO Auto-generated method stub
		logger.debug("Powiadomiono mnie " + this + " ze jestem zaznaczony");
		ApplicationContext.getInstance().onFocusChangeOwner(this.getSelectedComponent());
	}
}

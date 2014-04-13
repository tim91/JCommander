package org.jcommander.gui;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;
import org.jcommander.core.DeviceChangeListener;
import org.jcommander.core.path.Path;
import org.jcommander.core.util.JCommanderUtils;
import org.jcommander.model.Device;

public class CustomJTabbedPane extends JTabbedPane implements DeviceChangeListener {

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
				
				dwt.setDeviceInComboBox();
				
				
			}
			
			
			
//	        System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
			
		}
		
	}

	
	
	public void changeElementsInTabPanel(Path path) {
		int selected = this.getSelectedIndex();
		this.setTitleAt(selected, path.getLeaf());
	}
}

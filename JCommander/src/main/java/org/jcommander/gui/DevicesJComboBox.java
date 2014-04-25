package org.jcommander.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import org.apache.log4j.Logger;
import org.jcommander.core.listener.DeviceChangeListener;
import org.jcommander.core.listener.DirectoryChangeListener;
import org.jcommander.core.system.SystemService;
import org.jcommander.core.util.ColorUtils;
import org.jcommander.core.util.JCommanderUtils;
import org.jcommander.gui.locale.components.LocaleParametrizedJLabel;
import org.jcommander.model.Device;
import org.jcommander.model.DeviceComboBoxModel;
import org.jcommander.model.Path;
import org.jcommander.model.PrototypeDevice;

public class DevicesJComboBox extends JComboBox<Device> implements DirectoryChangeListener {

	static Logger logger = Logger.getLogger("org.jcommander.gui.DevicesJComboBox");
	
	protected JComboBox<Device> comboBox = null;
	
	protected CustomJTabbedPane tabbedPane = null;
	
	protected LocaleParametrizedJLabel devicedescriptionLabel = null;
	
	protected DeviceChangeListener deviceChangedListener = null;
	
//	protected List<DeviceChangeListener> deviceChangedListener = new ArrayList<DeviceChangeListener>();
	
	protected Path actualVisitingPath = null;
	
	public DevicesJComboBox(LocaleParametrizedJLabel deviceDescription) {
		super();
		
		
		
		this.setRenderer(new DeviceCellRenderer());
		
		this.setModel(new DeviceComboBoxModel());
		/*
		 * Klasa DirectoryJTable ma za zadanie wybranie odpowiedniego dysku w zaleznosci od wyswietlaego folderu
		 */
		
		this.addItemListener(new DeviceChangedListener());
//		this.addPopupMenuListener(new DeviceListPopup());
		this.setBackground(Color.WHITE);
//		this.setPreferredSize(new Dimension(50,20));
//		this.setPrototypeDisplayValue(new PrototypeDevice());
//		this.setOpaque(true);
		
		this.comboBox = this;
		this.devicedescriptionLabel = deviceDescription;
	}
	
//	
//	
//	
//	public void setTabbedPane(CustomJTabbedPane tabbedPane) {
//		this.tabbedPane = tabbedPane;
//	}

	private class DeviceCellRenderer extends JLabel implements ListCellRenderer<Device>
	{

		public Component getListCellRendererComponent(JList list, Device value,
				int index, boolean isSelected, boolean arg4) {
			
			
			if(value != null){
				Device device = value;
				setIcon(device.getIcon());
				
				String text = null;
//				if(index > -1){
//					text = device.toString() + "   | " + device.getDeviceName().toUpperCase();
//				}else{
//					text = device.toString();
//				}
				
				text = device.toString();
				
				setText(text);
				
				if(index > -1 && isSelected){
					super.setBackground(ColorUtils.DEVICE_LIST_SELECTION);
					setOpaque(true);
				}else{
					setBackground(Color.WHITE);
					setOpaque(false);
				}
				
			}else{
				System.out.println();
			}
			
			
			
//			logger.debug(device.toString() + " zaznaczone : " +  isSelected + " index: " + index);
			
			return this;
		}
		
	}
	
	private class DeviceListPopup implements PopupMenuListener
	{

		public void popupMenuCanceled(PopupMenuEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
			
		}

		public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			/*
			 * We are checking if new devices are available
			 */
			int selected = comboBox.getSelectedIndex();
			
			DeviceComboBoxModel model = (DeviceComboBoxModel) comboBox.getModel();
			model.update();
			
			if(comboBox.getItemCount() > selected){
				/*
				 * We select previous selected item
				 */
				comboBox.setSelectedIndex(selected);
			}
				
			
		}
		
	}
	
	private class DeviceChangedListener implements ItemListener
	{

		private Device deselected = null;
		
		public void itemStateChanged(ItemEvent e) {
			
			if(e.getStateChange() == ItemEvent.DESELECTED)
			{
				this.deselected = (Device) e.getItem();
			}
			
			if(e.getStateChange() == ItemEvent.SELECTED){
				logger.debug("Wybrano dysk: " + e.getItem());
				
				Device device = (Device) e.getItem();
				
				if(!device.equals(deselected)){
					Object [] params = new Object[] {device.getDeviceName().toLowerCase(),device.getFreeSpace(), device.getSpace()};
					devicedescriptionLabel.setValues(params);
					
					if(actualVisitingPath.getDevice().equals(device)){
						notifyListeners(actualVisitingPath);
					}else{
						notifyListeners(device.getPath());
						actualVisitingPath = device.getPath();
					}
					
					
				}
				
				
			}
			
		}
		
	}
	
	public void onDirectoryChangeAction(Path path) {
		actualVisitingPath = path;
		comboBox.setSelectedItem(path.getDevice());
	}
	
	public void registerDeviceChangeListener(DeviceChangeListener deviceChangedListener){
//		this.deviceChangedListener.add(deviceChangedListener);
		this.deviceChangedListener = deviceChangedListener;
	}
	
	public void notifyListeners(Path p){
		
//		logger.debug("Aktualnie zaznaczona zakladka :" + this.tabbedPane.getSelectedComponent());
		
//		DirectoryViewJTable selected = (DirectoryViewJTable) JCommanderUtils.getSpecifiedComponentInContainer((Container) this.tabbedPane.getSelectedComponent(), "Sdf");
//		
//		for (DeviceChangeListener listener : this.deviceChangedListener) {
//			logger.debug("Listener :" + listener);
//			if((listener instanceof DirectoryViewJTable && (selected.equals(listener)))
//					||!(listener instanceof DirectoryViewJTable) ){
//				listener.onDeviceChangeAction(p);
//			}
//		}
		if(this.deviceChangedListener != null){
			this.deviceChangedListener.onDeviceChangeAction(p);
		}
		
	}
}

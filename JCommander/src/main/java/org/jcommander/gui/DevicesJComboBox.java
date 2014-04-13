package org.jcommander.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import org.apache.log4j.Logger;
import org.jcommander.core.system.DirectoryChangeListener;
import org.jcommander.core.system.System;
import org.jcommander.gui.locale.components.LocaleParametrizedJLabel;
import org.jcommander.model.Device;
import org.jcommander.model.PrototypeDevice;

public class DevicesJComboBox extends JComboBox<Device> implements DirectoryChangeListener {

	static Logger logger = Logger.getLogger("org.jcommander.gui.DevicesJComboBox");
	
	protected System system = null;
	
	protected JComboBox<Device> comboBox = null;
	
	protected LocaleParametrizedJLabel devicedescriptionLabel = null;
	
	public DevicesJComboBox(LocaleParametrizedJLabel deviceDescription) {
		super();
		
		system = System.getInstance();
		
		this.setRenderer(new DeviceCellRenderer());
		
		List<Device> devices = system.getDevices();
		
		for (Device device : devices) {
			this.addItem(device);
		}
		/*
		 * Klasa DirectoryJTable ma za zadanie wybranie odpowiedniego dysku w zaleznosci od wyswietlaego folderu
		 */
		
		this.addItemListener(new DeviceChangeListener());
		this.addPopupMenuListener(new DeviceListPopup());
		this.setBackground(Color.WHITE);
		this.setMaximumSize(new Dimension(10,20));
		this.setPreferredSize(new Dimension(10,20));
		this.setPrototypeDisplayValue(new PrototypeDevice());
		this.setOpaque(true);
		
		this.comboBox = this;
		this.devicedescriptionLabel = deviceDescription;
	}
	
	
	private class DeviceCellRenderer extends JLabel implements ListCellRenderer 
	{

		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean arg4) {
			
			Device device = (Device) value;
			setIcon(device.getIcon());
			
			String text = null;
			if(index > -1){
				text = device.toString() + "   | " + device.getDeviceName().toUpperCase();
			}else{
				text = device.toString();
			}
			
			setText(text);
			
//			if(comboBox != null){
//				comboBox.setPreferredSize(new Dimension(text.length()*5, comboBox.getPreferredSize().height));
//			}
			
			
			if(index > -1 && isSelected){
				super.setBackground(Color.BLUE);
				setOpaque(true);
			}else{
				setBackground(Color.WHITE);
				setOpaque(false);
			}
			
			logger.debug(device.toString() + " zaznaczone : " +  isSelected + " index: " + index);
			
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
			
			List<Device> devices = system.getDevices();
			
			comboBox.removeAllItems();
			
			for (Device device : devices) {
				comboBox.addItem(device);
			}
			
			if(comboBox.getItemCount() > selected){
				/*
				 * We select previous selected item
				 */
				comboBox.setSelectedIndex(selected);
			}
				
			
		}
		
	}
	
	private class DeviceChangeListener implements ItemListener
	{

		public void itemStateChanged(ItemEvent e) {
			
			if(e.getStateChange() == ItemEvent.SELECTED){
				logger.debug("Wybrano dysk: " + e.getItem());
				
				Device device = (Device) e.getItem();
				Object [] params = new Object[] {device.getDeviceName().toLowerCase(),device.getFreeSpace(), device.getSpace()};
				
				devicedescriptionLabel.setValues(params);
				
			}
			
		}
		
	}

	public void changeDeviceOnList(Device device) {
		comboBox.setSelectedItem(device);
	}
	
}
package org.jcommander.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import org.apache.log4j.Logger;
import org.jcommander.core.system.SystemService;

public class DeviceComboBoxModel implements ComboBoxModel<Device> {

	static Logger logger = Logger
			.getLogger("org.jcommander.model.DeviceComboBoxModel");
	
	protected SystemService system = SystemService.getInstance();
	
	private List<Device> devices = null;
	
	private Device selectedItem = null;
	
	public DeviceComboBoxModel() {
		update();
	}
	
	private List<ListDataListener> listDataListener = new ArrayList<ListDataListener>();
	
	public void addListDataListener(ListDataListener arg0) {
		logger.debug("Chca dodac addListDataListener");
		listDataListener.add(arg0);
	}

	public Device getElementAt(int arg0) {
		// TODO Auto-generated method stub
		return this.devices.get(arg0);
	}

	public int getSize() {
		// TODO Auto-generated method stub
		return this.devices.size();
	}

	public void removeListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		listDataListener.remove(arg0);
	}

	public Object getSelectedItem() {
		// TODO Auto-generated method stub
		return this.selectedItem;
	}

	public void setSelectedItem(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 == null){
			selectedItem = this.devices.get(0);
		}else{
			selectedItem = (Device) arg0;
		}
		
	}

	public void update() {
		devices = system.getDevices();
	}

}

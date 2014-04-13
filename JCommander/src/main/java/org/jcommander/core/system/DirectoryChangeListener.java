package org.jcommander.core.system;

import org.jcommander.model.Device;

public interface DirectoryChangeListener {

	/*
	 * Method set given device in DeviceComboBox
	 */
	public void changeDeviceOnList(Device device);
}

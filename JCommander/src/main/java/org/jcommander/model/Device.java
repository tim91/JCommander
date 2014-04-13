package org.jcommander.model;

import javax.swing.Icon;

public interface Device {

	public String getFreeSpace();
	public String getSpace();
	public String getDeviceName();
	public String getDeviceLabel();
	public Icon getIcon();
}

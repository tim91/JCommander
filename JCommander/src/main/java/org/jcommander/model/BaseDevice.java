package org.jcommander.model;

import java.text.NumberFormat;

import javax.swing.Icon;

import org.jcommander.core.path.BasePath;
import org.jcommander.core.path.Path;
import org.jcommander.gui.locale.LocaleContext;

public class BaseDevice implements Device {

	
	private String deviceLabel = null;
	private String deviceName = null;
	private long freeSpace = -1;
	private long totalSpace = -1;
	private Icon imageIcon = null;
	
	public BaseDevice(String deviceLabel,String deviceName, long freeSpace, long totalSpace, Icon imageIcon) {
		super();
		this.deviceLabel = deviceLabel;
		this.freeSpace = freeSpace;
		this.totalSpace = totalSpace;
		this.deviceName = deviceName;
		this.imageIcon = imageIcon;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.deviceLabel.toLowerCase();
	}
	
	public String getFreeSpace() {
		
		String ret = NumberFormat.getNumberInstance(LocaleContext.getContext().getLocale()).format(this.freeSpace);
		return ret;
	}

	public String getSpace() {
		
		String ret = NumberFormat.getNumberInstance(LocaleContext.getContext().getLocale()).format(this.totalSpace);
		return ret;
	}

	public String getDeviceName() {
		// TODO Auto-generated method stub
		return this.deviceName;
	}

	public String getDeviceLabel() {
		// TODO Auto-generated method stub
		return this.deviceLabel;
	}

	public Icon getIcon() {
		// TODO Auto-generated method stub
		return this.imageIcon;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deviceLabel == null) ? 0 : deviceLabel.hashCode());
		result = prime * result
				+ ((deviceName == null) ? 0 : deviceName.hashCode());
		result = prime * result
				+ ((imageIcon == null) ? 0 : imageIcon.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseDevice other = (BaseDevice) obj;
		if (deviceLabel == null) {
			if (other.deviceLabel != null)
				return false;
		} else if (!deviceLabel.equals(other.deviceLabel))
			return false;
		if (deviceName == null) {
			if (other.deviceName != null)
				return false;
		} else if (!deviceName.equals(other.deviceName))
			return false;
//		if (imageIcon == null) {
//			if (other.imageIcon != null)
//				return false;
//		} else if (!imageIcon.equals(other.imageIcon))
//			return false;
		return true;
	}

	public Path getPath() {
		// TODO Auto-generated method stub
		String p = this.getDeviceLabel() + ":\\*.*";
		return new BasePath(p, this);
	}
}

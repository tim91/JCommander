package org.jcommander.core.system;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;

import org.apache.log4j.Logger;
import org.jcommander.core.util.JCommanderUtils;
import org.jcommander.model.BaseDevice;
import org.jcommander.model.Device;
import org.jcommander.util.exception.SingletonException;

public class System {

	static Logger logger = Logger.getLogger("org.jcommander.core.system.System");
	
	private static System system = null;

	private FileSystemView fileSystemView = null;

	public System() throws SingletonException {

		if (system != null) {
			throw new SingletonException();
		}

		this.fileSystemView = FileSystemView.getFileSystemView();

	}

	public List<Device> getDevices() {
		List<Device> devices = new ArrayList<Device>(2);

		List<File> files = Arrays.asList(File.listRoots());
		for (File f : files) {
			String deviceName = this.fileSystemView.getSystemDisplayName(f);
			
			
			if(deviceName == null || deviceName.length() == 0)
				continue;
			
			String label = JCommanderUtils.ExtractDeviceLabel(deviceName);
			String name = JCommanderUtils.ExtractDeviceName(deviceName);
			Icon icon = this.fileSystemView.getSystemIcon(f);
			
			logger.debug("Otrzymany ciag: " + deviceName);
			logger.debug("Nazwa : " + name);
			logger.debug("Label: " + label);
			logger.debug("Ikona: " + icon);
			
			long totalSpace = f.getTotalSpace();
			long freeSpace = f.getFreeSpace();
			
			devices.add(new BaseDevice(label, name, freeSpace/1024, totalSpace/1024,icon));
			
		}

		return devices;
	}

	public static System getInstance() {

		if (system == null) {
			try {
				return new System();
			} catch (SingletonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else {
			return system;
		}

	}

}

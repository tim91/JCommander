package org.jcommander.core.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;

import org.apache.log4j.Logger;
import org.jcommander.core.util.JCommanderUtils;
import org.jcommander.model.BaseDevice;
import org.jcommander.model.BaseDirectory;
import org.jcommander.model.BaseFile;
import org.jcommander.model.BasePath;
import org.jcommander.model.Device;
import org.jcommander.model.Directory;
import org.jcommander.model.Path;
import org.jcommander.util.exception.InvalidDirectoryPathException;
import org.jcommander.util.exception.SingletonException;

public class SystemService {

	static Logger logger = Logger.getLogger("org.jcommander.core.system.System");
	
	private static SystemService system = null;

	private FileSystemView fileSystemView = null;

	private SystemService() throws SingletonException {

		if (system != null) {
			throw new SingletonException();
		}

		this.fileSystemView = FileSystemView.getFileSystemView();

	}

	public Device getDeviceByLabel(String lab){
		List<File> files = Arrays.asList(File.listRoots());
		for (File f : files) {
			String deviceName = this.fileSystemView.getSystemDisplayName(f);
			
			if(deviceName == null || deviceName.length() == 0)
				continue;
			
			String label = JCommanderUtils.ExtractDeviceLabel(deviceName);
			
			if(lab.toLowerCase().equals(label.toLowerCase())){
				String name = JCommanderUtils.ExtractDeviceName(deviceName);
				Icon icon = this.fileSystemView.getSystemIcon(f);
				
				logger.debug("Otrzymany ciag: " + deviceName);
				logger.debug("Nazwa : " + name);
				logger.debug("Label: " + label);
				logger.debug("Ikona: " + icon);
				
				long totalSpace = f.getTotalSpace();
				long freeSpace = f.getFreeSpace();
				
				return new BaseDevice(label, name, freeSpace/1024, totalSpace/1024,icon);
			}
			
		}
		return null;
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

	public static SystemService getInstance() {

		if (system == null) {
			try {
				return new SystemService();
			} catch (SingletonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else {
			return system;
		}

	}


	public Directory getDirectory(Path path) throws InvalidDirectoryPathException, FileNotFoundException {
		
		String p = path.getPathReadableToJava();
		
		File f = new File(p);
		
		if(!f.exists()){
			throw new FileNotFoundException();
		}
		
		if(!f.isDirectory()){
			throw new InvalidDirectoryPathException();
		}
		
		File [] files = f.listFiles();
		
		Directory dir = new BaseDirectory(f.getName(), f.lastModified(), "---",new BasePath(f.getAbsolutePath()));
		
		for (File file : files) {
			
			org.jcommander.model.File ff = null;
			
			if(file.isDirectory()){
				ff = new BaseDirectory(file.getName(), file.lastModified(),"---", new BasePath(file.getAbsolutePath()));
			}else if(file.isFile()){
				String ext = "";
				int index = file.getName().lastIndexOf(".");
				if(index > 0){
					ext += file.getName().substring(index);
				}
				ff = new BaseFile(file.getName(),ext,file.length(),file.lastModified(),"---",new BasePath(file.getAbsolutePath()));
			}
			dir.addFile(ff);
		}
		
		return dir;
	}

}

package org.jcommander.core.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import org.apache.log4j.Logger;
import org.jcommander.core.ApplicationContext;
import org.jcommander.core.action.AbstractAction;
import org.jcommander.core.action.CopyAction;
import org.jcommander.core.action.MoveAction;
import org.jcommander.core.util.JCommanderUtils;
import org.jcommander.gui.locale.LocaleContext;
import org.jcommander.model.BaseDevice;
import org.jcommander.model.BaseDirectory;
import org.jcommander.model.BaseFile;
import org.jcommander.model.BasePath;
import org.jcommander.model.Device;
import org.jcommander.model.Directory;
import org.jcommander.model.ParentDirectory;
import org.jcommander.model.Path;
import org.jcommander.util.exception.InvalidDirectoryPathException;
import org.jcommander.util.exception.SingletonException;

public class SystemService {

	static Logger logger = Logger
			.getLogger("org.jcommander.core.system.System");

	private static SystemService system = null;

	private FileSystemView fileSystemView = null;

	private SystemService() throws SingletonException {

		if (system != null) {
			throw new SingletonException();
		}

		this.fileSystemView = FileSystemView.getFileSystemView();

	}

	public Device getDeviceByLabel(String lab) {
		List<File> files = Arrays.asList(File.listRoots());
		for (File f : files) {
			String deviceName = this.fileSystemView.getSystemDisplayName(f);

			if (deviceName == null || deviceName.length() == 0)
				continue;

			String label = JCommanderUtils.ExtractDeviceLabel(deviceName);

			if (lab.toLowerCase().equals(label.toLowerCase())) {
				String name = JCommanderUtils.ExtractDeviceName(deviceName);
				Icon icon = this.fileSystemView.getSystemIcon(f);

				logger.debug("Otrzymany ciag: " + deviceName);
				logger.debug("Nazwa : " + name);
				logger.debug("Label: " + label);
				logger.debug("Ikona: " + icon);

				long totalSpace = f.getTotalSpace();
				long freeSpace = f.getFreeSpace();

				return new BaseDevice(label, name, freeSpace / 1024,
						totalSpace / 1024, icon);
			}

		}
		return null;
	}

	public List<Device> getDevices() {
		List<Device> devices = new ArrayList<Device>(2);

		List<File> files = Arrays.asList(File.listRoots());
		for (File f : files) {
			String deviceName = this.fileSystemView.getSystemDisplayName(f);

			if (deviceName == null || deviceName.length() == 0)
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

			devices.add(new BaseDevice(label, name, freeSpace / 1024,
					totalSpace / 1024, icon));

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

	public Directory getParentDirectory(Path path)
			throws FileNotFoundException, InvalidDirectoryPathException {
		String p = path.toString();

		File f = new File(p);

		if (!f.exists()) {
			throw new FileNotFoundException();
		}

		if (!f.isDirectory()) {
			throw new InvalidDirectoryPathException();
		}

		String label = f.getAbsolutePath().split(":")[0];
		Device device = getDeviceByLabel(label);
		Directory dir = new ParentDirectory(f.lastModified(), "---",
				new BasePath(f.getAbsolutePath(), device));
		return dir;
	}

	public Directory getDirectory(Path path)
			throws InvalidDirectoryPathException, FileNotFoundException {

		String p = path.toString();

		File f = new File(p);

		if (!f.exists()) {
			throw new FileNotFoundException();
		}

		if (!f.isDirectory()) {
			throw new InvalidDirectoryPathException();
		}

		String label = f.getAbsolutePath().split(":")[0];
		Device device = getDeviceByLabel(label);
		Directory dir = new BaseDirectory(f.getName(), f.lastModified(), "---",
				new BasePath(f.getAbsolutePath(), device),
				this.fileSystemView.getSystemIcon(f));

		File[] files = null;
		try{
			files = f.listFiles();
		}catch(Exception e){
			String info = LocaleContext.getContext().getBundle().getString("dialog.directory.problem.info");
			JOptionPane.showMessageDialog(ApplicationContext.getInstance().getMainApplicationWindow(),
					info);
			return dir;
		}
		
		for (File file : files) {

			org.jcommander.model.File ff = null;

			String name = file.getName();
			if (name.contains(".") && name.lastIndexOf(".") > 0) { // check if
																	// '.' is
																	// not first
																	// sign in
																	// file name
				name = name.substring(0, name.lastIndexOf("."));
			}

			if (file.isHidden())
				continue;

			
			
			try {
				java.nio.file.Path symLinkDirectory = Paths.get(file.getAbsolutePath());
				DosFileAttributes dosFileAttributes = Files.readAttributes(symLinkDirectory, DosFileAttributes.class,LinkOption.NOFOLLOW_LINKS);
				
				if(dosFileAttributes.isOther()){
					continue;
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (file.isDirectory()) {
//				try {
//					if(isSymlink(file))
//						continue;
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				ff = new BaseDirectory(name, file.lastModified(), "---",
						new BasePath(file.getAbsolutePath(), device),
						this.fileSystemView.getSystemIcon(file));
			} else if (file.isFile()) {
				String ext = "";
				int index = file.getName().lastIndexOf(".");
				if (index > 0) {
					ext += file.getName().substring(index);
				}

				logger.debug(this.fileSystemView.getSystemIcon(file)
						.getIconHeight());

				ff = new BaseFile(name, ext, file.length(),
						file.lastModified(), "---", new BasePath(
								file.getAbsolutePath(), device),
						this.fileSystemView.getSystemIcon(file));
			}

			if (ff != null) {
				dir.addFile(ff);
			}

		}

		return dir;
	}

	
	private static boolean isSymlink(File file) throws IOException {
		  if (file == null)
		    throw new NullPointerException("File must not be null");
		  File canon;
		  if (file.getParent() == null) {
		    canon = file;
		  } else {
		    File canonDir = file.getParentFile().getCanonicalFile();
		    canon = new File(canonDir, file.getName());
		  }
		  return !canon.getCanonicalFile().equals(canon.getAbsoluteFile());
		}
	
	private int BUFFER_SIZE = 1024;

	public boolean copyDirectory(File from, File to, byte[] buffer,AbstractAction progressListener) {
		//
		// System.out.println("copyDirectory("+from+","+to+")");

		if(progressListener.isCancelled() == true){
			return false;
		}
		
		if (from == null)
			return false;
		if (!from.exists())
			return true;
		if (!from.isDirectory())
			return false;

		if (to.exists()) {
			return false;
		}
		if (!to.mkdirs()) {
			return false;
		}

		String[] list = from.list();

		if (list != null) {

			if (buffer == null)
				buffer = new byte[BUFFER_SIZE]; // reuse this buffer to copy
												// files

			for (int i = 0; i < list.length; i++) {

				String fileName = list[i];

				File entry = new File(from, fileName);

				if (entry.isDirectory()) {
					if (!copyDirectory(entry, new File(to, fileName), buffer,progressListener))
						return false;
				} else {
					if (!copyFile(entry, new File(to, fileName), buffer,progressListener))
						return false;
				}
			}
		}
		return true;
	}

	public boolean copyFile(File from, File to,AbstractAction progressListener) {
		return copyFile(from, to, (byte[]) null,progressListener);
	}

	public boolean copyFile(File from, File to, byte[] buf,AbstractAction progressListener) {
		if (buf == null)
			buf = new byte[BUFFER_SIZE];

		if(progressListener.isCancelled() == true){
			return false;
		}
		
		FileInputStream from_s = null;
		FileOutputStream to_s = null;

		
		boolean operationTerminated = false;
		try {
			from_s = new FileInputStream(from);
			to_s = new FileOutputStream(to);
			
			for (int bytesRead = from_s.read(buf); bytesRead != -1; bytesRead = from_s
					.read(buf)){
				if(progressListener.isCancelled() == true){
					// Delete this file
					operationTerminated = true;
					break;
				}
				to_s.write(buf, 0, bytesRead);
				if(progressListener instanceof CopyAction){
					((CopyAction)progressListener).onOperationProgressChange(bytesRead);
				}else if(progressListener instanceof MoveAction){
					((MoveAction)progressListener).onOperationProgressChange(bytesRead);
				}
//				((CopyAction)progressListener).onOperationProgressChange(bytesRead);
//				progressListener.sendChunk(bytesRead);
			}
				

			from_s.close();
			from_s = null;

			to_s.getFD().sync();
			to_s.close();
			to_s = null;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		} finally {
			if (from_s != null) {
				try {
					from_s.close();
				} catch (IOException ioe) {
				}
			}
			if (to_s != null) {
				try {
					to_s.close();
				} catch (IOException ioe) {
				}
			}
			if(operationTerminated){
				to.delete();
				return false;
			}
		}

		return true;
	}

	public static long getFolderSize(File dir) {
	    long size = 0;
	    for (File file : dir.listFiles()) {
	        if (file.isFile()) {
	            size += file.length();
	        }
	        else
	            size += getFolderSize(file);
	    }
	    return size;
	}
	
	public static void deleteDirectory(File file, AbstractAction progressListener) {
		
		if(progressListener.isCancelled() == true){
			return;
		}
		
		if (file.isDirectory()) {

			// directory is empty, then delete it
			if (file.list().length == 0) {

				file.delete();
				logger.debug("Directory is deleted : "
						+ file.getAbsolutePath());

			} else {

				// list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);

					// recursive delete
					deleteDirectory(fileDelete,progressListener);
					
					if(progressListener.isCancelled() == true){
						return;
					}
					
				}

				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
					logger.debug("Directory is deleted : "
							+ file.getAbsolutePath());
				}
			}

		} else {
			// if file, then delete it
			file.delete();
			logger.debug("File is deleted : " + file.getAbsolutePath());
		}
	}
}

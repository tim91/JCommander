package org.jcommander.core.path;

import org.jcommander.model.Device;

public class BasePath implements Path {

	private String path = null;
	
	private Device device = null;
	
	public BasePath(String path,Device device) {
		this.path = path.replaceAll("/","\\\\");
		this.device = device;
	}
	
	public String getLeaf() {
		String[] el = this.path.split("\\\\");
		
		int toRet = el.length - 2;
		
		if(toRet < 0){
			return null;
		}
		
		return el[toRet].toLowerCase();
	}

	public String getFullPath() {
		// TODO Auto-generated method stub
		return this.path;
	}

	public Device getDevice() {
		// TODO Auto-generated method stub
		return this.device;
	}

}

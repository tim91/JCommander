package org.jcommander.model;

import org.apache.log4j.Logger;


public class BasePath implements Path {

	static Logger logger = Logger.getLogger("org.jcommander.model.BasePath");
	
	private String path = null;
	
	private Device device = null;
	
	public BasePath(String path){
		this.path = path;
		
		//TODO nie wiem czy bd mi to potrzebne
		this.device = null;
	}
	
	public BasePath(String path,Device device) {
		this.path = path.replaceAll("/","\\\\");
		if(path.endsWith("*.*")){
			int idx = this.path.lastIndexOf("\\");
			
			this.path = this.path.substring(0, idx+1);
		}
		
		this.device = device;
	}
	
	public String getLeaf() {
		String[] el = this.path.split("\\\\");
		
		int toRet = el.length - 1;
		
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
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
		BasePath other = (BasePath) obj;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}

//	public String getPathReadableToJava() {
//		// TODO Auto-generated method stub
//		
//		if(this.path.endsWith("*.*")){
//			int idx = this.path.lastIndexOf("\\");
//			
//			String toRet = this.path.substring(0, idx+1);
//			
//			logger.debug("Przeksztalcilem sciezke dla Javy z " + this.path + " na " + toRet );
//			
//			return toRet;
//		}
//		
//		logger.debug("Przeksztalcilem sciezke dla Javy z " + this.path + " na " + this.path );
//		return this.path;
//		
//	}

	@Override
	public String toString() {
	
		return this.path;
	}

	public String getInTotalCommanderStyle() {
		// TODO Auto-generated method stub
		return this.path + "*.*";
	}
	
}

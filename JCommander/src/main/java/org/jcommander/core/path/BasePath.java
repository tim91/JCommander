package org.jcommander.core.path;

public class BasePath implements Path {

	private String path = null;
	
	public BasePath(String path) {
		this.path = path.replaceAll("/","\\\\");
	}
	
	public String getLeaf() {
		String[] el = this.path.split("\\\\");
		
		int toRet = el.length - 2;
		
		if(toRet < 0){
			return null;
		}
		
		return el[toRet];
	}

	public String getFullPath() {
		// TODO Auto-generated method stub
		return this.path;
	}

}

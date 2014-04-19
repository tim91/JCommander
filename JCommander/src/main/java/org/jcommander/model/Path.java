package org.jcommander.model;


public interface Path {

	public String getLeaf();
	public String getFullPath();
	public Device getDevice();
	public boolean isRoot();
	public Path getParentPath();
	public String getInTotalCommanderStyle();
}

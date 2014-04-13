package org.jcommander.core.path;

import org.jcommander.model.Device;

public interface Path {

	public String getLeaf();
	public String getFullPath();
	public Device getDevice();
}

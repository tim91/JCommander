package org.jcommander.core.listener;

import org.jcommander.model.Path;

public interface DirectoryChangeListener {

	/*
	 * Method set given device in DeviceComboBox
	 */
	public void onDirectoryChangeAction(Path path);
}

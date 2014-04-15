package org.jcommander.core.action;

import org.jcommander.model.File;

public interface ActionExecuter {

	public enum Action{
		CHANGE_DIR;
	}
	
	public void performAction(File f,Action action);
	
}

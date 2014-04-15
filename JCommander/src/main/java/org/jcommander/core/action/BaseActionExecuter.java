package org.jcommander.core.action;

import org.apache.log4j.Logger;
import org.jcommander.model.File;

public class BaseActionExecuter implements ActionExecuter {

	static Logger logger = Logger
			.getLogger("org.jcommander.core.action.BaseActionExecuter");
	
	public void performAction(File f, Action action) {
		
		if(action == Action.CHANGE_DIR){
			logger.debug("Przechodze do folderu : " + f.getPath());
		}
		
		
	}

}

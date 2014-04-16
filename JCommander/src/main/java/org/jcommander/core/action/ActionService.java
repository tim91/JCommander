package org.jcommander.core.action;

import org.jcommander.model.File;

public class ActionService {

	private static ActionService actionService = null;
	
	private ActionService() {
		
		
	}

	public static ActionService getInstance(){
		
		if(actionService == null){
			return new ActionService();
		}
		else{
			return actionService;
		}
		
	}
	
	
	public ActionExecuter getActionExecuter(){
		/*
		 * Tutaj bd mogl wybrac jakis inny
		 */
		
		return new BaseActionExecuter();
	}
	
}

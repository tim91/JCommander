package org.jcommander.core.action;

import org.jcommander.core.ApplicationContext;


public class BaseActionExecuter implements ActionExecuter {

	public void executeAction(Action action) {
		
		/*
		 * Tutaj moglbym robic jeszcze inne rzeczy
		 */
		action.execute();
	}

}

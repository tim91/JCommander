package org.jcommander.core.action;


public class BaseActionExecuter implements ActionExecuter {

	public void executeAction(Action action) {
		
		/*
		 * Tutaj moglbym robic jeszcze inne rzeczy
		 */
		action.execute();
	}

}

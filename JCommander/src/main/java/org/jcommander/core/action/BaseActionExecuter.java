package org.jcommander.core.action;

import org.jcommander.core.ApplicationContext;


public class BaseActionExecuter implements ActionExecuter {

	public void executeAction(final AbstractAction action) {
		
		/*
		 * Tutaj moglbym robic jeszcze inne rzeczy
		 */
		
		try {
			action.doInBackground();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

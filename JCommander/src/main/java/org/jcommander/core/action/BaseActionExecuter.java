package org.jcommander.core.action;

import javax.swing.SwingUtilities;

import org.jcommander.core.ApplicationContext;


public class BaseActionExecuter implements ActionExecuter {

	public void executeAction(final AbstractAction action) {
		
		/*
		 * Tutaj moglbym robic jeszcze inne rzeczy
		 */
		
		action.initDialogWindow();
		
		try {
			action.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

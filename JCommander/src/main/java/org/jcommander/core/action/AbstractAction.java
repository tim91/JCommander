package org.jcommander.core.action;

import javax.swing.SwingWorker;

import org.jcommander.core.system.SystemService;
import org.jcommander.gui.dialog.NotFileSelectedDialog;

public abstract class AbstractAction extends SwingWorker<Object, Object> implements Action {

	protected SystemService systemService = SystemService.getInstance();
	
	protected Boolean threadIsAlive  = new Boolean(true);
	
	protected void showNoFileSelectedDialog(){
		NotFileSelectedDialog.show();
	}
	
	@Override
	protected Object doInBackground() throws Exception {
		// TODO Auto-generated method stub
		executeTask();
		return null;
	}
	
	public void terminate(){
		threadIsAlive = false;
	}
	
}

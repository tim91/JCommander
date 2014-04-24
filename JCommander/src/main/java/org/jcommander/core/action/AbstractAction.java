package org.jcommander.core.action;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.apache.log4j.Logger;
import org.jcommander.core.ApplicationContext;
import org.jcommander.core.system.SystemService;
import org.jcommander.gui.dialog.NotFileSelectedDialog;
import org.jcommander.gui.dialog.ProgressDialog;

public abstract class AbstractAction extends SwingWorker<Object,Integer> implements Action {

	static Logger logger = Logger
			.getLogger("org.jcommander.core.action.AbstractAction");
	
	protected SystemService systemService = SystemService.getInstance();
	
	protected Boolean execute = true;
	protected ProgressDialog pd;
	protected long copied = 0;
	protected Integer statusBarPos = 0;
	protected long bytesToCopy = 0;
	
	public void setExecute(Boolean execute) {
		this.execute = execute;
	}

	protected void showNoFileSelectedDialog(){
		NotFileSelectedDialog.show();
	}
	
	protected void refreshTable(){
		/*
		 * Refresh table with directory view after finished action
		 */
		
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				ApplicationContext.getInstance().refreshTabbedPanels();
			}
		});
	}
	
	
	
	@Override
	protected Object doInBackground() throws Exception {
		if(execute){
			executeTask();
		}
		return null;
	}
	
	public void sendChunk(Integer ... vals){
		publish(vals);
	}
	
}

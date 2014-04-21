package org.jcommander.core.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jcommander.core.ApplicationContext;
import org.jcommander.core.listener.ActionTerminateListener;
import org.jcommander.gui.dialog.DeleteDialog;
import org.jcommander.gui.dialog.ProgressDialog;
import org.jcommander.model.File;
import org.jcommander.model.ParentDirectory;

public class DeleteAction extends AbstractAction implements ActionTerminateListener {

	static Logger logger = Logger
			.getLogger("org.jcommander.core.action.DeleteAction");
	
	private List<File> toDelete = new ArrayList<File>();
	
	public DeleteAction(List<File> files) {
		super();
		this.toDelete = files;
	}
	
	private boolean confirmed = false;
	
	public void executeTask() {
		
		if(toDelete.size() == 0 || (toDelete.size() == 1 && toDelete.get(0) instanceof ParentDirectory)){
			/*
			 * Not selected files
			 */
			showNoFileSelectedDialog();
			return;
		}
		
		if(!confirmed){
			int selected = DeleteDialog.show(toDelete);
			logger.debug("Wybrano : " + selected);
			if(selected == DeleteDialog.DELETE){
				confirmed = true;
			}
			else if(selected == DeleteDialog.NOT_DELETE ||
					selected == DeleteDialog.CANCEL){
				return;
			}
		}
		
//		ProgressDialog pd = new ProgressDialog(this,"dialog.progress.delete.header");
		
		for (File f : toDelete) {
			
			if(f instanceof ParentDirectory)
				continue;
			
			if(threadIsAlive == false)
				break;
			
			java.io.File file = new java.io.File(f.getPath().toString());
			if(file.isFile()){
				logger.debug("Usuwam plik : " + file.getAbsolutePath());
				file.delete();
			}
			else if(file.isDirectory()){
//				try {
					logger.debug("Usuwam folder : " + file.getAbsolutePath());
					systemService.deleteDirectory(file, threadIsAlive);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
		}
		
		ApplicationContext.getInstance().refreshFocusedPanel();
	}

	public void onTermination() {
		threadIsAlive = false;
	}

}

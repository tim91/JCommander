package org.jcommander.core.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jcommander.core.ApplicationContext;
import org.jcommander.gui.dialog.DeleteDialog;
import org.jcommander.model.File;
import org.jcommander.model.ParentDirectory;

public class DeleteAction extends AbstractAction{

	static Logger logger = Logger
			.getLogger("org.jcommander.core.action.DeleteAction");

	private List<File> toDelete = new ArrayList<File>();

	public DeleteAction(List<File> files) {
		super();
		this.toDelete = files;
	}

	public void executeTask() {

		// ProgressDialog pd = new
		// ProgressDialog(this,"dialog.progress.delete.header");

		for (File f : toDelete) {

			if (f instanceof ParentDirectory)
				continue;

			if (isCancelled() == true)
				break;

			java.io.File file = new java.io.File(f.getPath().toString());
			if (file.isFile()) {
				logger.debug("Usuwam plik : " + file.getAbsolutePath());
				file.delete();
			} else if (file.isDirectory()) {
				// try {
				logger.debug("Usuwam folder : " + file.getAbsolutePath());
				systemService.deleteDirectory(file, this);
			}
		}

		ApplicationContext.getInstance().refreshFocusedPanel();
	}


	public void initDialogWindow() {

		if (toDelete.size() == 0
				|| (toDelete.size() == 1 && toDelete.get(0) instanceof ParentDirectory)) {
			/*
			 * Not selected files
			 */
			showNoFileSelectedDialog();
			execute = false;
			return;
		}

		String key = null;
		boolean many = false;
		if(toDelete.size() == 1){
			many = false;
			if(toDelete.get(0).isDirectory()){
				key = "dialog.delete.oneDirectory.info";
			}
			else{
				key = "dialog.delete.oneFile.info";
			}
		}
		else{
			many = true;
			key = "dialog.delete.manyFiles.info";
		}
		
		int selected = DeleteDialog.show(toDelete,key,many);
		logger.debug("Wybrano : " + selected);
		if (selected == DeleteDialog.DELETE) {
			execute = true;
			return;
		} 
//		else if (selected == DeleteDialog.NOT_DELETE
//				|| selected == DeleteDialog.CANCEL) {
//			execute = false;
//			return;
//		}
		else{
			execute = false;
			return;
		}
		
		
	}

}

package org.jcommander.core.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.jcommander.gui.dialog.DeleteDialog;
import org.jcommander.gui.dialog.OverrideDialog;
import org.jcommander.gui.dialog.ProgressDialog;
import org.jcommander.model.Directory;
import org.jcommander.model.File;
import org.jcommander.model.ParentDirectory;
import org.jcommander.model.Path;

public class CopyAction extends AbstractAction implements ProgressListener {

	static Logger logger = Logger.getLogger("org.jcommander.core.action.CopyAction");
	
	private List<File> toCopy;
	private Path destination;

	public CopyAction(List<File> toCopy, Path destination) {
		super();
		this.toCopy = toCopy;
		this.destination = destination;
	}
	

	
	public void executeTask() {
		
		
		for(File f : toCopy ){
			if(!(f instanceof Directory)){
				bytesToCopy += f.getSize();
			}else{
				java.io.File dir = new java.io.File(f.getPath().toString());
				bytesToCopy += systemService.getFolderSize(dir);
			}
		}
		
		java.io.File toDirectory = new java.io.File(destination.toString());
		
		boolean overrideAll = false;
		for(File f : toCopy ){
			
			if(f instanceof ParentDirectory)
				continue;
			
			if(isCancelled() == true){
				break;
			}
			
			java.io.File from = new java.io.File(f.getPath().toString());
			java.io.File to = new java.io.File(toDirectory.getAbsolutePath() + "\\" + f.getPath().getLeaf());

			if(to.exists() && overrideAll == false){
				int resp = OverrideDialog.show(to.getAbsolutePath(), from.getAbsolutePath());
				
				if(resp == OverrideDialog.OVERRIDE_ALL){
					overrideAll = true;
				}
				else if(resp == OverrideDialog.NOT_OVERRIDE){
					continue;
				}
				else if(resp == OverrideDialog.CANCEL){
					done();
					return;
				}
			}
			
			
			pd.setFrom(from.getAbsolutePath());
			pd.setTo(to.getAbsolutePath());
			
			if(f instanceof Directory){
				boolean status = systemService.copyDirectory(from,to, null,this);
				logger.debug("Status kopiowania folderu " + status);
			}else{
				boolean status = systemService.copyFile(from, to,this);
				logger.debug("Status kopiowania pliku " + status);
			}
			
			refreshTable();
		}
		
		pd.setVisible(false);
		done();
	}
	
	public void onOperationProgressChange(int bytesProcessed) {
	
		logger.debug("Przekopiowalem: " + bytesProcessed);
		copied += bytesProcessed;
		int val = (int) (copied * 100 / bytesToCopy);
		logger.debug(val);
		statusBarPos = val;
		pd.setProgress(statusBarPos.intValue());
		
	}
	
	public void initDialogWindow() {
		if(toCopy.size() == 0 || (toCopy.size() == 1 && toCopy.get(0) instanceof ParentDirectory)){
			/*
			 * Not selected files
			 */
			showNoFileSelectedDialog();
			execute = false;
			return;
		}
		
		pd = new ProgressDialog(this,"dialog.progress.copy.header");
		pd.setVisible(true);
		
		execute = true;
		return;
		
	}

	
	@Override
	protected void process(List<Integer> chunks) {
		
		for (Integer integer : chunks) {
			onOperationProgressChange(integer);
		}
	}
	
}

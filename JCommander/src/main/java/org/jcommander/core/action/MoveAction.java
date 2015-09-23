package org.jcommander.core.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jcommander.core.ApplicationContext;
import org.jcommander.core.listener.ProcessingFileChangedListener;
import org.jcommander.gui.dialog.OverrideDialog;
import org.jcommander.gui.dialog.ProgressDialog;
import org.jcommander.model.Directory;
import org.jcommander.model.File;
import org.jcommander.model.ParentDirectory;
import org.jcommander.model.Path;

public class MoveAction extends AbstractAction implements ProgressListener  {

	static Logger logger = Logger
			.getLogger("org.jcommander.core.action.MoveAction");
	
	private List<File> toMove = new ArrayList<File>();
	private Path destination;
	
	public MoveAction(List<File> toMove, Path destination) {
		super();
		this.toMove = toMove;
		this.destination = destination;
		
	}
	public void executeTask() {
		
		java.io.File toDirectory = new java.io.File(destination.toString());
		boolean overrideAllNew = false;
		
		for(File f : toMove ){
			if(!(f instanceof Directory)){
				bytesToCopy += f.getSize();
			}else{
				java.io.File dir = new java.io.File(f.getPath().toString());
				bytesToCopy += systemService.getFolderSize(dir);
				//to jest ok, nie wymaga zmian
			}
		}
		
		for(File f : toMove ){
			
			if(f instanceof ParentDirectory)
				continue;
			
			if(isCancelled() == true){
				break;
			}
			
			java.io.File from = new java.io.File(f.getPath().toString());
			java.io.File to = new java.io.File(toDirectory.getAbsolutePath() + "\\" + f.getPath().getLeaf());
			
			if(to.exists() && overrideAllNew == false){
				int resp = OverrideDialog.show(to.getAbsolutePath(), from.getAbsolutePath());
				
				if(resp == OverrideDialog.OVERRIDE_ALL){
					overrideAllNew = true;
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
				if(status == true){
						systemService.deleteDirectory(from, this);
				}
				logger.debug("Status przenoszenia folderu " + status);
			}else{
				boolean status = systemService.copyFile(from, to,this);
				if(status == true){
					status = from.delete();
				}
				logger.debug("Status przenoszenia pliku " + status);
			}
			
			refreshTable();
		}
		pd.setVisible(false);
		done();
		
	}
	
	public void initDialogWindow() {
		if(toMove.size() == 0 || (toMove.size() == 1 && toMove.get(0) instanceof ParentDirectory)){
			/*
			 * Not selected files
			 */
			showNoFileSelectedDialog();
			execute =  false;
			return;
		}
		
		pd = new ProgressDialog(this,"dialog.progress.move.header");
		pd.setVisible(true);
		
		execute = true;
		return;
		
	}

	public void onOperationProgressChange(int bytesProcessed) {
		
		logger.debug("Przekopiowalem: " + bytesProcessed);
		copied += bytesProcessed;
		int val = (int) (copied * 100 / bytesToCopy);
		logger.debug(val);
		statusBarPos = val;
		pd.setProgress(statusBarPos.intValue());
		
	}
	
	
	@Override
	protected void process(List<Integer> chunks) {
		
		for (Integer integer : chunks) {
			onOperationProgressChange(integer);
		}
	}
	
}

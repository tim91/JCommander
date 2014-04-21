package org.jcommander.core.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jcommander.model.Directory;
import org.jcommander.model.File;
import org.jcommander.model.ParentDirectory;
import org.jcommander.model.Path;

public class MoveAction extends AbstractAction implements Action {

	static Logger logger = Logger
			.getLogger("org.jcommander.core.action.MoveAction");
	
	private List<File> toMove = new ArrayList<File>();
	private Path destination;
	
	public MoveAction(List<File> toMove, Path destination) {
		super();
		this.toMove = toMove;
		this.destination = destination;
	}
	public void execute() {
		
		if(toMove.size() == 0 || (toMove.size() == 1 && toMove.get(0) instanceof ParentDirectory)){
			/*
			 * Not selected files
			 */
			return;
		}
		
		java.io.File toDirectory = new java.io.File(destination.toString());
		
		for(File f : toMove ){
			
			if(f instanceof ParentDirectory)
				continue;
			
			java.io.File from = new java.io.File(f.getPath().toString());
			java.io.File to = new java.io.File(toDirectory.getAbsolutePath() + "\\" + f.getPath().getLeaf());
			if(f instanceof Directory){
				boolean status = systemService.copyDirectory(from,to, null);
				if(status == true){
					try {
						FileUtils.deleteDirectory(from);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						status = false;
					}
				}
				logger.debug("Status przenoszenia folderu " + status);
			}else{
				boolean status = systemService.copyFile(from, to);
				if(status == true){
					status = from.delete();
				}
				logger.debug("Status przenoszenia pliku " + status);
			}
			
		}
		
	}

	
}

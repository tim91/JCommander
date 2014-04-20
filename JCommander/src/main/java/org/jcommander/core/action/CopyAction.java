package org.jcommander.core.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.jcommander.model.Directory;
import org.jcommander.model.File;
import org.jcommander.model.Path;

public class CopyAction extends AbstractAction implements Action {

	static Logger logger = Logger
			.getLogger("org.jcommander.core.action.CopyAction");
	
	private List<File> toCopy;
	private Path destination;
	
	public CopyAction(List<File> toCopy, Path destination) {
		super();
		this.toCopy = toCopy;
		this.destination = destination;
	}
	public void execute() {
		java.io.File to = new java.io.File(destination.toString());
		for(File f : toCopy ){
			java.io.File from = new java.io.File(f.getPath().toString());
			to = new java.io.File(to.getAbsolutePath() + "\\" + f.getPath().getLeaf());
			if(f instanceof Directory){
				boolean status = systemService.copyDirectory(from,to, null);
				logger.debug("Status kopiowania folderu " + status);
			}else{
				boolean status = systemService.copyFile(from, to);
				logger.debug("Status kopiowania pliku " + status);
			}
		}
		
		
	}

	
}

package org.jcommander.core.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jcommander.model.File;

public class DeleteAction extends AbstractAction implements Action {

	static Logger logger = Logger
			.getLogger("org.jcommander.core.action.DeleteAction");
	
	private List<File> files = new ArrayList<File>();
	
	public DeleteAction(List<File> files) {
		super();
		this.files = files;
	}

	public void execute() {
		
		if(files.size() == 0){
			/*
			 * Not selected files
			 */
			return;
		}
		
		for (File f : files) {
			java.io.File file = new java.io.File(f.getPath().toString());
			if(file.isFile()){
				logger.debug("Usuwam plik : " + file.getAbsolutePath());
				file.delete();
			}
			else if(file.isDirectory()){
//				try {
					logger.debug("Usuwam folder : " + file.getAbsolutePath());
//					FileUtils.deleteDirectory(file);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
		}
	}

}

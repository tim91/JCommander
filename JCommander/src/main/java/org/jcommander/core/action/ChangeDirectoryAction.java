package org.jcommander.core.action;

import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.jcommander.model.Directory;
import org.jcommander.model.DirectoryTableModel;
import org.jcommander.model.Path;
import org.jcommander.util.exception.InvalidDirectoryPathException;

public class ChangeDirectoryAction extends AbstractAction{

	static Logger logger = Logger
			.getLogger("org.jcommander.core.action.ChangeDirectoryAction");
	
	private Path from;
	private Path to;
	private DirectoryTableModel directoryTableModel;
	
	
	
	public ChangeDirectoryAction(Path from, Path to,
			DirectoryTableModel directoryTableModel) {
		super();
		this.from = from;
		this.to = to;
		this.directoryTableModel = directoryTableModel;
	}

	public void executeTask() {
	
		logger.debug("Przechoidze z folderu : " + from + " do " + to);
		
		
		try {
			Directory directoryTo = systemService.getDirectory(to);
			
			directoryTableModel.setDirectory(directoryTo);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidDirectoryPathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

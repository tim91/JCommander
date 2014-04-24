package org.jcommander.core.action;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.jcommander.model.Path;

public class OpenFileAction extends AbstractAction {

	Path path;
	
	public OpenFileAction(Path path) {
		
		this.path = path;
	}
	
	public void executeTask() {
		
		try {
			Desktop.getDesktop().open(new File(this.path.toString()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void initDialogWindow() {
		// TODO Auto-generated method stub
		execute = true;
	}

}

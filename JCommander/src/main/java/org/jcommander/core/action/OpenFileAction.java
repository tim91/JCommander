package org.jcommander.core.action;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.jcommander.model.Path;

public class OpenFileAction extends AbstractAction implements Action {

	Path path;
	
	public OpenFileAction(Path path) {
		
		this.path = path;
	}
	
	public void execute() {
		
		try {
			Desktop.getDesktop().open(new File(this.path.toString()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

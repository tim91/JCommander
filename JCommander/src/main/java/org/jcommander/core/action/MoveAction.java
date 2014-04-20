package org.jcommander.core.action;

import org.jcommander.model.File;
import org.jcommander.model.Path;

public class MoveAction extends AbstractAction implements Action {

	private File toMove;
	private Path destination;
	
	public MoveAction(File toMove, Path destination) {
		super();
		this.toMove = toMove;
		this.destination = destination;
	}
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	
}

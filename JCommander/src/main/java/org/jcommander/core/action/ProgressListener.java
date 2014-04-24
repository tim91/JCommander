package org.jcommander.core.action;

public interface ProgressListener {

	public void onOperationProgressChange(int bytesProcessed);
	
}

package org.jcommander.util.exception;

public class InvalidDirectoryPathException extends Exception {

	public InvalidDirectoryPathException() {
		super("Given path is not directory");
	}
}

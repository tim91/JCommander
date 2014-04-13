package org.jcommander.util.exception;

public class SingletonException extends Exception {
	
	public SingletonException() {
		super("Can't create two instances of this class");
	}

}

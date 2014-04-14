package org.jcommander.core.initializer;

import java.util.List;

import org.jcommander.model.Path;
import org.jcommander.util.exception.SingletonException;

/**
 * Class responsible for returning appropriate application initializer, based on configuration
 * @author TOMEK
 *
 */
public class Initializer implements JCommanderInitializer {

	public static JCommanderInitializer initializer = null;
	
	public Initializer() throws SingletonException {
		
		if(initializer != null){
			throw new SingletonException();
		}
		
		/*
		 * Get information, with initializer choose
		 */
		
		initializer = new NewJCommanderInitializer();
	}
	
	public static JCommanderInitializer getInstance(){
		if(initializer == null){
			try {
				return new Initializer();
			} catch (SingletonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		else{
			return initializer;
		}
	}
	
	public List<Path> getTabsForLeftPanel() {
		// TODO Auto-generated method stub
		return initializer.getTabsForLeftPanel();
	}

	public List<Path> getTabsForRightPanel() {
		// TODO Auto-generated method stub
		return initializer.getTabsForRightPanel();
	}

}

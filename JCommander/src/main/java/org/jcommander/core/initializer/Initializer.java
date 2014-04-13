package org.jcommander.core.initializer;

import java.util.List;

import org.jcommander.core.path.Path;

/**
 * Class responsible for returning appropriate application initializer, based on configuration
 * @author TOMEK
 *
 */
public class Initializer implements JCommanderInitializer {

	public static JCommanderInitializer initializer = null;
	
	public Initializer() {
		
		/*
		 * Get information, with initializer choose
		 */
		
		initializer = new NewJCommanderInitializer();
	}
	
	public static JCommanderInitializer getInitializer(){
		if(initializer == null){
			return new Initializer();
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

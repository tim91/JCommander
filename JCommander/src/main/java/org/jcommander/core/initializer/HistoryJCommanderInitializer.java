package org.jcommander.core.initializer;

import java.util.ArrayList;
import java.util.List;

import org.jcommander.core.path.BasePath;
import org.jcommander.core.path.Path;

/**
 * Class return information to initialize JCommander
 * @author TOMEK
 *
 */
public class HistoryJCommanderInitializer implements JCommanderInitializer {

	public List<Path> getTabsForLeftPanel() {
		// TODO Auto-generated method stub
		List<Path> arr = new ArrayList<Path>();
		
		arr.add(new BasePath("c:\\*.*"));
		arr.add(new BasePath("d:\\*.*"));
		return arr;
	}

	public List<Path> getTabsForRightPanel() {
		// TODO Auto-generated method stub
		List<Path> arr = new ArrayList<Path>();
		
		arr.add(new BasePath("c:\\*.*"));
		arr.add(new BasePath("d:\\*.*"));
		return arr;
	}

}

package org.jcommander.core.initializer;

import java.util.ArrayList;
import java.util.List;

import org.jcommander.core.path.BasePath;
import org.jcommander.core.path.Path;

public class NewJCommanderInitializer implements JCommanderInitializer {

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

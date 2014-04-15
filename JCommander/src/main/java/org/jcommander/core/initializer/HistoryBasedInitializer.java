package org.jcommander.core.initializer;

import java.util.ArrayList;
import java.util.List;

import org.jcommander.core.system.SystemService;
import org.jcommander.model.BasePath;
import org.jcommander.model.Path;

/**
 * Class return information to initialize JCommander
 * @author TOMEK
 *
 */
public class HistoryBasedInitializer implements JCommanderInitializer {

	SystemService system = SystemService.getInstance();
	
	public List<Path> getTabsForLeftPanel() {
		// TODO Auto-generated method stub
		List<Path> arr = new ArrayList<Path>();
		
		arr.add(new BasePath("c:\\*.*",system.getDeviceByLabel("c")));
		arr.add(new BasePath("d:\\*.*",system.getDeviceByLabel("d")));
		return arr;
	}

	public List<Path> getTabsForRightPanel() {
		// TODO Auto-generated method stub
		List<Path> arr = new ArrayList<Path>();
		
		arr.add(new BasePath("c:\\*.*",system.getDeviceByLabel("c")));
		arr.add(new BasePath("d:\\*.*",system.getDeviceByLabel("d")));
		return arr;
	}

}

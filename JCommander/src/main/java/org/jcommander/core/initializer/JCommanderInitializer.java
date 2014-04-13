package org.jcommander.core.initializer;

import java.util.List;

import org.jcommander.core.path.Path;

public interface JCommanderInitializer {

	List<Path> getTabsForLeftPanel();
	List<Path> getTabsForRightPanel();
}

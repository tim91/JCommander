package org.jcommander.core.initializer;

import java.util.List;

import org.jcommander.model.Path;

public interface JCommanderInitializer {

	List<Path> getTabsForLeftPanel();
	List<Path> getTabsForRightPanel();
}

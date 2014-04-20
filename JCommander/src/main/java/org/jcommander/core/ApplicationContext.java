package org.jcommander.core;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.jcommander.gui.CustomJTabbedPane;
import org.jcommander.gui.DirectoryViewJTable;

public class ApplicationContext implements DirectoryTableFocusOwnerChangeListsner {

	private static ApplicationContext applicationContext;
	
	private static Component focuesDirectoryComponent;
	
	private ApplicationContext() {
		// TODO Auto-generated constructor stub
	}
	
	public static ApplicationContext getInstance(){
		if(applicationContext == null){
			return new ApplicationContext();
		}
		else{
			return applicationContext;
		}
	}
	
	private static List<CustomJTabbedPane> tabbedPanels = new ArrayList<CustomJTabbedPane>();
	
	public void regiseterTabbedPane(CustomJTabbedPane tabbed){
		tabbedPanels.add(tabbed);
	}
	
	
	
	public Pair<Component, Component> getSelectedPanels(){
		
		Component c1 = tabbedPanels.get(0).getSelectedComponent();
		Component c2 = tabbedPanels.get(1).getSelectedComponent();
		Pair<Component, Component> p = null;
		
		if(c1 == focuesDirectoryComponent){
			p = new ImmutablePair<Component, Component>(c1,c2);
		}
		else{
			p = new ImmutablePair<Component, Component>(c2,c1);
		}
		
		return p;
	}

	public void onFocusChangeOwner(Component table) {
		// TODO Auto-generated method stub
		focuesDirectoryComponent = table;
	}
	
	
}

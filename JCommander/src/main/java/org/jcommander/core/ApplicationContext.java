package org.jcommander.core;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.jcommander.core.util.JCommanderUtils;
import org.jcommander.gui.CustomJTabbedPane;
import org.jcommander.gui.DirectoryViewJTable;
import org.jcommander.model.DirectoryTableModel;

public class ApplicationContext implements DirectoryTableFocusOwnerChangeListsner {

	private static ApplicationContext applicationContext;
	
	private static ActionListener onMoveActionListsner = null;
	
	private static ActionListener onCopyActionListsner = null;
	
	private static ActionListener onDeleteActionListsner = null;
	
	private static Component lastFocusedDirectoryComponent;
	
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
	
	/*
	 * Method refresh panel with contain table with directory view.
	 * Useful when we are copying files and we want refresh table
	 */
	public void refreshTabbedPanels(){
		for (CustomJTabbedPane c : tabbedPanels) {
			c.getSelectedComponent();
			
			DirectoryViewJTable table = (DirectoryViewJTable) JCommanderUtils.getSpecifiedComponentInContainer((Container)c.getSelectedComponent(), "dsf");
			refreshTable(table);
		}
	}
	
	public void refreshFocusedPanel(){
		DirectoryViewJTable table = (DirectoryViewJTable) JCommanderUtils.getSpecifiedComponentInContainer((Container)lastFocusedDirectoryComponent, "dsf");
		refreshTable(table);
		
	}
	
	private void refreshTable(DirectoryViewJTable table){
		DirectoryTableModel model = (DirectoryTableModel) table.getModel();
		model.refreshDataSource();
	}
	
	public Pair<Component, Component> getSelectedPanels(){
		
		Component c1 = tabbedPanels.get(0).getSelectedComponent();
		Component c2 = tabbedPanels.get(1).getSelectedComponent();
		Pair<Component, Component> p = null;
		
		if(c1 == lastFocusedDirectoryComponent){
			p = new ImmutablePair<Component, Component>(c1,c2);
		}
		else{
			p = new ImmutablePair<Component, Component>(c2,c1);
		}
		
		return p;
	}

	public Component getLastFocusedDirectoryComponent(){
		return lastFocusedDirectoryComponent;
	}
	
	public void onFocusChangeOwner(Component table) {
		// TODO Auto-generated method stub
		lastFocusedDirectoryComponent = table;
	}

	public static ActionListener getOnMoveActionListsner() {
		return onMoveActionListsner;
	}

	public static void setOnMoveActionListsner(ActionListener onMoveActionListsner) {
		ApplicationContext.onMoveActionListsner = onMoveActionListsner;
	}

	public static ActionListener getOnCopyActionListsner() {
		return onCopyActionListsner;
	}

	public static void setOnCopyActionListsner(ActionListener onCopyActionListsner) {
		ApplicationContext.onCopyActionListsner = onCopyActionListsner;
	}

	public static ActionListener getOnDeleteActionListsner() {
		return onDeleteActionListsner;
	}

	public static void setOnDeleteActionListsner(
			ActionListener onDeleteActionListsner) {
		ApplicationContext.onDeleteActionListsner = onDeleteActionListsner;
	}
	
}

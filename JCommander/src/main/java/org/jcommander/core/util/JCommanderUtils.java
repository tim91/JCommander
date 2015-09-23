package org.jcommander.core.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.KeyboardFocusManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;
import org.jcommander.gui.DirectoryViewJTable;
import org.jcommander.model.Directory;
import org.jcommander.model.DirectoryTableModel;
import org.jcommander.model.File;


public class JCommanderUtils {

	static Logger logger = Logger
			.getLogger("org.jcommander.core.util.JCommanderUtils");
	
	public static String ExtractDeviceLabel(String deviceName){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(deviceName.charAt(deviceName.indexOf("(")+1));
		
		return sb.toString();
	}
	
	public static String ExtractDeviceName(String deviceName){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(deviceName.subSequence(0, deviceName.indexOf("(")-1));
		
		return sb.toString();
	}
	
	
	public static Component getSpecifiedComponentInContainer(final Container c,String ss) {
	    Component[] comps = c.getComponents();
	    for (Component comp : comps) {
	    	logger.debug(comp.getName());
	      if (comp instanceof DirectoryViewJTable) {
	        return comp;
	      }
	      if(comp instanceof Container){
	    	  Component cc = getSpecifiedComponentInContainer((Container) comp,ss);
	    	 if(cc!= null){
	    		 return cc;
	    	 }
	      }
	      if(comp instanceof JScrollPane){
	    	  Component cc = getSpecifiedComponentInContainer((Container) comp,ss);
	    	  if(cc!= null){
		    		 return cc;
	    	  }
		  }
	    }
	    return null;
	  }
	
	
	public static Pair<List<File>,Directory> extractFromAndToLocation(Pair<Component,Component> cps){
		
		Component c1 = cps.getLeft();
		Component c2 = cps.getRight();
		
		
		List<File> from = null;
		Directory to = null;
		
		DirectoryViewJTable fromTable = null;
		DirectoryViewJTable toTable = null;
		fromTable = (DirectoryViewJTable) getSpecifiedComponentInContainer((Container) c1, "Sdf");
		toTable = (DirectoryViewJTable) getSpecifiedComponentInContainer((Container) c2, "Sdf");

		from = getSelectedRows(fromTable);
		to = getDirectoryInTable(toTable);
		
		return new ImmutablePair<List<File>, Directory>(from, to);
	}
	
	public static List<File> getSelectedRows(DirectoryViewJTable table){
		DirectoryTableModel dtm = (DirectoryTableModel) table.getModel();
		List<File> files = new ArrayList<File>();
		int [] rows = table.getSelectedRows();
		
//		if(rows.length == 0){
//			table.setRowSelectionInterval(0, 0);
//			rows = new int[1];
//			rows[0] = 0;
//		}
		
		for (int i : rows) {
			Object o = dtm.getRowComponent(i);
			files.add((File)o);
		}
		return files;
	}
	
	public static Directory getDirectoryInTable(DirectoryViewJTable table){
		DirectoryTableModel dtm = (DirectoryTableModel) table.getModel();
		return dtm.getDirectory();
	}
	
	public static void newFeature1(){
		System.out.println("New featore");
		System.out.println("Zmodyfikowany feature 1");
	}
	
	public static void newFeature2(){
		System.out.println("New featore222");
	}
}

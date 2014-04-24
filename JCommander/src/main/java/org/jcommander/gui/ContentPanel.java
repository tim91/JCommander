package org.jcommander.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;
import org.jcommander.core.ApplicationContext;
import org.jcommander.core.initializer.InitializerService;
import org.jcommander.core.initializer.JCommanderInitializer;
import org.jcommander.core.util.ColorUtils;
import org.jcommander.gui.locale.components.LocaleJLabel;
import org.jcommander.gui.locale.components.LocaleParametrizedJLabel;
import org.jcommander.model.DirectoryTableModel;
import org.jcommander.model.Path;

public class ContentPanel extends JPanel {
	
	static Logger logger = Logger.getLogger("org.jcommander.gui.ContentPanel");
	
	public enum PanelSide {
		LEFT,RIGHT
	}
	
	private PanelSide panelSide;
	
	public ContentPanel(LayoutManager arg0,PanelSide side) {
		super(arg0);
		
		this.panelSide = side;
		CustomJTabbedPane tabPanel = new CustomJTabbedPane();
        final FocusListener fl = new ComponentFocused(tabPanel);
		
		/*
		 * combobox na north i to w srodku tez borderlayout
		 */
        
		JPanel navigationPanel = new JPanel();
		BoxLayout bl = new BoxLayout(navigationPanel,BoxLayout.X_AXIS);
		
		LocaleParametrizedJLabel diskInformationLabel = new LocaleParametrizedJLabel("label.paramterized.diskInformation");
		DevicesJComboBox devicesComboBox = new DevicesJComboBox(diskInformationLabel);
		
		JPanel diskInformation = new JPanel(new BorderLayout());
		navigationPanel.add(devicesComboBox);
		navigationPanel.add(diskInformationLabel);
		
		
		JButton rootButton = new JButton("\\");
        JButton upButton = new JButton("..");
//		navigationPanel.add(diskInformation);
//		navigationPanel.add(diskInformation);
        upButton.addFocusListener(fl);
        rootButton.addFocusListener(fl);
        
		
        /*
         * Here declaration of  tab panel center panel with content of directory
         */
        
        JCommanderInitializer initializer = InitializerService.getInstance();
        
        List<Path> paths = null;
        if(this.panelSide == PanelSide.LEFT){
        	paths = initializer.getTabsForLeftPanel();
        }else{
        	paths = initializer.getTabsForRightPanel();
        }
        
        for (Path path : paths) {
        	JPanel center = new JPanel(new BorderLayout());
        	JTextField directoryPathTextField = new CustomJTextField();
        	directoryPathTextField.setBackground(ColorUtils.ACTUAL_DIRECTORY_PATH);
        	directoryPathTextField.setEditable(false);
        	directoryPathTextField.setFocusable(false);
        	
        	LocaleParametrizedJLabel directoryInformation = new LocaleParametrizedJLabel("label.paramterized.directoryInformation");
        	
        	DirectoryTableModel model = new DirectoryTableModel(path,directoryPathTextField,directoryInformation);
        	DirectoryViewJTable djt = new DirectoryViewJTable(model);
    		
        	directoryInformation.addFocusListener(fl);
    		center.add(directoryInformation,BorderLayout.SOUTH);
    		
    		JScrollPane scroll = new JScrollPane(djt);
    		djt.addFocusListener(fl);
    		scroll.addFocusListener(fl);
    		center.add(scroll,BorderLayout.CENTER);
    		
    		directoryPathTextField.addFocusListener(fl);
    		center.add(directoryPathTextField,BorderLayout.NORTH);
    		
    		
    		/**
             * Register each other before we add tab
             */
    		model.registerDirectoryChangeListener(devicesComboBox);
    		model.registerDirectoryChangeListener(tabPanel);
            tabPanel.addTab(path.getLeafInLowerCase(), center);
            
            
            
		}
        
        devicesComboBox.registerDeviceChangeListener(tabPanel);
        
        ApplicationContext.getInstance().regiseterTabbedPane(tabPanel);
		this.add(navigationPanel,BorderLayout.NORTH);
		
		this.add(tabPanel,BorderLayout.CENTER);
		tabPanel.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent arg0) {
				fl.focusGained(null);
			}
		});
		
		
	}
	
	
	private class ComponentFocused implements FocusListener
	{

		CustomJTabbedPane parent;
		
		public ComponentFocused(CustomJTabbedPane parent) {
			super();
			this.parent = parent;
		}

		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			parent.onChildFocus();
		}

		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}

package org.jcommander.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;
import org.jcommander.core.ApplicationContext;
import org.jcommander.core.initializer.InitializerService;
import org.jcommander.core.initializer.JCommanderInitializer;
import org.jcommander.core.util.ColorUtils;
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
		final GridBagLayout gridbag = new GridBagLayout();
		final GridBagConstraints cc = new GridBagConstraints();
		
		CustomJTabbedPane tabPanel = new CustomJTabbedPane();
        final FocusListener fl = new ComponentFocused(tabPanel);
		
		
		cc.weightx = 1.0;
        cc.weighty = 1.0;
        cc.fill = GridBagConstraints.BOTH;
		
		JPanel navigationPanel = new JPanel();
//		navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.X_AXIS));
		navigationPanel.setLayout(gridbag);
		
//		Device[] petStrings = { new BaseDevice("c", "1000", "392342"),new BaseDevice("d", "453", "234234") };
//		Object[] paramDisk = new Object[]{"os",179157596,363755516};
		
		LocaleParametrizedJLabel diskInformationLabel = new LocaleParametrizedJLabel("label.paramterized.diskInformation");
		DevicesJComboBox devicesComboBox = new DevicesJComboBox(diskInformationLabel); 
//		comboBox.setMinimumSize(new Dimension(0, 0));
//		comboBox.setMaximumSize(new Dimension(((String)comboBox.getSelectedItem()).length()*5, comboBox.getHeight()));
		gridbag.setConstraints(devicesComboBox, cc);
		
//		navigationPanel.add(comboBox);
		
		
		
		diskInformationLabel.setMinimumSize(new Dimension(0, 0));
		JPanel diskInformation = new JPanel(new GridLayout(1,2));
		diskInformation.add(devicesComboBox);
		diskInformation.add(diskInformationLabel);
		diskInformation.setMinimumSize(new Dimension(0, 0));
		
		gridbag.setConstraints(diskInformation, cc);
		navigationPanel.add(diskInformation);
	
		JPanel pp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		
//		navigationPanel.add(Box.createHorizontalStrut(30));
		
		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        Dimension d = separator.getPreferredSize();
        d.height = diskInformation.getPreferredSize().height;
        separator.setPreferredSize(d);
        pp.add(separator);
        JButton rootButton = new JButton("\\");
        JButton upButton = new JButton("..");
        pp.add(rootButton);
        pp.add(upButton);
        gridbag.setConstraints(pp, cc);
        navigationPanel.add(pp);
        
//        pp.addFocusListener(fl);
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
        
        
        
        
        
//        devicesComboBox.setTabbedPane(tabPanel);
        
        for (Path path : paths) {
        	JPanel center = new JPanel(new BorderLayout());
        	JTextField directoryPathTextField = new CustomJTextField();
        	directoryPathTextField.setBackground(ColorUtils.ACTUAL_DIRECTORY_PATH);
        	directoryPathTextField.setEditable(false);
        	directoryPathTextField.setFocusable(false);
        	
        	LocaleParametrizedJLabel directoryInformation = new LocaleParametrizedJLabel("label.paramterized.directoryInformation");
        	
        	DirectoryTableModel model = new DirectoryTableModel(path,directoryPathTextField);
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
//    		devicesComboBox.registerDeviceChangeListener(model);
    		
    		
    		
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

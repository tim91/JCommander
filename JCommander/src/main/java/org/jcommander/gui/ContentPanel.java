package org.jcommander.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jcommander.core.initializer.InitializerService;
import org.jcommander.core.initializer.JCommanderInitializer;
import org.jcommander.core.util.ColorUtils;
import org.jcommander.gui.locale.components.LocaleParametrizedJLabel;
import org.jcommander.model.BaseDevice;
import org.jcommander.model.Device;
import org.jcommander.model.DirectoryTableModel;
import org.jcommander.model.Path;

public class ContentPanel extends JPanel {
	
	public enum PanelSide {
		LEFT,RIGHT
	}
	
	private PanelSide panelSide;
	
	public ContentPanel(LayoutManager arg0,PanelSide side) {
		super(arg0);
		
		this.panelSide = side;
		final GridBagLayout gridbag = new GridBagLayout();
		final GridBagConstraints cc = new GridBagConstraints();
		
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
        pp.add(new JButton("\\"));
        pp.add(new JButton(".."));
        gridbag.setConstraints(pp, cc);
        navigationPanel.add(pp);
        
		
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
        CustomJTabbedPane tabPanel = new CustomJTabbedPane();
        
//        devicesComboBox.setTabbedPane(tabPanel);
        
        for (Path path : paths) {
        	JPanel center = new JPanel(new BorderLayout());
        	JTextField directoryPathTextField = new CustomJTextField();
        	directoryPathTextField.setBackground(ColorUtils.ACTUAL_DIRECTORY_PATH);
        	directoryPathTextField.setEditable(false);
        	
        	LocaleParametrizedJLabel directoryInformation = new LocaleParametrizedJLabel("label.paramterized.directoryInformation");
        	
        	DirectoryTableModel model = new DirectoryTableModel(path,directoryPathTextField);
        	DirectoryViewJTable djt = new DirectoryViewJTable(model);
    		
    		center.add(directoryInformation,BorderLayout.SOUTH);
    		center.add(new JScrollPane(djt),BorderLayout.CENTER);
    		center.add(directoryPathTextField,BorderLayout.NORTH);
    		
    		
    		/**
             * Register each other before we add tab
             */
    		model.registerDirectoryChangeListener(devicesComboBox);
    		model.registerDirectoryChangeListener(tabPanel);
//    		devicesComboBox.registerDeviceChangeListener(model);
    		
            tabPanel.addTab(path.getLeaf(), center);
            
            
            
		}
        
        devicesComboBox.registerDeviceChangeListener(tabPanel);
        
        
		this.add(navigationPanel,BorderLayout.NORTH);
		
		this.add(tabPanel,BorderLayout.CENTER);
		
	}
	
	

}

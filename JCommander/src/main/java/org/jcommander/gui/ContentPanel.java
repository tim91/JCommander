package org.jcommander.gui;

import java.awt.BorderLayout;
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
import javax.swing.SwingConstants;

import org.jcommander.core.initializer.Initializer;
import org.jcommander.core.initializer.JCommanderInitializer;
import org.jcommander.core.path.Path;
import org.jcommander.gui.locale.components.LocaleParametrizedJLabel;

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
		
		String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
		JComboBox<String> comboBox = new JComboBox<String>(petStrings); 
		comboBox.setMinimumSize(new Dimension(0, 0));
		comboBox.setMaximumSize(new Dimension(((String)comboBox.getSelectedItem()).length()*5, comboBox.getHeight()));
		gridbag.setConstraints(comboBox, cc);
		
//		navigationPanel.add(comboBox);
		
		
		Object[] paramDisk = new Object[]{"os",179157596,363755516};
		
		JLabel diskInformationLabel = new LocaleParametrizedJLabel("label.paramterized.diskInformation",paramDisk);
		diskInformationLabel.setMinimumSize(new Dimension(0, 0));
		JPanel diskInformation = new JPanel(new GridLayout(1,2));
		diskInformation.add(comboBox);
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
        
        JCommanderInitializer initializer = Initializer.getInitializer();
        
        List<Path> paths = null;
        if(this.panelSide == PanelSide.LEFT){
        	paths = initializer.getTabsForLeftPanel();
        }else{
        	paths = initializer.getTabsForRightPanel();
        }
        JTabbedPane tabPanel = new JTabbedPane();
        
        for (Path path : paths) {
        	JPanel center = new JPanel(new BorderLayout());
        	JButton header = new JButton("header");
        	
        	DirectoryJTable djt = new DirectoryJTable(path.getFullPath()); 
            
            Object[] paramDir = djt.getDirectoryInformations();
    		JLabel directoryInformation = new LocaleParametrizedJLabel("label.paramterized.directoryInformation",paramDir);
    		center.add(directoryInformation,BorderLayout.SOUTH);
    		center.add(new JScrollPane(djt),BorderLayout.CENTER);
    		center.add(header,BorderLayout.NORTH);
            tabPanel.addTab(path.getLeaf(), center);
		}
        
        
		this.add(navigationPanel,BorderLayout.NORTH);
		
		this.add(tabPanel,BorderLayout.CENTER);
		
	}
	
	

}

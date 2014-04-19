package org.jcommander.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;
import org.jcommander.core.image.ImageService;
import org.jcommander.gui.locale.LocaleContext;
import org.jcommander.gui.locale.LocaleUtils;
import org.jcommander.gui.locale.components.LocaleJMenu;
import org.jcommander.gui.locale.components.LocaleJMenuItem;
import org.jcommander.gui.locale.components.LocaleSelectorJMenuItem;

public class JCommander {
	
	static Logger logger = Logger.getLogger("org.jcommander.gui.JCommander");
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private JComponent createTopPanel(){
		return new TopPanel();
	}
	
	private JComponent createBottomPanel(){
		return new BottomPanel();
	}
	
	private JComponent createCenterPanel() {
		JPanel leftPanel = new ContentPanel(new BorderLayout(),ContentPanel.PanelSide.LEFT);
        JPanel rightPanel = new ContentPanel(new BorderLayout(),ContentPanel.PanelSide.LEFT);
        final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
        		leftPanel, rightPanel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(0.5);
        splitPane.setResizeWeight(0.5);
        splitPane.setOneTouchExpandable(false);
        return splitPane;
	}
	
	private JMenuBar createMainMenuBar() {
		final JMenu fileMenu = new LocaleJMenu("files");
		final JMenuItem closeItem = new LocaleJMenuItem("files.close");
		closeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		fileMenu.add(closeItem);
		
		final JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new CustomFlowLayout(FlowLayout.LEADING));
		menuBar.add(fileMenu);
		menuBar.add(new LocaleJMenu("mark"));
		menuBar.add(new LocaleJMenu("commands"));
		menuBar.add(new LocaleJMenu("networks"));
		menuBar.add(new LocaleJMenu("view"));
		
		
		final JMenu fileMenuConf = new LocaleJMenu("configuration");
		final JMenu langItem = new LocaleJMenu("configuration.language");
		Iterator<Locale> langs = LocaleUtils.getAvailableLanguages();
		while(langs.hasNext()){
			Locale lang = langs.next();
			final JMenuItem l = new LocaleSelectorJMenuItem(lang);
			
			l.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					Locale selectedLocale = l.getLocale();
					LocaleContext.getContext().setLocale(selectedLocale);
					
				}
			});
			
			langItem.add(l);
		}
		
		
		fileMenuConf.add(langItem);
		menuBar.add(fileMenuConf);
		menuBar.add(new LocaleJMenu("start"));
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(new LocaleJMenu("help"));

		return menuBar;
	}

	private JButton createToolButton(String text) {
		final JButton button = new JButton(text);
		button.setPreferredSize(new Dimension(42, 42));

		return button;
	}

	

	private JComponent createToolbarPanel() {
		final JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(createToolButton("a"));
		panel.add(createToolButton("b"));
		panel.add(createToolButton("c"));
		panel.add(createToolButton("d"));
		panel.add(createToolButton("e"));

		return panel;
	}

	private JComponent createMainPanel() {

		final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        panel.add(createTopPanel(),BorderLayout.NORTH);
        panel.add(createBottomPanel(),BorderLayout.SOUTH);
        panel.add(createCenterPanel(),BorderLayout.CENTER);
        
        return panel;
	}

	private static void createAndShowGUI() {
		final JCommander instance = new JCommander();

		final JFrame frame = new JFrame(instance.getClass().getSimpleName());
		frame.getContentPane().add(instance.createMainPanel());
		frame.setJMenuBar(instance.createMainMenuBar());
		
		frame.setIconImage(ImageService.getInstance().APPLICATION_ICON);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(950, 650);
		frame.setVisible(true);
	}

}

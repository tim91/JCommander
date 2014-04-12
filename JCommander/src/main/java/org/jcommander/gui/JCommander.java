package org.jcommander.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
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

import org.jcommander.gui.components.LocaleJMenu;
import org.jcommander.gui.components.LocaleJMenuItem;

public class JCommander {

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
		return new JButton("TOP");
	}
	
	private JComponent createBottomPanel(){
		return new JButton("BOTTOM");
	}
	
	private JComponent createCenterPanel() {
		JPanel leftPanel = new JPanel(new BorderLayout());
        JPanel rightPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JButton("LEFT"),BorderLayout.CENTER);
        rightPanel.add(new JButton("RIGHT"),BorderLayout.CENTER);
        final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
        		leftPanel, rightPanel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(0.5);
        splitPane.setResizeWeight(0.5);
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
		menuBar.add(fileMenu);
		menuBar.add(new LocaleJMenu("mark"));
		menuBar.add(new LocaleJMenu("commands"));
		menuBar.add(new LocaleJMenu("networks"));
		menuBar.add(new LocaleJMenu("view"));
		menuBar.add(new LocaleJMenu("configuration"));
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
		
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/icons/totalCommander.png"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);
		frame.setVisible(true);
	}

}

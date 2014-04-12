package org.jcommander.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.jcommander.gui.locale.components.LocaleJButtonForTopPanel;

public class TopPanel extends JPanel {

	
	public TopPanel() {
		super();
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JButton refreshButton = new LocaleJButtonForTopPanel("button.refresh");
		
		JButton simpleViewButton = new LocaleJButtonForTopPanel("button.simpleView");
		
		JButton allViewButton = new LocaleJButtonForTopPanel("button.allView");
		JButton thumbnailViewButton = new LocaleJButtonForTopPanel("button.thumbnailView");
		JButton showTreeViewButton = new LocaleJButtonForTopPanel("button.showTreeView");
		JButton showRecurseFilesButton = new LocaleJButtonForTopPanel("button.showRecurseFiles");
		JButton invertSelectionButton = new LocaleJButtonForTopPanel("button.invertSelection");
		JButton goBackButton = new LocaleJButtonForTopPanel("button.goBack");
		JButton goForwardButton = new LocaleJButtonForTopPanel("button.goForward");
		JButton packButton = new LocaleJButtonForTopPanel("button.pack");
		JButton unpackButton = new LocaleJButtonForTopPanel("button.unpack");
		JButton connectToFTPButton = new LocaleJButtonForTopPanel("button.connectToFTP");
		JButton newFTPButton = new LocaleJButtonForTopPanel("button.newFTP");
		JButton searchButton = new LocaleJButtonForTopPanel("button.search");
		JButton renameMultipleFilesButton = new LocaleJButtonForTopPanel("button.renameMultipleFiles");
		JButton synchronizeButton = new LocaleJButtonForTopPanel("button.synchronize");
		JButton copyNameWithFullPathButton = new LocaleJButtonForTopPanel("button.copyNameWithFullPath");
		JButton notepadButton = new LocaleJButtonForTopPanel("button.notepad");
		
		
		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        Dimension d = separator.getPreferredSize();
        d.height = refreshButton.getPreferredSize().height;
        separator.setPreferredSize(d);
		
		this.add(refreshButton);
		this.add(separator);
		
		this.add(simpleViewButton);
		this.add(allViewButton);
		this.add(thumbnailViewButton);
		this.add(showTreeViewButton);
		
		separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setPreferredSize(d);
		this.add(separator);
		this.add(showRecurseFilesButton);
		separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setPreferredSize(d);
		this.add(separator);
		this.add(invertSelectionButton);
		separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setPreferredSize(d);
		this.add(separator);
		this.add(goBackButton);
		this.add(goForwardButton);
		separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setPreferredSize(d);
		this.add(separator);
		this.add(packButton);
		this.add(unpackButton);
		separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setPreferredSize(d);
		this.add(separator);
		this.add(connectToFTPButton);
		this.add(newFTPButton);
		separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setPreferredSize(d);
		this.add(separator);
		this.add(searchButton);
		this.add(renameMultipleFilesButton);
		this.add(synchronizeButton);
		this.add(copyNameWithFullPathButton);
		separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setPreferredSize(d);
		this.add(separator);
		this.add(notepadButton);
	}
	
	
	
}

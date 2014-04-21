package org.jcommander.gui.dialog;

import javax.swing.JFrame;

import org.jcommander.core.ApplicationContext;

public class AbstractDialog {

	protected static JFrame appliactionFrame = ApplicationContext.getInstance().getMainApplicationWindow();
}

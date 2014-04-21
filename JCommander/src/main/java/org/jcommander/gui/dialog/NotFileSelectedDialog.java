package org.jcommander.gui.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jcommander.core.ApplicationContext;
import org.jcommander.gui.locale.LocaleContext;

public class NotFileSelectedDialog extends AbstractDialog {

	public static void show(){
		String info = LocaleContext.getContext().getBundle().getString("dialog.filesNotSelected");
		JOptionPane jop = new JOptionPane(info,JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = jop.createDialog(appliactionFrame,appliactionFrame.getTitle());
//		dialog.setIconImage(frame.getIconImage());
		dialog.setVisible(true);
	}
}

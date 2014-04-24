package org.jcommander.gui.dialog;

import java.util.List;

import javax.swing.JOptionPane;

import org.jcommander.gui.locale.LocaleContext;
import org.jcommander.model.File;

public class OverrideDialog extends AbstractDialog{
	
	public static int OVERRIDE = 0;
	public static int OVERRIDE_ALL = 1;
	public static int NOT_OVERRIDE = 2;
	public static int CANCEL = 3;
	
	public static int show(String prev,String newFile){
		String info = null;
		String tpl = LocaleContext.getContext().getBundle().getString("dialog.overwrite.overwrite.info");
		info = String.format(tpl, prev,newFile);
		
		Object[] options = {LocaleContext.getContext().getBundle().getString("dialog.overwrite.button"),
				LocaleContext.getContext().getBundle().getString("dialog.overwriteAll.button"),
				LocaleContext.getContext().getBundle().getString("dialog.no"),
				LocaleContext.getContext().getBundle().getString("dialog.cancel")};
		
		int n = JOptionPane.showOptionDialog(appliactionFrame,
			    info,
			    appliactionFrame.getTitle(),
			    JOptionPane.YES_NO_CANCEL_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    options,
			    options[0]);
		
		return n;
	}
}

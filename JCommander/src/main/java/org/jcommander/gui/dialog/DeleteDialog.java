package org.jcommander.gui.dialog;

import java.util.List;

import javax.swing.JOptionPane;

import org.jcommander.gui.locale.LocaleContext;
import org.jcommander.model.File;

public class DeleteDialog extends AbstractDialog {

	public static int DELETE = 0;
	public static int NOT_DELETE = 1;
	public static int CANCEL = 2;
	
	public static int show(List<File> files,String key,boolean many){
		String info = null;
		String tpl = LocaleContext.getContext().getBundle().getString(key);
		if(!many){
			info = String.format(tpl, files.get(0).getPath().getLeaf());
		}else{
			info = String.format(tpl, files.size());
			StringBuilder sb = new StringBuilder(info);
			for(int i=0;i<files.size();i++){
				if(i == 5){
					sb.append("...");
					break;
				}else{
					sb.append(files.get(i).getPath().getLeaf());
					sb.append("\n");
				}
			}
			info = sb.toString();
		}
		
		
		Object[] options = {LocaleContext.getContext().getBundle().getString("dialog.yes"),
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

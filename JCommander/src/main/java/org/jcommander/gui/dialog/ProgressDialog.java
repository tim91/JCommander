package org.jcommander.gui.dialog;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import org.apache.log4j.Logger;
import org.jcommander.core.ApplicationContext;
import org.jcommander.core.action.AbstractAction;
import org.jcommander.core.listener.ProcessingFileChangedListener;
import org.jcommander.gui.locale.components.LocaleJButtonForBottomPanel;
import org.jcommander.gui.locale.components.LocaleJLabel;
import org.jcommander.gui.locale.components.LocaleProgressJLabel;
import org.jcommander.model.File;

public class ProgressDialog extends JDialog implements
		ProcessingFileChangedListener {

	static Logger logger = Logger
			.getLogger("org.jcommander.gui.dialog.ProgressDialog");
	
	private final JProgressBar pbProgress;
	private final LocaleProgressJLabel from;
	private final LocaleProgressJLabel to;
	private final String fromBaseText;
	private final String toBaseText;
	
	public void setFrom(String fromS){
		from.setText(fromBaseText + " " + fromS);
		repaint();
	}
	
	public void setTo(String toS){
		to.setText(toBaseText + " " + toS);
		repaint();
	}
	
	public void setProgress(int val){
		pbProgress.setValue(val);
		pbProgress.repaint();
	}
	
	public ProgressDialog(final AbstractAction worker,String headerProperty) {

		super(ApplicationContext.getInstance().getMainApplicationWindow());
		JFrame parent = ApplicationContext.getInstance().getMainApplicationWindow();
		this.setTitle(parent.getTitle());
		this.setIconImage(parent.getIconImage());
		
		JPanel panel = new JPanel(new BorderLayout());

		
		JPanel headerPanel = new JPanel();
		BoxLayout bl = new BoxLayout(headerPanel,BoxLayout.X_AXIS);
		headerPanel.setLayout(bl);
		
		LocaleJLabel header = new LocaleJLabel(headerProperty);
		headerPanel.add(Box.createHorizontalGlue());
		headerPanel.add(header);
		headerPanel.add(Box.createHorizontalGlue());
		
		JPanel fromToPanel = new JPanel();
		fromToPanel.setLayout(new GridLayout(2,1));
		from = new LocaleProgressJLabel("dialog.prograss.from");
		fromBaseText = from.getText();
		from.setAlignmentX(LEFT_ALIGNMENT);
		to = new LocaleProgressJLabel("dialog.prograss.to");
		toBaseText = to.getText();
		to.setAlignmentX(LEFT_ALIGNMENT);
		
		fromToPanel.add(from);
		fromToPanel.add(to);
		
		JPanel center = new JPanel(new GridLayout(2,1));
		center.add(fromToPanel);
		pbProgress = new JProgressBar();
		
		center.add(pbProgress);
		
		LocaleJButtonForBottomPanel cancelButton = new LocaleJButtonForBottomPanel(
				"dialog.cancel");
		
		cancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				worker.cancel(true);
				setVisible(false);
			}
		});
		
		
		
		panel.add(headerPanel,BorderLayout.NORTH);
		panel.add(center,BorderLayout.CENTER);
		panel.add(cancelButton,BorderLayout.SOUTH);
		
		this.add(panel);
		this.setSize(300, 200);
		this.setLocationRelativeTo(parent);
		this.addWindowListener(new WindowListener() {
			
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowClosing(WindowEvent arg0) {
				worker.cancel(true);
			}
			
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
//		worker.addPropertyChangeListener(new PropertyChangeListener() {
//			public void propertyChange(PropertyChangeEvent evt) {
//				String name = evt.getPropertyName();
//				logger.debug(name);
//				if (name.equals("pathFrom")) {
//					String f = (String) evt.getNewValue();
//					from.setText(fromBaseText + " " + f);
//					repaint();
//				} else if (name.equals("pathTo")) {
//					String t = (String) evt.getNewValue();
//					to.setText(toBaseText + " " + t);
//					repaint();
//				}else if (name.equals("statusBarPos")) {
//					Integer v = (Integer) evt.getNewValue();
//					pbProgress.setValue(v.intValue());
//					pbProgress.repaint();
//					repaint();
//				}
//			}
//
//		});
	}

	public void onProcessingFileChanged(File file) {
		/*
		 * change information in prograss bar window
		 */
	}


}

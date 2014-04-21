package org.jcommander.gui.dialog;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import org.apache.log4j.Logger;
import org.jcommander.core.ApplicationContext;
import org.jcommander.core.action.AbstractAction;
import org.jcommander.core.listener.ProcessingFileChangedListener;
import org.jcommander.gui.locale.components.LocaleJButtonForBottomPanel;
import org.jcommander.gui.locale.components.LocaleJLabel;
import org.jcommander.model.File;

public class ProgressDialog extends JDialog implements
		ProcessingFileChangedListener {

	static Logger logger = Logger
			.getLogger("org.jcommander.gui.dialog.ProgressDialog");
	
	private JProgressBar pbProgress;

	public ProgressDialog(final AbstractAction worker,String headerProperty) {

		super(ApplicationContext.getInstance().getMainApplicationWindow());
		JFrame parent = ApplicationContext.getInstance().getMainApplicationWindow();
		this.setTitle(parent.getTitle());
		this.setIconImage(parent.getIconImage());
		
		JPanel panel = new JPanel();
		
//		BoxLayout boxl = new BoxLayout(panel,BoxLayout.PAGE_AXIS);
//		panel.setLayout(boxl);
		
		GridLayout grid = new GridLayout(3,1);
		panel.setLayout(grid);
		
		
		JPanel headerPanel = new JPanel();
		BoxLayout bl = new BoxLayout(headerPanel,BoxLayout.X_AXIS);
		headerPanel.setLayout(bl);
		
		LocaleJLabel header = new LocaleJLabel(headerProperty);
		headerPanel.add(Box.createHorizontalGlue());
		headerPanel.add(header);
		headerPanel.add(Box.createHorizontalGlue());
		
		JPanel fromToPanel = new JPanel();
		fromToPanel.setLayout(new GridLayout(2,1));
		final LocaleJLabel from = new LocaleJLabel("dialog.prograss.from");
		final String fromBaseText = from.getText();
		from.setAlignmentX(LEFT_ALIGNMENT);
		final LocaleJLabel to = new LocaleJLabel("dialog.prograss.to");
		final String toBaseText = to.getText();
		to.setAlignmentX(LEFT_ALIGNMENT);
		
		fromToPanel.add(from);
		fromToPanel.add(to);
		
		LocaleJButtonForBottomPanel cancelButton = new LocaleJButtonForBottomPanel(
				"dialog.cancel");
		
		cancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				worker.terminate();
			}
		});
		
		pbProgress = new JProgressBar();
		
		panel.add(headerPanel);
		panel.add(fromToPanel);
		panel.add(cancelButton);
		
		this.add(panel);
		this.setSize(300, 200);
		this.setLocationRelativeTo(parent);
		this.setAlwaysOnTop(true);
		worker.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				String name = evt.getPropertyName();
				logger.debug(name);
				if (name.equals("pathFrom")) {
					String f = (String) evt.getNewValue();
					from.setText(fromBaseText + " " + f);
					repaint();
				} else if (name.equals("pathTo")) {
					String t = (String) evt.getNewValue();
					to.setText(toBaseText + " " + t);
					repaint();
				}
			}

		});
	}

	public void onProcessingFileChanged(File file) {
		/*
		 * change information in prograss bar window
		 */
	}

	private class ProgressWorker extends SwingWorker<Object, Object> {

		@Override
		protected Object doInBackground() throws Exception {
			int i = 0;
			int max = 2000;

			while (i < max) {
				i += 10;
				int progress = Math.round(((float) i / (float) max) * 100f);
				setProgress(progress);
				try {
					Thread.sleep(25);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			return null;
		}
	}

}

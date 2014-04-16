package org.jcommander.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import org.apache.log4j.Logger;

public class CustomJTextField extends JTextField {

	static Logger logger = Logger
			.getLogger("org.jcommander.gui.CustomJtextField");
	
	public CustomJTextField() {
		
		super.addMouseListener(new MouseClickedListener());
	}
	
	private class MouseClickedListener implements MouseListener
	{

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			logger.debug("Klienknieto dwa razy na pasek ze sciezka aktualnego katalogu");
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}

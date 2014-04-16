package org.jcommander.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;
import javax.swing.event.ChangeListener;
import javax.swing.text.Caret;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;

import org.apache.log4j.Logger;
import org.jcommander.core.util.ColorUtils;

public class CustomJTextField extends JTextField {

	static Logger logger = Logger
			.getLogger("org.jcommander.gui.CustomJTextField");
	
	
	CustomJTextField customJTextField = null;
	
	private enum State{
		EDITABLE, NOT_EDITABLE;
	}
	
	private State state;
	
	public CustomJTextField() {
		
		super.addMouseListener(new MouseClickedListener());
		super.addKeyListener(new KeyClicked());
		
		customJTextField = this;
		state = State.NOT_EDITABLE;
	}
	
	private class KeyClicked implements KeyListener
	{

		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void keyTyped(KeyEvent e) {
			
			if(e.getKeyChar() == KeyEvent.VK_ENTER){
				if(state == State.EDITABLE){
					
					state = State.NOT_EDITABLE;
					customJTextField.setEditable(false);
					customJTextField.setBackground(ColorUtils.ACTUAL_DIRECTORY_PATH);
				}
			}
			
		}
		
	}
 	
	private class MouseClickedListener implements MouseListener
	{

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getClickCount() == 2 && state == State.NOT_EDITABLE){
				logger.debug("Klienknieto dwa razy na pasek ze sciezka aktualnego katalogu");
				
				state = State.EDITABLE;
				customJTextField.setEditable(true);
				customJTextField.setBackground(Color.white);
				customJTextField.setCaret(new DefaultCaret());
				customJTextField.setCaretColor(Color.BLACK);
		
			}
			
			
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

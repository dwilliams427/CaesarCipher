import java.awt.*;

import javax.swing.*;


public class GUI extends JFrame {

	private JLabel label;
	private JButton button; 
	private JTextField textfield; 
	
	public GUI () {
		setLayout (new FlowLayout());
		
		label = new JLabel("Enter a phrase to encrypt."); 
		add(label); 
		
		textfield = new JTextField(20); 
		add(textfield);
		
		button = new JButton("Encrypt"); 
		add(button);
	}
	
	public static void main (String args []) {
		GUI gui = new GUI(); 
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gui.setVisible(true);
		gui.setSize(600, 600); 
		
	
	}
}

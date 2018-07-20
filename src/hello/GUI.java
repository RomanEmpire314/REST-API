package hello;

import javax.swing.*;

public class GUI {
	public static void main (String [] args) {
		JFrame window = new JFrame ("Private Owner");
		JPanel content = new JPanel();
		
		
		
		
		
		
	    window.setContentPane(content);
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setLocation(100,100);
	    window.setSize(800,300);
	    window.setVisible(true);
	}
}

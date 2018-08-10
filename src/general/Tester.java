package general;
import javax.swing.*;

import others.GUI;

public class Tester {

	public static void main(String[] args) {
		/*
		JTextField username = new JTextField();
		JTextField password = new JPasswordField();
		JLabel lmao = new JLabel ("lmao");
		Object[] message = {
		    "Username:", username,
		    "Password:", password,
		    "lmao", lmao
		};

		int option = JOptionPane.showConfirmDialog(null, message, "abc", JOptionPane.OK_CANCEL_OPTION);
		System.out.println(option);
		if (option == JOptionPane.OK_OPTION) {
		    if (username.getText().equals("h") && password.getText().equals("h")) {
		        System.out.println("Login successful");
		    } else {
		        System.out.println("login failed");
		    }
		} else {
		    System.out.println("Login canceled");
		}
		*/
		
		GUI testGUI = new GUI();
		testGUI.main(null);
		
	}

}
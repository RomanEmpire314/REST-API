package hello;

import java.awt.*;
import java.awt.event.*;
import PrivateOwner.Member;

import javax.swing.*;

public class GUI {
	
	public static void main (String [] args) {
		//Build main GUI window
		JFrame window = new JFrame ("Private Owner");
		JPanel content = new JPanel();
		
		content.setLayout(new BorderLayout());
		JPanel top = new JPanel();
		JLabel topLabel = new JLabel("Choose what you want to do");
		topLabel.setFont(new Font("Impact", Font.PLAIN, 25));
		top.add(topLabel);
		
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(2,3));
		JButton view = new JButton("View"); center.add(view);
		JButton create = new JButton("Create"); center.add(create);
		JButton viewAll = new JButton("View All"); center.add(viewAll);
		JButton check = new JButton("Check"); center.add(check);
		JButton edit = new JButton("Edit"); center.add(edit);
		JButton delete = new JButton("Delete"); center.add(delete);
		JButton backB = new JButton ("Back");


		
		/**
		 * function for the "view" button
		 */
		view.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {

				window.setVisible(false);
				JFrame windowView = new JFrame ("Viewing Private Owner");
				JPanel contentView = new JPanel();
				contentView.setLayout(new FlowLayout());
				
				JLabel email = new JLabel("Enter your email"); contentView.add(email);
				JTextField emailTF = new JTextField(""); 
				emailTF.setPreferredSize(new Dimension(300,25));
				contentView.add(emailTF);
				
				JButton searchB = new JButton("Search");
				contentView.add(searchB);
				
				JTextArea resultView = new JTextArea();
		//		resultView.setPreferredSize(new Dimension (400,200));
				resultView.setEditable(false);
				resultView.setLineWrap(true);
				resultView.setOpaque(false);
				resultView.setBorder(BorderFactory.createEmptyBorder());
				contentView.add(resultView);
				contentView.add(backB);
				
				//function for the Search button
				searchB.addActionListener(new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						String idInput = emailTF.getText();
						System.out.println(idInput);
						if (idInput.equals("")) {
							resultView.setText("Input can't be empty");
						} else {
							Member newMember = new Member();
							newMember.jsonMap(newMember.getByID(idInput));
							System.out.println(newMember.toString());
							resultView.setText(newMember.toString());
						}
					}
				});
				
				backButton(backB, windowView, window);
								
				windowView.setContentPane(contentView);
				windowView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				windowView.setLocation(100,100);
				windowView.setSize(800,300);
				windowView.setVisible(true);
			}
		});
		
		
		create.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				
				window.setVisible(false);
				JFrame windowCreate = new JFrame ("Creating A Private Owner");	
				JPanel contentCreate = new JPanel();
				contentCreate.setLayout(new FlowLayout());
				JTextField emailCreate = new JTextField();
				JTextField firstCreate = new JTextField();
				JTextField lastCreate = new JTextField();
				JTextField balanceCreate = new JTextField();
				
				Object [] message = {
						"Email:", emailCreate,
						"First name:", firstCreate,
						"Last name:", lastCreate,
						"Balance", balanceCreate
				};
				int option;
				do {
					option = JOptionPane.showConfirmDialog(windowCreate, message, "Input", JOptionPane.OK_CANCEL_OPTION);
				} while (emailCreate.getText().equals("") && option == JOptionPane.OK_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						if (emailCreate.getText().equals("")) {
							JOptionPane.showMessageDialog(windowCreate, "Email can't be empty. Input again", "Error",JOptionPane.ERROR_MESSAGE);
						} else {
							Member newMember = new Member(firstCreate.getText(), lastCreate.getText(),
									Double.parseDouble(balanceCreate.getText()), emailCreate.getText());
							String result = newMember.genJson(newMember);
							newMember.create(result);
						}
					}
				
				contentCreate.add(backB);
				windowCreate.setContentPane(contentCreate);
				windowCreate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				windowCreate.setLocation(100,100);
				windowCreate.setSize(800,300);
				windowCreate.setVisible(true);
				/*
				
				
				
				
				
				*/
				
			}
		});
		
		
		
		
		//to create window
		content.add(top, BorderLayout.PAGE_START);
		content.add(center, BorderLayout.CENTER);
		
	    window.setContentPane(content);
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setLocation(100,100);
	    window.setSize(800,300);
	    window.setVisible(true);
	}
	
	
	
	public static void backButton (JButton back, JFrame ongoingFrame, JFrame home) {
		back.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				home.setVisible(true);
				ongoingFrame.dispose();
			}
		});
	}
	
	public static String htmlConvert (String input) {
		input = "<html>" + input;
		input += "</html>";
		input.replaceAll("\n", "</br>");
		return input;
	}
}

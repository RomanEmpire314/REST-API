package hello;

import java.awt.*;
import java.awt.event.*;
import PrivateOwner.Member;

import javax.swing.*;

public class GUI {
	
	private static boolean searchPressed = false;
	private static Font primaryFont = new Font ("TimeNewsRoman", Font.PLAIN, 16);
	
	public static void main (String [] args) {
		//Build main GUI window
		JFrame window = new JFrame ("Member");
		JPanel content = new JPanel();
		
		content.setLayout(new BorderLayout());
		JPanel top = new JPanel();
		JLabel topLabel = new JLabel("Choose what you want to do");
		topLabel.setFont(new Font("Impact", Font.PLAIN, 30));
		top.add(topLabel);
		
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(2,3));
		JButton find = new JButton("Find"); center.add(find);
		JButton create = new JButton("Create"); center.add(create);
		JButton viewAll = new JButton("View All"); center.add(viewAll);
		JButton check = new JButton("Check"); center.add(check);
	//	JButton edit = new JButton("Edit"); center.add(edit);
	//	JButton delete = new JButton("Delete"); center.add(delete);
		JButton backB = new JButton ("Back");


		
		/**
		 * function for the "view" button
		 */
		find.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				
				window.setVisible(false);
				JFrame windowView = new JFrame ("Viewing Member");
				JPanel contentView = new JPanel();
				contentView.setLayout(new BorderLayout());
				
				JPanel mainPanel = new JPanel();
				mainPanel.setLayout(new FlowLayout());
				contentView.add(mainPanel, BorderLayout.CENTER);
				
				JLabel email = new JLabel("Enter your email"); mainPanel.add(email);
				JTextField emailTF = new JTextField(""); 
				emailTF.setPreferredSize(new Dimension(300,25));
				mainPanel.add(emailTF);
				
				JButton searchB = new JButton("Search");
				mainPanel.add(searchB);
				
				JTextArea resultView = new JTextArea();
		//		resultView.setPreferredSize(new Dimension (400,200));
				resultView.setEditable(false);
				resultView.setLineWrap(true);
				resultView.setOpaque(false);
				resultView.setBorder(BorderFactory.createEmptyBorder());
				resultView.setPreferredSize(new Dimension(600,500));
				resultView.setFont(primaryFont);
				mainPanel.add(resultView);
				
				//function for the Search button
				searchB.addActionListener(new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						String idInput = emailTF.getText();
						Member viewedMember = new Member();
						String infoReceived; //received information under json format 
						//System.out.println(idInput);
						
						if (idInput.equals("")) {
							resultView.setText("Input can't be empty");
						} else {
							if (viewedMember.check(idInput)) {
								infoReceived = viewedMember.getByID(idInput);
								viewedMember.jsonMap(infoReceived);
					//			System.out.println(viewedMember.toString());
								resultView.setText(viewedMember.toString());
								emailTF.setText("");
								
								
								JButton edit = new JButton ("Edit"); 
								edit.setPreferredSize(new Dimension(100,60));
								edit.setAlignmentX(Component.CENTER_ALIGNMENT);
								JButton delete = new JButton ("Delete"); 
								delete.setPreferredSize(new Dimension(100,60));
								if (!searchPressed) {
									mainPanel.add(edit); mainPanel.add(delete);
								}
								
								edit.addActionListener(new ActionListener () {
									public void actionPerformed (ActionEvent e) {
										JTextField editFirst = new JTextField();
										JTextField editLast = new JTextField();
										JTextField editBalance = new JTextField();
										Object [] message = {
												"First name:", editFirst,
												"Last name:", editLast,
												"Balance", editBalance
										};																				
										int option = JOptionPane.showConfirmDialog(windowView, message, "Editor", JOptionPane.OK_CANCEL_OPTION);
										if (option == JOptionPane.OK_OPTION) {											
											String first = editFirst.getText();
											String last = editLast.getText();
											String balance = editBalance.getText();
											
											if (first.equals("")) {
												first = viewedMember.getFirstName();
											}
											if (last.equals("")) {
												last = viewedMember.getLastName();
											}
											if (balance.equals("")) {
												balance = String.valueOf(viewedMember.getBalance());
											}
											System.out.println("first: " + first + "; last: " + last + "; balance: " + balance); 
											
											Member editMember = new Member (first, last,Double.parseDouble(balance),idInput);
											editMember.edit(idInput, editMember.genJson());
										}
										
										
									}
								}); // end of edit button ActionListener
								
								
								delete.addActionListener(new ActionListener() {
									public void actionPerformed (ActionEvent e) {
										
										int option = JOptionPane.showConfirmDialog(windowView, "Are you sure you want to delete user "
												+ idInput, "Delete Member", JOptionPane.WARNING_MESSAGE);
										if (option == JOptionPane.OK_OPTION) {
											if (viewedMember.delete(viewedMember.getEmail())) {
												resultView.setText("User " + idInput + " deleted successfully");
											}
										}
										
										
									}
								});

								
							} else {
								resultView.setText("User with ID " + idInput + " doesn't exist");
							}
						}
						
						searchPressed = true;

						
					}
				});// end of seach function
				

				contentView.add(backB, BorderLayout.EAST);
				backButton(backB, windowView, window);
								
				windowView.setContentPane(contentView);
				windowView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				windowView.setLocation(100,100);
				windowView.setSize(1000,600);
				windowView.setVisible(true);
			}
		});
		
		
		create.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				
				//window.setVisible(false);
				/*JFrame windowCreate = new JFrame ("Creating A Private Owner");	
				JPanel contentCreate = new JPanel();
				contentCreate.setLayout(new FlowLayout());
				*/
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
					option = JOptionPane.showConfirmDialog(window, message, "Input", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						if (firstCreate.getText().equals("") || lastCreate.getText().equals("") ||
								balanceCreate.getText().equals("") || emailCreate.getText().equals("") ) {
							JOptionPane.showMessageDialog(window, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
						} else {
							Member newMember = new Member(firstCreate.getText(), lastCreate.getText(),
									Double.parseDouble(balanceCreate.getText()), emailCreate.getText());
							String result = newMember.genJson();
							newMember.create(result);
						}
					}
				} while ( (firstCreate.getText().equals("") || lastCreate.getText().equals("") ||
						balanceCreate.getText().equals("") || emailCreate.getText().equals("") ) && option == JOptionPane.OK_OPTION );
				
				
				/*
				contentCreate.add(backB);
				backButton(backB, window, window);
				
				
				window.setContentPane(contentCreate);
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				window.setLocation(100,100);
				window.setSize(800,300);
				window.setVisible(true);
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
		back.setPreferredSize(new Dimension (100, 100));
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

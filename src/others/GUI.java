package others;

import java.awt.*;
import java.awt.event.*;

import member.Member;

import javax.swing.*;

import general.CarAuctionRESTCall;

public class GUI {
	
	private static boolean searchPressed = false;
	private static Font primaryFont = new Font ("TimeNewsRoman", Font.PLAIN, 16);
	private static JTextArea resultView = new JTextArea();

	
	
	public static void main (String [] args) {
		//Build main GUI window
		JFrame window = new JFrame ("Member");
		JPanel content = new JPanel();
		
		content.setLayout(new BorderLayout());
		JPanel top = new JPanel();
		JLabel topLabel = new JLabel("What you want to do?");
		topLabel.setFont(new Font("Impact", Font.PLAIN, 30));
		top.add(topLabel);
		
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(0,3));
		JButton find = new JButton("Find"); center.add(find);
		JButton create = new JButton("Create"); center.add(create);
		JButton viewAll = new JButton("View All"); center.add(viewAll);
		JButton backB = new JButton ("Back");

		
		resultViewProcess();

		
		/**
		 * function for the "view" button
		 */
		find.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				
				window.setVisible(false);
				
				JFrame windowFind = new JFrame ("Viewing Member");
				JPanel contentFind = new JPanel();
				contentFind.setLayout(new BorderLayout());
				
				JPanel topFind = new JPanel();
				topFind.setLayout(new FlowLayout());
				contentFind.add(topFind, BorderLayout.PAGE_START);
				
				JPanel mainPanel = new JPanel();
				mainPanel.setLayout(new FlowLayout());
				contentFind.add(mainPanel, BorderLayout.CENTER);
				
				JLabel email = new JLabel("Enter your email"); topFind.add(email);
				JTextField emailTF = new JTextField(""); 
				emailTF.setPreferredSize(new Dimension(300,25));
				topFind.add(emailTF);
				
				JButton searchB = new JButton("Search");
				topFind.add(searchB);
				
		//		resultView.setPreferredSize(new Dimension (400,200));
				
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
										int option = JOptionPane.showConfirmDialog(windowFind, message, "Editor", JOptionPane.OK_CANCEL_OPTION);
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
										
										int option = JOptionPane.showConfirmDialog(windowFind, "Are you sure you want to delete user "
												+ idInput, "Delete Member", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
										if (option == JOptionPane.OK_OPTION) {
											if (viewedMember.delete(viewedMember.getEmail())) {
												resultView.setText("User " + idInput + " is deleted successfully");
											}
										}
										
										
									}
								});
								searchPressed = true;

								
							} else {
								resultView.setText("User with ID " + idInput + " doesn't exist");
							}
						}
						

						
					}
				});// end of seach function
				

				contentFind.add(backB, BorderLayout.EAST);
				backButton(backB, windowFind, window);
								
				windowFind.setContentPane(contentFind);
				windowFind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				windowFind.setLocation(300,200);
				windowFind.setSize(800,400);
				windowFind.setVisible(true);
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
		
		
		viewAll.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				
				JFrame vaWindow = new JFrame();
				JPanel avContent = new JPanel();
		//		avContent.setLayout(new BorderLayout());
				JTextArea viewAll = resultView;
				//avContent.add(viewAll);
				
				JScrollPane scroll = new JScrollPane(viewAll);
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				scroll.setPreferredSize(new Dimension(600,350));
				avContent.add(scroll, BorderLayout.CENTER);
				
				Member caller = new Member();
				String result = caller.get();
				viewAll.setText("All Members:\n\n" + result);
				
				avContent.add(backB);
				backButton(backB, vaWindow, window);

						
				vaWindow.setContentPane(avContent);
				vaWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				vaWindow.setLocation(300,200);
				vaWindow.setSize(800,400);
				vaWindow.setVisible(true);
			}
		});
		
		
		//to create window
		content.add(top, BorderLayout.PAGE_START);
		content.add(center, BorderLayout.CENTER);
		
	    window.setContentPane(content);
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setLocation(300,200);
	    window.setSize(500,200);
	    window.setVisible(true);
	}
	
	
	public static void resultViewProcess () {
		resultView.setEditable(false);
		resultView.setLineWrap(true);
		resultView.setOpaque(false);
		resultView.setBorder(BorderFactory.createEmptyBorder());
		resultView.setPreferredSize(new Dimension(450,450));
		resultView.setFont(primaryFont);
	}
	
	public static void backButton (JButton back, JFrame ongoingFrame, JFrame home) {
		back.setPreferredSize(new Dimension (80, 80));
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

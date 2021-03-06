package general;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import member.Member;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.DropMode;
import java.awt.Cursor;

public class UserGUI extends JFrame {

	private JPanel contentPane;
	private JButton btnEditName;
	private JButton btnDeleteAccount;
	private JButton btnLogOut;
	private JButton btnAuction;
	private JButton btnDeposit;
	private JButton btnWithdrawal;
	private String userName;
	private JTextArea infoViewTA;	
	private JTextField balanceTF;	
	Member currentMember = new Member (); //Member object during the window

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserGUI frame = new UserGUI("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Creating the frame
	 * @param ID: User identified username
	 */
	public UserGUI(String ID) {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//assign username to the user's ID
		this.userName = ID;
		
		JLabel lblNewLabel = new JLabel("Member " + ID);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		//display info
		infoViewTA = new JTextArea();
		infoViewTA.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		infoViewTA.setAlignmentX(Component.RIGHT_ALIGNMENT);
		infoViewTA.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		infoViewTA.setOpaque(false);
		infoViewTA.setFont(new Font("Constantia", Font.PLAIN, 14));
		
		//IMPORTANT Get user's info as json string and map it to the object Member
		String jsonFetched = currentMember.getByID(ID);
		currentMember.jsonMap(jsonFetched);
		//IMPORTANT
		
		infoViewTA.setText(currentMember.toString());
		infoViewTA.setEditable(false);
		infoViewTA.setLineWrap(true);
		
		JLabel lblAccount = new JLabel("Account Setting");
		lblAccount.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		btnEditName = new JButton("Edit Name");
		btnEditName.setFont(new Font("Cambria", Font.PLAIN, 13));
		btnDeleteAccount = new JButton("Delete Account");
		btnDeleteAccount.setFont(new Font("Cambria", Font.PLAIN, 13));
		btnDeposit = new JButton("Deposit");
		btnDeposit.setFont(new Font("Cambria", Font.PLAIN, 13));
		btnWithdrawal = new JButton("Withdrawal");
		btnWithdrawal.setFont(new Font("Cambria", Font.PLAIN, 13));
		btnLogOut = new JButton("Log Out");
		btnLogOut.setFont(new Font("Cambria", Font.PLAIN, 13));
		
		balanceTF = new JTextField();
		balanceTF.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		balanceTF.setText("0.0");
		balanceTF.setColumns(10);
		
		JLabel label = new JLabel("$");
		label.setFont(new Font("Cambria", Font.PLAIN, 13));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		
		btnAuction = new JButton("Auction");
		btnAuction.setFont(new Font("Cambria", Font.BOLD, 16));
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(btnDeposit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnWithdrawal, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
										.addGap(4))
									.addComponent(infoViewTA, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(92)
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(balanceTF, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAuction, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(btnLogOut, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
									.addComponent(btnDeleteAccount, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnEditName, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
								.addGap(39))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblAccount)
								.addContainerGap()))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblAccount)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(infoViewTA, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(btnEditName, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDeleteAccount, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLogOut, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnDeposit, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
								.addComponent(btnWithdrawal, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label)
								.addComponent(balanceTF, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
							.addGap(21))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAuction, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
							.addGap(23))))
		);
		contentPane.setLayout(gl_contentPane);
		methodCall();
	}
	
	/**
	 * Add buttons' ActionListener
	 */
	public void methodCall() {
		
		btnEditName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField editFirst = new JTextField();
				JTextField editLast = new JTextField();
				Object [] message = {
						"First name:", editFirst,
						"Last name:", editLast,
				};																				
				int option = JOptionPane.showConfirmDialog(null, message, "Editor", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {											
					String first = editFirst.getText();
					String last = editLast.getText();
					
					if (first.equals("")) {
						first = currentMember.getFirstName();
					}
					if (last.equals("")) {
						last = currentMember.getLastName();
					}
					
					
					currentMember.setName(first, last); //update object Members
					if (currentMember.edit(userName, currentMember.genJson()) == 200)  { //query server 
						//show success message
						JOptionPane.showMessageDialog(null, "Name changed successfully into " + currentMember.toString(),
								"Success", JOptionPane.INFORMATION_MESSAGE);
					} else {
						//failed message for any reason
						JOptionPane.showMessageDialog(null, "Name change failed", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					//update GUI
					infoViewTA.setText(currentMember.toString());
					
				} //end of JOptionPane.OK_OPTION
				
			}
		}); //end of ActionListener
		

		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete user " + currentMember.getEmail() 
				+"\nYou will lose " + currentMember.getBalance() + " credits!", "Delete Member",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if (option == JOptionPane.OK_OPTION) {
					if (currentMember.delete()) {
						//show success message
						JOptionPane.showMessageDialog(null, "User " + userName + " is deleted successfully",
								"Delete Success", JOptionPane.INFORMATION_MESSAGE);
						infoViewTA.setText("User deleted");
					} else {
						//failed message for any reason
						JOptionPane.showMessageDialog(null, "User delete failed", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} //end of JOptionPane.OK_OPTION
				
			}
		}); //end of ActionListener
		
		
		/**
		 * Can be connected to transfer money automatically to user's bank account
		 */
		btnWithdrawal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double amountWithdraw; //input amount
				
				try {
					amountWithdraw = Double.parseDouble(balanceTF.getText());					
					//confirm withdrawal
					int option = JOptionPane.showConfirmDialog(null, "You want to withdraw $" + amountWithdraw + "?",
							"Withdraw", JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						// if user chose yes
						if (amountWithdraw < 0.0) {
							throw new ArithmeticException ("Withdrawing negative money");
						} //handle negative input
						
						if (amountWithdraw > currentMember.getBalance()) {
							//handle withdrawing more than available 
							JOptionPane.showMessageDialog(null, "You can't withdraw more money than you have",
									"Error", JOptionPane.ERROR_MESSAGE);
						} else {
							//withdrawal actions
							currentMember.setBalance(currentMember.getBalance() - amountWithdraw);
							currentMember.edit(userName, currentMember.genJson());
							
							//display successful withdrawal
							JOptionPane.showMessageDialog(null,	"Withdrawing $" + amountWithdraw + 
									" successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
							infoViewTA.setText(currentMember.toString());
						}
					}
					
				} catch (NumberFormatException er) {
					JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (ArithmeticException ar) {
					JOptionPane.showMessageDialog(null, "You can't withdraw negative money", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}); //end of ActionListener
		
		
		/**
		 * Can be connected to withdraw money automatically from user's bank account
		 */
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double amountDeposit;
				try {
					amountDeposit = Double.parseDouble(balanceTF.getText());
					int option = JOptionPane.showConfirmDialog(null, "You want to deposit $" + amountDeposit + "?",
							"Deposit", JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						if (amountDeposit < 0.0) {
							//handle negative input
							throw new ArithmeticException ("Depositing negative money");
						} 
						
						//depositing actions
						currentMember.setBalance(currentMember.getBalance() + amountDeposit);
						currentMember.edit(userName, currentMember.genJson());
						JOptionPane.showMessageDialog(null, "Deposit $" + amountDeposit +
								" successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
						infoViewTA.setText(currentMember.toString());
						
					}
				} catch (NumberFormatException er) {
					JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (ArithmeticException ar) {
					JOptionPane.showMessageDialog(null, "You can't deposit negative money", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
				
		/**
		 * back to Login Menu
		 */
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GUILogin.main(null);
				dispose();
				
			}
		});
		
		/**
		 * to Member Auction menu
		 */
		btnAuction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserAuctionGUI userAuction = new UserAuctionGUI(userName);
			}
		}); //end of ActionListener
		
	}//end of method methodCall()
	
	
} //end of class

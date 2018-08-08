package hello;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import PrivateOwner.Member;

import javax.swing.JPasswordField;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUILogin {

	private JFrame frmAuctionLogin;
	private JPanel mainPanel;
	private JTextField tfUserName;
	private JPasswordField tfPassword;
	private Font mainFont = new Font ("Times New Roman", Font.PLAIN, 12);
	private JButton btnCreateAccount;
	private JButton btnLogin;
	
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
					GUILogin window = new GUILogin();
					window.frmAuctionLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUILogin() {
		initialize();
		methodCall();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainPanel = new JPanel();
		frmAuctionLogin = new JFrame();
		frmAuctionLogin.setResizable(false);
		frmAuctionLogin.setTitle("Car Auction Login");
		frmAuctionLogin.setBounds(100, 100, 400, 250);
		frmAuctionLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAuctionLogin.setContentPane(mainPanel);
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setFont(new Font("Impact", Font.PLAIN, 25));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(mainFont);
		
		tfUserName = new JTextField();
		tfUserName.setColumns(10);
		
		JLabel lblAdminPassword = new JLabel("Admin Password");
		lblAdminPassword.setFont(mainFont);
		
		tfPassword = new JPasswordField();
		
		btnLogin = new JButton("Login");
		
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		JLabel lblNoAccount = new JLabel("Don't have an account?");
		lblNoAccount.setFont(mainFont);
		
		btnCreateAccount = new JButton("Create Account");
		
		
		GroupLayout gl_mainPanel = new GroupLayout(mainPanel);
		gl_mainPanel.setHorizontalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPanel.createSequentialGroup()
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_mainPanel.createSequentialGroup()
							.addGap(139)
							.addComponent(lblWelcome, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_mainPanel.createSequentialGroup()
							.addGap(36)
							.addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_mainPanel.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addGap(51)
									.addComponent(tfUserName, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
								.addGroup(gl_mainPanel.createSequentialGroup()
									.addComponent(lblAdminPassword)
									.addGap(16)
									.addComponent(tfPassword, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
								.addGroup(gl_mainPanel.createSequentialGroup()
									.addComponent(lblNoAccount)
									.addGap(18)
									.addComponent(btnCreateAccount)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_mainPanel.setVerticalGroup(
			gl_mainPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_mainPanel.createSequentialGroup()
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_mainPanel.createSequentialGroup()
							.addGap(76)
							.addComponent(btnLogin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_mainPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblWelcome, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addGap(23)
							.addGroup(gl_mainPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(tfUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_mainPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAdminPassword)
								.addComponent(tfPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNoAccount)
						.addComponent(btnCreateAccount, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(85))
		);
		mainPanel.setLayout(gl_mainPanel);
		
	}
	
	
	private void methodCall() {
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
					option = JOptionPane.showConfirmDialog(null, message, "Input", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						if (firstCreate.getText().equals("") || lastCreate.getText().equals("") ||
								balanceCreate.getText().equals("") || emailCreate.getText().equals("") ) {
							JOptionPane.showMessageDialog(null, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
						} else {
							Member newMember = new Member(firstCreate.getText(), lastCreate.getText(),
									Double.parseDouble(balanceCreate.getText()), emailCreate.getText());
							String result = newMember.genJson();
							newMember.create(result);
						}
					}
				} while ( (firstCreate.getText().equals("") || lastCreate.getText().equals("") ||
						balanceCreate.getText().equals("") || emailCreate.getText().equals("") ) && option == JOptionPane.OK_OPTION );
			}
		});
		
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = tfUserName.getText();
				String adPassword = new String(tfPassword.getPassword());
				
				//check if it is the admin
				if (adPassword.equalsIgnoreCase("thecooladmin")) {
					System.out.println("Admin window"); 
				} else {
					//when it's the user
					Member testMember = new Member();
					if (userName.equals("")) {
						JOptionPane.showMessageDialog(null, "User name can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						if (testMember.check(userName)) {
							System.out.println("User window");
							UserGUI userWindow = new UserGUI(userName);
							frmAuctionLogin.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "User " + userName + " doesn't exist!", "Error", JOptionPane.ERROR_MESSAGE);
						}
						
					}
				}
			}
		});
		
		
		
	} //end of methodCall()
	
	
} //end of class

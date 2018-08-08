package hello;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import PrivateOwner.Member;

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

public class UserGUI extends JFrame {

	private JPanel contentPane;
	Member currentMember = new Member ();
	private JButton btnEditName;
	private JButton btnDeleteAccount;
	private String userName;
	private JTextArea infoViewTA;
	
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
	 * Create the frame.
	 */
	public UserGUI(String ID) {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		this.userName = ID;
		
		JLabel lblNewLabel = new JLabel("Member" + ID);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		//display info
		infoViewTA = new JTextArea();
		infoViewTA.setFont(new Font("Georgia", Font.PLAIN, 13));
		String jsonFetched = currentMember.getByID(ID);
		currentMember.jsonMap(jsonFetched);
		infoViewTA.setText(currentMember.toString());
		infoViewTA.setEditable(false);
		infoViewTA.setLineWrap(true);
		infoViewTA.setOpaque(false);
		
		JLabel lblAccount = new JLabel("Account Setting");
		lblAccount.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		btnEditName = new JButton("Edit Name");
		
		
		btnDeleteAccount = new JButton("Delete Account");

		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(infoViewTA, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblAccount)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnDeleteAccount, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
								.addComponent(btnEditName, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
							.addGap(39))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblAccount))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(infoViewTA, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(btnEditName, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDeleteAccount, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(115, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		methodCall();
	}
	
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
					System.out.println("first: " + first + "; last: " + last); 
					
					Member editMember = new Member (first, last,currentMember.getBalance(), userName);
					editMember.edit(userName, editMember.genJson());
					infoViewTA.setText(editMember.toString());
					JOptionPane.showMessageDialog(null, "Name changed successfully into " + editMember.toString(),
							"Success", JOptionPane.INFORMATION_MESSAGE);

				}
				
			}
		});
		
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete user " + currentMember.getEmail() 
				+"\nYou will lose " + currentMember.getBalance() + " credits!", "Delete Member",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if (option == JOptionPane.OK_OPTION) {
					if (currentMember.delete(currentMember.getEmail())) {
						JOptionPane.showMessageDialog(null, "User " + userName + " is deleted successfully",
								"Delete Success", JOptionPane.INFORMATION_MESSAGE);
						infoViewTA.setText("User deleted");
					}
				}
				
				
			}
		});
		
		
		
	}
	
	
}

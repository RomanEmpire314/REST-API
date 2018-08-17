package general;

import java.awt.EventQueue;

import javax.swing.JFrame;

import member.Member;

public class UserAuctionGUI {

	private JFrame frame;
	private String userName;
	Member thisMember;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserAuctionGUI window = new UserAuctionGUI(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserAuctionGUI(String userName) {
		//userName is user email
		this.userName = userName;
		initialize();
		methodCall();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		
	}
	
	
	/**
	 * For adding ActionListeners
	 */
	private void methodCall() {
		
	}

}

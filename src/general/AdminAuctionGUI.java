package general;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Transaction.CloseBidding;

public class AdminAuctionGUI extends CarAuctionRESTCall {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminAuctionGUI window = new AdminAuctionGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println(new AdminAuctionGUI().getRecord());
		closeBidding("123");
	}

	/**
	 * Create the application.
	 */
	public AdminAuctionGUI() {
		initialize();
	}
	
	public String Url() {
		return super.Url()+file.getProperty("record");
	}
	public String getRecord() {
		return this.get().replaceAll(",", ",\n");
	}
	public static void closeBidding(String listingId) {
		CloseBidding close= new CloseBidding();
		close.setListingByID(listingId);
		close.create(close.genJson());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

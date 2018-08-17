package general;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Transaction.Offer;

public class UserAuctionGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserAuctionGUI window = new UserAuctionGUI();
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
	public UserAuctionGUI() {
		initialize();
	}
	
	public void makeOffer(String id, double bid) {
		Offer offer= new Offer();
		offer.setListingByID(id);
		offer.setBidPrice(bid);
		offer.create(offer.genJson());
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

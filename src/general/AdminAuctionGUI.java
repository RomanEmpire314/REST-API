package general;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Vehicle.VehicleListing;
import Transaction.CloseBidding;


import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

public class AdminAuctionGUI extends CarAuctionRESTCall {

	private JFrame frmAdminAuctionMenu;
	private ArrayList<VehicleListing> listingAL;
	
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
					AdminAuctionGUI window = new AdminAuctionGUI();
					window.frmAdminAuctionMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println(new AdminAuctionGUI().getRecord());
	//	closeBidding("");
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
		frmAdminAuctionMenu = new JFrame();
		frmAdminAuctionMenu.setTitle("Admin Auction Menu");
		frmAdminAuctionMenu.setBounds(100, 100, 800, 450);
		frmAdminAuctionMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdminAuctionMenu.setLocationRelativeTo(null);
		
		JPanel contentPanel = new JPanel();
		frmAdminAuctionMenu.setContentPane(contentPanel);
		
		JList Listings = new JList();
		
		JTextArea offersTA = new JTextArea();
		offersTA.setText("Lists of offers");
		offersTA.setFont(new Font("Constantia", Font.PLAIN, 15));
		offersTA.setEditable(false);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.YELLOW);
		separator.setForeground(Color.PINK);
		
		JButton btnSearch = new JButton("Search");
		
		JButton btnCloseBidding = new JButton("Close Bidding!");
		btnCloseBidding.setFont(new Font("Impact", Font.PLAIN, 15));
		
		JScrollPane scrollPaneTransactions = new JScrollPane();
		scrollPaneTransactions.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneTransactions.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(offersTA, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnCloseBidding, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
								.addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)))
						.addComponent(Listings, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE))
					.addGap(14)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneTransactions, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
						.addComponent(scrollPaneTransactions, GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(Listings, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
									.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnCloseBidding, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
								.addComponent(offersTA, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		
		JTextArea textArea = new JTextArea();
		textArea.setText("All Transactions");
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Constantia", Font.PLAIN, 14));
		textArea.setEditable(false);
		scrollPaneTransactions.setViewportView(textArea);
		contentPanel.setLayout(gl_contentPanel);
	}
	
	/**
	 * All button's ActionListeners
	 */
	private void methodCall() {
		
	}
	
	private ArrayList<VehicleListing> mapListingObject() {
		VehicleListing currentListing = new VehicleListing();
		String jsonFetched = currentListing.get();
	    ObjectMapper mapper = new ObjectMapper();
		try {
			//map JSON string into VehicleListing object ArrayList
			ArrayList<VehicleListing> listing = mapper.readValue(jsonFetched, new TypeReference<ArrayList<VehicleListing>>(){});
			return listing;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}


	
}

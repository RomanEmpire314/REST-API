package general;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Vehicle.VehicleListing;
import transaction.CloseBidding;
import transaction.Offer;

import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminAuctionGUI extends CarAuctionRESTCall {

	private JFrame frmAdminAuctionMenu;
	private ArrayList<VehicleListing> listingsAL;
	private JList list;
	private JButton btnSearch;
	private JButton btnCloseBidding;
	private JTextArea transactionsTA;
	private JButton btnUpdate;
	private JTextArea offersTA;
	private JButton btnBack;
	
	public String Url() {
		return super.Url()+file.getProperty("record");
	}
	
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
	}

	/**
	 * Create the application.
	 */
	public AdminAuctionGUI() {
		initialize();
		methodCall();
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
		
		//IMPORTANT creating ArrayList listing
		listingsAL = mapListingObject();
		//IMPORTANT
		
		
		JPanel contentPanel = new JPanel();
		frmAdminAuctionMenu.setContentPane(contentPanel);
		
		offersTA = new JTextArea();
		offersTA.setText("Lists of offers");
		offersTA.setFont(new Font("Constantia", Font.PLAIN, 15));
		offersTA.setEditable(false);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.GRAY);
		separator.setForeground(Color.BLACK);
		
		btnSearch = new JButton("Search Offer");
		
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		btnCloseBidding = new JButton("Close Bidding!");

		btnCloseBidding.setFont(new Font("Impact", Font.PLAIN, 16));
		
		JScrollPane scrollPaneTransactions = new JScrollPane();
		scrollPaneTransactions.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneTransactions.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		JScrollPane scrollPaneListing = new JScrollPane();
		
		btnBack = new JButton("Back");
		
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		btnUpdate = new JButton("Update");

		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
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
								.addComponent(btnCloseBidding, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
									.addComponent(btnBack, GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)))
						.addComponent(scrollPaneListing, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE))
					.addGap(10)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneTransactions, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
						.addComponent(scrollPaneTransactions, GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(scrollPaneListing, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnCloseBidding, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnUpdate, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)))
								.addComponent(offersTA, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		
		//display JList
		list = new JList<String>(modeling(listingsAL));		 
		scrollPaneListing.setViewportView(list);
		//
		
		transactionsTA = new JTextArea();
		transactionsTA.setText("All Transactions");
		transactionsTA.setFont(new Font("Constantia", Font.PLAIN, 14));
		transactionsTA.setEditable(false);
		scrollPaneTransactions.setViewportView(transactionsTA);
		
		//display all transactions
		 showTransactions();
		
		contentPanel.setLayout(gl_contentPanel);
	} //end of initialize()
	
	/**
	 * All button's ActionListeners
	 */
	private void methodCall() {
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VehicleListing listing = listingsAL.get(list.getSelectedIndex());
				List<Offer> offerList = listing.getOffers();
				if (offerList.isEmpty()) {
					offersTA.setText("No offer for this listing yet!");
				} else {
					String result = "";
					for (Offer o: offerList) {
						result += o.toString();
					}
					offersTA.setText(result);
				}
				showTransactions();
			}
		}); //end of ActionListener for Search
		
		
		btnCloseBidding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		}); //end of ActionListener for Close Bidding
		
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showTransactions();
			}
		}); //end of ActionListener for Update
		
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminGUI.main(null);
				frmAdminAuctionMenu.dispose();
			}
		}); //end of ActionListener for Back button
		
	} //end of methodCall()
	
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
	
	public void showTransactions() {
		transactionsTA.setText(this.get().replaceAll(",", ",\n"));
	}
	
	
	/**
	 * convert ArrayList into DefaultListModel
	 * @param arrayList list of objects: vehicles, listings, or members
	 * @return its DefaultListModel
	 */
	private <T> DefaultListModel<String> modeling (ArrayList<T> arrayList) {
		DefaultListModel<String> model = new DefaultListModel<String>();
		for(Object o:arrayList){
			String s = o.toString();
		    model.addElement(s);
		}
		return model;
	}


	
} //end of class

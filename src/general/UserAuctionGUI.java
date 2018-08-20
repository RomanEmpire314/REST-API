package general;

import java.awt.EventQueue;

import javax.swing.JFrame;

import member.Member;
import transaction.Offer;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Vehicle.VehicleListing;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserAuctionGUI {

	private JFrame frame;
	private String userName;
	Member thisMember;
	private ArrayList<VehicleListing> listingsAL;
	private JList list;
	private JButton btnBid;
	private JTextField offerAmount;
	private JTextArea stateTA;

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
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		//IMPORTANT creating ArrayList listing and Member object
		listingsAL = mapListingObject();
		thisMember = new Member();
		userName = "abc"; //generic member
		String jsonFetched = thisMember.getByID(userName);
		thisMember.jsonMap(jsonFetched);
		//IMPORTANT
		
		JTextArea userInfoTA = new JTextArea();
		userInfoTA.setOpaque(false);
		userInfoTA.setEditable(false);
		userInfoTA.setLineWrap(true);
		userInfoTA.setFont(new Font("Constantia", Font.PLAIN, 15));
		//write user info
		userInfoTA.setText(thisMember.toString());
		
		offerAmount = new JTextField();
		offerAmount.setFont(new Font("Garamond", Font.PLAIN, 15));
		offerAmount.setColumns(10);
		
		btnBid = new JButton("Bid");
		btnBid.setFont(new Font("Impact", Font.PLAIN, 15));
		
		JLabel lblBidPrice = new JLabel("<html>Bid Price</br> (in $):</html>");
		lblBidPrice.setFont(new Font("Cambria", Font.PLAIN, 13));
		
		JScrollPane scrollPane = new JScrollPane();
		
		stateTA = new JTextArea();
		stateTA.setOpaque(false);
		stateTA.setLineWrap(true);
		stateTA.setFont(new Font("Tahoma", Font.PLAIN, 13));
		stateTA.setEditable(false);
		
		JLabel lblUserInformation = new JLabel("User Information");
		lblUserInformation.setFont(new Font("Cambria", Font.BOLD, 16));
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUserInformation)
								.addComponent(userInfoTA, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblBidPrice, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(30)
									.addComponent(btnBid, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(offerAmount, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(stateTA, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
							.addGap(11))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(lblUserInformation)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(userInfoTA, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(stateTA, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBidPrice)
								.addComponent(offerAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(7)
							.addComponent(btnBid, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		//displaying JList
		list = new JList<String>(modeling(listingsAL));
		scrollPane.setViewportView(list);
		//IMPORTANT
		
		frame.getContentPane().setLayout(groupLayout);
	
	} //end of initialize()
	
	
	/**
	 * For adding ActionListeners
	 */
	private void methodCall() {
		
		btnBid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Offer offer= new Offer();
				try {
					offer.setBidPrice(Double.parseDouble(offerAmount.getText()));
					if (offer.getBidPrice() >= thisMember.getBalance()) {
						//bid more than account
						JOptionPane.showMessageDialog(null, "You can bid more than you have!", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						//create new offer
						offer.setListingByID(listingsAL.get(list.getSelectedIndex()).getListingId());
						offer.setMemberByID(userName);
						if (offer.create(offer.genJson())) {
							//success
							String listing = offer.getListing();
							stateTA.setText("New bid for listing#" + listing.substring(listing.indexOf('#') +1, listing.length()) +
									" for $" + offer.getBidPrice() );
						} else {
							//other error
							throw new IOException();
						}
					}					
				} catch (NumberFormatException nem) {
					JOptionPane.showMessageDialog(null, "Input error!", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (ArrayIndexOutOfBoundsException aob) {
					JOptionPane.showMessageDialog(null, "Choose a valid listing", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (IOException io) {
					//Failed for any other reason
					JOptionPane.showMessageDialog(null, "Can't create new bidding", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		}); //end of ActionListener
		
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
	
	private <T> DefaultListModel<String> modeling (ArrayList<T> arrayList) {
		DefaultListModel<String> model = new DefaultListModel<String>();
		for(Object o:arrayList){
			String s = o.toString();
		    model.addElement(s);
		}
		return model;
	}
} //end of class

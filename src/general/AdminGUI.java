package general;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JSplitPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultListModel;

import java.awt.Dimension;
import javax.swing.JSpinner;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.html.ListView;

import Vehicle.*;
import member.Member;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;

public class AdminGUI {

	private JFrame frmAdmin;
	private ArrayList<Vehicle> carsArrayList;
	private ArrayList<VehicleListing> listingsArrayList;
	private ArrayList<Member> membersArrayList;
	private JList<String> vehicleJList;
	private JList<String> listingJList;
	private JList<String> memberJList;
	private JScrollPane scrollPaneVehicle;
	private JScrollPane scrollPaneListing;
	private JScrollPane scrollPaneMember;
	private JButton btnDeleteMember;
	private JButton btnEditMember;
	private JButton btnEditListing;
	private JButton btnNewVehicle;
	private JButton btnDeleteVehicle;
	private JButton btnAddListing;
	private JButton btnNewMember;
	private JButton btnDeleteListing;
	private JButton btnAuction;
	private JButton btnBack;

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
					AdminGUI window = new AdminGUI();
					window.frmAdmin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public AdminGUI() {
		initialize();
		methodCall();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JPanel contentPane = new JPanel();
		
		frmAdmin = new JFrame();
		frmAdmin.setResizable(false);
		frmAdmin.setTitle("Admin Window");
		frmAdmin.setBounds(100, 100, 663, 400);
		frmAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdmin.setLocationRelativeTo(null);
		frmAdmin.setContentPane(contentPane);
		
		//IMPORTANT: get JSON data from server, mapping them into ArrayLists of objects
		carsArrayList = mapVehicleObject();
		listingsArrayList = mapListingObject();
		membersArrayList = mapMemberObject();
		//IMPORTANT
		
		scrollPaneVehicle = new JScrollPane();
		scrollPaneVehicle.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JLabel lblVehicle = new JLabel("Vehicle");
		lblVehicle.setFont(new Font("Impact", Font.PLAIN, 17));
		
		scrollPaneListing = new JScrollPane();
		scrollPaneListing.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		scrollPaneMember = new JScrollPane();
		scrollPaneMember.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JLabel lblListed = new JLabel("Listed Vehicle");
		lblListed.setFont(new Font("Impact", Font.PLAIN, 17));
		
		JLabel lblMember = new JLabel("Member");
		lblMember.setFont(new Font("Impact", Font.PLAIN, 17));
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		
		JSeparator separator_1 = new JSeparator();
		
		btnAuction = new JButton("AUCTION");
		btnAuction.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnNewVehicle = new JButton("Create New");
		btnDeleteVehicle = new JButton("Delete");
		btnAddListing = new JButton("Add to Listing");
		btnDeleteListing = new JButton("Remove Listing");
		btnNewMember = new JButton("Create New");
		btnEditMember = new JButton("Edit");
		btnDeleteMember = new JButton("Delete");
		btnEditListing = new JButton("Edit");
		btnBack = new JButton("Back");
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(73)
					.addComponent(lblVehicle)
					.addPreferredGap(ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
					.addComponent(lblListed)
					.addGap(125)
					.addComponent(lblMember)
					.addGap(97))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPaneVehicle, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addComponent(btnNewVehicle, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnDeleteVehicle, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(45)
							.addComponent(btnAddListing)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnEditListing, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDeleteListing, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
						.addComponent(btnAuction, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPaneListing, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPaneMember, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnDeleteMember, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewMember, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnEditMember, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBack, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblVehicle)
									.addComponent(lblMember, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblListed, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(scrollPaneVehicle, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
										.addComponent(scrollPaneListing, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
									.addComponent(scrollPaneMember, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
													.addGroup(gl_contentPane.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
														.addComponent(btnAuction)
														.addPreferredGap(ComponentPlacement.RELATED))
													.addGroup(gl_contentPane.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(btnBack)
														.addPreferredGap(ComponentPlacement.RELATED)))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(btnAddListing)
													.addPreferredGap(ComponentPlacement.RELATED)))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnNewMember)
												.addPreferredGap(ComponentPlacement.RELATED)))
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
											.addComponent(btnEditListing)
											.addComponent(btnDeleteListing)
											.addComponent(btnDeleteMember)
											.addComponent(btnDeleteVehicle)
											.addComponent(btnNewVehicle)))
									.addComponent(btnEditMember))
								.addGap(14))
							.addComponent(separator_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addGap(30))))
		);
		
		//displaying the JLists
		vehicleJList = new JList<String>(modeling(carsArrayList));
		vehicleJList.setFont(new Font("Cambria", Font.PLAIN, 13));
		vehicleJList.setSelectedIndex(0);
		scrollPaneVehicle.setViewportView(vehicleJList);
		
		memberJList = new JList<String>(modeling(membersArrayList));
		memberJList.setFont(new Font("Cambria", Font.PLAIN, 13));
		memberJList.setSelectedIndex(0);
		scrollPaneMember.setViewportView(memberJList);
		
		listingJList = new JList<String>(modeling(listingsArrayList));
		listingJList.setFont(new Font("Cambria", Font.PLAIN, 13));
		listingJList.setSelectedIndex(0);
		scrollPaneListing.setViewportView(listingJList);
		
		
		contentPane.setLayout(gl_contentPane);
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
	
	/**
	 * buttons' ActionListeners
	 */
	private void methodCall() {
		
		btnNewVehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTextField vin = new JTextField();
				JTextField owner = new JTextField();
				Object [] message = {
						"Vehicle ID number:", vin,
						"Owner's email:", owner,
				};
				int option = JOptionPane.showConfirmDialog(null, message, "Editor", JOptionPane.OK_CANCEL_OPTION);
				
				if (option == JOptionPane.OK_OPTION) {
					
					//Input validation
					if (vin.getText().equals("") || owner.getText().equals("") ) {
						JOptionPane.showMessageDialog(null, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						// Input validation successful, creating new vehicle object
						Vehicle newVehicle = new Vehicle(vin.getText(), owner.getText());
						String newVehicleJSON = newVehicle.genJson();
						if (newVehicle.check(vin.getText())) { //check for existing vehicles
							JOptionPane.showMessageDialog(null, "Vehicle already existed!", "Error", JOptionPane.ERROR_MESSAGE);
						} else {
							if (newVehicle.create(newVehicleJSON)) {
								//create new member
								JOptionPane.showMessageDialog(null, "Create account successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
								carsArrayList = mapVehicleObject();
					//			lmVehicle = modeling(carsArrayList);
							} else {
								//failed for any other reason
								JOptionPane.showMessageDialog(null, "Account creation failed", "Error", JOptionPane.ERROR_MESSAGE);
							} //end of other error check
						} //end of already existing check
						
					} //end of input validation
					
				} //end of OK_OPTION
								
				//update JList
				vehicleJList = new JList<String>(modeling(carsArrayList));
				scrollPaneVehicle.setViewportView(vehicleJList);
			}
		}); //end of ActionListener

		
		btnDeleteVehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Vehicle deleteVehicle = carsArrayList.get(vehicleJList.getSelectedIndex());
				int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete vehicle " + deleteVehicle.getVin() + "?" 
				, "Remove Vehicle", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if (option == JOptionPane.OK_OPTION) {
					if (deleteVehicle.delete(deleteVehicle.getVin())) {
						JOptionPane.showMessageDialog(null, "Vehicle ID: " + deleteVehicle.getVin() + " is removed successfully",
								"Delete Success", JOptionPane.INFORMATION_MESSAGE);
						//update ArrayList
						carsArrayList = mapVehicleObject();
				//		lmVehicle = modeling(carsArrayList);
					} else {
						JOptionPane.showMessageDialog(null, "Delete failed!", "Failed", JOptionPane.ERROR_MESSAGE);
					}
					//update JList
					vehicleJList = new JList<String>(modeling(carsArrayList));
					scrollPaneVehicle.setViewportView(vehicleJList);
				}
				
				
			}
		}); //end of ActionListener
		
		
		btnAddListing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vehicle vehicle = carsArrayList.get(vehicleJList.getSelectedIndex());				
				JTextField listingId = new JTextField();
				JTextField reservePrice = new JTextField();
				JTextField description = new JTextField();

				Object [] message = {
						"Listing ID:", listingId,
						"Car's reserved price:", reservePrice,
						"Car's description", description
				};
				
				int option;
				do {
					//User input
					option = JOptionPane.showConfirmDialog(null, message, "Input", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						
						//Input validation
						if (listingId.getText().equals("") || reservePrice.getText().equals("") || description.getText().equals("") ) {
							JOptionPane.showMessageDialog(null, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
						} else {
							// Input validation successful, creating new listing object
							VehicleListing listing = new VehicleListing(vehicle, listingId.getText(), Double.parseDouble(reservePrice.getText())
									, description.getText() );
							String resultJSON = listing.genJson();
							System.out.println(resultJSON);
							if (listing.check(listing.getListingId())) { //check for existing vehicles
								JOptionPane.showMessageDialog(null, "Listing already existed!", "Error", JOptionPane.ERROR_MESSAGE);
							} else {
								if (new VehicleListing().check(listing.getVehicleID())) { //check for duplicates listings
									JOptionPane.showMessageDialog(null, "Can't create 2 listings for 1 vehicle",
											"Error", JOptionPane.ERROR_MESSAGE);
								} else {
									//create new listing
									if (listing.create(resultJSON)) {
										//show success message
										JOptionPane.showMessageDialog(null, "Create listing successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
										listingsArrayList = mapListingObject();
									} else {
										//fail for any other reason
										JOptionPane.showMessageDialog(null, "Listing creation failed", "Error", JOptionPane.ERROR_MESSAGE);
									}
								}
							}
						}
						
					} // end of OK_OPTION
					
				} while ( (listingId.getText().equals("") || reservePrice.getText().equals("") || description.getText().equals("") )
						 && option == JOptionPane.OK_OPTION );
				//refresh JList
				listingJList = new JList<String>(modeling(listingsArrayList));
				scrollPaneListing.setViewportView(listingJList);
				
			}
		}); //end of ActionListener
		
		
		btnEditListing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JTextField reservePrice = new JTextField();
				JTextField description = new JTextField();
				Object [] message = {
						"Reserve price:", reservePrice,
						"Description:", description						
				};																				
				int option = JOptionPane.showConfirmDialog(null, message, "Editor", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {			
					VehicleListing editListing = listingsArrayList.get(listingJList.getSelectedIndex());
										
					if (!reservePrice.getText().equals("")) {
						editListing.setReservePrice(Double.parseDouble(reservePrice.getText()));
					}
					if (!description.getText().equals("")) {
						editListing.setDescription(description.getText());
					}

					if (editListing.edit(editListing.getListingId(), editListing.genJson()) == 200) {
						//successful message
						JOptionPane.showMessageDialog(null, "Listing changed changed successfully into\n" + editListing.toString(),
								"Success", JOptionPane.INFORMATION_MESSAGE);
					} else {
						//failed message
						JOptionPane.showMessageDialog(null, "Edit failed!", "Failed", JOptionPane.ERROR_MESSAGE);
					}
					
					//update ArrayList
					listingsArrayList = mapListingObject();
					
					//update JList and display
					listingJList = new JList<String>(modeling(listingsArrayList));
					scrollPaneListing.setViewportView(listingJList);
					
				}
			}
		}); //end of ActionListener
		
		
		btnDeleteListing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VehicleListing deleteListing = listingsArrayList.get(listingJList.getSelectedIndex());
				int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete listing: " + deleteListing.getListingId() + "?" 
				, "Remove Vehicle Listing", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if (option == JOptionPane.OK_OPTION) {
					if (deleteListing.delete(deleteListing.getListingId())) {
						JOptionPane.showMessageDialog(null, "Vehicle listing " + deleteListing.getListingId() + " is removed successfully",
								"Delete Success", JOptionPane.INFORMATION_MESSAGE);
						//update ArrayList
						listingsArrayList = mapListingObject();
				//		lmVehicle = modeling(carsArrayList);
					} else {
						JOptionPane.showMessageDialog(null, "Delete failed!", "Failed", JOptionPane.ERROR_MESSAGE);
					}
					//update JList
					listingJList = new JList<String>(modeling(listingsArrayList));
					scrollPaneListing.setViewportView(listingJList);
				}
			}
		});
		
		/**
		 * to Admin Auction window
		 */
		btnAuction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminAuctionGUI.main(null); 
				frmAdmin.dispose();
			}
		});
		
		
		btnNewMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					//User input
					option = JOptionPane.showConfirmDialog(null, message, "Input", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						
						//Input validation
						if (firstCreate.getText().equals("") || lastCreate.getText().equals("") ||
								balanceCreate.getText().equals("") || emailCreate.getText().equals("") ) {
							JOptionPane.showMessageDialog(null, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
						} else {
							// Input validation successful, creating new member object
							Member newMember = new Member(firstCreate.getText(), lastCreate.getText(),
									Double.parseDouble(balanceCreate.getText()), emailCreate.getText());
							String result = newMember.genJson();
							if (newMember.check(newMember.getEmail())) { //check for existing vehicles
								JOptionPane.showMessageDialog(null, "Vehicle already existed!", "Error", JOptionPane.ERROR_MESSAGE);
							} else {
								//create new member
								if (newMember.create(result)) {
									JOptionPane.showMessageDialog(null, "Create account successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
									membersArrayList = mapMemberObject();
								} else {
									JOptionPane.showMessageDialog(null, "Account creation failed", "Error", JOptionPane.ERROR_MESSAGE);
								}
							}
						}
						
					} // end of OK_OPTION
					
				} while ( (firstCreate.getText().equals("") || lastCreate.getText().equals("") ||
						balanceCreate.getText().equals("") || emailCreate.getText().equals("") ) && option == JOptionPane.OK_OPTION );
				memberJList = new JList<String>(modeling(membersArrayList));
				scrollPaneMember.setViewportView(memberJList);			
			}
		}); //end of ActionListener
		
		
		btnDeleteMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Member deleteMember = membersArrayList.get(memberJList.getSelectedIndex());
				int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete user: " + deleteMember.getEmail() + "?" 
				, "Remove Member", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if (option == JOptionPane.OK_OPTION) {
					if (deleteMember.delete(deleteMember.getEmail())) {
						JOptionPane.showMessageDialog(null, "Member account " + deleteMember.getEmail() + " is removed successfully",
								"Delete Success", JOptionPane.INFORMATION_MESSAGE);
						//update ArrayList
						membersArrayList = mapMemberObject();
				//		lmVehicle = modeling(carsArrayList);
					} else {
						JOptionPane.showMessageDialog(null, "Delete failed!", "Failed", JOptionPane.ERROR_MESSAGE);
					}
					//update JList
					memberJList = new JList<String>(modeling(membersArrayList));
					scrollPaneMember.setViewportView(memberJList);
				}
			}
		}); //end of ActionListener
		
		
		btnEditMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField editFirst = new JTextField();
				JTextField editLast = new JTextField();
				JTextField editBalance = new JTextField();
				Object [] message = {
						"First name:", editFirst,
						"Last name:", editLast,
						"Balance", editBalance
				};																				
				int option = JOptionPane.showConfirmDialog(null, message, "Editor", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {			
					Member editMember = membersArrayList.get(memberJList.getSelectedIndex());
					
					String first = editFirst.getText();
					String last = editLast.getText();
					double balanceD;
					
					if (first.equals("")) {
						first = editMember.getFirstName();
					}
					if (last.equals("")) {
						last = editMember.getLastName();
					}
					if (editBalance.getText().equals("")) {
						balanceD = editMember.getBalance();
					} else {
						balanceD = Double.parseDouble(editBalance.getText());
					}
					
					editMember.setName(first, last);
					editMember.setBalance(balanceD);
					editMember.edit(editMember.getEmail(), editMember.genJson());
					
					//update ArrayList
					membersArrayList = mapMemberObject();
					
					//update JList and display
					memberJList = new JList<String>(modeling(membersArrayList));
					scrollPaneMember.setViewportView(memberJList);
					JOptionPane.showMessageDialog(null, "User changed changed successfully into\n" + editMember.toString(),
							"Success", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}); //end of ActionListener
		
		/**
		 * Back to Login window
		 */
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUILogin.main(null);
				frmAdmin.dispose();
			}
		}); //end of ActionListener
		
		
	} //end of method methodCall()
	
	
	/**
	 * map JSON data to ArrayList of Vehicle
	 * @return an ArrayList<Vehicle>
	 */
	private ArrayList<Vehicle> mapVehicleObject() {
		Vehicle currentVehicle = new Vehicle();
		String jsonFetched = currentVehicle.get();
	    ObjectMapper mapper = new ObjectMapper();
		try {

			//map JSON string into Vehicle object ArrayList
			ArrayList<Vehicle> listVehicle = mapper.readValue(jsonFetched, new TypeReference<ArrayList<Vehicle>>(){});
			return listVehicle;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * map JSON data to ArrayList of VehicleListing
	 * @return an ArrayList<VehicleListing>
	 */
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
	
	/**
	 * map JSON data to ArrayList of Member
	 * @return an ArrayList<Member>
	 */
	private ArrayList<Member> mapMemberObject() {
		Member currentListing = new Member();
		String jsonFetched = currentListing.get();
	    ObjectMapper mapper = new ObjectMapper();
		try {
			//map JSON string into VehicleListing object ArrayList
			ArrayList<Member> listMember = mapper.readValue(jsonFetched, new TypeReference<ArrayList<Member>>(){});
			return listMember;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
} //end of class

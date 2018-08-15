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
	private JButton btnNewVehicle;

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
		
		JScrollPane scrollPaneVehicle = new JScrollPane();
		scrollPaneVehicle.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneVehicle.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JLabel lblVehicle = new JLabel("Vehicle");
		lblVehicle.setFont(new Font("Impact", Font.PLAIN, 17));
		
		JScrollPane scrollPaneListing = new JScrollPane();
		scrollPaneListing.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneListing.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JScrollPane scrollPaneMember = new JScrollPane();
		scrollPaneMember.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneMember.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JLabel lblListed = new JLabel("Listed Vehicle");
		lblListed.setFont(new Font("Impact", Font.PLAIN, 17));
		
		JLabel lblMember = new JLabel("Member");
		lblMember.setFont(new Font("Impact", Font.PLAIN, 17));
		
		btnNewVehicle = new JButton("Create New");

		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		
		JButton btnList = new JButton("Add to Listing");
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		
		JButton btnNewButton = new JButton("AUCTION");
		
		JButton button = new JButton("Edit");
		
		JSeparator separator_1 = new JSeparator();
		
		JButton btnDelete_1 = new JButton("Remove Listing");
		
		JButton button_1 = new JButton("Create New");
		
		JButton button_2 = new JButton("Edit");
		
		JButton button_3 = new JButton("Delete");
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
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneVehicle, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnEdit, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewVehicle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnList, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDelete_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(scrollPaneListing, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPaneMember, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(53)
									.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(separator, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblVehicle)
										.addComponent(lblMember, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblListed, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPaneMember, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
										.addComponent(scrollPaneListing, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
										.addComponent(scrollPaneVehicle, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
													.addComponent(btnNewVehicle)
													.addComponent(btnList))
												.addComponent(btnNewButton))
											.addPreferredGap(ComponentPlacement.RELATED))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(button_1)
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
											.addComponent(btnEdit)
											.addComponent(btnDelete))
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
											.addComponent(button)
											.addComponent(btnDelete_1)
											.addComponent(button_3)
											.addComponent(button_2)))))
							.addGap(14))
						.addComponent(separator_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)))
		);
		
		memberJList = new JList<String>(modeling(membersArrayList));
		scrollPaneMember.setViewportView(memberJList);
		
		listingJList = new JList<String>(modeling(listingsArrayList));
		scrollPaneListing.setViewportView(listingJList);
		
		vehicleJList = new JList<String>(modeling(carsArrayList));
		scrollPaneVehicle.setViewportView(vehicleJList);
		

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
	
	private void methodCall() {
		btnNewVehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTextField vin = new JTextField();
				JTextField member = new JTextField();
				Object [] message = {
						"Vehicle ID number:", vin,
						"Owner's email:", member,
				};
				int option = JOptionPane.showConfirmDialog(null, message, "Editor", JOptionPane.OK_CANCEL_OPTION);

			}
		});

	}
	/*
	private void displayVehicles () {
		carsListArray = new String[carsArrayList.size()];

		for (int i = 0; i< carsListArray.length;i++) {
			carsListArray[i] = carsArrayList.get(i).toString();
		}
		
		//for testing
		for (int i = 0; i< carsListArray.length;i++) {
			System.out.println(carsListArray[i]);
		}
		//end of testing
	}
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
	
	private ArrayList<VehicleListing> mapListingObject() {
		VehicleListing currentListing = new VehicleListing();
		String jsonFetched = currentListing.get();
	    ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(jsonFetched);
			//map JSON string into VehicleListing object ArrayList
			ArrayList<VehicleListing> listing = mapper.readValue(jsonFetched, new TypeReference<ArrayList<VehicleListing>>(){});
			return listing;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
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

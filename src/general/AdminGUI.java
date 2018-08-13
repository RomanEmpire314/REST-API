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
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.JList;
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
		frmAdmin.setBounds(100, 100, 635, 400);
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
		
		JButton btnCreateNew = new JButton("Create New");
		btnCreateNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		
		JButton btnList = new JButton("Add to Listing");
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		
		JButton btnNewButton = new JButton("New button");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(73)
					.addComponent(lblVehicle)
					.addGap(125)
					.addComponent(lblListed)
					.addGap(125)
					.addComponent(lblMember, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(85))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneVehicle, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnDelete, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnCreateNew, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnList, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(scrollPaneListing, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPaneMember, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
						.addComponent(btnNewButton))
					.addGap(17))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblListed, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMember, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblVehicle))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPaneMember, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
								.addComponent(scrollPaneListing, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
								.addComponent(scrollPaneVehicle, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnCreateNew)
										.addComponent(btnEdit))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnDelete)
										.addComponent(btnList)))
								.addComponent(btnNewButton))))
					.addGap(14))
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

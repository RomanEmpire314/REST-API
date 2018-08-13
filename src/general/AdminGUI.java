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

public class AdminGUI {

	private JFrame frmAdmin;
	private ArrayList<Vehicle> carsList;
	private JList vehicleJList;
	String[] carsListArray;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		frmAdmin.setBounds(100, 100, 600, 400);
		frmAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdmin.setLocationRelativeTo(null);
		frmAdmin.setContentPane(contentPane);
		
		carsList = mapVehicleObject();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JLabel lblVehicle = new JLabel("Vehicle");
		lblVehicle.setFont(new Font("Impact", Font.PLAIN, 17));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(104)
							.addComponent(lblVehicle)))
					.addContainerGap(289, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblVehicle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(60, Short.MAX_VALUE))
		);
		
		displayVehicles();
		vehicleJList = new JList(carsListArray);
	//	String [] content = { "a","b","c"};
	//	vehicleJList = new JList(content);
		scrollPane.setViewportView(vehicleJList);
		contentPane.setLayout(gl_contentPane);
		
		
	}
	
	private void methodCall() {
		
	}
	
	private void displayVehicles () {
		carsListArray = new String[carsList.size()];

		for (int i = 0; i< carsListArray.length;i++) {
			carsListArray[i] = carsList.get(i).toString();
		}
		
		for (int i = 0; i< carsListArray.length;i++) {
			System.out.println(carsListArray[i]);
		}
	}
	
	private ArrayList<Vehicle> mapVehicleObject() {
		Vehicle currentVehicle = new Vehicle();
		String jsonFetched = currentVehicle.get();
	    ObjectMapper mapper = new ObjectMapper();
		try {
			//map JSON string into vehicle object ArrayList
			ArrayList<Vehicle> listVehicle = mapper.readValue(jsonFetched, new TypeReference<ArrayList<Vehicle>>(){});
			return listVehicle;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}

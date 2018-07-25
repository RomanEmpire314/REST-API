package hello;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GUI {
	public static void main (String [] args) {
		JFrame window = new JFrame ("Private Owner");
		JPanel content = new JPanel();
		
		content.setLayout(new BorderLayout());
		JPanel top = new JPanel();
		JLabel topLabel = new JLabel("Choose what you want to do");
		topLabel.setFont(new Font("Impact", Font.PLAIN, 25));
		top.add(topLabel);
		
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(2,3));
		JButton view = new JButton("View"); center.add(view);
		JButton create = new JButton("Create"); center.add(create);
		JButton viewAll = new JButton("View All"); center.add(viewAll);
		JButton check = new JButton("Check"); center.add(check);
		JButton edit = new JButton("Edit"); center.add(edit);
		JButton delete = new JButton("Delete"); center.add(delete);

		
		view.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				System.out.println("AAAA");

				window.setVisible(false);
				JFrame windowView = new JFrame ("Viewing Private Owner");
				JPanel contentView = new JPanel();
				contentView.setLayout(new FlowLayout());
				
				JLabel email = new JLabel("Enter your email"); contentView.add(email);
				JTextField emailTF = new JTextField(""); 
				emailTF.setPreferredSize(new Dimension(300,25));
				contentView.add(emailTF);
				
				JButton searchB = new JButton("Search");
				contentView.add(searchB);
				
				JLabel resultView = new JLabel();
				contentView.add(resultView);
				
				
				searchB.addActionListener(new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						String idInput = emailTF.getText();
						PrivateOwner owner = new PrivateOwner();
						owner.getByID(idInput);
						resultView.setText(owner.getByID(idInput));
					}
				});
				
				
			//	window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));

				
				windowView.setContentPane(contentView);
				windowView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				windowView.setLocation(100,100);
				windowView.setSize(800,300);
				windowView.setVisible(true);
			}
		});
		
		
		
		content.add(top, BorderLayout.PAGE_START);
		content.add(center, BorderLayout.CENTER);
		
	    window.setContentPane(content);
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setLocation(100,100);
	    window.setSize(800,300);
	    window.setVisible(true);
	}
}

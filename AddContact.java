import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class AddContact extends PhonePresetWithNoWallpaper implements ActionListener {

	
	/**
	 * 
	 */

	public AddContact(String Param) {
		JLabel headText = new JLabel("Add New Contact");
		headText.setFont(new Font("Tahoma", Font.BOLD, 24));
		headText.setForeground(Color.BLUE);
		headText.setBackground(Color.WHITE);
		headText.setBounds(28, 63, 220, 40);
		add(headText);
		
		ImageIcon imgContactImage = new ImageIcon(getClass().getResource("./img/contactImage.png"));
		JLabel contactImage = new JLabel(imgContactImage);
		contactImage.setBounds(53, 114, 177, 143);
		add(contactImage);
		
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(58, 319, 55, 23);
		add(lblName);
		
		JTextField nameTextField = new JTextField();
		nameTextField.setBounds(58, 339, 172, 20);
		add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblContact = new JLabel("Contact");
		lblContact.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContact.setBounds(58, 381, 55, 23);
		add(lblContact);
		
		JTextField numTextField = new JTextField();
		numTextField.setColumns(10);
		numTextField.setBounds(58, 400, 172, 20);
		add(numTextField);
		numTextField.setText(Param);
		
		ImageIcon imgDesign = new ImageIcon(getClass().getResource("./img/design.png"));
		JLabel design = new JLabel(imgDesign);
		design.setBounds(24, 175, 237, 92);
		add(design);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(141, 292, 89, 23);
		add(btnSave);
		
		JButton btnDiscard = new JButton("Discard");
		btnDiscard.setBounds(28, 292, 89, 23);
		add(btnDiscard);
		
		
		ImageIcon imgOptions = new ImageIcon(getClass().getResource("./img/options.png"));
		JButton optionsBtn = new JButton(imgOptions);
		optionsBtn.setBorder(null);
		optionsBtn.setBounds(53, 515, 39, 30);
		add(optionsBtn);
		
		ImageIcon imgHome = new ImageIcon(getClass().getResource("./img/homeButton.png"));
		JButton homeBtn = new JButton(imgHome);
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 HomeScreen panel = new HomeScreen();
		           NewWindowFrame frame = new NewWindowFrame(panel);
		           frame.setVisible(true);
			}
		});
		homeBtn.setBorder(null);
		homeBtn.setBounds(128, 515, 22, 26);
		add(homeBtn);
		
		ImageIcon imgBack = new ImageIcon(getClass().getResource("./img/back.png"));
		JButton backBtn = new JButton(imgBack);
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PhoneDial panel = new PhoneDial();
		         NewWindowFrame frame = new NewWindowFrame(panel);
		         frame.setVisible(true);
		          
			}
		});
		backBtn.setBorder(null);
		backBtn.setBounds(198, 515, 28, 26);
		add(backBtn);
	}
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 JFrame app = new JFrame();
				     String Param = null;
					AddContact interFace = new AddContact( Param);

				        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				        app.setSize(287, 590);
				        app.setVisible(true);
				        app.setResizable(false);
				        app.setLocationRelativeTo(null);
				        app.getContentPane().add(interFace);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
	//	numTextField.getText();
	}
}

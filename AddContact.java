import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class AddContact extends PhonePresetWithNoWallpaper implements ActionListener {	
	private static final Font font = new Font("Raleway", Font.PLAIN, 14);
	private JTextField nameTextField = new JTextField();
	private JTextField numTextField = new JTextField();

	private JButton homeBtn = new JButton("");

	JButton btnDiscard = new JButton("Discard");
	JButton btnSave = new JButton("Save");


	public AddContact(String Param) {
		JLabel headText = new JLabel("New Contact");
		headText.setFont(new Font("Raleway", Font.BOLD, 24));
		headText.setForeground(Color.BLUE);
		headText.setBounds(28, 63, 220, 40);
		headText.setHorizontalAlignment(SwingConstants.CENTER);
		add(headText);
		
		ImageIcon imgContactImage = new ImageIcon(getClass().getResource("./images/icons/camera.png"));
		JButton contactImage = new JButton(imgContactImage);
		contactImage.setBounds(50, 114, 176, 143);
		contactImage.setAlignmentX(SwingConstants.CENTER);
		contactImage.setAlignmentY(SwingConstants.CENTER);
		super.makeButtonTransparent(contactImage, false);
		add(contactImage);
		
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Raleway", Font.PLAIN, 14));
		lblName.setForeground(Color.GRAY);
		lblName.setBounds(50, 270, 55, 23);
		add(lblName);
		
		nameTextField.setFont(new Font("Raleway", Font.PLAIN, 20));
		nameTextField.setBounds(50, 295, 176, 26);
		add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblContact = new JLabel("Contact");
		lblContact.setForeground(Color.GRAY);
		lblContact.setFont(new Font("Raleway", Font.PLAIN, 14));
		lblContact.setBounds(50, 323, 55, 23);
		add(lblContact);
		
		numTextField.setColumns(10);
		numTextField.setFont(new Font("Raleway", Font.PLAIN, 20));
		numTextField.setBounds(50, 348, 176, 26);
		add(numTextField);
		numTextField.setText(Param);
	
		
		btnDiscard.setBounds(50, 400, 85, 25);
		btnDiscard.setFont(new Font("Raleway", Font.PLAIN, 14));
		btnDiscard.setBackground(new Color(0xE54141));
		btnDiscard.setForeground(Color.WHITE);
		btnDiscard.setBorder(null);
		btnDiscard.addActionListener(this);
		add(btnDiscard);
		
		btnSave.setBounds(142, 400, 85, 25);
		btnSave.setFont(new Font("Raleway", Font.PLAIN, 14));
		btnSave.setBackground(new Color(0x4AD862));
		btnSave.setForeground(Color.WHITE);
		btnSave.setBorder(null);
		btnSave.addActionListener(this);
		add(btnSave);
		
        // Home Button
		Icon homebar = new ImageIcon(getClass().getResource("./images/homebar.png"));
		homeBtn.setIcon(homebar);
        homeBtn.setForeground(Color.BLUE);
        homeBtn.setBounds(75, 532, 131, 10);
        homeBtn.setHorizontalAlignment(SwingConstants.CENTER);
        makeButtonTransparent(homeBtn, false);
        add(homeBtn);
        homeBtn.addActionListener(this);	
	
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDiscard){
			// --go to home screen
            PhoneDial panel = new PhoneDial();
            NewWindowFrame frame = new NewWindowFrame(panel);
            frame.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
            
        }
		if (e.getSource() == btnSave){
			// -- save contact
			String name = nameTextField.getText();
			String phone = numTextField.getText();
			
			if(phone != ""){
				MyDatabaseManager db = new MyDatabaseManager();
				System.out.println(db.insertContact(name, phone, ""));				
			}

		}
		if (e.getSource() == homeBtn){
			// -- go to home screen
			HomeScreen panel = new HomeScreen();
			NewWindowFrame frame = new NewWindowFrame(panel);
			frame.setVisible(true);
			((JFrame)SwingUtilities.getWindowAncestor(this)).dispose();
		}
	}

	// Make a button transparent
    public void makeButtonTransparent(JButton btn, boolean visibleBorder){
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(visibleBorder);
    }// <-- end makeButtonTransparent
}

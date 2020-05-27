import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class AddContact extends PhonePresetWithNoWallpaper implements ActionListener {	
	private static final Font labelFont = new Font("Raleway", Font.PLAIN, 14);
	private JTextField nameTextField = new JTextField();
	private JTextField numTextField = new JTextField();

	private JButton homeBtn = new JButton("");

	JButton btnDiscard = new JButton("Discard");
	JButton btnSave = new JButton("Save");

	public AddContact(String param) {
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
		contactImage.setFocusable(false);
		add(contactImage);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(labelFont);
		lblName.setForeground(Color.GRAY);
		lblName.setBounds(50, 270, 55, 23);
		add(lblName);
		
		nameTextField.setFont(new Font("Raleway", Font.PLAIN, 18));
		nameTextField.setBounds(50, 295, 176, 32);
		add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblContact = new JLabel("Contact");
		lblContact.setForeground(Color.GRAY);
		lblContact.setFont(labelFont);
		lblContact.setBounds(50, 329, 55, 23);
		add(lblContact);
		
		numTextField.setColumns(10);
		numTextField.setFont(new Font("Raleway", Font.PLAIN, 18));
		numTextField.setBounds(50, 354, 176, 32);
		add(numTextField);
		numTextField.setText(param);
		
		btnDiscard.setBounds(50, 406, 85, 25);
		btnDiscard.setFont(labelFont);
		btnDiscard.setBackground(new Color(0xE54141));
		btnDiscard.setForeground(Color.WHITE);
		btnDiscard.setBorder(null);
		btnDiscard.addActionListener(this);
		btnDiscard.setFocusable(false);
		add(btnDiscard);
		
		btnSave.setBounds(142, 406, 85, 25);
		btnSave.setFont(labelFont);
		btnSave.setBackground(new Color(0x4AD862));
		btnSave.setForeground(Color.WHITE);
		btnSave.setBorder(null);
		btnSave.addActionListener(this);
		btnSave.setFocusable(false);
		add(btnSave);
		
        // Home Button
		Icon homebar = new ImageIcon(getClass().getResource("./images/homebar.png"));
		homeBtn.setIcon(homebar);
        homeBtn.setForeground(Color.BLUE);
        homeBtn.setBounds(75, 532, 131, 10);
        homeBtn.setHorizontalAlignment(SwingConstants.CENTER);
		makeButtonTransparent(homeBtn, false);
		homeBtn.setFocusable(false);
        add(homeBtn);
        homeBtn.addActionListener(this);	
	
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDiscard){
			// --go to dial screen
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
				boolean status = db.insertContact(name, phone, "");

				if(status == true){
					// --go to dial screen
					ContactsPage panel = new ContactsPage();
					NewWindowFrame frame = new NewWindowFrame(panel);
					frame.setVisible(true);
					((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
				}else{
					JOptionPane.showMessageDialog(this, String.format("Contact already saved!"));
				}

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

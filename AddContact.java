import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class AddContact extends PhonePresetWithNoWallpaper implements ActionListener {	
	private static final Font font = new Font("Raleway", Font.PLAIN, 14);
    private JButton contactsBtn = new JButton("Contacts");
	private JButton backBtn = new JButton("Back");


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
		
		// contacts Button
        contactsBtn.setFont(font);
        contactsBtn.setForeground(Color.BLUE);
        contactsBtn.setBounds(170, 60, 100, 20);
        contactsBtn.setHorizontalAlignment(SwingConstants.RIGHT);
        makeButtonTransparent(contactsBtn, false);
        add(contactsBtn);
        contactsBtn.addActionListener(this);

		// back Button
        backBtn.setFont(font);
        backBtn.setForeground(Color.BLUE);
        backBtn.setBounds(7, 60, 80, 20);
        backBtn.setHorizontalAlignment(SwingConstants.LEFT);
        makeButtonTransparent(backBtn, false);
        add(backBtn);
        backBtn.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backBtn){
			// --go to home screen
            PhoneDial panel = new PhoneDial();
            NewWindowFrame frame = new NewWindowFrame(panel);
            frame.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
            
        }
        if (e.getSource() == contactsBtn){
			// -- go to contacts page
            ContactsPage panel = new ContactsPage();
            NewWindowFrame frame = new NewWindowFrame(panel);
            frame.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
        }
	}

	// Make a button transparent
    public void makeButtonTransparent(JButton btn, boolean visibleBorder){
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(visibleBorder);
    }// <-- end makeButtonTransparent
}

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class AddContact extends PhonePresetWithNoWallpaper implements ActionListener {	
	private static final Font labelFont = new Font("Raleway", Font.PLAIN, 14);
	private JTextField nameTextField = new JTextField();
	private JTextField numTextField = new JTextField();
	private String imageUrl = "";

	private JButton homeBtn = new JButton("");
	private JButton contactImage = new JButton();


	private JButton btnDiscard = new JButton("Discard");
	private JButton btnSave = new JButton("Save");

	public AddContact(String param) {
		JLabel headText = new JLabel("New Contact");
		headText.setFont(new Font("Raleway", Font.BOLD, 24));
		headText.setForeground(Color.DARK_GRAY);
		headText.setBounds(28, 63, 231, 40);
		headText.setHorizontalAlignment(SwingConstants.CENTER);
		add(headText);
		
		ImageIcon imgContactImage = new ImageIcon(getClass().getResource("./images/icons/user-m.png"));
		contactImage.setIcon(imgContactImage);
		contactImage.setBounds(50, 114, 176, 143);
		contactImage.setAlignmentX(SwingConstants.CENTER);
		contactImage.setAlignmentY(SwingConstants.CENTER);
		super.makeButtonTransparent(contactImage, false);
		contactImage.setFocusable(false);

		// Add Event Listener to image button
		contactImage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				imageUrl = uploadNewImage(contactImage, 120, 120);
			}
		});

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
		
		JLabel lblContact = new JLabel("Phone Number");
		lblContact.setForeground(Color.GRAY);
		lblContact.setFont(labelFont);
		lblContact.setBounds(50, 329, 130, 23);
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
        homeBtn.setBounds(78, 535, 131, 10);
        homeBtn.setHorizontalAlignment(SwingConstants.CENTER);
		makeButtonTransparent(homeBtn, false);
		homeBtn.setFocusable(false);
        add(homeBtn);
        homeBtn.addActionListener(this);	
	
	}

	//Upload image
	public String uploadNewImage(JButton label, int w, int h){
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png");
        file.addChoosableFileFilter(filter);
        setFileChooserFont(file.getComponents());

        int result = file.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile =file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            label.setIcon(super.resizeSelectedImage(path, w, h));
            label.setVerticalAlignment(SwingConstants.CENTER);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			
			return path;

        }else if(result == JFileChooser.CANCEL_OPTION){
            JLabel msg = new JLabel("No image was selected");
            msg.setFont(new Font("Raleway", Font.PLAIN, 14));
			JOptionPane.showMessageDialog(null, msg, "CANCELLED", JOptionPane.WARNING_MESSAGE);
			
		}
		
		return "";        
	}
	
	// handler for actions
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
			
			if(!phone.contentEquals("")){
				MyDatabaseManager db = new MyDatabaseManager();
				boolean status = db.insertContact(name, phone, imageUrl);

				if(status == true){
					// --go to dial screen
					ContactsPage panel = new ContactsPage();
					NewWindowFrame frame = new NewWindowFrame(panel);
					frame.setVisible(true);
					((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
				}else{
					JLabel msg = new JLabel("Contact already saved");
					msg.setFont(new Font("Raleway", Font.PLAIN, 14));
					JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.WARNING_MESSAGE);
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

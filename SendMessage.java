import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.security.SecureRandom;

public class SendMessage extends PhonePresetWithNoWallpaper implements ActionListener{
	
	private JTextField textField = new JTextField();// Message Typing Area
	private JButton sentMessageArea = new JButton();// Send Message Area
	private JButton homeBtn = new JButton();// Home Button
	private JButton btnSend = new JButton();// Send Button
	private String recipient;// Message Recipient
	private JTextField lblContactName = new JTextField();// Recipient label under contact image

	private SecureRandom randomNumbers = new SecureRandom(); // object for random numbers


	/**
	 * Create the panel.
	 */

	public SendMessage(String recipient) {
		setAutoscrolls(true);

		int num = 1 + randomNumbers.nextInt(6);
        String defaultImageUrl = "./images/icons/contacts/contacts-"+num+".png";

		// Contacts Icon		
		JLabel lblContactImage = new JLabel("");
		lblContactImage.setIcon(new ImageIcon(getClass().getResource(defaultImageUrl)));
		lblContactImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblContactImage.setBounds(123, 40, 40, 40);
		lblContactImage.setFocusable(false);
		add(lblContactImage);

		if(recipient.contentEquals("")){
			lblContactName.setHorizontalAlignment(SwingConstants.CENTER);
			lblContactName.setFont(new Font("Raleway", Font.PLAIN, 12));
			lblContactName.setForeground(Color.DARK_GRAY);
			lblContactName.setBounds(78, 80, 130, 18);
			add(lblContactName);
		}else {
			// Check if contact is saved and display name instead of number
			MyDatabaseManager db = new MyDatabaseManager();
			String name = db.findContact(recipient);
			String param = (name == null || (name.contentEquals(""))) ? recipient : name;
			JLabel lblContactName = new JLabel(param);
			lblContactName.setHorizontalAlignment(SwingConstants.CENTER);
			lblContactName.setFont(new Font("Raleway", Font.PLAIN, 12));
			lblContactName.setForeground(Color.DARK_GRAY);
			lblContactName.setBounds(43, 80, 200, 18);
			lblContactName.setFocusable(false);
			add(lblContactName);
		}

		// Message Area -- where the user types the message	
		textField.setAutoscrolls(false);
		textField.setActionCommand("");
		// textField.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.GRAY, null, null));
		textField.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
		textField.setOpaque(false);
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setFont(new Font("Raleway", Font.PLAIN, 16));
		textField.setBounds(29, 473, 189, 35);
		add(textField);
		textField.setColumns(10);

		// Send Button		
		btnSend.setIcon(new ImageIcon(getClass().getResource("./images/icons/send.png")));
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textMsg = textField.getText();
				sendNewMessage(textMsg);
			}
		});
		btnSend.setBounds(218, 473, 34, 34);
		btnSend.setVerticalAlignment(SwingConstants.CENTER);
		btnSend.setHorizontalAlignment(SwingConstants.CENTER);
		// btnSend.setFont(new Font("Raleway", Font.PLAIN, 12));
		btnSend.setFocusable(false);
		super.makeButtonTransparent(btnSend, false);
		add(btnSend);

		
		// Home Bar
		Icon homebar = new ImageIcon(getClass().getResource("./images/homebar.png"));
		homeBtn.setIcon(homebar);
        homeBtn.setForeground(Color.BLUE);
        homeBtn.setBounds(75, 532, 131, 10);
        homeBtn.setHorizontalAlignment(SwingConstants.CENTER);
		super.makeButtonTransparent(homeBtn, false);
		homeBtn.setFocusable(false);
        add(homeBtn);
		homeBtn.addActionListener(this);			

	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);

		// area to hold typed messages
		sentMessageArea.setHorizontalTextPosition(SwingConstants.LEFT);
		sentMessageArea.setHorizontalAlignment(SwingConstants.LEFT);
		sentMessageArea.setBounds(71, 110, 185, 250);
		sentMessageArea.setFont(new Font("Raleway", Font.PLAIN, 16));
		sentMessageArea.setBackground(Color.LIGHT_GRAY);
		sentMessageArea.setForeground(Color.BLACK);
		add(sentMessageArea);

		// draw round area to hold message typing area and send button
		g.setColor(new Color(0x4DD868));
        g.drawRoundRect(26, 470, 235, 40, 30, 30);
		
	}

	public void sendNewMessage(String msg){
		if(!msg.contentEquals("")){
			String newText = String.format("<html>%s<br>%s</br>",sentMessageArea.getText(),msg);
			sentMessageArea.setText(newText);
			textField.setText("");			
		}
	}


    // Handle ActionListener events
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == homeBtn){
            // -- go to home screen
            HomeScreen panel = new HomeScreen();
            NewWindowFrame frame = new NewWindowFrame(panel);
            frame.setVisible(true);
            ((JFrame)SwingUtilities.getWindowAncestor(this)).dispose();
        }
	}
	
}

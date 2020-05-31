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
		JLabel lblContacImage = new JLabel("");
		lblContacImage.setIcon(new ImageIcon(getClass().getResource(defaultImageUrl)));
		lblContacImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblContacImage.setBounds(123, 40, 40, 40);
		add(lblContacImage);

		if(recipient.contentEquals("")){
			lblContactName.setHorizontalAlignment(SwingConstants.CENTER);
			lblContactName.setFont(new Font("Raleway", Font.PLAIN, 12));
			lblContactName.setForeground(Color.DARK_GRAY);
			lblContactName.setBounds(78, 80, 130, 18);
			add(lblContactName);
		}else {
			JLabel lblContactName = new JLabel(recipient);
			lblContactName.setHorizontalAlignment(SwingConstants.CENTER);
			lblContactName.setFont(new Font("Raleway", Font.PLAIN, 12));
			lblContactName.setForeground(Color.DARK_GRAY);
			lblContactName.setBounds(43, 80, 200, 18);
			add(lblContactName);
		}

		// Message Area		
		textField.setAutoscrolls(false);
		textField.setActionCommand("");
		textField.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.GRAY, null, null));
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setFont(new Font("Raleway", Font.PLAIN, 18));
		textField.setBounds(26, 450, 189, 40);
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
		btnSend.setBounds(215, 450, 40, 40);
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

		sentMessageArea.setHorizontalTextPosition(SwingConstants.LEFT);
		sentMessageArea.setHorizontalAlignment(SwingConstants.LEFT);
		sentMessageArea.setBounds(71, 110, 185, 250);
		sentMessageArea.setFont(new Font("Raleway", Font.PLAIN, 16));
		sentMessageArea.setBackground(Color.GREEN);
		sentMessageArea.setForeground(Color.WHITE);
		add(sentMessageArea);
		
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

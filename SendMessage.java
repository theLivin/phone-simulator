import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SendMessage extends PhonePresetWithNoWallpaper implements ActionListener{
	private JButton backBtn = new JButton("Back");
	private JTextField textField = new JTextField();// Message Typing Area
	private JButton sentMessageArea = new JButton();// Send Message Area
	private JButton homeBtn = new JButton();// Home Button
	private JButton btnSend = new JButton();// Send Button
	private String recipient = "";// Message Recipient
	private JTextField lblContactName = new JTextField();// Recipient label under contact image

	private SecureRandom randomNumbers = new SecureRandom(); // object for random numbers
	private String time;
	private String date;

	private boolean eventListenerAdded = false;


	 /* Create the panel.
	 */

	public SendMessage(String rec, String imageUrl) {
		setAutoscrolls(true);
		this.recipient = rec;

		
        // back Button
        backBtn.setFont(new Font("Raleway", Font.PLAIN, 14));
        backBtn.setForeground(Color.BLUE);
        backBtn.setBounds(10, 70, 80, 30);
        backBtn.setHorizontalAlignment(SwingConstants.CENTER);
        super.makeButtonTransparent(backBtn, false);
        add(backBtn);
        backBtn.addActionListener(this);
        backBtn.setFocusable(false);

		// -- get system time and date
		LocalDateTime now = LocalDateTime.now();

		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
		time = timeFormat.format(now);

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("E, MMM d yyyy");
		date = dateFormat.format(now);
	     
	     
		int num = 1 + randomNumbers.nextInt(6);
		String defaultImageUrl = "./images/icons/contacts/contacts-"+num+".png";
		
		Icon userImage;
        if(imageUrl == null || imageUrl.contentEquals("")){
           userImage = new ImageIcon(getClass().getResource(defaultImageUrl));
        }else{
            try{
                userImage = super.resizeSelectedImage(imageUrl, 32, 32);
            }catch(Exception ex){
                userImage = new ImageIcon(getClass().getResource(defaultImageUrl));
            }
        }

		// Contacts Icon		
		JLabel lblContactImage = new JLabel("");
		lblContactImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblContactImage.setBounds(123, 40, 40, 40);
		lblContactImage.setFocusable(false);
		
		lblContactImage.setIcon(userImage);
		
		
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

		if(recipient.contentEquals("")){
		}else {
			// Check if contact is saved and display name instead of number
			MyDatabaseManager db = new MyDatabaseManager();
			ResultSet allMsgs = db.returnAllMessagesToThis(recipient);
			String output = "<html>";
			try{
				while(allMsgs.next()){
					output += allMsgs.getString("body")+"<br>";
				}
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}
			output += "";
			sentMessageArea.setText(output);
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
		if(!eventListenerAdded){
			btnSend.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String textMsg = textField.getText();
					String toWho = lblContactName.getText();
					sendNewMessage(textMsg,toWho);
				}
			});

			eventListenerAdded = true;
		}
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

	public void sendNewMessage(String msg, String toWhichPerson){
		if(!msg.contentEquals("")){
			MyDatabaseManager db = new MyDatabaseManager();
			String newText = String.format("%s%s",sentMessageArea.getText(),msg);
			String target = toWhichPerson;
			
			if(recipient.contentEquals("")){
			}else{
				target = recipient;
			}

			if(target.matches("[0-9]+") && target.length() > 2) {
				sentMessageArea.setText(newText);
				textField.setText("");	
				db.saveMessage(msg,date,time,target);
				recipient = target;
			}else {
				JLabel alert = new JLabel("Message was no sent.");
				alert.setFont(new Font("Raleway", Font.PLAIN, 14));
				JOptionPane.showMessageDialog(null, alert, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			System.out.println("target is "+target);
				
					 
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
		if (e.getSource() == backBtn){
            // Go to messages page
            MessageLogs panel = new MessageLogs();
            NewWindowFrame frame = new NewWindowFrame(panel);
            frame.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
        }
	}
	
}

import javax.swing.*;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class PhoneDial extends PhonePresetWithNoWallpaper implements ActionListener {
	private JTextField textField;
	
	private static final Font font = new Font("Raleway", Font.PLAIN, 14);
    private JButton contactsBtn = new JButton("Contacts");
	private JButton backBtn = new JButton("Back");

	/**
	 * Create the panel.
	 */
	public PhoneDial() {
		setBounds(new Rectangle(0, 0, 281, 561));
		setBackground(Color.WHITE);
		setLayout(null);

		ImageIcon icon;
		JButton[] numpads = new JButton[13];
		int x = 37, y = 201, w = 61, h = 52;
		int xi = 37;

		// Add text numpads
		for(int i=1; i<=12; i++){
			if(i>=10 && i<=12){
				switch(i){
					case 10:
					icon = new ImageIcon(getClass().getResource("./img/btnStar.png"));
					break;
					case 11:
					icon = new ImageIcon(getClass().getResource("./img/btn0.png"));
					break;
					default:
					icon = new ImageIcon(getClass().getResource("./img/btnHash.png"));
				}
			}else{
				icon = new ImageIcon(getClass().getResource("./img/btn"+i+".png"));
			}

			numpads[i] = new JButton("",icon);
			numpads[i].setBounds(x, y, w, h);
			add(numpads[i]);
			// -- register listeners for buttons
            numpads[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
					String key = handleButtonClick((JButton)e.getSource());
					textField.setText(textField.getText()+key);;
                }
            });

			if(i%3==0){
				x = xi;
				y = y+h+2;
			}else{
				x = x+w+2;
			}
			
		}

		// Call button		
		ImageIcon img1 = new ImageIcon(getClass().getResource("./img/callButton.png"));
		JButton call = new JButton(img1);
		call.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = textField.getText();
				handleButtonClick("call", msg);
			}
		});
		call.setBorder(null);
		call.setBounds(118, 473, 39, 35);
		add(call);

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

		
		// Textfield		
		textField = new JTextField();
		textField.setFont(new Font("Raleway", Font.PLAIN, 20));
		textField.setHorizontalAlignment(SwingConstants.TRAILING);
		textField.setBorder(null);
		textField.setBounds(23, 155, 156, 35);
		add(textField);
		textField.setColumns(10);

		// Backspace		
		ImageIcon imgBackSpace = new ImageIcon(getClass().getResource("./img/icons8-backspace-30.png"));
		JLabel backSpace = new JLabel(imgBackSpace);
		backSpace.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int length = textField.getText().length();
				int number = textField.getText().length()-1;
				
				if(length>0) {
					StringBuilder back = new StringBuilder(textField.getText());
					back.deleteCharAt(number);
					String store = back.toString(); 
					textField.setText(store);
				}
			}
		});
		backSpace.setBounds(181, 155, 46, 46);
		add(backSpace);
		
		// Add contact		
		ImageIcon imgAddContact1 = new ImageIcon(getClass().getResource("./img/addContact.png"));
		JButton addContact1 = new JButton(imgAddContact1);
		addContact1.setBorder(null);
		addContact1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String msg = textField.getText();
				handleButtonClick("add", msg);				
			}
		});
		addContact1.setBounds(67, 90, 39, 30);
		add(addContact1);
		
		// Send message		
		ImageIcon imgSendMessage1 = new ImageIcon(getClass().getResource("./img/sendMessage.png"));
		JButton sendMessage1 = new JButton(imgSendMessage1);
		sendMessage1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = textField.getText();
				handleButtonClick("message", msg);
			
			}
		});
		sendMessage1.setBorder(null);
		sendMessage1.setBounds(166, 85, 46, 35);
		add(sendMessage1);
			
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backBtn){
			// --go to home screen
            HomeScreen panel = new HomeScreen();
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

	// Handle numpad clicks
	public String handleButtonClick(JButton btn){
		Icon[] imgs = new ImageIcon[13];
		for(int i=1; i<=12; i++){
			if(i>=10 && i<=12){
				switch(i){
					case 10:
					imgs[i] = new ImageIcon(getClass().getResource("./img/btnStar.png"));
					break;
					case 11:
					imgs[i] = new ImageIcon(getClass().getResource("./img/btn0.png"));
					break;
					default:
					imgs[i] = new ImageIcon(getClass().getResource("./img/btnHash.png"));
				}
			}else{
				imgs[i] = new ImageIcon(getClass().getResource("./img/btn"+i+".png"));
			}
		}

		Icon btnIcon = btn.getIcon();
		String output = "0";

		for(int i=1; i<=12; i++){
			if(i>=1 && i<=9){
				if((btnIcon.toString()).compareTo(imgs[i].toString()) == 0)
					output = (String)(""+i);
			}else{
				if((btnIcon.toString()).compareTo(imgs[10].toString()) == 0)
					output = "*";
				else if((btnIcon.toString()).compareTo(imgs[11].toString()) == 0)
					output = "0";
				else if((btnIcon.toString()).compareTo(imgs[12].toString()) == 0)
					output = "#";
			}
		}
		return output;
	}

	// Handle special buttons clicks ie: add contacts, send message, dial
	public void handleButtonClick(String btn, String msg){
		System.out.println(msg);
		if(msg.contentEquals("")) {
			// do nothing
		}else{
			if(btn == "call"){
				PhoneCall panel = new PhoneCall(msg);
				NewWindowFrame frame = new NewWindowFrame(panel);
				frame.setVisible(true);
				((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
			}

			if(btn == "add"){
				AddContact panel = new AddContact(msg);
				NewWindowFrame frame = new NewWindowFrame(panel);
				frame.setVisible(true);
				((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
			}

			if(btn == "message"){
				SendMessage panel = new SendMessage();
				NewWindowFrame frame = new NewWindowFrame(panel);
				frame.setVisible(true);
				((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
				// TODO: let user be able to send message to typed number
			}			

		}
	}
}

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class PhoneDial extends PhonePresetWithNoWallpaper implements ActionListener {
	private JTextField textField;
	
	private static final Font font = new Font("Raleway", Font.PLAIN, 14);
	private JButton homeBtn = new JButton();
	String dialpadDirectory = "./images/dialpad/";

	/**
	 * Create the panel.
	 */
	public PhoneDial() {
		// Remove all active layouts
		setLayout(null);

		// Width and height of most buttons
		int x = 70, y = 50, w = 65, h = 65;

		// Add contact		
		ImageIcon imgAddContact1 = new ImageIcon(getClass().getResource("./images/icons/addContact.png"));
		JButton addContact1 = new JButton(imgAddContact1);
		addContact1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String msg = textField.getText();
				handleButtonClick("add", msg);				
			}
		});
		addContact1.setBounds(70, y, w, h);
		add(addContact1);
		super.makeButtonTransparent(addContact1, false);
		addContact1.setFocusable(false);
		
		// Send message		
		ImageIcon imgSendMessage1 = new ImageIcon(getClass().getResource("./images/icons/sendMessage.png"));
		JButton sendMessage1 = new JButton(imgSendMessage1);
		sendMessage1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = textField.getText();
				handleButtonClick("message", msg);
			
			}
		});
		sendMessage1.setBounds(159, y, w, h);
		add(sendMessage1);
		super.makeButtonTransparent(sendMessage1, false);
		sendMessage1.setFocusable(false);

		// Change starting y
		y = y + h + 5;
		
		// Textfield		
		textField = new JTextField();
		textField.setFont(new Font("Raleway", Font.PLAIN, 22));
		textField.setHorizontalAlignment(SwingConstants.TRAILING);
		textField.setBorder(null);
		textField.setBounds(42, y, 156, 35);
		add(textField);
		textField.setColumns(10);

		// Backspace		
		ImageIcon imgBackSpace = new ImageIcon(getClass().getResource("./images/icons/backspace.png"));
		JButton backSpace = new JButton(imgBackSpace);
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
		backSpace.setBounds(199, y - 7, 46, 46);
		add(backSpace);
		super.makeButtonTransparent(backSpace, false);
		backSpace.setFocusable(false);
		

		// Dial pad
		ImageIcon icon;
		JButton[] numpads = new JButton[13];
		x = 42; y = y + 42;
		w = 65; h = 65;
		int xi = 42;


		// Add text numpads
		for(int i=1; i<=12; i++){
			if(i>=10 && i<=12){
				switch(i){
					case 10:
					icon = new ImageIcon(getClass().getResource(dialpadDirectory + "btnStar.png"));
					break;
					case 11:
					icon = new ImageIcon(getClass().getResource(dialpadDirectory + "btn0.png"));
					break;
					default:
					icon = new ImageIcon(getClass().getResource(dialpadDirectory + "btnHash.png"));
				}
			}else{
				icon = new ImageIcon(getClass().getResource(dialpadDirectory + "btn"+i+".png"));
			}

			numpads[i] = new JButton("",icon);
			numpads[i].setBounds(x, y, w, h);
			add(numpads[i]);
			super.makeButtonTransparent(numpads[i], false);
			numpads[i].setFocusable(false);

			// -- register listeners for buttons
            numpads[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
					String key = handleButtonClick((JButton)e.getSource());
					textField.setText(textField.getText()+key);;
                }
            });

			if(i%3==0){
				x = xi;
				y = y+h+3;
			}else{
				x = x+w+2;
			}
			
		}

		// Change x
		x = x + w + 2;

		// Call button		
		ImageIcon img1 = new ImageIcon(getClass().getResource("./images/dialpad/" + "callButton.png"));
		JButton call = new JButton(img1);
		call.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = textField.getText();
				handleButtonClick("call", msg);
			}
		});
		call.setBounds(x, y-3, w, h);
		add(call);
		super.makeButtonTransparent(call, false);
		call.setFocusable(false);

		// Add navigation buttons
		addRecAndConTab();			

		// Home Button
		Icon homebar = new ImageIcon(getClass().getResource("./images/homebar.png"));
		homeBtn.setIcon(homebar);
        homeBtn.setForeground(Color.BLUE);
        homeBtn.setBounds(78, 535, 131, 10);
        homeBtn.setHorizontalAlignment(SwingConstants.CENTER);
        super.makeButtonTransparent(homeBtn, false);
        add(homeBtn);
		homeBtn.addActionListener(this);
		homeBtn.setFocusable(false);
	
	}

	   
    // Draw buttons at the bottom ie call logs, contacts and dialpad
    public void addRecAndConTab() {
    	JLabel recentLabel = new JLabel("New label");
    	recentLabel.setBounds(62, 489, 34, 34);
        recentLabel.setIcon(new ImageIcon(getClass().getResource("/images/icons/recent.png")));
        recentLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        recentLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                changeCurrentPage("logs");
            }
        });
		add(recentLabel);
		
        
        JLabel contactsLabel = new JLabel("New label");
        contactsLabel.setBounds(185, 489, 34, 34);
        contactsLabel.setIcon(new ImageIcon(getClass().getResource("/images/icons/contac.png")));
        contactsLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        contactsLabel.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent arg0) {
        		changeCurrentPage("contacts");
        	}
        });
        add(contactsLabel);   
        
	}

	public void setTextFieldText(String txt){
		textField.setText(txt);
	}
	
	// Haddle action event
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == homeBtn){
			// --go to home screen
            HomeScreen panel = new HomeScreen();
            NewWindowFrame frame = new NewWindowFrame(panel);
            frame.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
            
        }
	}

	// Handle numpad clicks
	public String handleButtonClick(JButton btn){
		Icon[] imgs = new ImageIcon[13];
		for(int i=1; i<=12; i++){
			if(i>=10 && i<=12){
				switch(i){
					case 10:
					imgs[i] = new ImageIcon(getClass().getResource(dialpadDirectory + "btnStar.png"));
					break;
					case 11:
					imgs[i] = new ImageIcon(getClass().getResource(dialpadDirectory + "btn0.png"));
					break;
					default:
					imgs[i] = new ImageIcon(getClass().getResource(dialpadDirectory + "btnHash.png"));
				}
			}else{
				imgs[i] = new ImageIcon(getClass().getResource(dialpadDirectory + "btn"+i+".png"));
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
		// System.out.println(msg);
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
				SendMessage panel = new SendMessage(msg);
				NewWindowFrame frame = new NewWindowFrame(panel);
				frame.setVisible(true);
				((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
			}

		}
	}

	// Change current page
    public void changeCurrentPage(String target){
        if(target == "dial"){
            // go to dial page
            PhoneDial panel = new PhoneDial();
            NewWindowFrame frame = new NewWindowFrame(panel);
            frame.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
        }
        else if(target == "contacts"){
            // go to contacts page
            ContactsPage panel = new ContactsPage();
            NewWindowFrame frame = new NewWindowFrame(panel);
            frame.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
        }
        else if(target == "logs"){
            // go to call logs page
            CallLogs panel = new CallLogs();
            NewWindowFrame frame = new NewWindowFrame(panel);
            frame.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
        }
    }
}

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

	/**
	 * Create the panel.
	 */
	public PhoneDial() {
		setBounds(new Rectangle(0, 0, 281, 561));
		setBackground(Color.WHITE);
		setLayout(null);
	        
		
		ImageIcon imgBton1 = new ImageIcon(getClass().getResource("./img/btn1.png"));
		JLabel bton1 = new JLabel(imgBton1);
		bton1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText(textField.getText()+"1");
			}
		});
		bton1.setBounds(37, 201, 61, 52);
		add(bton1);
		
		ImageIcon imgBton2 = new ImageIcon(getClass().getResource("./img/btn2.png"));
		JLabel bton2 = new JLabel(imgBton2);
		bton2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText(textField.getText()+"2");
			}
		});
		bton2.setBounds(108, 201, 61, 52);
		add(bton2);
		
		ImageIcon imgBton3 = new ImageIcon(getClass().getResource("./img/btn3.png"));
		JLabel bton3 = new JLabel(imgBton3);
		bton3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText(textField.getText()+"3");
			}
		});
		bton3.setBounds(181, 201, 61, 52);
	    add(bton3);
		
	    ImageIcon imgBton4 = new ImageIcon(getClass().getResource("./img/btn4.png"));
		JLabel bton4 = new JLabel(imgBton4);
		bton4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText(textField.getText()+"4");
			}
		});
		bton4.setBounds(37, 258, 61, 59);
		add(bton4);
		
		ImageIcon imgBton5 = new ImageIcon(getClass().getResource("./img/btn5.png"));
		JLabel bton5 = new JLabel(imgBton5);
		bton5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText(textField.getText()+"5");
			}
		});
		bton5.setBounds(108, 263, 61, 52);
		add(bton5);
		
		ImageIcon imgBton6 = new ImageIcon(getClass().getResource("./img/btn6.png"));
		JLabel bton6 = new JLabel(imgBton6);
		bton6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText(textField.getText()+"6");
			}
		});
		bton6.setBounds(181, 263, 61, 52);
		add(bton6);
		
		ImageIcon imgBton7 = new ImageIcon(getClass().getResource("./img/btn7.png"));
		JLabel bton7 = new JLabel(imgBton7);
		bton7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    textField.setText(textField.getText()+"7");
			}
		});
		bton7.setBounds(37, 326, 61, 59);
		add(bton7);
		
		ImageIcon imgBton8 = new ImageIcon(getClass().getResource("./img/btn8.png"));
		JLabel bton8 = new JLabel(imgBton8);
		bton8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText(textField.getText()+"8");
			}
		});
		bton8.setBounds(108, 326, 61, 59);
		add(bton8);
		
		ImageIcon imgBton9 = new ImageIcon(getClass().getResource("./img/btn9.png"));
		JLabel bton9 = new JLabel(imgBton9);
		bton9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 textField.setText(textField.getText()+"9");
			}
		});
		bton9.setBounds(181, 326, 61, 59);
	    add(bton9);
		
	    ImageIcon imgBtonStar = new ImageIcon(getClass().getResource("./img/btnStar..png"));
		JLabel btonStar = new JLabel(imgBtonStar);
		btonStar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText(textField.getText()+"*");
			}
		});
		btonStar.setBounds(37,389, 61, 59);
		add(btonStar);
		
		ImageIcon imgBton0 = new ImageIcon(getClass().getResource("./img/btn0.png"));
		JLabel bton0 = new JLabel(imgBton0);
		bton0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 textField.setText(textField.getText()+"0");
			}
		});
		bton0.setBounds(108, 389, 61, 59);
		add(bton0);
		
		ImageIcon imgBtonHash = new ImageIcon(getClass().getResource("./img/btnHash.png"));
		JLabel btonHash = new JLabel(imgBtonHash);
		btonHash.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText(textField.getText()+"#");
			}
		});
		btonHash.setBounds(181,389, 61, 59);
		add(btonHash);
		
		ImageIcon img1 = new ImageIcon(getClass().getResource("./img/callButton.png"));
		JButton call = new JButton(img1);
		call.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = textField.getText();
	             if(msg.contentEquals("")) {
					
				}
				else {
				PhoneCall panel = new PhoneCall(msg);
		           NewWindowFrame frame = new NewWindowFrame(panel);
		           frame.setVisible(true);
				}
			}
		});
		call.setBorder(null);
		call.setBounds(118, 473, 39, 35);
		add(call);
		
		ImageIcon imgOptions = new ImageIcon(getClass().getResource("./img/options.png"));
		JButton optionsBtn = new JButton(imgOptions);
		optionsBtn.setBorder(null);
		optionsBtn.setBounds(53, 515, 39, 30);
		add(optionsBtn);
		
		ImageIcon imgHome = new ImageIcon(getClass().getResource("./img/homeButton.png"));
		JButton homeBtn = new JButton(imgHome);
		homeBtn.setBorder(null);
		homeBtn.setBounds(128, 515, 22, 26);
		add(homeBtn);
		
		ImageIcon imgBack = new ImageIcon(getClass().getResource("./img/back.png"));
		JButton backBtn = new JButton(imgBack);
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  HomeScreen panel = new HomeScreen();
		           NewWindowFrame frame = new NewWindowFrame(panel);
		           frame.setVisible(true);
		          
			}
		});
		backBtn.setBorder(null);
		backBtn.setBounds(198, 515, 28, 26);
		add(backBtn);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setHorizontalAlignment(SwingConstants.TRAILING);
		textField.setBorder(null);
		textField.setBounds(23, 155, 156, 35);
		add(textField);
		textField.setColumns(10);
		
		
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
		
		
		
		
		ImageIcon imgAddContact1 = new ImageIcon(getClass().getResource("./img/addContact.png"));
		JButton addContact1 = new JButton(imgAddContact1);
		addContact1.setBorder(null);
		addContact1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String msg = textField.getText();
				if(msg.contentEquals("")) {
					
				}
				else {
					AddContact panel = new AddContact(msg);
			           NewWindowFrame frame = new NewWindowFrame(panel);
			           frame.setVisible(true);
				}
				
			}
		});
		addContact1.setBounds(67, 90, 39, 30);
	    add(addContact1);
		
		ImageIcon imgSendMessage1 = new ImageIcon(getClass().getResource("./img/sendMessage.png"));
		JButton sendMessage1 = new JButton(imgSendMessage1);
		sendMessage1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = textField.getText();
				if(msg.contentEquals("")) {
					
				}
				else {
					SendMessage panel = new SendMessage();
			           NewWindowFrame frame = new NewWindowFrame(panel);
			           frame.setVisible(true);
				}
			
			}
		});
		sendMessage1.setBorder(null);
		sendMessage1.setBounds(166, 85, 46, 35);
		add(sendMessage1);
		
		JLabel lblNewLabel = new JLabel("Add Contact");
		lblNewLabel.setBounds(53, 120, 61, 14);
		add(lblNewLabel);
		
		JLabel lblT = new JLabel("Send Message");
		lblT.setBounds(160, 120, 76, 14);
		add(lblT);
		
		
		
		
	}
	
	

	public static void main(String[] args) {
		

		JFrame app = new JFrame("eyePhone");
	    PhoneDial dial = new PhoneDial();
		  app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        app.setSize(287, 590);
	        app.setVisible(true);
	        app.setResizable(false);
	        app.setLocationRelativeTo(null);
	        app.getContentPane().add(dial);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

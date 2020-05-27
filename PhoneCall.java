import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class PhoneCall extends PhonePresetWithNoWallpaper {
	private static String iconsRootUrl = "./images/dialpad/";

	private JLabel[] extraLabel = new JLabel[6];
	private String[][] extras = {
		{"mute", "keypad", "speaker", "add call", "FaceTime", "contacts"}, 
		{"mute.png", "keypad-white.png", "speaker.png", "plus.png", "facetime-2.png", "contact-filled-white.png"}
	};
	
	public PhoneCall( String param) {
		setBackground(new Color(82, 97, 107, 220));

		// Name or number		
		JLabel contactName = new JLabel(param);
		contactName.setFont(new Font("Raleway", Font.PLAIN, 30));
		// contactName.setToolTipText("");
		contactName.setForeground(Color.WHITE);
		contactName.setBounds(30, 87, 201, 50);
		contactName.setHorizontalAlignment(SwingConstants.CENTER);
		add(contactName);
		
		JLabel lblCalling = new JLabel("calling eyePhone...");
		lblCalling.setFont(new Font("Raleway", Font.PLAIN, 12));
		lblCalling.setForeground(Color.WHITE);
		lblCalling.setBounds(30, 137, 201, 27);
		lblCalling.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblCalling);

		// Extra icons
		int w = 79,
			h = 75, 
			x = 22, 
			y1 = 190, 
			y2 = y1 + h;

		for(int i=0; i<extraLabel.length; i++){
			int xUp = (i%3) * w;
			
			extraLabel[i] = createExtraLabel(extras[0][i], extras[1][i]);
			if(i < 3)
				extraLabel[i].setBounds(x + xUp, y1, w, h);
			else
				extraLabel[i].setBounds(x + xUp, y2, w, h);

			add(extraLabel[i]);
		}

		// Listen for clicks on keypads button and contacts button
		extraLabel[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				respondToClick("keypad");
			}
		});

		extraLabel[5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				respondToClick("contacts");
			}
		});
		
		// End call		
		ImageIcon imgEndCall = new ImageIcon(getClass().getResource(iconsRootUrl + "endCall.png"));
		JLabel endCall = new JLabel(imgEndCall);
		endCall.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				respondToClick("end");
			}
		});
		endCall.setBounds((x + w), (y2 + h + 10), w, h);
		add(endCall);
		
	}

	public void respondToClick(String str){
		if(str == "end" || str == "keypad"){
			PhoneDial panel = new PhoneDial();
			NewWindowFrame frame = new NewWindowFrame(panel);
			frame.setVisible(true);
			((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
		}
		if(str == "contacts"){
			ContactsPage panel = new ContactsPage();
			NewWindowFrame frame = new NewWindowFrame(panel);
			frame.setVisible(true);
			((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
		}
			
	}

	public JLabel createExtraLabel(String str, String img){
		Icon icon = new ImageIcon(getClass().getResource(iconsRootUrl + img));
		JLabel label = new JLabel(str, icon, JLabel.CENTER);
		label.setFont(new Font("Raleway", Font.PLAIN, 10));
        label.setIconTextGap(2);
        label.setVerticalTextPosition(JLabel.BOTTOM);
		label.setHorizontalTextPosition(JLabel.CENTER);
		
		if(str == "keypad" || str == "contacts")
			label.setForeground(Color.WHITE);
		else
			label.setForeground(Color.GRAY);
		
		
		return label;
	}

}

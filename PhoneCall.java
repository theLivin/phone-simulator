import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class PhoneCall extends PhonePresetWithNoWallpaper {

	
	public PhoneCall( String Param) {
		ImageIcon imgContactImage = new ImageIcon(getClass().getResource("./img/contactImage.png"));
		JLabel contactImage = new JLabel(imgContactImage);
		contactImage.setBounds(47, 287, 177, 144);
		add(contactImage);

		ImageIcon imgEndCall = new ImageIcon(getClass().getResource("./img/endCall.png"));
		JLabel endCall = new JLabel(imgEndCall);
		endCall.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PhoneDial panel = new PhoneDial();
		           NewWindowFrame frame = new NewWindowFrame(panel);
		           frame.setVisible(true);
			}
		});
		endCall.setBounds(115, 470, 56, 60);
		add(endCall);
		
		JLabel contactName = new JLabel("Name Of Contact ");
		contactName.setFont(new Font("Tahoma", Font.BOLD, 20));
		contactName.setToolTipText("");
		contactName.setBounds(47, 87, 177, 38);
		add(contactName);
		
		JLabel phoneNum = new JLabel();
		phoneNum.setText(Param);
		phoneNum.setFont(new Font("Tahoma", Font.BOLD, 16));
		phoneNum.setBounds(70, 122, 120, 20);
		add(phoneNum);
		
		ImageIcon imgExtraIcons = new ImageIcon(getClass().getResource("./img/extra icons.png"));
		JLabel extraIcons = new JLabel(imgExtraIcons);
		extraIcons.setBounds(61, 190, 163, 44);
		add(extraIcons);
		
		JLabel lblCalling = new JLabel("Calling...");
		lblCalling.setBounds(new Rectangle(115, 164, 48, 27));
		setBounds(76, 178, 281, 686);
		add(lblCalling);
		
		JLabel lblMtnGhana = new JLabel("MTN Ghana");
		lblMtnGhana.setBounds(105, 150, 70, 14);
		add(lblMtnGhana);
	
	
	
	
	}

	
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String Param = null;
					 JFrame app = new JFrame();
				       PhoneCall dial = new PhoneCall( Param);

				        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				        app.setSize(287, 590);
				        app.setVisible(true);
				        app.setResizable(false);
				        app.setLocationRelativeTo(null);
				        app.getContentPane().add(dial);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


}

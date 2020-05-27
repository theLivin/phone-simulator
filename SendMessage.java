import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class SendMessage extends PhonePresetWithNoWallpaper implements ActionListener{
	private JTextField textField;
	private JButton homeBtn = new JButton();

	/**
	 * Create the panel.
	 */

	public SendMessage() {
		setAutoscrolls(true);
		
		textField = new JTextField();
		textField.setAutoscrolls(false);
		textField.setActionCommand("");
		textField.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.GRAY, null, null));
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setBounds(22, 384, 157, 40);
		add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setHorizontalTextPosition(SwingConstants.RIGHT);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(71, 286, 185, 46);
		add(label);
		

		JLabel label2 = new JLabel("");
		label2.setHorizontalTextPosition(SwingConstants.RIGHT);
		label2.setHorizontalAlignment(SwingConstants.RIGHT);
		label2.setBounds(71, 205, 185, 46);
		add(label2);
		
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Text;
				Text= textField.getText();
				label.setText(Text);
			  //  label2.setText(Text);
			}
		});
		btnSend.setBounds(189, 384, 63, 40);
		btnSend.setFocusable(false);
		add(btnSend);
		
		JLabel lblWriteMessage = new JLabel("Write Message");
		lblWriteMessage.setBackground(Color.LIGHT_GRAY);
		lblWriteMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblWriteMessage.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblWriteMessage.setForeground(Color.BLUE);
		lblWriteMessage.setBounds(22, 69, 232, 40);
		add(lblWriteMessage);

		// Home Button
		Icon homebar = new ImageIcon(getClass().getResource("./images/homebar.png"));
		homeBtn.setIcon(homebar);
        homeBtn.setForeground(Color.BLUE);
        homeBtn.setBounds(75, 532, 131, 10);
        homeBtn.setHorizontalAlignment(SwingConstants.CENTER);
		makeButtonTransparent(homeBtn, false);
		homeBtn.setFocusable(false);
        add(homeBtn);
		homeBtn.addActionListener(this);			

	}


	// Make a button transparent
    public void makeButtonTransparent(JButton btn, boolean visibleBorder){
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(visibleBorder);
    }// <-- end makeButtonTransparent

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

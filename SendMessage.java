import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class SendMessage extends PhonePresetWithNoWallpaper{
	private JTextField textField;

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
		add(btnSend);
		
		JLabel lblWriteMessage = new JLabel("Write Message");
		lblWriteMessage.setBackground(Color.LIGHT_GRAY);
		lblWriteMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblWriteMessage.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblWriteMessage.setForeground(Color.BLUE);
		lblWriteMessage.setBounds(22, 69, 232, 40);
		add(lblWriteMessage);
		

	}
	
}

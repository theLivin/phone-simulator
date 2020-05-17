import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

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
public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 JFrame app = new JFrame();
				    SendMessage interFace = new SendMessage();

				        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				        app.setSize(287, 590);
				        app.setVisible(true);
				        app.setResizable(false);
				        app.setLocationRelativeTo(null);
				        app.getContentPane().add(interFace);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

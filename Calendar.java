import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import cambodia.raven.DateChooser;
import java.awt.Component;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Calendar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calendar frame = new Calendar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Calendar() {
		setForeground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		 
         JLabel homebar = new JLabel("");
         homebar.addMouseListener(new MouseAdapter() {
         	@Override
         	public void mouseClicked(MouseEvent e) {
         		PhoneSimulator.main(null);
         	}
         });
         homebar.setIcon(new ImageIcon(Calendar.class.getResource("/images/hombarbig.jpg")));
         homebar.setBounds(37, 516, 196, 14);
         getContentPane().add(homebar);
         
         JPanel panel = new JPanel();
         panel.setBounds(26, 42, 235, 4);
         getContentPane().add(panel);
         
         DateChooser dateChooser = new DateChooser();
         dateChooser.setAlignmentY(Component.BOTTOM_ALIGNMENT);
         dateChooser.setAlignmentX(Component.LEFT_ALIGNMENT);
         dateChooser.setBounds(21, 120, 243, 225);
         getContentPane().add(dateChooser);
         
      
         
         JLabel lblCalendar = new JLabel("CALENDAR");
         lblCalendar.setForeground(new Color(0, 0, 51));
         lblCalendar.setFont(new Font("Lucida Sans Unicode", Font.BOLD | Font.ITALIC, 13));
         lblCalendar.setIcon(new ImageIcon(Calendar.class.getResource("/images/interface.png")));
         lblCalendar.setBounds(25, 68, 215, 29);
         getContentPane().add(lblCalendar);
         setForeground(Color.DARK_GRAY);
         
         JSeparator separator = new JSeparator();
         separator.setBackground(Color.BLACK);
         separator.setBounds(26, 99, 235, 5);
         getContentPane().add(separator);
       
         
         
         
         
         
         
		
	}
}

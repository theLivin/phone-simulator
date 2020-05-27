import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSeparator;

import cambodia.raven.DateChooser;


public class CalendarPanel extends PhonePresetWithNoWallpaper implements ActionListener, KeyListener{
//public class CalendarPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public CalendarPanel() {
addhomebar();
		showCalendar();
		addcalendarlabel();
		
		
		
		
		
	}

	
	
	// Below Function for displaying homebar
	public void addhomebar() {  JLabel homebar = new JLabel("");
    homebar.addMouseListener(new MouseAdapter() {
     	@Override
     	public void mouseClicked(MouseEvent e) {
     		 removeAll();// remove all component
			     revalidate();// check if plane is availale
			     repaint();
     		
     		
     		
     		
     		//dispose();
     	}
     });
     homebar.setIcon(new ImageIcon(Calendar.class.getResource("/images/hombarbig.jpg")));
     homebar.setBounds(37, 516, 196, 14);
     add(homebar);
       
       }
	// JLABEL FOR DISPLAYING CALENDAR TITLE
	 
    public void addcalendarlabel() {
    JLabel lblCalendar = new JLabel("CALENDAR");
    lblCalendar.setForeground(new Color(0, 0, 51));
    lblCalendar.setFont(new Font("Lucida Sans Unicode", Font.BOLD | Font.ITALIC, 13));
    lblCalendar.setIcon(new ImageIcon(Calendar.class.getResource("/images/interface.png")));
    lblCalendar.setBounds(25, 68, 215, 29);
    add(lblCalendar);
    setForeground(Color.DARK_GRAY);
    
    JSeparator separator = new JSeparator();
    separator.setBackground(Color.BLACK);
    separator.setBounds(26, 99, 235, 5);
   add(separator);
   
  //below is to display network bar at topmost right corner
   
   JLabel network_bar = new JLabel("");
   network_bar.setIcon(new ImageIcon(CalendarPanel.class.getResource("/images/network-battery.png")));
   network_bar.setBounds(217, 22, 83, 14);
   add(network_bar);
        
    }
	public void showCalendar() {
		
	    DateChooser dateChooser = new DateChooser();
	    dateChooser.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        dateChooser.setAlignmentX(Component.LEFT_ALIGNMENT);
        dateChooser.setBounds(21, 120, 250, 225);
      
       add(dateChooser);
       
        
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
// predefined functions called below due to implementation of	ActionListeners and Keylistener
@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
}

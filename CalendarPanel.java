import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import cambodia.raven.DateChooser;


public class CalendarPanel extends PhonePresetWithNoWallpaper  implements ActionListener{
	
	// Variables for color of bubbles
	private Color BUBBLE3 = new Color(239, 243, 198, 70);
    private Color BUBBLE2 = new Color(239, 243, 198, 140);
    private Color BUBBLE1 = new Color(239, 243, 198, 160);
    private Color SHADOW1 = new Color(23, 31, 42, 80);
    private Color SHADOW2 = new Color(23, 31, 42, 120);
    private Color SHADOW3 = new Color(23, 31, 42, 200);

    private JButton homeBtn = new JButton();
    private BufferedImage iphone;

	
	// main that runs other functions outs the Calendar Pnael
	public CalendarPanel() {

        //Changes the color of screeen, if i change the color the left corner doesnt fool
        // setBackground(new Color(19,15,16,0));
	
        
        addcalendarlabel();
        showCalendar();
        
		homebar();
		
	}

	// Below Function for displaying homebar

	public void homebar() {
		
		Icon homebar = new ImageIcon(getClass().getResource("./images/homebar.png"));
		homeBtn.setIcon(homebar);
        homeBtn.setForeground(Color.BLUE);
        homeBtn.setBounds(75, 527, 131, 10);
        homeBtn.setHorizontalAlignment(SwingConstants.CENTER);
        super.makeButtonTransparent(homeBtn, false);
        add(homeBtn);
        homeBtn.addActionListener(this);	    
	
	}
	

	
	
	// JLABEL FOR DISPLAYING CALENDAR TITLE
	 
    public void addcalendarlabel() {
        JLabel lblCalendar = new JLabel("CALENDAR");
        lblCalendar.setForeground(new Color(0, 0, 51));
        lblCalendar.setFont(new Font("Lucida Sans Unicode", Font.BOLD | Font.ITALIC, 13));
        lblCalendar.setIcon(new ImageIcon(CalendarPanel.class.getResource("/images/icons/interface.png")));
        lblCalendar.setBounds(25, 62, 215, 35);
        add(lblCalendar);
        lblCalendar.setForeground(Color.DARK_GRAY);

        // makinf the line under the Calendar label
        JSeparator separator = new JSeparator();
        separator.setBackground(Color.BLACK);
        separator.setBounds(26, 99, 235, 5);
        add(separator);
  
    }
    
    
    // Displays the calendar 
	public void showCalendar() {	
        DateChooser dateChooser = new DateChooser();
        dateChooser.setBounds(19, 120, 250, 225);
        add(dateChooser);
    }

   

	// predefined functions called below due to implementation of	ActionListeners and Keylistener
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub  public void actionPerformed(ActionEvent e){
        if( e.getSource() == homeBtn ){
            // Go to home screen
            HomeScreen panel = new HomeScreen();
            NewWindowFrame frame = new NewWindowFrame(panel);
            frame.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        // Draw Wallpaper design
       // first tiny bubble at the top 
        g.setColor(SHADOW1);
        g.fillOval(100, 55, 52, 52);
        g.setColor(BUBBLE1);
        g.fillOval(96, 53, 50, 50);

        //bubble on far left bottom
        g.setColor(SHADOW2);
        g.fillOval(134, 354, 104, 104);        
        g.setColor(BUBBLE2);
        g.fillOval(130, 350, 100, 100);
    
    }

}

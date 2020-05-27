import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class PhonePresetWithNoWallpaper extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Define custom colors
    private Color BACKGROUND = Color.WHITE;
    protected Color FOREGROUND = Color.BLACK;

    private JButton clock = new JButton();

    private BufferedImage iphone;

    public PhonePresetWithNoWallpaper(){
        setLayout(null);
        
        setBackground(BACKGROUND);
        setForeground(FOREGROUND);

        // Load iphone image
        try{
            iphone = ImageIO.read(new File("./images/iphone.png"));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void shownavatright() {
    	
    	
    	 JLabel network_bar = new JLabel("");
    	   network_bar.setIcon(new ImageIcon(CalendarPanel.class.getResource("/images/network-battery.png")));
    	   network_bar.setBounds(215, 19, 83, 14);
    	   add(network_bar);
    	        
    	
    
    	
    }
    
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // Get width and height of panel
        int width = getWidth();
        int height = getHeight();

        // Get system time
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();

        String time = timeFormat.format(now);
        clock.setText(time);

        Dimension size = clock.getPreferredSize();
        
        clock.setFont(new Font("Raleway", Font.PLAIN, 12));
        clock.setBounds(0, 12, 100, size.height);

        // -- make clock button transparent
        clock.setOpaque(false);
        clock.setContentAreaFilled(false);
        clock.setBorderPainted(false);
        add(clock);
       
        // Draw iphone image on panel
        g.drawImage(iphone, 0, 0, width, height, this);
        
        shownavatright();
    }

}

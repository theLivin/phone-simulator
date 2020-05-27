import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class PhonePresetWithNoWallpaper extends JPanel{
	// Define custom colors
    private Color BACKGROUND = Color.WHITE;
    protected Color FOREGROUND = Color.BLACK;

    private JLabel clock = new JLabel();

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
        clock.setBounds(40, 15, 100, size.height);

        add(clock);
       
        // Draw iphone image on panel
        g.drawImage(iphone, 0, 0, 281, height, this);
        
    }

    
    // Make a button transparent
    public void makeButtonTransparent(JButton btn, boolean visibleBorder){
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(visibleBorder);
    }// <-- end makeButtonTransparent



}

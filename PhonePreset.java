import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.security.*;
import java.util.*;

public class PhonePreset extends PhonePresetWithNoWallpaper{
    // Define custom colors
    private int[] BACKGROUND = {0xE9EA77, 0xFF847C, 0xF6CD61, 
        0xA0855B, 0x3DA4AB, 0xFF8234, 0xEE91BC, 0x79D70F, 0xFDCB9E, 
        0x97E5EF, 0x639A67, 0xF6D186, 0xC9B6E4, 0xFFE3ED, 0xBEEBE9, 0xFF7C7C, 0xEEF9BF};

    private Color SHADOW3 = new Color(23, 31, 42, 200);

    private BufferedImage iphone;
    private BufferedImage wallpaper;

    private SecureRandom randomNumbers = new SecureRandom(); // object for random numbers


    public PhonePreset(){
        // setBackground(new Color(BACKGROUND[randomNumbers.nextInt(BACKGROUND.length)]));
        // Load iphone image
        try{
            iphone = ImageIO.read(new File("./images/iphone.png"));
            wallpaper = ImageIO.read(new File("./images/wallpaper.png"));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        TimerTask changeBackgroundColor = new TimerTask(){
            public void run(){
                setBackground(new Color(BACKGROUND[randomNumbers.nextInt(BACKGROUND.length)]));
                // repaint();
            }
        };

        new Timer().schedule(changeBackgroundColor, 0, 10000);

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.setColor(SHADOW3);
        g.fillOval(12, 202, 104, 104);        
        
        // Get width and height of panel
        int width = getWidth();
        int height = getHeight();

        // Draw iphone image on panel
        g.drawImage(wallpaper, 0, 0, width, height, this);
        g.drawImage(iphone, 0, 0, width, height, this);

        // System.out.println("width: "+width+" height: "+height);

        // width = 287, height = 567

    }
}
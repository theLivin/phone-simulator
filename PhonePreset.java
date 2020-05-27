import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;


public class PhonePreset extends PhonePresetWithNoWallpaper{
    // Define custom colors
    private Color BACKGROUND = new Color(0xEB6383);
    private Color BUBBLE3 = new Color(239, 243, 198, 70);
    private Color BUBBLE2 = new Color(239, 243, 198, 140);
    private Color BUBBLE1 = new Color(239, 243, 198, 160);
    private Color SHADOW1 = new Color(23, 31, 42, 80);
    private Color SHADOW2 = new Color(23, 31, 42, 120);
    private Color SHADOW3 = new Color(23, 31, 42, 200);

    private BufferedImage iphone;

    public PhonePreset(){
        setBackground(BACKGROUND);
        // Load iphone image
        try{
            iphone = ImageIO.read(new File("./images/iphone.png"));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        // Draw Wallpaper design
        g.setColor(SHADOW1);
        g.fillOval(12, 202, 52, 52);
        g.setColor(BUBBLE1);
        g.fillOval(10, 200, 50, 50);

        g.setColor(SHADOW2);
        g.fillOval(63, 273, 73, 73);        
        g.setColor(BUBBLE2);
        g.fillOval(60, 270, 70, 70);

        g.setColor(SHADOW3);
        g.fillOval(134, 354, 104, 104);        
        g.setColor(BUBBLE3);
        g.fillOval(130, 350, 100, 100);

        g.fillOval(130, 350, 100, 100);

        // Get width and height of panel
        int width = getWidth();
        int height = getHeight();

        // Draw iphone image on panel
        g.drawImage(iphone, 0, 0, 281, height, this);

    }
}
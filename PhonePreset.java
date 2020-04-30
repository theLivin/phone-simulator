import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.imageio.ImageIO;

import java.io.File;


public class PhonePreset extends JPanel{
    // Define custom colors
    private Color BACKGROUND = new Color(07, 69, 159);
    private Color BUBBLE3 = new Color(255, 212, 00, 70);
    private Color BUBBLE2 = new Color(255, 212, 00, 140);
    private Color BUBBLE1 = new Color(255, 212, 00, 210);
    private Color SHADOW1 = new Color(23, 31, 42, 80);
    private Color SHADOW2 = new Color(23, 31, 42, 160);
    private Color SHADOW3 = new Color(23, 31, 42, 240);

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
        // Get width and height of panel
        int width = getWidth();
        int height = getHeight();

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


        // new Color(23, 31, 42, 160)

        // Draw iphone image on panel
        g.drawImage(iphone, 0, 0, width, height, this);

        // System.out.println("Width: "+width+" Height: "+height);
    }
}
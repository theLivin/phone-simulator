import java.awt.*;
import javax.swing.*;
import java.security.*;
import java.util.*;
import java.util.Timer;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class AnimationScreen extends JPanel{
    
    private BufferedImage iphone;
    private Icon eyePhoneGIF;

    public AnimationScreen(boolean powerOn){
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        // Load iphone image
        try{
            iphone = ImageIO.read(new File("./images/framephone.png"));
            eyePhoneGIF = new ImageIcon(this.getClass().getResource("./images/eyePhone.gif"));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        JLabel label = new JLabel(eyePhoneGIF);
        label.setBounds(24, 400, 250, 300);
        add(label, BorderLayout.CENTER);

        TimerTask changeBackgroundColor = new TimerTask(){
            public void run(){
                if(powerOn){
                    // show home screen
                    openHomeScreen();
                }
                else{
                    // turn phone off
                    System.exit(0);
                }
            }
        };

        new Timer().schedule(changeBackgroundColor, 13000);

    }

    public void openHomeScreen(){
        // Go to home screen
        HomeScreen panel = new HomeScreen();
        NewWindowFrame frame = new NewWindowFrame(panel);
        frame.setVisible(true);
        ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        
        // Get width and height of panel
        int width = getWidth();
        int height = getHeight();

        // Draw iphone image on panel
        g.drawImage(iphone, 0, 0, width, height, this);

        // System.out.println("width: "+width+" height: "+height);

        // width = 287, height = 567

    }
}
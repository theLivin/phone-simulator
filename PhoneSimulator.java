import java.awt.*;
import javax.swing.*;
import java.io.File;

public class PhoneSimulator{
    public static void main(String[] args){
        // Create font
        try{
            Font f = Font.createFont(Font.TRUETYPE_FONT, new File("./fonts/Raleway-Regular.ttf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(f);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        JFrame application = new JFrame("eyePhone");
        HomeScreen home = new HomeScreen();

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setSize(287, 590);
        application.setVisible(true);
        application.setResizable(false);
        application.setLocationRelativeTo(null);
        application.add(home);
        
    }
}
import java.awt.BorderLayout;

import javax.swing.JFrame;

public class PhoneSimulator{

    public static void main(String[] args){
        JFrame application = new JFrame("eyePhone");
        PhonePreset phone = new PhonePreset();
        HomeScreen home = new HomeScreen();

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setSize(287, 590);
        application.setVisible(true);
        application.setResizable(false);
        application.setLocationRelativeTo(null);
        application.add(phone);
        application.add(home);
        
    }
}
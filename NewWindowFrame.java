import java.awt.*;
import javax.swing.*;

public class NewWindowFrame extends JFrame{
    private JPanel panel;
    

    public NewWindowFrame(JPanel panel){
        this.panel = panel;
        setTitle("eyePhone");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(293, 596);//287, 590
        setResizable(false);
        setLocationRelativeTo(null);
        // setUndecorated(true);

        add(panel);
    }
        
}
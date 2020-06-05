import java.awt.event.*;
import javax.swing.*;

public class NewWindowFrame extends JFrame{
    private JPanel panel;
    

    public NewWindowFrame(JPanel panel){
        this.panel = panel;
        setTitle("eyePhone");
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                AnimationScreen panel = new AnimationScreen(false);
                NewWindowFrame frame = new NewWindowFrame(panel);
                frame.setVisible(true);
                dispose();
            }
        });
        setSize(293, 596);//287, 590
        setResizable(false);
        setLocationRelativeTo(null);
        // setUndecorated(true);

        add(panel);
    }
        
}
import javax.swing.*;

public class NewWindowFrame extends JFrame{

    public NewWindowFrame(JPanel panel){
        setTitle("eyePhone");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(287, 590);
        setResizable(false);
        setLocationRelativeTo(null);
        add(panel);
    }
        
}
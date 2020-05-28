import java.awt.*;
import javax.swing.*;

public class NewWindowFrame extends JFrame{
    private JPanel panel;
    private JScrollPane scroll = new JScrollPane(
        panel,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS
    );
    private Container con;

    public NewWindowFrame(JPanel panel){
        this.panel = panel;
        setTitle("eyePhone");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(293, 596);//287, 590
        setResizable(false);
        setLocationRelativeTo(null);

        // add scroll pane
        con = getContentPane();
        con.add(scroll);
        
        add(panel);
    }
        
}
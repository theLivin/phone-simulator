import javax.swing.*;

public class NewWindowFrame extends JFrame{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NewWindowFrame(JPanel panel){
        setTitle("eyePhone");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(305, 590);
        setResizable(false);
        setLocationRelativeTo(null);
        add(panel);
    }
        
}
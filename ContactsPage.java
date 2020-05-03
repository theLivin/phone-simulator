import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;


public class ContactsPage extends PhonePresetWithNoWallpaper{
    private static final Font font = new Font("Raleway", Font.PLAIN, 14);

    private JButton homeBtn = new JButton("Home");
    
    private JTextField searchBar = new JTextField("Search Contact", 20);
    private JButton searchBtn = new JButton();

    private ArrayList<JButton> contactBtn = new ArrayList<JButton>();
    private String[] contactName = {"Robert", "Akyena", "Tomsin"};
    private String[] contactNumber = {"0234567891", "0234567891", "0234567891"};
    
    public ContactsPage(){
        setLayout(null);

        // Home Button
        homeBtn.setFont(font);
        homeBtn.setForeground(Color.BLUE);
        homeBtn.setBounds(7, 60, 80, 20);
        homeBtn.setHorizontalAlignment(SwingConstants.LEFT);
        makeButtonTransparent(homeBtn, false);
        add(homeBtn);
        
        // SearchBar
        searchBar.setBounds(23, 90, 185, 25);
        searchBar.setFont(font);
        add(searchBar);

        // Search Button
        Icon searchIcon = new ImageIcon(getClass().getResource("./images/icons/search.png"));
        searchBtn.setBounds(208, 90, 50, 25);
        searchBtn.setIcon(searchIcon);
        makeButtonTransparent(searchBtn, true);
        add(searchBtn);

        // Contacts List
        Icon icon = new ImageIcon(getClass().getResource("./images/icons/user.png"));
        int x = 23, y = 120, w = 235, h = 60;
        String buttonText;

        // -- show contacts list
        for(int i=0; i < contactName.length; i++){
            
            buttonText = String.format("<html><b>%s</b><br>%s</html>", 
            contactName[i], contactNumber[i]);

            contactBtn.add(new JButton(buttonText, icon));
            contactBtn.get(i).setFont(font);
            contactBtn.get(i).setBounds(x, y, w, h);
            contactBtn.get(i).setHorizontalAlignment(SwingConstants.LEFT);
            contactBtn.get(i).setIconTextGap(10);

            contactBtn.get(i).setBackground(new Color(0xe7e7e7));

            // makeButtonTransparent(contactBtn.get(i), true);

            // contactBtn.get(i).setBorder(BorderFactory.createCompoundBorder(
            //     BorderFactory.createLineBorder(new Color(239, 243, 198), 1),
            //     BorderFactory.createEmptyBorder(10, 10, 10 ,10))
            // );

                       
            add(contactBtn.get(i));

            y += h+2;
        }

    }

    public void makeButtonTransparent(JButton btn, boolean visibleBorder){
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(visibleBorder);
    }
}
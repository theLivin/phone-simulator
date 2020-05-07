import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class ContactsPage extends PhonePresetWithNoWallpaper implements ActionListener, KeyListener{
    // Variable declarations and definitions
    private static final Font font = new Font("Raleway", Font.PLAIN, 14);
    private static final Font boldFont = new Font("Raleway", Font.BOLD, 14);

    private JButton homeBtn = new JButton("Home");
    
    private JTextField searchBar = new JTextField("Search Contact", 20);
    private JButton searchBtn = new JButton();
    private String searchString = "";

    private ArrayList<JButton> contactBtn = new ArrayList<JButton>();
    private String[] contactNames = {"Robert", "Akyena", "Tomsin", "Gifty", "Elle", "Mandy"};
    private String[] contactNumber = {"0234567891", "0234567891", "0234567891", "0234567891", "0234567891", "0234567891"};

    // Constructor -->
    public ContactsPage(){ 
        setLayout(null);

        // Sort contacts list
        Arrays.sort(contactNames);
       
        // Home Button
        homeBtn.setFont(font);
        homeBtn.setForeground(Color.BLUE);
        homeBtn.setBounds(7, 60, 80, 20);
        homeBtn.setHorizontalAlignment(SwingConstants.LEFT);
        makeButtonTransparent(homeBtn, false);
        add(homeBtn);
        homeBtn.addActionListener(this);
        
        // SearchBar
        searchBar.setBounds(23, 90, 185, 25);
        searchBar.setFont(font);
        add(searchBar);
        searchBar.addKeyListener(this);

        // Search Button
        Icon searchIcon = new ImageIcon(getClass().getResource("./images/icons/search.png"));
        searchBtn.setBounds(208, 90, 50, 25);
        searchBtn.setIcon(searchIcon);
        makeButtonTransparent(searchBtn, true);
        add(searchBtn);
        searchBtn.addActionListener(this);

        // Contacts
        JLabel label = new JLabel("Contacts");
        label.setBounds(23, 125, 200, 20);
        label.setFont(font);
        label.setForeground(Color.GRAY);
        add(label);

        showContacts(contactNames, contactNumber);

    }// <-- end Constructor

    // Show contacts on screen from param array
    public void showContacts(String[] names, String[] numbers){
        // Contacts List
        int x = 23, y = 150, w = 235, h = 35;

        // -- show contacts list
        for(int i=0; i < names.length; i++){
            
            contactBtn.add(new JButton(names[i]));
            contactBtn.get(i).setFont(boldFont);
            contactBtn.get(i).setBounds(x, y, w, h);
            contactBtn.get(i).setHorizontalAlignment(SwingConstants.LEFT);

            contactBtn.get(i).setBackground(Color.WHITE);
                       
            add(contactBtn.get(i));

            // add an action listener
            contactBtn.get(i).addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    // Go to home screen
                    String name = ((JButton)(e.getSource())).getText();
                    showSingleContactPage(name, e);
                }
            });

            y += h+2;
        }

    }// <-- end showContacts

    // Make a button transparent
    public void makeButtonTransparent(JButton btn, boolean visibleBorder){
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(visibleBorder);
    }// <-- end makeButtonTransparent

    // Handle ActionListener events
    public void actionPerformed(ActionEvent e){
        if( e.getSource() == homeBtn ){
            // Go to home screen
            HomeScreen panel = new HomeScreen();
            NewWindowFrame frame = new NewWindowFrame(panel);
            frame.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
        }

        if( e.getSource() == searchBtn ){
            // Search for contact whose details matches the text in the search bar
            searchString = searchBar.getText();
            System.out.println("searching for: " + searchString);
        }
    }// <-- end actionPerformed

    // Handle KeyListener events
    public void keyTyped(KeyEvent e){
        char c = e.getKeyChar();
        searchString = searchBar.getText() + c;
        System.out.println(searchString);
    }// <-- end keyTyped

    public void keyPressed(KeyEvent e){
        // code...
    }// <-- end keyPressed

    public void keyReleased(KeyEvent e){
        // code...
    }// <-- end keyReleased
  
    // Show single contact page
    public void showSingleContactPage(String name, ActionEvent e){

        ContactDetailsPage panel = new ContactDetailsPage(name);
        NewWindowFrame frame = new NewWindowFrame(panel);
        frame.setVisible(true);
        ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
        System.out.println(((JButton)(e.getSource())).getText());

    }// <-- end showSingleContactPage

}
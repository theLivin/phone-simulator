import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class ContactsPage extends PhonePresetWithNoWallpaper implements ActionListener, KeyListener{
    // Variable declarations and definitions
    private static final Font font = new Font("Raleway", Font.PLAIN, 14);
    private static final Font boldFont = new Font("Raleway", Font.BOLD, 14);

    private JButton homeBtn = new JButton();
    
    private JTextField searchBar = new JTextField("Search Contact", 20);
    private JButton searchBtn = new JButton();
    private String searchString = "";

    private ArrayList<JButton> contactBtn = new ArrayList<JButton>();
    private String[] contactNames = {"Robert", "Akyena", "Tomsin", "Gifty", "Elle", "Mandy"};
    private String[] contactNumber = {"0234567891", "0234567891", "0234567891", "0234567891", "0234567891", "0234567891"};
    
    // Constructor -->
    public ContactsPage(){

        // Sort contacts list
        Arrays.sort(contactNames);
        setLayout(null);
        homeBtn.setBounds(7, 60, 80, 20);
       
        // Home Button
		Icon homebar = new ImageIcon(getClass().getResource("./images/homebar.png"));
		homeBtn.setIcon(homebar);
        homeBtn.setForeground(Color.BLUE);
        homeBtn.setBounds(75, 532, 131, 10);
        homeBtn.setHorizontalAlignment(SwingConstants.CENTER);
        super.makeButtonTransparent(homeBtn, false);
        add(homeBtn);
        homeBtn.addActionListener(this);	
	
        // Search Bar
        searchBar.setBounds(23, 90, 185, 25);
        searchBar.setFont(font);
        add(searchBar);
        searchBar.addKeyListener(this);

        // Search Button
        Icon searchIcon = new ImageIcon(getClass().getResource("./images/icons/search.png"));
        searchBtn.setBounds(203, 85, 50, 30);
        searchBtn.setIcon(searchIcon);
        super.makeButtonTransparent(searchBtn, false);
        add(searchBtn);
        searchBtn.addActionListener(this);

        // Contacts
        JLabel label = new JLabel("Contacts");
        label.setBounds(33, 125, 190, 20);
        label.setFont(font);
        label.setForeground(Color.GRAY);
        add(label);
        
        addRecAndConTab();

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
       
    // Draw buttons at the bottom ie call logs, contacts and dialpad
    public void addRecAndConTab() {
    	JLabel recentLabel = new JLabel("New label");
    	recentLabel.setBounds(62, 489, 34, 34);
        recentLabel.setIcon(new ImageIcon(getClass().getResource("/images/icons/recent.png")));
        recentLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        recentLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                changeCurrentPage("logs");
            }
        });
        add(recentLabel);
        
        JLabel dialLabel = new JLabel("New label");
        dialLabel.setBounds(185, 489, 34, 34);
        dialLabel.setIcon(new ImageIcon(getClass().getResource("/images/icons/dialpad.png")));
        dialLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        dialLabel.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent arg0) {
        		changeCurrentPage("dial");
        	}
        });
        add(dialLabel);   
        
    }

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

    // Change current page
    public void changeCurrentPage(String target){
        if(target == "dial"){
            // go to dial page
            PhoneDial panel = new PhoneDial();
            NewWindowFrame frame = new NewWindowFrame(panel);
            frame.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
        }
        else if(target == "contacts"){
            // go to contacts page
            ContactsPage panel = new ContactsPage();
            NewWindowFrame frame = new NewWindowFrame(panel);
            frame.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
        }
        else if(target == "logs"){
            // go to call logs page
            CallLogs panel = new CallLogs();
            NewWindowFrame frame = new NewWindowFrame(panel);
            frame.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
        }
    }
}
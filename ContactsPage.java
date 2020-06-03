import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.sql.*;
import java.security.SecureRandom;


public class ContactsPage extends PhonePresetWithNoWallpaper implements ActionListener, KeyListener{
    // Variable declarations and definitions
    private static final Font font = new Font("Raleway", Font.PLAIN, 14);
    private static final Font boldFont = new Font("Raleway", Font.BOLD, 14);

    private JButton homeBtn = new JButton();
    private JButton addBtn  = new JButton();
    private JTextField searchBar = new JTextField("Search Contact", 20);
    private JButton searchBtn = new JButton();
    private String searchString = "";

    // Panel to hold contacts
    private JPanel contactListPanel = new JPanel();

    private ArrayList<JButton> contactBtns = new ArrayList<JButton>();

    // contacts list to be displayed
    private ResultSet contacts; 
    // DB Manager object
    private MyDatabaseManager db = new MyDatabaseManager();

    private SecureRandom randomNumbers = new SecureRandom(); // object for random numbers


    // Constructor -->
    public ContactsPage(){
        // Change background color of parent panel
        setBackground(new Color(0xFCE8DF));

        // Add contact button
        Icon addIcon = new ImageIcon(getClass().getResource("./images/icons/add.png"));
        addBtn.setBounds(15, 50, 50, 30);
        addBtn.setIcon(addIcon);
        super.makeButtonTransparent(addBtn, false);
        addBtn.setFocusable(false);
        add(addBtn);
        addBtn.addActionListener(this);
        
        setLayout(null);
        contacts = db.fetchAllContacts();
   
        // Home Button
		Icon homebar = new ImageIcon(getClass().getResource("./images/homebar.png"));
		homeBtn.setIcon(homebar);
        homeBtn.setForeground(Color.BLUE);
        homeBtn.setBounds(78, 532, 131, 10);
        homeBtn.setHorizontalAlignment(SwingConstants.CENTER);
        super.makeButtonTransparent(homeBtn, false);
        homeBtn.setFocusable(false);
        add(homeBtn);
        homeBtn.addActionListener(this);	
	
        // Search Bar
        searchBar.setBounds(26, 90, 185, 25);
        searchBar.setFont(font);
        add(searchBar);
        searchBar.addKeyListener(this);

        // Search Button
        Icon searchIcon = new ImageIcon(getClass().getResource("./images/icons/search.png"));
        searchBtn.setBounds(206, 85, 53, 30);
        searchBtn.setIcon(searchIcon);
        super.makeButtonTransparent(searchBtn, false);
        searchBtn.setFocusable(false);
        add(searchBtn);
        searchBtn.addActionListener(this);

        // Contacts
        JLabel label = new JLabel("Contacts");
        label.setBounds(26, 120, 190, 20);
        label.setFont(font);
        label.setForeground(Color.GRAY);
        add(label);
        
        addRecAndConTab();

        showContacts();

        addInnerJPanel();

    }// <-- end Constructor

     // add panel on which list wil be written
     public void addInnerJPanel(){
        // Add scroll pane to inner panel
        JScrollPane scrollPane = new JScrollPane(contactListPanel,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds( 20, 145, 247, 320 );
        scrollPane.setPreferredSize(new Dimension(160, 200));
        scrollPane.setBorder(null);

        scrollPane.getVerticalScrollBar().setUnitIncrement(10);

        int count = db.countNumOfRowsFrom(db.fetchAllContacts());
                
        contactListPanel.setPreferredSize(new Dimension(600,(count*45+1) ));
        contactListPanel.setLayout(null);
        contactListPanel.setBackground(Color.WHITE);

        add(scrollPane);

    }

    // Show contacts on screen from param array
    public void showContacts(){
        // stating values
        int x = 3, y = 10, w = 224, h = 45;

        // Contacts List
        int i = 0;
        contactBtns.clear();

        // show contacts if phonebook is not empty
        try{
            // System.out.printf("%-5s%-20s%-20s%-20s%n", "ID","NAME","PHONE","IMAGE");
            if(contacts.next() == true){
                do{
                    //--show list in console
                    // System.out.printf("%-5s%-20s%-20s%-20s%n",
                    // contacts.getInt("id"),contacts.getString("name"),
                    // contacts.getString("phone"), contacts.getString("image"));
    
                    //--show list on phone screen
                    String phone = contacts.getString("phone");
                    String name = ((contacts.getString("name")).contentEquals("")) ? phone : (contacts.getString("name"));
                    String userImageUrl = contacts.getString("image");
                    Icon image;
    
                    int num = 1 + randomNumbers.nextInt(6);
                    String defaultImageUrl = "./images/icons/contacts/contacts-"+num+".png";

    
                    if(userImageUrl == null){
                        image = new ImageIcon(getClass().getResource(defaultImageUrl));
                    }
                    else{
                        try{
                            image = super.resizeSelectedImage(userImageUrl, 32, 32);
                        }catch(Exception exc){
                            image = new ImageIcon(getClass().getResource(defaultImageUrl));
                        }
                    }
    
                    String str = String.format("<html><b>%s</b><br>%s</html>", name, phone);
    
                    contactBtns.add(new JButton(str));
                    contactBtns.get(i).setFont(font);
                    contactBtns.get(i).setIcon(image);
                    contactBtns.get(i).setBounds(x, y, w, h);
                    contactBtns.get(i).setIconTextGap(10);
                    contactBtns.get(i).setVerticalAlignment(SwingConstants.CENTER);
                    contactBtns.get(i).setHorizontalAlignment(SwingConstants.LEFT);
    
                    contactBtns.get(i).setBackground(Color.WHITE);
                                
                    contactListPanel.add(contactBtns.get(i));
        
                    // add an action listener
                    contactBtns.get(i).addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            // Show single contact page
                            showSingleContactPage(name, phone, userImageUrl, e);
                        }
                    });
                    
                    i++;
                    y += h+2;
                }while(contacts.next());// end do-while loop

            }else{
                // System.out.println("contacts list is empty");
                JLabel nothingFound = new JLabel("Nothing Found!");
                nothingFound.setForeground(Color.GRAY);
                nothingFound.setHorizontalAlignment(SwingConstants.CENTER);
                nothingFound.setVerticalAlignment(SwingConstants.CENTER);
                nothingFound.setFont(font);
                nothingFound.setBounds(5, 110, 235, 45);
                contactListPanel.add(nothingFound);
            }

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
       

    }// <-- end showContacts
       
    // Draw buttons at the bottom ie call logs, contacts and dialpad
    public void addRecAndConTab() {
    	JLabel recentLabel = new JLabel("New label");
    	recentLabel.setBounds(65, 492, 34, 34);
        recentLabel.setIcon(new ImageIcon(getClass().getResource("/images/icons/recent.png")));
        recentLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        recentLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                changeCurrentPage("logs");
            }
        });
        add(recentLabel);
        
        JLabel dialLabel = new JLabel("New label");
        dialLabel.setBounds(188, 492, 34, 34);
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
            // System.out.println("searching for: " + searchString);
            makeSearch();
        }

        if( e.getSource() == addBtn ){
            // go to add contacts page
            AddContact panel = new AddContact("");
            NewWindowFrame frame = new NewWindowFrame(panel);
            frame.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
        }
    }// <-- end actionPerformed

    // Search for contact in database
    public void makeSearch(){
        if(!searchString.contentEquals("")){
            contacts = db.findContactByNameOrPhone(searchString);
            contactListPanel.removeAll();
            contactListPanel.revalidate();
            showContacts();

            // Change size of scroll panel
            int count = db.countNumOfRowsFrom(db.findContactByNameOrPhone(searchString));
            contactListPanel.setPreferredSize(new Dimension(600,(count*45+1) ));
            contactListPanel.repaint(); // show changes made
        }
    }
        
    // Handle KeyListener events
    public void keyTyped(KeyEvent e){
        char c = e.getKeyChar();
        searchString = searchBar.getText() + c;
        // System.out.println(searchString);
        makeSearch();
    }// <-- end keyTyped

    public void keyPressed(KeyEvent e){
        // code...
    }// <-- end keyPressed

    public void keyReleased(KeyEvent e){
        // code...
    }// <-- end keyReleased
  
    // Show single contact page
    public void showSingleContactPage(String name, String number, String image, ActionEvent e){
        ContactDetailsPage panel = new ContactDetailsPage(name, number, image);
        NewWindowFrame frame = new NewWindowFrame(panel);
        frame.setVisible(true);
        ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
        // System.out.println(((JButton)(e.getSource())).getText());

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
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class ContactsPage extends PhonePresetWithNoWallpaper implements ActionListener, KeyListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
    private final JPanel timePanel = new JPanel();
    private final JPanel panel_1 = new JPanel();
    private final JLabel lblToday = new JLabel(" Today");
    private final JPanel missedCallPlane_1 = new JPanel();
    private final JPanel panel_1_1 = new JPanel();
    private final JLabel lblNewLabel_2 = new JLabel("");
    private final JLabel lblNewLabel_1_2 = new JLabel("Akyena");
    private final JLabel lblNewLabel_1_1_1 = new JLabel("10:20");
    private final JPanel Recievedcallpanel = new JPanel();
    private final JPanel panel_1_1_1 = new JPanel();
    private final JLabel lblNewLabel_2_1 = new JLabel("");
    private final JLabel lblNewLabel_1_2_1 = new JLabel("Robert");
    private final JLabel lblNewLabel_1_1_1_1 = new JLabel("10:20");

    public void addhomebar() {
    	   JLabel homebar = new JLabel("");
           homebar.setIcon(new ImageIcon(Calendar.class.getResource("/images/hombarbig.jpg")));
           homebar.setBounds(37, 520, 224, 14);
          add(homebar);
           
		
    }
    
    
    // Constructor -->
    public ContactsPage(){
    	addhomebar();

        // Sort contacts list
        Arrays.sort(contactNames);
        setLayout(null);
        homeBtn.setBounds(7, 60, 80, 20);
       
        // Home Button
        homeBtn.setFont(font);
        homeBtn.setForeground(Color.BLUE);
        homeBtn.setHorizontalAlignment(SwingConstants.LEFT);
        makeButtonTransparent(homeBtn, false);
        add(homeBtn);
        homeBtn.addActionListener(this);
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

    
    
    
    //  RECENT AND CONTACTS TAB CODES
    public void addRecAndConTab() {

		JLabel recentLabel = new JLabel("New label");
    	recentLabel.setBounds(49, 471, 34, 34);
    	 recentLabel.setIcon(new ImageIcon(ContactsPage.class.getResource("/images/icons/recent.png")));
         recentLabel.setVerticalAlignment(SwingConstants.BOTTOM);
         add(recentLabel);
         
         
    	//Event listenr for recent icon at bottom
        recentLabel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		 removeAll();// remove all component
  			     revalidate();// check if plane is availale
  			     repaint();// reinput new graphics
  			     addhomebar();
  			   addRecAndConTab();
  			   showAllLogs(1);
        		
        	}
        });
        
        // Contacts icon on bottom right
       
        JLabel contactsLabel = new JLabel("New label");
        contactsLabel.setBounds(183, 471, 34, 34);
        contactsLabel.setIcon(new ImageIcon(ContactsPage.class.getResource("/images/icons/contac.png")));
        contactsLabel.setVerticalAlignment(SwingConstants.BOTTOM);
       add(contactsLabel);
     
        
    }
    
    public void showAllLogs(int num) {
    	
    	// All and missed tabs at the topmost corner
    	
    	JPanel panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.setBorder(new LineBorder(new Color(169, 169, 169), 3, true));
        panel_2.setBackground(Color.BLUE);
        panel_2.setBounds(71, 50, 124, 30);
        add(panel_2);
        
        
        JPanel allPanel = new JPanel();
        allPanel.setLayout(null);
        allPanel.setBackground(SystemColor.controlDkShadow);
        allPanel.setBounds(0, 0, 62, 30);
        panel_2.add(allPanel);
        
        
        JPanel missedPanel = new JPanel();
        missedPanel.setBackground(Color.BLACK);
        missedPanel.setBounds(62, 0, 62, 30);
        panel_2.add(missedPanel);
        
        
        // labels put on the All and missed panels to chane colors
        JLabel allLabel = new JLabel("All");
  
        allLabel.setHorizontalAlignment(SwingConstants.CENTER);
        allLabel.setForeground(Color.WHITE);
        allLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        allLabel.setBounds(3, 5, 56, 16);
        allPanel.add(allLabel);
        
        
        
        JLabel misslabel = new JLabel("Missed");
        misslabel.setHorizontalAlignment(SwingConstants.CENTER);
        misslabel.setForeground(Color.WHITE);
        misslabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        missedPanel.add(misslabel);
        misslabel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		

       		
       		    removeAll();
			     revalidate();
			     addhomebar();
			     repaint();
			   addRecAndConTab();
			   showAllLogs(2);
			   missedPanel.setBackground(SystemColor.controlDkShadow);
	       	   allPanel.setBackground(Color.BLACK);
        	}
        });
       
       // panel for  showing yesterday and things
        timePanel.setLayout(null);
        timePanel.setForeground(Color.BLACK);
        timePanel.setBackground(Color.BLACK);
        timePanel.setBounds(30, 90, 220, 24);
        
        add(timePanel);
        panel_1.setLayout(null);
        panel_1.setBackground(Color.WHITE);
        panel_1.setBounds(0, 0, 265, 21);
        
        timePanel.add(panel_1);
        lblToday.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblToday.setBounds(0, 0, 171, 21);
        
        panel_1.add(lblToday);
        
        
      //num 1 for all logs 2 for only missed call 
        if(num == 1) {
        	
        	
        	// missed call planes
        	missedCallPlane_1.setLayout(null);
            missedCallPlane_1.setForeground(Color.BLACK);
            missedCallPlane_1.setBackground(Color.BLACK);
            missedCallPlane_1.setBounds(31, 115, 218, 60);
            
            add(missedCallPlane_1);
            panel_1_1.setLayout(null);
            panel_1_1.setBackground(Color.WHITE);
            panel_1_1.setBounds(0, 0, 218, 59);
            
            missedCallPlane_1.add(panel_1_1);
            lblNewLabel_2.setIcon(new ImageIcon(ContactsPage.class.getResource("/images/icons/contacts-32.png")));
            lblNewLabel_2.setBounds(12, 10, 43, 33);
            
            panel_1_1.add(lblNewLabel_2);
            lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
            lblNewLabel_1_2.setBounds(84, 3, 132, 24);
            
            panel_1_1.add(lblNewLabel_1_2);
            lblNewLabel_1_1_1.setIcon(new ImageIcon(ContactsPage.class.getResource("/images/icons/missedCall.png")));
            lblNewLabel_1_1_1.setForeground(Color.RED);
            lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
            lblNewLabel_1_1_1.setBounds(84, 28, 132, 24);
                        
           panel_1_1.add(lblNewLabel_1_1_1);
            
           
           
           
           // Received call plane 
            Recievedcallpanel.setLayout(null);
            Recievedcallpanel.setForeground(Color.BLACK);
            Recievedcallpanel.setBackground(Color.BLACK);
            Recievedcallpanel.setBounds(31, 175, 218, 60);
            
            add(Recievedcallpanel);
            panel_1_1_1.setLayout(null);
            panel_1_1_1.setBackground(Color.WHITE);
            panel_1_1_1.setBounds(0, 0, 218, 59);
            
            Recievedcallpanel.add(panel_1_1_1);
            lblNewLabel_2_1.setIcon(new ImageIcon(ContactsPage.class.getResource("/images/icons/contacts-32.png")));
            lblNewLabel_2_1.setBounds(12, 10, 43, 33);
            
            panel_1_1_1.add(lblNewLabel_2_1);
            lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
            lblNewLabel_1_2_1.setBounds(84, 3, 132, 24);
            
            panel_1_1_1.add(lblNewLabel_1_2_1);
            lblNewLabel_1_1_1_1.setIcon(new ImageIcon(ContactsPage.class.getResource("/images/icons/callout.png")));
            lblNewLabel_1_1_1_1.setForeground(SystemColor.textHighlight);
            lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
            lblNewLabel_1_1_1_1.setBounds(84, 28, 132, 24);
            
            panel_1_1_1.add(lblNewLabel_1_1_1_1);
          
            
          
          
        }
        
       if(num == 2) {
    	   // if i put 2 in function(showlogs fucntion ) when missed tab at bottom is pressed all missed calls show
    	  
    	   
    	   // this shows the all and miss tab at the top again
    	   missedPanel.setBackground(SystemColor.controlDkShadow);
       	   allPanel.setBackground(Color.BLACK);   
       	   
           allLabel.addMouseListener(new MouseAdapter() {
           	@Override
           	public void mouseClicked(MouseEvent e) {
           		 missedPanel.setBackground(Color.black);
           		 allPanel.setBackground(SystemColor.controlDkShadow);
           		 removeAll();
  			     revalidate();
  			     repaint();
  			     addhomebar();
  			    addRecAndConTab();
  			    showAllLogs(1);   // calls the showLogs code which shows all call logs
  			    
  		
           	}
           });
          
           
           
           missedCallPlane_1.setLayout(null);
           missedCallPlane_1.setForeground(Color.BLACK);
           missedCallPlane_1.setBackground(Color.BLACK);
           missedCallPlane_1.setBounds(31, 115, 218, 60);
           
           add(missedCallPlane_1);
           panel_1_1.setLayout(null);
           panel_1_1.setBackground(Color.WHITE);
           panel_1_1.setBounds(0, 0, 218, 59);
           
           missedCallPlane_1.add(panel_1_1);
           lblNewLabel_2.setIcon(new ImageIcon(ContactsPage.class.getResource("/images/icons/contacts-32.png")));
           lblNewLabel_2.setBounds(12, 10, 43, 33);
           
           panel_1_1.add(lblNewLabel_2);
           lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
           lblNewLabel_1_2.setBounds(84, 3, 132, 24);
           
           panel_1_1.add(lblNewLabel_1_2);
           lblNewLabel_1_1_1.setIcon(new ImageIcon(ContactsPage.class.getResource("/images/icons/missedCall.png")));
           lblNewLabel_1_1_1.setForeground(Color.RED);
           lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
           lblNewLabel_1_1_1.setBounds(84, 28, 132, 24);
           
           panel_1_1.add(lblNewLabel_1_1_1);
        }
        
        
        
        
    }
    
    
    
    
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
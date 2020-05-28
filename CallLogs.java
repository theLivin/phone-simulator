import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class CallLogs extends PhonePresetWithNoWallpaper implements ActionListener, KeyListener{

    private JButton homeBtn = new JButton();

    private MyDatabaseManager db = new MyDatabaseManager();
    private ResultSet allCalls;
    private ResultSet missedCalls;

    String today;

    // Control how events are added; make sure listeners are added only once 
    boolean eventsAdded = false;

    private JPanel allPanel = new JPanel();
    private JLabel allLabel = new JLabel("All");
    private JPanel missedPanel = new JPanel();
    private JLabel missedLabel = new JLabel("Missed");

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

    // Constructor -->
    public CallLogs(){
        setLayout(null);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("E, MMM d yyyy");
		today = dateFormat.format(now);
        
        showAllCalls();
        
        // Add event listeners to nav buttons      
        if(!eventsAdded){
            missedLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    showMissedCalls();
                    // System.out.println("Missed Label pressed");
                    missedLabel.setForeground(Color.WHITE);
                    missedPanel.setBackground(SystemColor.controlDkShadow);
                    allPanel.setBackground(Color.BLACK);
                }
            });
    
            allLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    showAllCalls();
                    // System.out.println("All Label pressed");
                    allLabel.setForeground(Color.WHITE);
                    allPanel.setBackground(SystemColor.controlDkShadow);
                    missedPanel.setBackground(Color.BLACK);
                }
            });
            homeBtn.addActionListener(this);

            eventsAdded = true;
        }

    }// <-- end Constructor

    //RECENT AND CONTACTS TAB CODES
    public void addRecAndConTab() {
        JLabel dialLabel = new JLabel("New label");
        dialLabel.setBounds(62, 489, 34, 34);
        dialLabel.setIcon(new ImageIcon(getClass().getResource("/images/icons/dialpad.png")));
        dialLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        dialLabel.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent arg0) {
        		changeCurrentPage("dial");
        	}
        });
        add(dialLabel);   
		
        
        JLabel contactsLabel = new JLabel("New label");
        contactsLabel.setBounds(185, 489, 34, 34);
        contactsLabel.setIcon(new ImageIcon(getClass().getResource("/images/icons/contac.png")));
        contactsLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        contactsLabel.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent arg0) {
        		changeCurrentPage("contacts");
        	}
        });
        add(contactsLabel); 
        
        // Home Button
		Icon homebar = new ImageIcon(getClass().getResource("./images/homebar.png"));
		homeBtn.setIcon(homebar);
        homeBtn.setForeground(Color.BLUE);
        homeBtn.setBounds(75, 532, 131, 10);
        homeBtn.setHorizontalAlignment(SwingConstants.CENTER);
        super.makeButtonTransparent(homeBtn, false);
        homeBtn.setFocusable(false);
        add(homeBtn);
        
    }

    private void showNavigationButtons(){
        // Show top nav
        JPanel topNav = new JPanel();
        topNav.setLayout(null);
        topNav.setBorder(new LineBorder(new Color(169, 169, 169), 3, true));
        topNav.setBackground(Color.WHITE);
        topNav.setBounds(77, 50, 125, 30);
        add(topNav);
        
        allPanel.setLayout(null);
        allPanel.setBackground(SystemColor.controlDkShadow);
        allPanel.setBounds(0, 0, 62, 30);
        topNav.add(allPanel);

        allLabel.setHorizontalAlignment(SwingConstants.CENTER);
        allLabel.setForeground(Color.WHITE);
        allLabel.setFont(new Font("Raleway", Font.PLAIN, 15));
        allLabel.setBounds(3, 5, 56, 16);
        allPanel.add(allLabel);
        
        
        missedPanel.setBackground(Color.BLACK);
        missedPanel.setBounds(62, 0, 62, 30);
        topNav.add(missedPanel);
        
        missedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        missedLabel.setForeground(Color.WHITE);
        missedLabel.setFont(new Font("Raleway", Font.PLAIN, 15));
        missedPanel.add(missedLabel);


        // System.out.println("all label mouse listeners count : "+allLabel.getMouseListeners().length);
        // System.out.println("missed label mouse listeners count : "+missedLabel.getMouseListeners().length);

        // Show bottom nav
        addRecAndConTab();

    }

    public void showMissedCalls(){
        removeAll();
        revalidate();
        repaint(); 
        showNavigationButtons();

        missedCalls = db.fetchSpecificCallLog("missed");
        
        System.out.println("Missed Calls");
        try{
            while(missedCalls.next()){
                System.out.printf("%10s%10s%10s%n", missedCalls.getString("phone"), missedCalls.getString("time"), allCalls.getString("category"));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    
    
    public void showAllCalls() {
        removeAll();
        revalidate();
        repaint(); 
        showNavigationButtons();

        allCalls = db.fetchAllCallLog();

        System.out.println("All Calls");
        try{
            while(allCalls.next()){
                System.out.printf("%10s%10s%10s%n", allCalls.getString("phone"), allCalls.getString("time"), allCalls.getString("category"));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        
        // timePanel.setLayout(null);
        // timePanel.setForeground(Color.BLACK);
        // timePanel.setBackground(Color.BLACK);
        // timePanel.setBounds(30, 90, 220, 24);
        // add(timePanel);


        // panel_1.setLayout(null);
        // // panel_1.setBackground(Color.WHITE);
        // // panel_1.setBounds(0, 0, 265, 21);
        
        // timePanel.add(panel_1);
        // lblToday.setFont(new Font("Raleway", Font.PLAIN, 16));
        // lblToday.setForeground(Color.GRAY);
        // lblToday.setBounds(0, 0, 171, 21);
        
        // panel_1.add(lblToday);

        //num 1 for all logs 2 for only missed call 
        // if(num == 1) {
        	// missedCallPlane_1.setLayout(null);
            // missedCallPlane_1.setForeground(Color.BLACK);
            // missedCallPlane_1.setBackground(Color.BLACK);
            // missedCallPlane_1.setBounds(31, 115, 218, 60);
            
            // add(missedCallPlane_1);
            // panel_1_1.setLayout(null);
            // panel_1_1.setBackground(Color.WHITE);
            // panel_1_1.setBounds(0, 0, 218, 59);
            
            // missedCallPlane_1.add(panel_1_1);
            // lblNewLabel_2.setIcon(new ImageIcon(ContactsPage.class.getResource("/images/icons/contacts-32.png")));
            // lblNewLabel_2.setBounds(12, 10, 43, 33);
            
            // panel_1_1.add(lblNewLabel_2);
            // lblNewLabel_1_2.setFont(new Font("Raleway", Font.PLAIN, 20));
            // lblNewLabel_1_2.setBounds(84, 3, 132, 24);
            
            // panel_1_1.add(lblNewLabel_1_2);
            // lblNewLabel_1_1_1.setIcon(new ImageIcon(ContactsPage.class.getResource("/images/icons/missedCall.png")));
            // lblNewLabel_1_1_1.setForeground(Color.RED);
            // lblNewLabel_1_1_1.setFont(new Font("Raleway", Font.PLAIN, 16));
            // lblNewLabel_1_1_1.setBounds(84, 28, 132, 24);
            
            // panel_1_1.add(lblNewLabel_1_1_1);
            
            // Recievedcallpanel.setLayout(null);
            // Recievedcallpanel.setForeground(Color.BLACK);
            // Recievedcallpanel.setBackground(Color.BLACK);
            // Recievedcallpanel.setBounds(31, 175, 218, 60);
            
            // add(Recievedcallpanel);
            // panel_1_1_1.setLayout(null);
            // panel_1_1_1.setBackground(Color.WHITE);
            // panel_1_1_1.setBounds(0, 0, 218, 59);
            
            // Recievedcallpanel.add(panel_1_1_1);
            // lblNewLabel_2_1.setIcon(new ImageIcon(ContactsPage.class.getResource("/images/icons/contacts-32.png")));
            // lblNewLabel_2_1.setBounds(12, 10, 43, 33);
            
            // panel_1_1_1.add(lblNewLabel_2_1);
            // lblNewLabel_1_2_1.setFont(new Font("Raleway", Font.PLAIN, 20));
            // lblNewLabel_1_2_1.setBounds(84, 3, 132, 24);
            
            // panel_1_1_1.add(lblNewLabel_1_2_1);
            // lblNewLabel_1_1_1_1.setIcon(new ImageIcon(ContactsPage.class.getResource("/images/icons/callout.png")));
            // lblNewLabel_1_1_1_1.setForeground(SystemColor.textHighlight);
            // lblNewLabel_1_1_1_1.setFont(new Font("Raleway", Font.PLAIN, 16));
            // lblNewLabel_1_1_1_1.setBounds(84, 28, 132, 24);
            
            // panel_1_1_1.add(lblNewLabel_1_1_1_1);
          
        // }
        
    //    if(num == 2) {
    	//    missedPanel.setBackground(SystemColor.controlDkShadow);
       	//    allPanel.setBackground(Color.BLACK);   
        //    allLabel.addMouseListener(new MouseAdapter() {
        //    	@Override
        //    	public void mouseClicked(MouseEvent e) {
        //    		 missedPanel.setBackground(Color.black);
        //    		 allPanel.setBackground(SystemColor.controlDkShadow);
        //    		 removeAll();
  		// 	     revalidate();
  		// 	     repaint();
  		// 	    addRecAndConTab();
  		// 	    showAllLogs(1);
        //    	}
        //    });
           
        //    missedCallPlane_1.setLayout(null);
        //    missedCallPlane_1.setForeground(Color.BLACK);
        //    missedCallPlane_1.setBackground(Color.BLACK);
        //    missedCallPlane_1.setBounds(31, 115, 218, 60);
           
        //    add(missedCallPlane_1);
        //    panel_1_1.setLayout(null);
        //    panel_1_1.setBackground(Color.WHITE);
        //    panel_1_1.setBounds(0, 0, 218, 59);
           
        //    missedCallPlane_1.add(panel_1_1);
        //    lblNewLabel_2.setIcon(new ImageIcon(ContactsPage.class.getResource("/images/icons/contacts-32.png")));
        //    lblNewLabel_2.setBounds(12, 10, 43, 33);
           
        //    panel_1_1.add(lblNewLabel_2);
        //    lblNewLabel_1_2.setFont(new Font("Raleway", Font.PLAIN, 20));
        //    lblNewLabel_1_2.setBounds(84, 3, 132, 24);
           
        //    panel_1_1.add(lblNewLabel_1_2);
        //    lblNewLabel_1_1_1.setIcon(new ImageIcon(ContactsPage.class.getResource("/images/icons/missedCall.png")));
        //    lblNewLabel_1_1_1.setForeground(Color.RED);
        //    lblNewLabel_1_1_1.setFont(new Font("Raleway", Font.PLAIN, 16));
        //    lblNewLabel_1_1_1.setBounds(84, 28, 132, 24);
           
        //    panel_1_1.add(lblNewLabel_1_1_1);
        // }

             
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

    }// <-- end actionPerformed    
    
    // Handle KeyListener events
    public void keyTyped(KeyEvent e){
        // code...
    }// <-- end keyTyped

    public void keyPressed(KeyEvent e){
        // code...
    }// <-- end keyPressed

    public void keyReleased(KeyEvent e){
        // code...
    }// <-- end keyReleased
    
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
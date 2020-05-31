import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.security.*;


public class CallLogs extends PhonePresetWithNoWallpaper implements ActionListener, KeyListener{

    // Clear Call log button
    private JButton homeBtn = new JButton();
    JButton clearBtn = new JButton();
    
    private SecureRandom randomNumbers = new SecureRandom(); // object for random numbers

    // Panel to hold contacts
    private JPanel contactListPanel = new JPanel();

    private String today;
    private String yesterday;

    // Control how events are added; make sure listeners are added only once 
    private boolean eventsAdded = false;

    private JPanel allPanel = new JPanel();
    private JLabel allLabel = new JLabel("All");
    private JPanel missedPanel = new JPanel();
    private JLabel missedLabel = new JLabel("Missed");

  
    // Constructor -->
    public CallLogs(){
        setLayout(null);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("E, MMM d yyyy");
        LocalDateTime now = LocalDateTime.now();
        today = dateFormat.format(now);
        LocalDate yestee = (LocalDate.now()).minusDays(1);
        yesterday = dateFormat.format(yestee);
        
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
            clearBtn.addActionListener(this);

            eventsAdded = true;
        }

        addInnerJPanel();    

    }// <-- end Constructor

    // add panel on which list wil be written
    public void addInnerJPanel(){
        // Add scroll pane to inner panel
        JScrollPane scrollPane = new JScrollPane(contactListPanel,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds( 20, 90, 247, 380 );
        scrollPane.setPreferredSize(new Dimension(160, 200));
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);

        contactListPanel.setLayout(null);
        contactListPanel.setBackground(Color.WHITE);
        add(scrollPane);

        showNavigationButtons();
    }

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
        
        Icon addIcon = new ImageIcon(getClass().getResource("./images/icons/delete.png"));
        clearBtn.setBounds(223, 50, 50, 30);
        clearBtn.setIcon(addIcon);
        super.makeButtonTransparent(clearBtn, false);
        clearBtn.setFocusable(false);
        add(clearBtn);

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

    // display missed calls
    public void showMissedCalls(){
        contactListPanel.removeAll();
        contactListPanel.revalidate();
        contactListPanel.repaint();

        System.out.println("Missed Calls");
        MyDatabaseManager db = new MyDatabaseManager();
        displayCallLogs(db.fetchSpecificCallLog("missed"));

        // Resize scroll bar
        int count = db.countNumOfRowsFrom( db.fetchSpecificCallLog("missed"));
        contactListPanel.setPreferredSize(new Dimension(247, (count*65)));
        contactListPanel.repaint(); // show changes made
    }

    
    // display all calls
    public void showAllCalls() {
        contactListPanel.removeAll();
        contactListPanel.revalidate();
        contactListPanel.repaint(); 

        System.out.println("All Calls");
        MyDatabaseManager db = new MyDatabaseManager();
        displayCallLogs(db.fetchAllCallLog());

        // Resize scroll bar
        int count = db.countNumOfRowsFrom( db.fetchAllCallLog());
        contactListPanel.setPreferredSize(new Dimension(247, (count*65)));
        contactListPanel.repaint(); // show changes made
     
    }

    // display call logs function
    public void displayCallLogs(ResultSet logsList){
        // starting values
        int x = 10, y = 5, w = 200, h = 60;
 
        // Display all call logs if it is not empty
        try{
            
            // Display contact list now
            if(logsList.next()){
                do{
                    String phone =  logsList.getString("log_phone");
                    String time = logsList.getString("time");
                    String date = logsList.getString("date");
                    String category = logsList.getString("category");
                    String categoryIconUrl = "";

                    String name = logsList.getString("name");
                    name = (name == null || name.contentEquals("")) ? phone : name;

                    if(category.contentEquals("missed")){
                        categoryIconUrl = "./images/icons/missedCall.png";
                    }
                    else if(category.contentEquals("dialed")){
                        categoryIconUrl = "./images/icons/callout.png";
                    }
    
                    int num = 1 + randomNumbers.nextInt(6);
                    String defaultImageUrl = "./images/icons/contacts/contacts-"+num+".png";
    
                    Icon contactImage = new ImageIcon(getClass().getResource(defaultImageUrl));
                    
    
                    // System.out.printf("%10s%10s%10s%n", phone, time, category);
                    Icon categoryIcon = new ImageIcon(ContactsPage.class.getResource(categoryIconUrl));

                    // making the line under the Calendar label
                    JSeparator separator = new JSeparator();
                    separator.setBackground(Color.LIGHT_GRAY);
                    separator.setBounds(x , y, w, 2);
                    contactListPanel.add(separator);
                    y += 4;

                    // outer label for name/phone                    
                    JLabel contactLog = new JLabel();
                    contactLog.setText(String.format("%s",name));
                    contactLog.setIcon(contactImage);
                    contactLog.setForeground(Color.DARK_GRAY);
                    contactLog.setFont(new Font("Raleway", Font.PLAIN, 18));
                    contactLog.setHorizontalAlignment(SwingConstants.LEFT);
                    contactLog.setVerticalAlignment(SwingConstants.TOP);
                    contactLog.setHorizontalTextPosition(SwingConstants.RIGHT);
                    contactLog.setVerticalTextPosition(SwingConstants.TOP);
                    contactLog.setIconTextGap(22);
                    contactLog.setBounds(x, y, w, h);
                    contactLog.setLayout(null);

                    // inner label for icon and time
                    String day = date;
                    String innerStr = String.format("<html>%s<br>%s</html>", day, time);
                    
                    if(today.compareTo(date) == 0){
                        innerStr = String.format("Today, %s", time);
                    }
                    else if(yesterday.compareTo(date) == 0){
                        innerStr = String.format("Yesterday, %s", time);;
                    }

                    // inner label
                    JButton inner = new JButton(innerStr, categoryIcon);
                    super.makeButtonTransparent(inner, false);
                    inner.setBounds(40, 26, 180, 30);
                    inner.setFocusable(false);
                    inner.setFont(new Font("Raleway", Font.PLAIN, 12));
                    inner.setHorizontalAlignment(SwingConstants.LEFT);
                    
                    contactLog.add(inner);
                    contactListPanel.add(contactLog);
                    y = y+h+1;

                    // add an action listener
                    contactLog.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            makeCall(phone);
                        }
                    });
                    
                }while(logsList.next());// end do while loop

            }else{
                JLabel nothingFound = new JLabel("Nothing Found!");
                nothingFound.setForeground(Color.GRAY);
                nothingFound.setHorizontalAlignment(SwingConstants.CENTER);
                nothingFound.setVerticalAlignment(SwingConstants.CENTER);
                nothingFound.setFont(new Font("Raleway", Font.PLAIN, 14));
                nothingFound.setBounds(5, 110, 235, 45);
                contactListPanel.add(nothingFound);     
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    // make a call
    public void makeCall(String contactNumber){
        // System.out.println("dialing "+contactName+"...");
        PhoneDial panel = new PhoneDial();
        panel.setTextFieldText(contactNumber);
        NewWindowFrame frame = new NewWindowFrame(panel);
        frame.setVisible(true);
        ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
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
        if( e.getSource() == clearBtn ){
            // Go to home screen
            MyDatabaseManager db = new MyDatabaseManager();
            if(db.clearCallLog()){
                // refresh page
                CallLogs panel = new CallLogs();
                NewWindowFrame frame = new NewWindowFrame(panel);
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
            }
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
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.security.*;


public class MessageLogs extends PhonePresetWithNoWallpaper implements ActionListener, KeyListener{

    // Clear Call log button
    private JButton homeBtn = new JButton();
    JButton composeBtn = new JButton();
    JButton clearBtn = new JButton();
    
    private SecureRandom randomNumbers = new SecureRandom(); // object for random numbers

    // Panel to hold contacts
    private JPanel contactListPanel = new JPanel();

    private String today;
    private String yesterday;

    // Control how events are added; make sure listeners are added only once 
    private boolean eventsAdded = false;

  
    // Constructor -->
    public MessageLogs(){
        setLayout(null);

        
		// Home Bar
		Icon homebar = new ImageIcon(getClass().getResource("./images/homebar.png"));
		homeBtn.setIcon(homebar);
        homeBtn.setForeground(Color.BLUE);
        homeBtn.setBounds(75, 532, 131, 10);
        homeBtn.setHorizontalAlignment(SwingConstants.CENTER);
		super.makeButtonTransparent(homeBtn, false);
		homeBtn.setFocusable(false);
        add(homeBtn);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("E, MMM d yyyy");
        LocalDateTime now = LocalDateTime.now();
        today = dateFormat.format(now);
        LocalDate yestee = (LocalDate.now()).minusDays(1);
        yesterday = dateFormat.format(yestee);
        
        showMessageLogs();
        
        // Add event listeners to nav buttons      
        if(!eventsAdded){
            
            homeBtn.addActionListener(this);
            composeBtn.addActionListener(this);
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

        
        // Resize scroll bar
        MyDatabaseManager db = new MyDatabaseManager();
        int count = db.countNumOfRowsFrom( db.getAllMessages());
        contactListPanel.setPreferredSize(new Dimension(247, (count*65)));
        contactListPanel.repaint(); // show changes made

        showNavigationButtons();
    }

    private void showNavigationButtons(){
        
        Icon createIcon = new ImageIcon(getClass().getResource("./images/icons/compose.png"));
        composeBtn.setBounds(20, 50, 50, 30);
        composeBtn.setIcon(createIcon);
        super.makeButtonTransparent(composeBtn, false);
        composeBtn.setFocusable(false);
        add(composeBtn);

        Icon addIcon = new ImageIcon(getClass().getResource("./images/icons/delete.png"));
        clearBtn.setBounds(223, 50, 50, 30);
        clearBtn.setIcon(addIcon);
        super.makeButtonTransparent(clearBtn, false);
        clearBtn.setFocusable(false);
        add(clearBtn);

        // Show top nav
        JLabel topNav = new JLabel("All Messages");
        topNav.setLayout(null);
        topNav.setForeground(Color.DARK_GRAY);
        topNav.setFont(new Font("Raleway", Font.PLAIN, 18));
        topNav.setHorizontalAlignment(SwingConstants.CENTER);
        topNav.setBounds(77, 50, 125, 30);
        add(topNav);
        
    }

    // display missed calls
    public void showMissedCalls(){
        contactListPanel.removeAll();
        contactListPanel.revalidate();
        contactListPanel.repaint();

        // System.out.println("Missed Calls");
        MyDatabaseManager db = new MyDatabaseManager();
        displayMessageLogs(db.fetchSpecificCallLog("missed"));

        // Resize scroll bar
        int count = db.countNumOfRowsFrom( db.fetchSpecificCallLog("missed"));
        contactListPanel.setPreferredSize(new Dimension(247, (count*65)));
        contactListPanel.repaint(); // show changes made
    }

    
    // display all calls
    public void showMessageLogs() {
        contactListPanel.removeAll();
        contactListPanel.revalidate();
        contactListPanel.repaint(); 

        // System.out.println("All Calls");
        MyDatabaseManager db = new MyDatabaseManager();
        displayMessageLogs(db.getAllMessages());

        // Resize scroll bar
        int count = db.countNumOfRowsFrom( db.fetchAllCallLog());
        contactListPanel.setPreferredSize(new Dimension(247, (count*65)));
        contactListPanel.repaint(); // show changes made
     
    }

    // display call logs function
    public void displayMessageLogs(ResultSet logsList){
        // starting values
        int x = 10, y = 5, w = 200, h = 60;
 
        // Display all call logs if it is not empty
        try{
            
            // Display contact list now
            if(logsList.next()){
                do{
                    
                    String phone =  logsList.getString("receiver");
                    String time = logsList.getString("time");
                    String date = logsList.getString("date");
                    String body = logsList.getString("body");
                    String name = logsList.getString("name");
                    String image = logsList.getString("image");
                    name = (name == null || name.contentEquals("")) ? phone : name;

                    Icon contactImage;
                    int num = 1 + randomNumbers.nextInt(6);
                    String defaultImageUrl = "./images/icons/contacts/contacts-"+num+".png";
                    
                    Icon userImage;
                    if(image== null || image.contentEquals("")){
                        contactImage = new ImageIcon(getClass().getResource(defaultImageUrl));
                    }else{
                        try{
                            contactImage = super.resizeSelectedImage(image, 32, 32);
                        }catch(Exception ex){
                            contactImage = new ImageIcon(getClass().getResource(defaultImageUrl));
                        }
                    }
                

                    // making the line under the Calendar label
                    JSeparator separator = new JSeparator();
                    separator.setBackground(Color.LIGHT_GRAY);
                    separator.setBounds(x , y, w+20, 1);
                    contactListPanel.add(separator);
                    y += 4;

                    // outer label for name/phone                    
                    JLabel contactLog = new JLabel();
                    contactLog.setText(String.format("%s",body));
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
                    String innerStr = String.format("<html>%s<br>to %s</html>", day, name);
                    
                    if(today.compareTo(date) == 0){
                        innerStr = String.format("Today, to %s", name);
                    }
                    else if(yesterday.compareTo(date) == 0){
                        innerStr = String.format("Yesterday, to %s", name);;
                    }

                    // inner label
                    JButton inner = new JButton(innerStr);
                    super.makeButtonTransparent(inner, false);
                    inner.setBounds(40, 26, 180, 30);
                    inner.setFocusable(false);
                    inner.setFont(new Font("Raleway", Font.PLAIN, 12));
                    inner.setHorizontalAlignment(SwingConstants.LEFT);
                    inner.setForeground(Color.MAGENTA);
                    
                    contactLog.add(inner);
                    contactListPanel.add(contactLog);
                    y = y+h+1;

                    // add an action listener
                    contactLog.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            sendMessage(phone);
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
    public void sendMessage(String contactNumber){
        // System.out.println("dialing "+contactName+"...");
        SendMessage panel = new SendMessage(contactNumber, "");
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
            if(db.clearMessageLog()){
                // refresh page
                MessageLogs panel = new MessageLogs();
                NewWindowFrame frame = new NewWindowFrame(panel);
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
            }
        }
        if( e.getSource() == composeBtn ){
            // Go to home screen
            SendMessage panel = new SendMessage("", "");
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
            MessageLogs panel = new MessageLogs();
            NewWindowFrame frame = new NewWindowFrame(panel);
            frame.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
        }
    }
}
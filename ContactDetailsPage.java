import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ContactDetailsPage extends PhonePresetWithNoWallpaper implements ActionListener{
    // Variable declarations and definitions
    private static final Font font = new Font("Raleway", Font.PLAIN, 14);

    private JButton homeBtn = new JButton();
    private JButton backBtn = new JButton("Back");
    private JButton deleteBtn = new JButton("Delete");
    private JButton msgBtn = new JButton("Send Message");
    private JLabel nameLabel;
    private JButton dialBtn;

    private String contactName;
    private String contactNumber;
    
    // Constructor -->
    public ContactDetailsPage(String contactName, String contactNumber){ 
        setLayout(null);

        this.contactName = contactName;
        this.contactNumber = contactNumber;

        // back Button
        backBtn.setFont(font);
        backBtn.setForeground(Color.BLUE);
        backBtn.setBounds(10, 70, 80, 30);
        backBtn.setHorizontalAlignment(SwingConstants.CENTER);
        super.makeButtonTransparent(backBtn, false);
        add(backBtn);
        backBtn.addActionListener(this);
        backBtn.setFocusable(false);

        // delete Button
        deleteBtn.setForeground(Color.RED);
        deleteBtn.setFont(font);
        deleteBtn.setFocusable(false);
        deleteBtn.setBounds(193, 70, 80, 30);
        deleteBtn.setHorizontalAlignment(SwingConstants.CENTER);
        super.makeButtonTransparent(deleteBtn, false);
        add(deleteBtn);
        deleteBtn.addActionListener(this);

        // Home Button
		Icon homebar = new ImageIcon(getClass().getResource("./images/homebar.png"));
		homeBtn.setIcon(homebar);
        homeBtn.setForeground(Color.BLUE);
        homeBtn.setBounds(78, 532, 131, 10);
        homeBtn.setHorizontalAlignment(SwingConstants.CENTER);
        super.makeButtonTransparent(homeBtn, false);
        add(homeBtn);
        homeBtn.addActionListener(this);	
        homeBtn.setFocusable(false);
	

        // Contacts
        showContactDetails(contactName, contactNumber);

    }// <-- end Constructor

    // Show contacts on screen from param array
    public void showContactDetails(String name, String number){
        // Load user icon
        Icon user = new ImageIcon(getClass().getResource("./images/icons/user-m.png"));

        // Contacts List
        // -- load dial icon
        Icon dial = new ImageIcon(getClass().getResource("./images/icons/dial.png"));

        int x = 26, y = 140, w = 238, h = 150;

        // -- name button
        nameLabel = new JLabel(name, user, JLabel.CENTER);
        nameLabel.setFont(new Font("Raleway", Font.BOLD, 22));
        nameLabel.setBounds(x, y, w, h);
        nameLabel.setIconTextGap(10);
        nameLabel.setVerticalTextPosition(JLabel.BOTTOM);
        nameLabel.setHorizontalTextPosition(JLabel.CENTER);
        nameLabel.setForeground(Color.DARK_GRAY);
        add(nameLabel);

        // -- dial button
        y = y+h+15;
        h = 35;
        dialBtn = new JButton(number, dial);
        dialBtn.setFont(font);
        dialBtn.setBounds(x, y, w, h);
        dialBtn.setHorizontalAlignment(SwingConstants.LEFT);
        dialBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
        dialBtn.setIconTextGap(25);
        dialBtn.setBackground(Color.WHITE);                  
        add(dialBtn);
        dialBtn.addActionListener(this);
        dialBtn.setFocusable(false);

        // -- send message Button
        y = y+h+10;
        msgBtn.setFont(font);
        msgBtn.setForeground(Color.BLUE);
        msgBtn.setBounds(x, y, w, h);
        msgBtn.setHorizontalAlignment(SwingConstants.LEFT);
        msgBtn.setBackground(Color.WHITE);
        add(msgBtn);
        msgBtn.addActionListener(this);
        msgBtn.setFocusable(false);

    }// <-- end showContactDetails

    // Handle ActionListener events
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == backBtn){
            // -- go back to contacts page
            ContactsPage panel = new ContactsPage();
            NewWindowFrame frame = new NewWindowFrame(panel);
            frame.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
        }
        if(e.getSource() == homeBtn){
            // -- go to home screen
            HomeScreen panel = new HomeScreen();
            NewWindowFrame frame = new NewWindowFrame(panel);
            frame.setVisible(true);
            ((JFrame)SwingUtilities.getWindowAncestor(this)).dispose();
        }
        if (e.getSource() == deleteBtn){
            // Delete contact
            MyDatabaseManager db = new MyDatabaseManager();
            if(db.deleteContact(contactNumber)){
                // -- go back to contacts page
                ContactsPage panel = new ContactsPage();
                NewWindowFrame frame = new NewWindowFrame(panel);
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
            }else{
                JOptionPane.showMessageDialog(this, String.format("You cannot delete this contact!"));
            }

        }
        if (e.getSource() == dialBtn){
            // System.out.println("dialing "+contactName+"...");
            PhoneCall panel = new PhoneCall(contactNumber);
            NewWindowFrame frame = new NewWindowFrame(panel);
            frame.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
        }
        if (e.getSource() == msgBtn){
            // System.out.println("we'll start messaging "+contactName+" shortly");
            // Go to messages page
            SendMessage panel = new SendMessage(contactNumber);
            NewWindowFrame frame = new NewWindowFrame(panel);
            frame.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
        }
    }// <-- end actionPerformed

}
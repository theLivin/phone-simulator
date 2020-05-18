import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class HomeScreen extends PhonePreset implements ActionListener{

    // Create home screen buttons
    private JButton[] buttons = new JButton[4];
    private JButton clock = new JButton();
    private JButton smallClock = new JButton();

    // Load image files
    Icon phone = new ImageIcon(getClass().getResource("./images/icons/phone.png"));
    Icon contacts = new ImageIcon(getClass().getResource("./images/icons/contacts.png"));
    Icon calendar = new ImageIcon(getClass().getResource("./images/icons/calendar.png"));
    Icon messages = new ImageIcon(getClass().getResource("./images/icons/messages.png"));


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        // Set layout -- remove all existing layouts
        setLayout(null);

        // Create mat for clock to on
        g.setColor(new Color(82, 97, 107, 160)); // rgba , a => transparency(alpha)
        g.fillRoundRect(23, 135, 235, 100, 30, 30);

        // Get system time
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();

        String time = timeFormat.format(now);

        // Clock
        clock.setText(time);
       
        clock.setFont(new Font("Raleway", Font.PLAIN, 72));
        clock.setForeground(Color.WHITE);
        clock.setBounds(23, 125, 235, 105);

        // -- make clock button transparent
        clock.setOpaque(false);
        clock.setContentAreaFilled(false);
        clock.setBorderPainted(false);
        add(clock);
        
        // Create mat for icons to lie on
        g.setColor(new Color(23, 31, 42, 160)); // rgba , a => transparency(alpha)
        g.fillRoundRect(23, 471, 235, 60, 30, 30);

        // Load icons and put them buttons
        // -- instantiate buttons
        for(int i=0; i<buttons.length; i++)
            buttons[i] = new JButton();
        
        
        // - put icons in an array
        Icon[] icons = {phone, contacts, calendar, messages};

        // - attach images to buttons
        for( int i=0; i<buttons.length; i++)
            buttons[i].setIcon(icons[i]);
        
        // - make buttons background transparent
        for( int i=0; i<buttons.length; i++){
            buttons[i].setOpaque(false);
            buttons[i].setContentAreaFilled(false);
            buttons[i].setBorderPainted(false);
        }

        // Set coordinates for buttons
        int btnX = 25;
        int btnY = 471;
        int btnW = 58;
        int btnH = 60;

        // Set size of buttons and add them to panel
        for(int i=0; i<buttons.length; i++){
            buttons[i].setBounds(btnX, btnY, btnW, btnH);
            add(buttons[i]);

            // -- register listeners for buttons
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    handleButtonClick((JButton)e.getSource());
                }
            });

            btnX += btnW;
        }

        clock.addActionListener(this);
        
    }

    // Handle clock click
    public void actionPerformed(ActionEvent e){
        System.out.println("clock");
    }

    // Handle icon clicks
    public void handleButtonClick(JButton src){
        
        if( src.getIcon() == phone ){
            System.out.println("phone");
            // Go to phone dialing page
            PhoneDial panel = new PhoneDial();
            NewWindowFrame frame = new NewWindowFrame(panel);
            frame.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
        }

        if( src.getIcon() == contacts ){
            System.out.println("contacts");
            // Go to contacs page
            ContactsPage panel = new ContactsPage();
            NewWindowFrame frame = new NewWindowFrame(panel);
            frame.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
        }

        if( src.getIcon() == calendar ){
            System.out.println("calendar");
            // Go to calendar page
            // code...
        }

        if( src.getIcon() == messages ){
            System.out.println("messages");
            // Go to messages page
            SendMessage panel = new SendMessage();
            NewWindowFrame frame = new NewWindowFrame(panel);
            frame.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
        }
    }

}

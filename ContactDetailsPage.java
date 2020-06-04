import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


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
    private String contactImage;
    private String newImageUrl = "";
    
    // Constructor -->
    public ContactDetailsPage(String contactName, String contactNumber, String contactImage){ 
        setLayout(null);

        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.contactImage = contactImage;

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
        showContactDetails(contactName, contactNumber, contactImage);

    }// <-- end Constructor

    // Show contacts on screen from param array
    public void showContactDetails(String name, String number, String image){
        // Load user icon

        Icon user;
        if(image == null || image.contentEquals("")){
           user = new ImageIcon(getClass().getResource("./images/icons/user-m.png"));
        }else{
            try{
                user = super.resizeSelectedImage(image, 120, 120);
            }catch(Exception ex){
                user = new ImageIcon(getClass().getResource("./images/icons/user-m.png"));
            }
        }

        // Contacts List
        // -- load dial icon
        Icon dial = new ImageIcon(getClass().getResource("./images/icons/dial.png"));

        int x = 26, y = 140, w = 238, h = 150;

        // -- name button
        nameLabel = new JLabel(name, user, JLabel.CENTER);
        nameLabel.setFont(new Font("Raleway", Font.BOLD, 22));
        nameLabel.setBounds(x, y-30, w, 200);
        nameLabel.setIconTextGap(10);
        nameLabel.setVerticalTextPosition(JLabel.BOTTOM);
        nameLabel.setHorizontalTextPosition(JLabel.CENTER);
        nameLabel.setForeground(Color.GRAY);

        // Add Event Listener to image icon
		nameLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				newImageUrl = uploadNewImage(nameLabel, 120, 120);
			}
		});
        add(nameLabel);

        // -- dial button
        y = y+h+25;
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
            // Update phonebook table with new image if contact changed it
            if(!newImageUrl.contentEquals("")){
                MyDatabaseManager db = new MyDatabaseManager();
                db.updateContactImage(contactNumber, newImageUrl);
            }

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
                JLabel msg = new JLabel("You cannot delete this contact");
                msg.setFont(new Font("Raleway", Font.PLAIN, 14));
                JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.WARNING_MESSAGE);
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
            SendMessage panel = new SendMessage(contactNumber, contactImage);
            NewWindowFrame frame = new NewWindowFrame(panel);
            frame.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
        }
    }// <-- end actionPerformed

    //Upload image
	public String uploadNewImage(JLabel label, int w, int h){
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png");
        file.addChoosableFileFilter(filter);
        setFileChooserFont(file.getComponents());

        int result = file.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile =file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            label.setIcon(super.resizeSelectedImage(path, w, h));
            label.setVerticalAlignment(SwingConstants.CENTER);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			
			return path;

        }else if(result == JFileChooser.CANCEL_OPTION){
            JLabel msg = new JLabel("No image was selected");
            msg.setFont(new Font("Raleway", Font.PLAIN, 14));
			JOptionPane.showMessageDialog(null, msg, "CANCELLED", JOptionPane.WARNING_MESSAGE);
			
		}
		
		return "";        
	}

}
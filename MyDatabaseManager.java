// Control Center for anything DATABASE

import java.sql.*;

public class MyDatabaseManager{
    private Connection conn = null;
    

    // Connect to database
    private void connect(){

        try{
            // db parameters
            String url = "jdbc:sqlite:./db/eyePhone.db";

            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established...");

            // initialise database
            initialise();

        }catch(SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }

    }

    // Initialise database with tables if no table exists -- create tables
    private void initialise(){
        Statement stmnt1 = null;
        Statement stmnt2 = null;
        Statement stmnt3 = null;
        // connect if not connected
        if(conn == null){
            connect();
        }

        try{
            String sql;

            // check if table is does not exist and create it if it does not -- contact
            stmnt1 = conn.createStatement();
            sql = "CREATE TABLE IF NOT EXISTS phonebook(" +
                "id INTEGER," +
                "name VARCHAR(20)," +
                "phone VARCHAR(20) UNIQUE NOT NULL," +
                "image VARCHAR(255)," +
                "PRIMARY KEY(id)" +
            ")";
            stmnt1.execute(sql);
            // System.out.println("contact table created or already exists");

            // check if table is does not exist and create it if it does not -- call logs
            stmnt2 = conn.createStatement();
            sql = "CREATE TABLE IF NOT EXISTS call_log(" +
                "log_id INTEGER," +
                "log_phone VARCHAR(20) NOT NULL," +
                "date VARCHAR(255) NOT NULL," +
                "time VARCHAR(10) NOT NULL," +
                "category VARCHAR(10) NOT NULL," +
                "PRIMARY KEY(log_id)" +
            ")";
            stmnt2.execute(sql);
            // System.out.println("call_log table created or already exists");

            // check if table is does not exist and create it if it does not -- message
            stmnt3 = conn.createStatement();
            sql = "CREATE TABLE IF NOT EXISTS message(" +
                "msg_id INTEGER," +
                "body VARCHAR(255) NOT NULL," +
                "date VARCHAR(255) NOT NULL," +
                "time VARCHAR(10)," +
                "receiver VARCHAR(10)," +
                "PRIMARY KEY(msg_id)" +
            ")";
            stmnt3.execute(sql);
            // System.out.println("message table created or already exists");


        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }finally{
            // release resources
            try{
                if(stmnt1 != null){
                    stmnt1.close();
                }
                if(stmnt2 != null){
                    stmnt2.close();
                }
                if(stmnt3 != null){
                    stmnt3.close();
                }
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    // Phonebook
    // -- insert into contacts table
    public boolean insertContact(String name, String phone, String image){
        PreparedStatement prepStmnt = null;

        boolean status = false;

        // connect if not connected
        if(conn == null){
            connect();
        }

        try{
            String sql;

            // add contact to phonebook table
            if(image == ""){
                sql = "INSERT INTO phonebook(name, phone) VALUES(?,?)";
                prepStmnt = conn.prepareStatement(sql);
            }
            else{
                sql = "INSERT INTO phonebook(name, phone, image) VALUES(?,?,?)";
                prepStmnt = conn.prepareStatement(sql);
                prepStmnt.setString(3, image);
            }
            prepStmnt.setString(1, name);
            prepStmnt.setString(2, phone);
            prepStmnt.execute();

            status = true;

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            status = false;

        }finally{
            // release resources
            try{
                if(prepStmnt !=null){
                    prepStmnt.close();
                }
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }

        return status;
    }

    // -- read from contacts table
    public ResultSet fetchAllContacts(){
        Statement stmnt = null;
        ResultSet res = null;

        // connect if not connected
        if(conn == null){
            connect();
        }

        try{
            String sql;

            stmnt = conn.createStatement();
            sql = "SELECT * FROM phonebook ORDER BY name";
            res = stmnt.executeQuery(sql);

            return res;
        
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return null;
        }
    }

    // -- find contact
    public String findContact(String phone){
        // find contact with phone number from phonebook table and return name
        PreparedStatement prepStmnt = null;
        ResultSet res = null;

        // connect if not connected
        if(conn == null){
            connect();
        }

        try{
            String sql;

            sql = "SELECT name FROM phonebook WHERE phone=?";
            prepStmnt = conn.prepareStatement(sql);
            prepStmnt.setString(1, phone);
            res = prepStmnt.executeQuery();

            String name = res.getString("name");

            if(!name.contentEquals(""))
                return name;
            else
                return "";

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return "";
        }
    }

    // search for contact by name or phone
    public ResultSet findContactByNameOrPhone(String arg){
        PreparedStatement prepStmnt = null;

        // connect if not connected
        if(conn == null){
            connect();
        }

        try{
            String sql;
            try{
                int num = Integer.parseInt(arg);
                sql = "SELECT * FROM phonebook WHERE phone LIKE ?";
            }catch(Exception excep){
                sql = "SELECT * FROM phonebook WHERE name LIKE ?";
            }

            prepStmnt = conn.prepareStatement(sql);
            prepStmnt.setString(1, arg+"%");
            return prepStmnt.executeQuery();

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return null;
        }
    }

    // upadate contact image
    public void updateContactImage(String phone, String image){
        PreparedStatement prepStmnt = null;

        // connect if not connected
        if(conn == null){
            connect();
        }

        try{
            String sql;
                        
            sql = "UPDATE phonebook SET image=? WHERE phone=?";
            prepStmnt = conn.prepareStatement(sql);
            prepStmnt.setString(1, image);
            prepStmnt.setString(2, phone);
            prepStmnt.execute();

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }
    }
    

    // delete Contact
    public boolean deleteContact(String phone){
        PreparedStatement prepStmnt = null;

        // connect if not connected
        if(conn == null){
            connect();
        }

        try{
            String sql;

            sql = "DELETE FROM phonebook WHERE phone=?";
            prepStmnt = conn.prepareStatement(sql);
            prepStmnt.setString(1, phone);
            prepStmnt.execute();

            return true;


        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return false;
            
        }finally{
            // release resources
            try{
                if(prepStmnt != null){
                    prepStmnt.close();
                }
            }catch(Exception ex){

            }
        }
    }

    // Call log
    // -- add call logs
    public boolean insertCallLog(String phone, String date, String time, String category){
        PreparedStatement prepStmnt = null;

        boolean status = false;

        // connect if not connected
        if(conn == null){
            connect();
        }

        try{
            String sql;

            // add contact to call_log table
            sql = "INSERT INTO call_log(log_phone, date, time, category) VALUES(?,?,?,?)";
            prepStmnt = conn.prepareStatement(sql);

            prepStmnt.setString(1, phone);
            prepStmnt.setString(2, date);
            prepStmnt.setString(3, time);
            prepStmnt.setString(4, category);

            prepStmnt.execute();

            status = true;

            // System.out.printf("call log entry created{phone: %s, date: %s, time: %s, cateory: %s} %n", phone, date, time, category);
            System.out.println("its a "+category+" call!");

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            status = false;
        }finally{
            try{
                // release resources
                if(prepStmnt !=null){
                    prepStmnt.close();
                }
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        return status;
    }

    // -- fetch call history
    public ResultSet fetchAllCallLog(){
        Statement stmnt = null;

        // connect if not connected
        if(conn == null){
            connect();
        }

        try{
            String sql;

            stmnt = conn.createStatement();
            sql = "SELECT * FROM call_log AS c LEFT JOIN phonebook AS p ON c.log_phone=p.phone ORDER BY c.log_id DESC";
            return stmnt.executeQuery(sql);
        
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return null;
        }
    }
    
    // -- fetch a specific category of call logs
    public ResultSet fetchSpecificCallLog(String category){
        PreparedStatement prepStmnt = null;

        // connect if not connected
        if(conn == null){
            connect();
        }

        try{
            String sql;

            sql = "SELECT * FROM call_log AS c LEFT JOIN phonebook AS p ON c.log_phone=p.phone WHERE category=? ORDER BY c.log_id DESC";
            prepStmnt = conn.prepareStatement(sql);
            prepStmnt.setString(1, category);
            return prepStmnt.executeQuery();

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return null;
        }
    }

    // count number of rows returned from result set
    public int countNumOfRowsFrom(ResultSet rs){
        // connect if not connected
        if(conn == null){
            connect();
        }
        int rowCount = 0;
        
        try{
            do{
                rowCount ++;
            }while(rs.next());
            // System.out.println("row count = "+rowCount);

            return rowCount;

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return 0;
        }
    }

    // clear call log
    public boolean clearCallLog(){
        PreparedStatement prepStmnt = null;

        // connect if not connected
        if(conn == null){
            connect();
        }

        try{
            String sql;

            sql = "DELETE FROM call_log";
            prepStmnt = conn.prepareStatement(sql);
            prepStmnt.execute();

            return true;

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return false;
            
        }finally{
            // release resources
            try{
                if(prepStmnt != null){
                    prepStmnt.close();
                }
            }catch(Exception ex){

            }
        }
    }

    // Messages
    // add message to database
    public void saveMessage(String msg, String date, String time, String receiver) {
        PreparedStatement prepStmnt = null;
     	if(conn == null) {
     		connect();
     	}
     	
     	String sql="INSERT INTO message(body,date,time,receiver) VALUES(?,?,?,?)";
     	try {
 			prepStmnt = conn.prepareStatement(sql);
 			prepStmnt.setString(1, msg);
 			prepStmnt.setString(2, date);
 			prepStmnt.setString(3, time);
 			prepStmnt.setString(4, receiver);
 			
 			prepStmnt.execute();
 			
 			
 			System.out.println("Values inserted into database!");
 			
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
     	
    }

    // get contact phone number using the name -- returns first contact with such name  
    public String validateContact(String name){
        // find contact with phone number from phonebook table and return name
        PreparedStatement prepStmnt = null;
        ResultSet res = null;
    

        // connect if not connected
        if(conn == null){
            connect();
        }

        try{
            String sql;

            sql = "SELECT phone FROM phonebook WHERE name=? LIMIT 1";
            prepStmnt = conn.prepareStatement(sql);
            prepStmnt.setString(1,name);
            res = prepStmnt.executeQuery();

            String phone = res.getString("phone");

            if(!name.contentEquals(""))
                return phone;
            else
            return "";

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return "";
        }
    }
    

    // get all messages sent by contact
    public ResultSet returnAllMessagesToThis(String contact){
        // find contact with phone number from phonebook table and return name
        PreparedStatement prepStmnt = null;
        ResultSet res = null;
    

        // connect if not connected
        if(conn == null){
            connect();
        }

        try{
            String sql;

            sql = "SELECT body FROM message LEFT JOIN phonebook ON message.receiver=phonebook.phone WHERE receiver=?";
            prepStmnt = conn.prepareStatement(sql);
            prepStmnt.setString(1,contact);
            res = prepStmnt.executeQuery();

            return res;

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return res=null;
        }
    
    }

    // get all messages in the messages table
    public ResultSet getAllMessages(){
        // find contact with phone number from phonebook table and return name
        PreparedStatement prepStmnt = null;
        ResultSet res = null;
    

        // connect if not connected
        if(conn == null){
            connect();
        }

        try{
            String sql;
            sql = "SELECT DISTINCT * FROM message LEFT JOIN phonebook ON message.receiver=phonebook.phone ORDER BY msg_id DESC";
            prepStmnt = conn.prepareStatement(sql);
            res = prepStmnt.executeQuery();

            return res;

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return res=null;
        }
    
    }

    // clear call log
    public boolean clearMessageLog(){
        PreparedStatement prepStmnt = null;

        // connect if not connected
        if(conn == null){
            connect();
        }

        try{
            String sql;

            sql = "DELETE FROM message";
            prepStmnt = conn.prepareStatement(sql);
            prepStmnt.execute();

            return true;

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return false;
            
        }finally{
            // release resources
            try{
                if(prepStmnt != null){
                    prepStmnt.close();
                }
            }catch(Exception ex){

            }
        }
    }




}
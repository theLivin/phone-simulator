// Control Center for anything DATABASE

import java.sql.*;

public class MyDatabaseManager{
    private Connection conn = null;
    private Statement stmnt = null;
    private PreparedStatement prepStmnt = null;
    private ResultSet res = null;

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
        // connect if not connected
        if(conn == null){
            connect();
        }

        try{
            String sql;

            // check if table is does not exist and create it if it does not -- contact
            stmnt = conn.createStatement();
            sql = "CREATE TABLE IF NOT EXISTS phonebook(" +
                "id INTEGER," +
                "name VARCHAR(20)," +
                "phone VARCHAR(20) UNIQUE NOT NULL," +
                "image VARCHAR(255)," +
                "PRIMARY KEY(id)" +
            ")";
            stmnt.execute(sql);
            // System.out.println("contact table created or already exists");

            // check if table is does not exist and create it if it does not -- call logs
            stmnt = conn.createStatement();
            sql = "CREATE TABLE IF NOT EXISTS call_log(" +
                "id INTEGER," +
                "phone VARCHAR(20) NOT NULL," +
                "date VARCHAR(255) NOT NULL," +
                "time VARCHAR(10) NOT NULL," +
                "category VARCHAR(10) NOT NULL," +
                "PRIMARY KEY(id)" +
            ")";
            stmnt.execute(sql);
            // System.out.println("call_log table created or already exists");

            // check if table is does not exist and create it if it does not -- message
            stmnt = conn.createStatement();
            sql = "CREATE TABLE IF NOT EXISTS message(" +
                "id INTEGER," +
                "body VARCHAR(255) NOT NULL," +
                "date VARCHAR(255) NOT NULL," +
                "time VARCHAR(10)," +
                "receiver VARCHAR(10)," +
                "PRIMARY KEY(id)" +
            ")";
            stmnt.execute(sql);
            // System.out.println("message table created or already exists");


        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }finally{
            releaseResources();
        }
    }

    // Phonebook
    // -- insert into contacts table
    public boolean insertContact(String name, String phone, String image){
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
            releaseResources();
        }

        return status;
    }

    // -- read from contacts table
    public ResultSet fetchAllContacts(){
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
        // find contact with phone number from phonebook table
        String name = null;

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

            name = res.getString("name");

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }finally{
            releaseResources();
        }

        return name;
    }

    // Call log
    // -- add call logs
    public boolean insertCallLog(String phone, String date, String time, String category){
        boolean status = false;

        // connect if not connected
        if(conn == null){
            connect();
        }

        try{
            String sql;

            // add contact to call_log table
            sql = "INSERT INTO call_log(phone, date, time, category) VALUES(?,?,?,?)";
            prepStmnt = conn.prepareStatement(sql);

            prepStmnt.setString(1, phone);
            prepStmnt.setString(2, date);
            prepStmnt.setString(3, time);
            prepStmnt.setString(4, category);

            prepStmnt.execute();

            status = true;

            System.out.printf("call log entry created{phone: %s, date: %s, time: %s, cateory: %s} %n", phone, date, time, category);

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            status = false;

        }finally{
            releaseResources();
        }

        return status;
    }

    // -- fetch call history
    public ResultSet fetchAllCallLog(){
        // connect if not connected
        if(conn == null){
            connect();
        }

        try{
            String sql;

            stmnt = conn.createStatement();
            sql = "SELECT * FROM call_log ORDER BY time DESC";
            res = stmnt.executeQuery(sql);

            return res;
        
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return null;
        }

    }
    
    // -- fetch a specific category of call logs
    public ResultSet fetchSpecificCallLog(String category){
        // connect if not connected
        if(conn == null){
            connect();
        }

        try{
            String sql;

            sql = "SELECT * FROM call_log WHERE category=? ORDER BY id DESC";
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




    // Release resources
    private void releaseResources(){
        try{
           if(conn != null){
            //    conn.close();
           }
           if(stmnt != null){
               stmnt.close();
           }
           if(res != null){
            //    res.close();
           }
           if(prepStmnt !=null){
               prepStmnt.close();
           }
       }catch(SQLException ex){
           System.out.println(ex.getMessage());
       }
   }



}
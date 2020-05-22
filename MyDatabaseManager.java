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
                "name VARCHAR(20)," +
                "phone VARCHAR(20) NOT NULL," +
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
                "time VARCHAR(10)," +
                "sender VARCHAR(10)," +
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

    // Release resources
    private void releaseResources(){
         try{
            if(conn != null){
                // conn.close();
            }
            if(stmnt != null){
                stmnt.close();
            }
            if(res != null){
                res.close();
            }
            if(prepStmnt !=null){
                prepStmnt.close();
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    // Insert from table
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

        fetchAll();

        return status;
    }

    // Read from table
    public void fetchAll(){
        // connect if not connected
        if(conn == null){
            connect();
        }

        try{
            String sql;

            stmnt = conn.createStatement();
            sql = "SELECT * FROM phonebook";
            res = stmnt.executeQuery(sql);

            // System.out.println("reading from phonebook...");
            System.out.printf("%-5s%-20s%-20s%-20s%n", "ID","NAME","PHONE","IMAGE");
            while( res.next() ){
                System.out.printf("%-5s%-20s%-20s%-20s%n",
                res.getInt("id"),res.getString("name"),
                res.getString("phone"), res.getString("image"));
            }

            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }finally{
            releaseResources();
        }
    }



}
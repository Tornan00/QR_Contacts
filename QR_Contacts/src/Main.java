import javax.swing.event.*; 
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*; 
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Main extends JFrame { 
      
    //globals
    static JFrame frame; 
    static JList contacts;
    static JLabel label;
    static String selected;
    static Connection connection;
   
  
    public static void main(String[] args) {
    	//Initializations
        frame = new JFrame("frame");  
        JPanel panel = new JPanel(); 
        label = new JLabel();
        label.setText("Contacts");
          
        try {
			connectData();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        
        String people[] = buildPeopleString();
          
        for (int i = 0; i < people.length; i ++) {
        	System.out.println(people[i]);
        }
        
        //create list
        contacts = new JList(people);
        contacts.setSelectedIndex(0);
          
        //add list to panel 
        panel.add(label); 
        panel.add(contacts); 
   
        frame.add(panel); 
          
        //set the size of frame 
        frame.setSize(500,600); 
           
        frame.show(); 
    } 
    
    //Updates the contacts String if contacts are changed
    private static String[] buildPeopleString() {
    	try {
    		Statement statement = connection.createStatement();
    		statement.setQueryTimeout(30);  // set timeout to 30 sec.
    		
    		//creates a string array of size equal to amount of contacts
    		ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM Contacts");
    		int size = rs.getInt(1);
    		String[] people = new String[size];
    		
    		//goes through result set to add each contact name to people array
    		rs = statement.executeQuery("SELECT Name FROM Contacts");
    		for (int i = 0; i < size; i++) {
    			rs.next();
    			people[i] = rs.getString(1);
    		}
    		contacts.getselectedvalue
    		return people;
   
    	} catch (SQLException e) {
			return null;
		}
    	
	}	 
    
    private static void connectData() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

       connection = null;
        try {
           // create a database connection
           connection = DriverManager.getConnection("jdbc:sqlite:database\\QR_Contacts.db");
           System.out.println("Connected to the database");
           DatabaseMetaData dm = (DatabaseMetaData) connection.getMetaData();
           System.out.println("Driver name: " + dm.getDriverName());
           System.out.println("Driver version: " + dm.getDriverVersion());
           System.out.println("Product name: " + dm.getDatabaseProductName());
           System.out.println("Product version: " + dm.getDatabaseProductVersion());
        } catch(SQLException e) { 
        	
        }      

    }
      
} 

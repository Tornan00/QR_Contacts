import javax.swing.event.*; 
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*; 
import java.sql.Connection;
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
   
  
    public static void main(String[] args) {
    	//Initializations
        frame = new JFrame("frame");  
        JPanel panel = new JPanel(); 
        label.setText("Contacts");
          
        //String people[] = buildPeopleString();
          String people[] = new String[1];
          people[0] = "jeff";
          
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
    	
		
	}	 
    
    private static void connection() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        Connection connection = null;
        try
        {
           // create a database connection
           connection = DriverManager.getConnection("jdbc:sqlite:/database/QR_Contacts.db");


        } catch(SQLException e) { 
        	
        }      

    }
      
} 

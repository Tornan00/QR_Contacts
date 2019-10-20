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
           connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

           Statement statement = connection.createStatement();
           statement.setQueryTimeout(30);  // set timeout to 30 sec.


           statement.executeUpdate("DROP TABLE IF EXISTS person");
           statement.executeUpdate("CREATE TABLE person (id INTEGER, name STRING)");

           int ids [] = {1,2,3,4,5};
           String names [] = {"Peter","Pallar","William","Paul","James Bond"};

           for(int i=0;i<ids.length;i++){
                statement.executeUpdate("INSERT INTO person values(' "+ids[i]+"', '"+names[i]+"')");   
           }

           //statement.executeUpdate("UPDATE person SET name='Peter' WHERE id='1'");
           //statement.executeUpdate("DELETE FROM person WHERE id='1'");

             ResultSet resultSet = statement.executeQuery("SELECT * from person");
             while(resultSet.next())
             {
                // iterate & read the result set
                System.out.println("name = " + resultSet.getString("name"));
                System.out.println("id = " + resultSet.getInt("id"));
             }
            }

       catch(SQLException e){  System.err.println(e.getMessage()); }       
        finally {         
              try {
                    if(connection != null)
                       connection.close();
                    }
              catch(SQLException e) {  // Use SQLException class instead.          
                 System.err.println(e); 
               }
        }
    }
      
} 

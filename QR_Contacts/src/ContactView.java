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

public class ContactView extends JPanel {
	
//	*** Global Variables ***
	private static String name = "";
	private static int id = -1;
	private static Connection connection;
	
//	*** Constructors ***
	public ContactView() {
		System.out.println("FATAL ERROR: failed to pass ID and SQL Connection into ContactView");
	}
	
	public ContactView(int ID, Connection conn) {
		if (ID < 0) {
			System.out.println("FATAL ERROR: ID less than 0");
		} else if (conn == null) {
			System.out.println("FATAL ERROR: could not resolve SQL connection");
		} else {
			id = ID;
			connection = conn;
		}
	}
	
//	*** Method definitions ***
	private static void connectData() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

       connection = null;
        try {
           // create a database connection
           connection = DriverManager.getConnection("jdbc:sqlite:database\\QR_Contacts.db");
        } catch(SQLException e) { 
        	
        }      

    }

//	*** JPanel management ***
	public void display() {
		this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
		try {
			connectData();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
    		Statement statement = connection.createStatement();
    		statement.setQueryTimeout(30);  // set timeout to 30 sec.
    		
    		//goes through result set to add each contact name to people array
    		ResultSet rs = statement.executeQuery("SELECT Name FROM Contacts WHERE Contacts.ID = Info.ID");
    		rs.next();
    		name = rs.getString(1);
    	} catch (SQLException e) {
			e.printStackTrace();
		}
		

		JLabel header = new JLabel(name, WIDTH / 2);
		JButton editb = new JButton("Edit");
		
		c.gridx = 1;
		c.gridy = 1;
		this.add(header, c);
		
		c.gridx = 1;
		c.gridy = 3;
		this.add(editb, c);
	}

}

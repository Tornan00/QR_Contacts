import javax.swing.event.*; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    static JButton newb;
    static JButton viewb;
    static JButton deleteb;
    static JButton scanb;
    static String selected;
    static Connection connection;
   
  
    public static void main(String[] args) {
    	//Initializations
        frame = new JFrame("frame");  
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        label = new JLabel();
        newb = new JButton("New");
        viewb = new JButton("View");
        deleteb = new JButton("Delete");
        scanb = new JButton("Scan");
         
        //design label
        label.setText("Contacts");
        label.setFont(new Font("Calibri", Font.BOLD, 24));
        
        try {
			connectData();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        
        String people[] = buildPeopleString();
        
        //create list
        contacts = new JList(people);
        contacts.setSelectedIndex(0);
        contacts.setFont(new Font("TimesRoman", Font.PLAIN, 18));
          
        //add label to panel 
        c.gridx = 1;
        c.gridy = 0;
        c.ipady = 30;
        panel.add(label, c);
        
        //make the list scroll
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(contacts);
        contacts.setLayoutOrientation(JList.VERTICAL);
        
        //add list to panel
        c.gridx = 1;
        c.gridy = 1;
        c.ipady = 200;
        c.ipadx = 100;
        c.gridheight = 5;
        panel.add(scrollPane, c);
        
        //add new button
        c.ipady = 0;
        c.ipadx = 0;
        c.gridx = 0;
        c.gridy = 6;
        panel.add(newb, c);
        
        //add scan button
        c.gridx = 0;
        c.gridy = 5;
        panel.add(scanb, c);
        
        //add view button
        c.gridx = 1;
        c.gridy = 6;
        panel.add(viewb, c);

        //add delete button
        c.gridx = 2;
        c.gridy = 6;
        panel.add(deleteb, c);
   
        //action listeners
        //Create new Function
        newb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Initializations
				JFrame neww = new JFrame();
				JPanel newp = new JPanel();
				newp.setLayout(new BoxLayout(newp, BoxLayout.PAGE_AXIS));
				JLabel newl = new JLabel("New Contact Name");
				JFormattedTextField newt = new JFormattedTextField();
				JButton but = new JButton("Create");
				
				newp.add(newl);
				newp.add(newt);
				newp.add(but);
				neww.add(newp);
				neww.setSize(100,100);
				neww.show();
				
		        but.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String newName = newt.getText();
						if (newName != null) {
							try {
								connectData();
								Statement statement = connection.createStatement();
								statement.execute("INSERT INTO Contacts (Name) VALUES (\"" + newName + "\")");
								neww.dispose();
							} catch (SQLException e1) {
								e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								e1.printStackTrace();
							}
						}
 					}
				});
			}
		});
        //Start scan function
        scanb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
        //Open view function
        viewb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
        //Delete function
        deleteb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = (String) contacts.getSelectedValue();
		
				try {
					connectData();
					Statement statement = connection.createStatement();
					
					//gets ID
					ResultSet rs = statement.executeQuery("SELECT ID FROM Contacts WHERE Name = \"" + name + "\"");
					int ID = rs.getInt(1);
					rs.close();
					
					//prevents users from deleting themselves
					if (ID == 1) {
						return;
					}
					
					//removes all instances in
					statement.execute("DELETE FROM Contacts WHERE ID = " + ID);
					statement.execute("DELETE FROM Info WHERE ID = " + ID);
					connection.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
        
        
        frame.add(panel); 
          
        //set the size of frame 
        frame.setSize(300,400); 
           
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

    		connection.close();
    		rs.close();
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
        } catch(SQLException e) { 
        	
        }      

    }
    
    private static void rebuildContacts() {
String people[] = buildPeopleString();
        
        //recreate list
        contacts = new JList(people);
        contacts.setSelectedIndex(0);
        contacts.setFont(new Font("TimesRoman", Font.PLAIN, 18));
    }
      
} 

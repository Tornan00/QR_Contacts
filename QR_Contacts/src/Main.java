import javax.swing.event.*; 
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*; 

class Main extends JFrame implements ListSelectionListener 
{ 
      
    //globals
    static JFrame frame; 
    static JList contacts;
    static JLabel label;
    static String selected;
   
  
    public static void main(String[] args) {
    	//Initializations
        frame = new JFrame("frame");  
        Main main = new Main();  
        JPanel panel = new JPanel(); 
        label.setText("Contacts");
          
        String people[] = buildPeopleString();
          
        //create list
        contacts = new JList(people);
        contacts.setSelectedIndex(0);
          
        //add item listener 
        contacts.addListSelectionListener(s);
          
        //add list to panel 
        p.add(l); 
        p.add(b); 
        p.add(b1); 
        p.add(b2); 
        p.add(l1); 
   
        frame.add(p); 
          
        //set the size of frame 
        frame.setSize(500,600); 
           
        frame.show(); 
    } 
    
    //Updates the contacts String if contacts are changed
    private static String[] buildPeopleString() {
    	
		
	}
	
      
      
} 

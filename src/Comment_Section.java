import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * Name: Sophisticated Software Solutions
 * Instructor: Dr Stephan
 * Course: CSE 201, Section C
 * Date: 5/6/2021
 * Assignment: Appdex
 * File: Comment_Section.java
 * Description: Displays the gui for the entire comment
 * section, which allows users to go to pages 
 * allowing them to view, delete, or add comments based on
 * the privileges for their account
 */
public class Comment_Section  extends JFrame
implements ActionListener {
	  
// Components of the Form
private Container c;
private JLabel name;
private JTextField tname;
private JButton sub;
private static JTextArea tout;
public static int num, data;
public static ArrayList<App> list;

// constructor, to initialize the components
// with default values.
public Comment_Section()
{
    setTitle("App Selection");
    setBounds(200, 90, 1600, 900);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setResizable(false);

    c = getContentPane();
    c.setLayout(null);


    name = new JLabel("Select Option");
    name.setFont(new Font("Arial", Font.BOLD, 14));
    name.setSize(100, 20);
    name.setLocation(100, 100);
    c.add(name);

    tname = new JTextField();
    tname.setFont(new Font("Arial", Font.PLAIN, 15));
    tname.setSize(190, 20);
    tname.setLocation(100, 130);
    c.add(tname);
    
    sub = new JButton("Select");
    sub.setFont(new Font("Arial", Font.PLAIN, 15));
    sub.setSize(100, 20);
    sub.setLocation(100, 170);
    sub.addActionListener(this);
    c.add(sub);

    tout = new JTextArea();
    tout.setFont(new Font("Arial", Font.PLAIN, 12));
    tout.setSize(1000, 600);
    tout.setLocation(500, 100);
    tout.setLineWrap(true);
    tout.setEditable(false);
    c.add(tout);


    setVisible(true);
}

public static void main(String[] args) throws Exception
{
    Comment_Section f = new Comment_Section();
    createList();
    appList();
}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sub) {
			if (tname.getText().equals("") || Integer.parseInt(tname.getText()) < 0 || Integer.parseInt(tname.getText()) > num)
				Login_Register.infoBox("Please put in a numberered option (0,1,2,etc)", "Comment error message");
			else {
				data = Integer.parseInt(tname.getText());
				App app = list.get(data);
				HashMap<String, ArrayList<String>> map = null;
				boolean bool = false;
				try {
					map = Add_Page.getMap(app);
				} catch (ClassNotFoundException | IOException | NullPointerException e2) {
					// TODO Auto-generated catch block
					bool = true;
				}
				if (bool == true)
					Login_Register.infoBox("This app has no comments", "Comment error message");
				else {
					Comment_Page frame = new Comment_Page();
					try {
						frame.readComments(map.get(app.printName()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NullPointerException t) {
						Login_Register.infoBox("This app has no comments", "Comment error message");
					}
					frame.setVisible(true);
					setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					dispose();
				}
			}
		}
		
		
	}
	
	/*
	 * Stores apps into a list to allow users to view them
	 */
	public static void appList() {
			String str = "";
			num = 0;
			for (App app : list) {
				str += "Option [" + num + "]  " + app.print() + "\n\n";
				num++;
			}
			num--;
			tout.setText(str);
			}
	
	/*
	 * Reads comments from the comment.dat file and stores them
	 * with their respective application
	 */
	public static void readComments(File check) {
		// Declare a Scanner object to read from the file
		Scanner file = null;

		try {
			file = new Scanner(check);
		} catch (FileNotFoundException e) {
			// Print the stack trace if there is a FileNotFound exception
			e.printStackTrace();
			// Unable to locate the file. We don't want to perform rest of the operations
			return;
		}
		
		if(check.length() == 0)
			tout.setText("");
		
		else {
		String str = "";
		int num = 0;
		while(file.hasNextLine()) {
			str += file.nextLine() + "\n";
		}
		file.close();
		tout.setText(str);
		}
	}
	
	/*
	 * Creates the list that stores app objects inside of it
	 * to display to the user
	 */
	public static void createList() throws IOException {
		boolean cont = true;
	    FileInputStream fileIn = new FileInputStream("app_object.dat");
	    list = new ArrayList<App>();

	       while(cont) {
	    	   try {
	    		  ObjectInputStream objectIn = new ObjectInputStream(fileIn);
	    		  Object obj = objectIn.readObject();
	    		  App app = (App) obj;
	    			  list.add(app);
	       } catch (Exception e) {
	    	   cont = false;
	       }
	       }
	       fileIn.close();
	}
	
	
}
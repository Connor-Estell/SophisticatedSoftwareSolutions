import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

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
 * File: App_Page.java
 * Description: Allows a user to add a comment,
 * and adds it to a hash map so that other users
 * regardless of their privileges can view the 
 * comments correctly. 
 */
public class Add_Page  extends JFrame
implements ActionListener {
	  
// Components of the Form
private Container c;
private JLabel name;
private JTextField tname;
private JButton sub;
private static JTextArea tout;
private static int num;
public static HashMap<String,ArrayList<String>> comment;

// constructor, to initialize the components
// with default values.
public Add_Page() {
    setTitle("Adding a Comment");
    setBounds(300, 90, 600, 600);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setResizable(false);

    c = getContentPane();
    c.setLayout(null);

    name = new JLabel("Write your comment");
    name.setFont(new Font("Arial", Font.BOLD, 14));
    name.setSize(200, 20);
    name.setLocation(230, 100);
    c.add(name);

    tname = new JTextField();
    tname.setFont(new Font("Arial", Font.PLAIN, 15));
    tname.setSize(450, 20);
    tname.setLocation(100, 150);
    c.add(tname);
    
    sub = new JButton("Add");
    sub.setFont(new Font("Arial", Font.PLAIN, 15));
    sub.setSize(100, 20);
    sub.setLocation(250, 180);
    sub.addActionListener(this);
    c.add(sub);

    setVisible(true);
}

public static void main(String[] args) throws Exception {
    Add_Page f = new Add_Page();
}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sub) {
			if (tname.getText().equals(""))
				Login_Register.infoBox("Please add a comment.", "Comment error message");
			else {
				App app = Comment_Add.list.get(Comment_Add.data);
				File file = new File("Comment.dat");
				//File file2 = new File("Comment.txt");
				if (file.length() == 0)
					try {
						newFile(app);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				else
					try {
						oldFile(app);
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
					}
				HashMap<String,ArrayList<String>> map = null;
				Comment_Page frame = new Comment_Page();
				try {
					 map = getMap(app);
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					frame.readComments(map.get(app.printName()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setVisible(true);
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				dispose();
			}
		}
	}
	
	public void newFile(App app) throws IOException {
		comment = new HashMap<String,ArrayList<String>>();
		comment.putIfAbsent(app.printName(), new ArrayList<String>());
		comment.get(app.printName()).add("User " + Database.name + " says: " + tname.getText());
		FileOutputStream fileOut = new FileOutputStream("Comment.dat", true);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(comment);
        objectOut.close();
	}
	
	public void oldFile(App app) throws ClassNotFoundException, IOException {
		 FileInputStream fileIn = new FileInputStream("Comment.dat");
		 ObjectInputStream objectIn = new ObjectInputStream(fileIn);
		  Object obj = objectIn.readObject();
		  objectIn.close();
		  @SuppressWarnings("unchecked")
		HashMap<String,ArrayList<String>> map = (HashMap<String,ArrayList<String>>) obj;
		  map.putIfAbsent(app.printName(), new ArrayList<String>());
		  map.get(app.printName()).add("User " + Database.name + " says: " + tname.getText());
		  FileOutputStream fileOut = new FileOutputStream("Comment.dat", false);
	        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
	        objectOut.writeObject(map);
	        objectOut.close();
	}
	
	/*
	 * implements the hash map in which comments are added to
	 * so that users can view them correctly
	 */
	public static HashMap<String,ArrayList<String>> getMap(App app) throws ClassNotFoundException, IOException {
		FileInputStream fileIn = new FileInputStream("Comment.dat");
		 ObjectInputStream objectIn = new ObjectInputStream(fileIn);
		  Object obj = objectIn.readObject();
		  @SuppressWarnings("unchecked")
		HashMap<String,ArrayList<String>> map = (HashMap<String,ArrayList<String>>) obj;
		  /*list = map.get(app.printName());
		  BufferedWriter out = new BufferedWriter(new FileWriter("Comment.txt", false));
		  for(String str : list)
			  out.write(str + "\n");
		  objectIn.close();
		  out.close();*/
		  objectIn.close();
		  return map;
	}
	
	/*
	 * saves comment objects to the comment.dat file
	 */
	public static void save(HashMap<String,ArrayList<String>> map) throws IOException {
		FileOutputStream fileOut = new FileOutputStream("Comment.dat", false);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(map);
        objectOut.close();
	}
}
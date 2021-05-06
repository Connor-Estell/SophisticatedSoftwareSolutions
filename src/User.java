import java.util.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;

/*
 * Name: Sophisticated Software Solutions
 * Instructor: Dr Stephan
 * Course: CSE 201, Section C
 * Date: 5/6/2021
 * Assignment: Appdex
 * File: User.java
 * Description: Displays mostly the same as the HomePage,
 * however has the added functionality of reading and 
 * adding comments on posts, as well as actually being able to post. 
 * This will only show up once the user has logged in as only a user. 
 */
public class User extends JFrame implements ActionListener {
	
	JLabel appName, description, organization, version, link, price, search;
	JTextField nameTF, descriptionTF, orgTF, versionTF, linkTF, priceTF, searchTF;
	JButton addButton, saveButton, Logout;
	JButton inbox;
	JMenu filter, sort, comment;
	JMenuItem ios, android, full, alph_desc, alph_asc, price_low, price_high, read_comment, add_comment;
	JTextArea appTA;
	JMenuBar menuBar;
	
	public User() {
		super("Appdex");
		
		search = new JLabel("Search: ");
		searchTF = new JTextField(50);
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout());
		searchPanel.add(search);
		searchPanel.add(searchTF);
		
		appName = new JLabel("Application Name: ");
		description = new JLabel("Description:");
		organization = new JLabel("Organization:");
		version = new JLabel("Version(s):");
		link = new JLabel("External Link:");
		price = new JLabel("Price");
		nameTF = new JTextField(50);
		descriptionTF = new JTextField(50);
		orgTF = new JTextField(50);
		versionTF = new JTextField(50);
		linkTF = new JTextField(50);
		priceTF = new JTextField(50);
		addButton = new JButton("Add Post");
		Logout = new JButton("Log-out");
		menuBar = new JMenuBar();
		filter = new JMenu("Filter");
		sort = new JMenu("Sort");
		comment = new JMenu("Comments");
		full = new JMenuItem("Full List");
		ios = new JMenuItem("IOS Version");
		android = new JMenuItem("Android Version");
		alph_desc = new JMenuItem("Alphabetical (Descending)");
		alph_asc = new JMenuItem("Alphabetical (Ascending)");
		price_low = new JMenuItem("Lowest Price");
		price_high = new JMenuItem("Highest Price");
		read_comment = new JMenuItem("Read Comment");
		add_comment = new JMenuItem("Add Comment");
		//inbox = new JButton("Inbox");

		JPanel addNewAppPanel = new JPanel();
		addNewAppPanel.setLayout(new GridLayout(14, 1));
		addNewAppPanel.setBorder(new TitledBorder("Enter New Application Information"));
		
		addNewAppPanel.add(appName, 0);
		addNewAppPanel.add(nameTF, 1);
		addNewAppPanel.add(description, 2);
		addNewAppPanel.add(descriptionTF, 3);
		addNewAppPanel.add(organization, 4);
		addNewAppPanel.add(orgTF, 5);
		addNewAppPanel.add(version, 6);
		addNewAppPanel.add(versionTF, 7);
		addNewAppPanel.add(link, 8);
		addNewAppPanel.add(linkTF, 9);
		addNewAppPanel.add(price, 10);
		addNewAppPanel.add(priceTF, 11);
		addNewAppPanel.add(addButton, 12);
		addNewAppPanel.add(Logout, 13);
		//addNewAppPanel.add(inbox, 14);
		addButton.addActionListener(this);
		Logout.addActionListener(this);
		//inbox.addActionListener(this);
		
		//Sets menu
		ios.addActionListener(this);
		android.addActionListener(this);
		full.addActionListener(this);
		alph_desc.addActionListener(this);
		alph_asc.addActionListener(this);
		price_low.addActionListener(this);
		price_high.addActionListener(this);
		read_comment.addActionListener(this);
		add_comment.addActionListener(this);
		filter.add(ios);
		filter.add(android);
		filter.add(full);
		sort.add(alph_desc);
		sort.add(alph_asc);
		sort.add(price_low);
		sort.add(price_high);
		comment.add(read_comment);
		comment.add(add_comment);
		menuBar.add(filter);
		menuBar.add(sort);
		menuBar.add(comment);
		setJMenuBar(menuBar);
		
		JPanel displayPanel = new JPanel();
		displayPanel.setBorder(new TitledBorder("Applications:"));
		appTA = new JTextArea(20, 80);
		displayPanel.add(appTA);
		
		// CONNOR'S ADDITION ------------------------------------------------------------------------------------------------
		// Button to click for searching so that we don't have to deal with live updating (it's a pain)
		JButton searchButton = new JButton("Search");
		searchPanel.add(searchButton);
		/*
		 * TODO Add some UI stuff to edit button's location
		 */
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				appTA.setText(""); // Empties previous text from textfield
				
				String searchCriteria = searchTF.getText(); // Gets text from search bar
				List<String> appList = new ArrayList<>(); // List of all apps that have the search criteria
				try (BufferedReader br = new BufferedReader(new FileReader("app.txt"))) {
				    String line; // Individual App
				    while ((line = br.readLine()) != null) {
				        String[] values = line.split(","); // Split app by comma and add to array
				        if(values[0].contains(searchCriteria))
				        	appList.add(line); // add array to app list
				    }
				} catch (IOException e1) {
					appTA.append("IO Exception");
				}
				try {
				BufferedWriter out = new BufferedWriter(new FileWriter("search.txt", false));
				for(String application : appList) {
						out.write(application + "\n");
				}
				out.close();
				} catch (IOException e1) {
					appTA.append("IO Exception");
				}
				readAppsFromFile(new File("search.txt"));
			}
		});
		// END CONNOR'S ADDITION -------------------------------------------------------------------------------------------------------
		
		add(searchPanel, BorderLayout.NORTH);
		add(addNewAppPanel, BorderLayout.CENTER);
		add(displayPanel, BorderLayout.SOUTH);
		readAppsFromFile(new File("app.txt"));
	}
	
	/*
	 * writes applications to requests.txt comma separated. 
	 */
	public void writeAppsToFile(String name, String desc, String org, String vers, String link, String price) throws IOException {
		// Declare an object PrintWriter object. It points to null
		//PrintWriter pw = null;

			BufferedWriter pw = new BufferedWriter(new FileWriter("requests.txt", true));
			pw.write(name + "," + desc + "," + org + "," + vers + "," + link + "," + price);
			pw.newLine();
			pw.close();
	}
	
	/*
	 * Reads applications from the given file 'check', 
	 * we use it to scan through app.txt and display it
	 * to the appTA. 
	 */
	public void readAppsFromFile(File check){
		// Declare a Scanner object to read from the file
		Scanner file = null;

		try {
			file = new Scanner(check);
		} catch (FileNotFoundException e) {
			// Print the stack trace if there is a FileNotFound exception
			
			// Unable to locate the file. We don't want to perform rest of the operations
			return;
		}
		
		if(check.length() == 0)
			appTA.setText("");
		
		else {
		String str = "";
		while(file.hasNextLine()) {
			String[] data = file.nextLine().split(",");
			str += "Application name: " + data[0] + " : Description: " + data[1] + " : Organization: " + data[2] + " : Versions: " + data[3] + 
					" : Link: " + data[4] + " : Price: " + data[5] + "\n";
		}
		file.close();
		appTA.setText(str);
		}
	}

	public static void main (String[] args) {
		HomePage hp =  new HomePage();
		hp.setSize(900, 900);
		hp.setDefaultCloseOperation(EXIT_ON_CLOSE);
		hp.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		boolean bool = false;
		try {
			Double.parseDouble(priceTF.getText());
		} catch (Exception e) {
			bool = true;
		}
		if (arg0.getActionCommand().equals("Add Post")) {
			if (!versionTF.getText().equals("IOS") && !versionTF.getText().equals("Android"))
				Login_Register.infoBox("Please have the app either be an IOS or Android version", "App error message");
			else if (bool)
				Login_Register.infoBox("Please put in an actual number for the price.", "App error message");
			else if (nameTF.getText().equals("") || descriptionTF.getText().equals("") || orgTF.getText().equals("") || versionTF.getText().equals("") || linkTF.getText().equals("")
					|| priceTF.getText().equals(""))
				Login_Register.infoBox("Please fill in all of the app information", "App error message");
			else {
			//appTA.append("Application name: " + nameTF.getText() + " : Description: " + descriptionTF.getText() + " : Organization: " + orgTF.getText() + " : Versions: " + versionTF.getText() + 
			//		" : Link: " + linkTF.getText() + " : Price: " + priceTF.getText() + "\n");
			App app = new App(nameTF.getText(), descriptionTF.getText(), orgTF.getText(), versionTF.getText(), linkTF.getText(), Double.parseDouble(priceTF.getText()));
			app.save();
			try {
				writeAppsToFile(nameTF.getText(), descriptionTF.getText(), orgTF.getText(), versionTF.getText(), linkTF.getText(), priceTF.getText());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			nameTF.setText("");
			descriptionTF.setText("");
			orgTF.setText("");
			versionTF.setText("");
			linkTF.setText("");
			priceTF.setText("");
			//writeAppsToFile();
			}
		}
		if (arg0.getActionCommand().equals("Log-in or Register")) {
			Login_Register frame = new Login_Register();
			frame.setBounds(300, 90, 300, 300);
			frame.setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			dispose();
		}
		if (arg0.getActionCommand().equals("Search")) {
			// Have to initialize to null or while loop gets upset
			BufferedReader read = null;
			try {
				read = new BufferedReader(new FileReader("app.txt")); //TODO Add file name here
			} catch (FileNotFoundException e1) {
				appTA.append("File not found exception. GUI @ Line 84" + "\n");
			}
			
			List<String> appList = new ArrayList<>();
			String app = null;
			
			// Get text from search bar and save it to string
			String searchCriteria = search.getText();
			
			// Check every app (line of csv) to see if it contains search criteria, if so, add to list
			try {
				
				while ((app = read.readLine()) != null) { 
					if(app.contains(searchCriteria))
						appList.add(app);
				}
				
			} catch (Exception e1) {
				appTA.append("Input output exception. GUI @ Line 102" + "\n"); 
			}
			
			// Print list of apps that contained search criteria
			for (String application : appList) {
				appTA.setText("");
				appTA.append(application + "\n"); // Not 100% sure if this is correct area to be printing the apps just from looking at your code
			}
		}
		
		if (arg0.getActionCommand().equals("Log-out")) {
			HomePage frame = new HomePage();
			frame.setSize(900, 900);
			frame.setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			dispose();
		}
		if (arg0.getActionCommand().equals("IOS Version")) {
			try {
				File file = Database.filter_version("IOS");
				readAppsFromFile(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (arg0.getActionCommand().equals("Android Version")) {
			try {
				File file = Database.filter_version("Android");
				readAppsFromFile(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (arg0.getActionCommand().equals("Full List")) {
			readAppsFromFile(new File("app.txt"));
		}
		if (arg0.getActionCommand().equals("Alphabetical (Descending)")) {
			try {
				File file = Database.alphabet_sort_desc();
				readAppsFromFile(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (arg0.getActionCommand().equals("Alphabetical (Ascending)")) {
			try {
				File file = Database.alphabet_sort_asc();
				readAppsFromFile(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (arg0.getActionCommand().equals("Lowest Price")) {
			try {
				File file = Database.low_price();
				readAppsFromFile(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (arg0.getActionCommand().equals("Highest Price")) {
			try {
				File file = Database.high_price();
				readAppsFromFile(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (arg0.getActionCommand().equals("Highest Price")) {
			try {
				File file = Database.high_price();
				readAppsFromFile(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (arg0.getActionCommand().equals("Read Comment")) {
			Comment_Section frame = new Comment_Section();
			try {
				Comment_Section.createList();
			} catch (IOException e) {
				e.printStackTrace();
			}
		    Comment_Section.appList();
			frame.setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
		if (arg0.getActionCommand().equals("Add Comment")) {
			Comment_Add frame = new Comment_Add();
			try {
				Comment_Add.createList();
			} catch (IOException e) {
				e.printStackTrace();
			}
		    Comment_Add.appList();
			frame.setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
//		if (arg0.getActionCommand().equals("Inbox")) {
//			Inbox frame = new Inbox();
//			frame.setBounds(600, 90, 600, 600);
//			frame.setVisible(true);
//			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//			dispose();
//		}
	}
}
import java.util.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;


public class HomePage extends JFrame implements ActionListener {
	
	JLabel appName, description, organization, version, search;
	JTextField nameTF, descriptionTF, orgTF, versionTF, searchTF;
	JButton addButton, saveButton;
	JTextArea appTA;
	
	public HomePage() {
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
		nameTF = new JTextField(50);
		descriptionTF = new JTextField(50);
		orgTF = new JTextField(50);
		versionTF = new JTextField(50);
		addButton = new JButton("Add Post");

		JPanel addNewAppPanel = new JPanel();
		addNewAppPanel.setLayout(new GridLayout(9, 1));
		addNewAppPanel.setBorder(new TitledBorder("Enter New Application Information"));
		
		addNewAppPanel.add(appName, 0);
		addNewAppPanel.add(nameTF, 1);
		addNewAppPanel.add(description, 2);
		addNewAppPanel.add(descriptionTF, 3);
		addNewAppPanel.add(organization, 4);
		addNewAppPanel.add(orgTF, 5);
		addNewAppPanel.add(version, 6);
		addNewAppPanel.add(versionTF, 7);
		addNewAppPanel.add(addButton, 8);
		addButton.addActionListener(this);
		
		JPanel displayPanel = new JPanel();
		displayPanel.setBorder(new TitledBorder("Applications:"));
		appTA = new JTextArea(20, 80);
		displayPanel.add(appTA);
		
		// CONNOR'S ADDITION ------------------------------------------------------------------------------------------------
		// Button to click for searching so that we don't have to deal with live updating (it's a pain)
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(this);
		searchPanel.add(searchButton);
		/*
		 * TODO Add some UI stuff to edit button's location
		 */
//		searchButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// Have to initialize to null or while loop gets upset
//				BufferedReader read = null;
//				try {
//					read = new BufferedReader(new FileReader("applications.txt")); //TODO Add file name here
//				} catch (FileNotFoundException e1) {
//					appTA.append("File not found exception. GUI @ Line 84" + "\n");
//				}
//				
//				List<String> appList = new ArrayList<>();
//				String app = null;
//				
//				// Get text from search bar and save it to string
//				String searchCriteria = search.getText();
//				
//				// Check every app (line of csv) to see if it contains search criteria, if so, add to list
//				try {
//					
//					while ((app = read.readLine()) != null) { 
//						if(app.contains(searchCriteria))
//							appList.add(app);
//					}
//					
//				} catch (Exception e1) {
//					appTA.append("Input output exception. GUI @ Line 102" + "\n"); 
//				}
//				
//				// Print list of apps that contained search criteria
//				for (String application : appList) {
//					appTA.setText("");
//					appTA.append(application + "\n"); // Not 100% sure if this is correct area to be printing the apps just from looking at your code
//				}
//			}
//		});
		// END CONNOR'S ADDITION -------------------------------------------------------------------------------------------------------
		
		add(searchPanel, BorderLayout.NORTH);
		add(addNewAppPanel, BorderLayout.CENTER);
		add(displayPanel, BorderLayout.SOUTH);
		
		readAppsFromFile();
	}
	
	public void writeAppsToFile() {
		// Declare an object PrintWriter object. It points to null
		PrintWriter pw = null;

		try {
			pw = new PrintWriter(new File("applications.txt"));
			pw.println(appTA.getText());
		} catch (FileNotFoundException e) {
			// Print the stack trace if there is a FileNotFound exception
			e.printStackTrace();
			// Unable to create file. We don't want to perform rest of the operations
			return;
		}
		pw.close();
	}
	
	public void readAppsFromFile(){
		// Declare a Scanner object to read from the file
		Scanner file = null;

		try {
			file = new Scanner(new File("applications.txt"));
		} catch (FileNotFoundException e) {
			// Print the stack trace if there is a FileNotFound exception
			e.printStackTrace();
			// Unable to locate the file. We don't want to perform rest of the operations
			return;
		}

		String str = "";
		while(file.hasNextLine()) {
			str += file.nextLine();
			if(file.hasNextLine())
				str += "\n";
		}
		file.close();
		appTA.setText(str);
	}

	public static void main (String[] args) {
		HomePage hp =  new HomePage();
		hp.setSize(670, 550);
		hp.setDefaultCloseOperation(EXIT_ON_CLOSE);
		hp.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("Add Post")) {
			appTA.append("Application name: " + nameTF.getText() + " : Description: " + descriptionTF.getText() + " : Organization: " + orgTF.getText() + " : Versions: " + versionTF.getText() + "\n");
			nameTF.setText("");
			descriptionTF.setText("");
			orgTF.setText("");
			versionTF.setText("");
			writeAppsToFile();
		}
		if (arg0.getActionCommand().equals("Search")) {
			// Have to initialize to null or while loop gets upset
			BufferedReader read = null;
			try {
				read = new BufferedReader(new FileReader("applications.txt")); //TODO Add file name here
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
	}
}

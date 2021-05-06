import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/*
 * Name: Sophisticated Software Solutions
 * Instructor: Dr Stephan
 * Course: CSE 201, Section C
 * Date: 5/6/2021
 * Assignment: Appdex
 * File: Administrator.java
 * Description: Has essentially the same functionality as
 * the moderator class, except with further privileges of 
 * promoting other people to administrators, and going
 * to the administrator board to approve or decline app requests.
 */
public class Administrator extends Moderator {
	JMenu admin_board;
	JMenuItem admin_promote, board_link;
	
	public Administrator() {
		
		admin_board = new JMenu("Administrator Board");
		board_link = new JMenuItem("Go To Admin Board");
		
		admin_promote = new JMenuItem("Admin Promotion");
		admin_promote.addActionListener(this);
		promote.add(admin_promote);
		menuBar.add(promote);
		board_link.addActionListener(this);
		admin_board.add(board_link);
		menuBar.add(admin_board);
		setJMenuBar(menuBar);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Delete Comment")) {
			Comment_Delete_Selection frame = new Comment_Delete_Selection();
			try {
				Comment_Add.createList();
			} catch (IOException t) {
				t.printStackTrace();
			}
		    Comment_Add.appList();
			frame.setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
		if (arg0.getActionCommand().equals("Add Post")) {
			boolean bool = false;
			try {
				Double.parseDouble(priceTF.getText());
			} catch (Exception e) {
				bool = true;
			}
			if (!versionTF.getText().equals("IOS") && !versionTF.getText().equals("Android"))
				Login_Register.infoBox("Please have the app either be an IOS or Android version", "App error message");
			else if (bool)
				Login_Register.infoBox("Please put in an actual number for the price.", "App error message");
			else if (nameTF.getText().equals("") || descriptionTF.getText().equals("") || orgTF.getText().equals("") || versionTF.getText().equals("") || linkTF.getText().equals("")
					|| priceTF.getText().equals(""))
				Login_Register.infoBox("Please fill in all of the app information", "App error message");
			else {
			appTA.append("Application name: " + nameTF.getText() + " : Description: " + descriptionTF.getText() + " : Organization: " + orgTF.getText() + " : Versions: " + versionTF.getText() + 
					" : Link: " + linkTF.getText() + " : Price: " + priceTF.getText() + "\n");
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
		if (arg0.getActionCommand().equals("Mod Promotion")) {
			Mod_Promoter frame = new Mod_Promoter();
			try {
				Mod_Promoter.userList();
			} catch (IOException e) {
				e.printStackTrace();
			}
			frame.setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
		if (arg0.getActionCommand().equals("Admin Promotion")) {
			Admin_Promoter frame = new Admin_Promoter();
			try {
				Admin_Promoter.userList();
			} catch (IOException e) {
				e.printStackTrace();
			}
			frame.setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
		if (arg0.getActionCommand().equals("Go To Admin Board")) {
			AdminBoard frame = new AdminBoard();
			frame.setSize(1000, 500);
			frame.setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			dispose();
		}
	}
}
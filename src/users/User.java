import java.util.ArrayList;

/**
 * Name: Joe Alcini (alcinija)
 * Instructor: Dr Stephan
 * Course: CSE 201, Section C
 * Date: 4/8/2021
 * Assignment: Appdex
 * File: UserTest.java
 * Description: This class creates a User object that represents
 * the basic user in the system that has an account. The user
 * has a userrname, password and a list of apps that they contributed.
 * They have the ability to change there password, view their submitted apps,
 * and request new apps to be submitted.
 */
public class User {
	// Creates the fields of the User
	private String userName;
	private String password;
	private ArrayList<String> createdApps;
	
	/**
	 * Created for a newly registered user
	 * creates a new list of apps that have yet to be added
	 * @param uName the username 
	 * @param pass the password
	 */
	public User(String uName, String pass) {
		this.userName = uName;
		this.password = pass;
		this.createdApps = new ArrayList<String>();
	}
	
	/**
	 * Creates a user object for a existing user signed in
	 * @param uName the username
	 * @param pass the password
	 * @param apps the list of apps the user submitted
	 */
	public User(String uName, String pass, ArrayList<String> apps) {
		this.userName = uName;
		this.password = pass;
		this.createdApps = apps;
	}
	
	/**
	 * Returns the username of the user
	 * @return the username
	 */
	public String getUserName() {
		return this.userName;
	}
	
	/**
	 * Returns the password of the user
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * Returns the apps the user created
	 * @return the list of apps
	 */
	public ArrayList<String> getCreatedApps() {
		return this.createdApps;
	}
	
	/**
	 * Changes the user's password
	 * @param newPass the password for the user
	 * @return true if password is changed false otherwise
	 */
	public boolean changePassword(String newPass) {
		// Holds old password
		String oldPass = new String(this.password);
		
		// Sets new password
		this.password = newPass;
		
		// Compares passwords to determine change
		if (!oldPass.equals(this.password)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Creates a request for a new app
	 * @param name name of the app
	 * @param desc description of the app
	 * @param platforms platforms app runs on
	 * @param link a link to the app's website
	 * @return formatted string to the user
	 */
	public String requestNewApp(String name, String desc, String platforms, String link) {
		String appName = name + ", " + desc + ", " + platforms + ", " + link;
		
		createdApps.add(appName);
		
		return "Your app request:\n  Name: " + name + "\n  Desc: " + desc + "\n  Platform(s): " + platforms + "\n  Link: " + link;
	}
	
	/**
	 * Checks if two Users are the same
	 * @param otherUser the user being compared
	 * @return true if the username is the same false otherwise
	 */
	public boolean equals(User otherUser) {
		// Compares the usernames
		if (otherUser.getUserName().equals(this.getUserName())) {
			return true;
		} else {
			return false;
		}
	}
}

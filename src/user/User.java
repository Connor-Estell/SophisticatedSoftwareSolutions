import java.util.ArrayList;

/**
 * Name: Joe Alcini (alcinija)
 * Instructor: Dr Stephan
 * Course: CSE 201, Section C
 * Date: 4/24/2021
 * Assignment: Appdex
 * File: User.java
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
	private ArrayList<String> messages;
	private String status;
	/**
	 * Created for a newly registered user
	 * creates a new list of apps that have yet to be added
	 * @param uName the username 
	 * @param pass the password
	 */
	public User(String uName, String pass) {
		this.userName = uName;
		this.password = pass;
		this.messages = new ArrayList<String>();
		this.status = "User";
	}
	
	/**
	 * Creates a user object for a existing user signed in
	 * @param uName the username
	 * @param pass the password
	 * @param apps the list of apps the user submitted
	 */
	public User(String uName, String pass, ArrayList<String> messages) {
		this.userName = uName;
		this.password = pass;
		this.messages = messages;
		this.status = "User";
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
	 * Returns the mesages of the users inbox
	 * @return the list of messages
	 */
	public ArrayList<String> getmessages() {
		return this.messages;
	}
	
	/**
	 * Gets the classification of the user
	 * @return user status
	 */
	public String getStatus() {
		return this.status;
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
	 * Promotes a user to a moderator 
	 * @param String username The username of the User being promoted
	 * @return true if successful, false otherwise
	 */
	public boolean changeStatusToModerator(String username, ArrayList<User> userList) {
		String newStatus = "Moderator";
		
		if (!this.getStatus().equals("Moderator") || !this.getStatus().equals("Administrator")) {
			return false;
		}
		
		for (User u : userList) {
			if (u.getUserName().equals(userName)) {
				u.status = newStatus;
			}
		}
		
		return true;
	}
	
	
	/**
	 * Promotes a user to a Administrator 
	 * @param String username The username of the User being promoted
	 * @return true if successful, false otherwise
	 */
	public boolean changeStatusToAdministrator(String username, ArrayList<User> userList) {
		String newStatus = "Administrator";
		
		if (!this.getStatus().equals("Administrator")) {
			return false;
		}
		
		for (User u : userList) {
			if (u.getUserName().equals(userName)) {
				u.status = newStatus;
			}
		}
		
		return true;
	}
	
	/**
	 * Adds a message to the users inbox
	 * @param message String the message being added
	 * @return true when complete
	 */
	public boolean addMessage(String message) {
		this.messages.add(message);
		
		return true;
	}
	
	/**
	 * Creates a request for a new app
	 * @param name String name of the app
	 * @param desc String description of the app
	 * @param org String the creator of the app
	 * @param platforms String platforms app runs on
	 * @param versions String list of versions of the app
	 * @param link String a link to the app's website
	 * @param price double the cost of the app
	 * @return formatted string to the user
	 */
	public String requestNewApp(String name, String desc, String org, String platforms, String versions, String link, double price) {
		App newApp = new App(name, desc, org, platforms, versions, link, price);
		
		new AppRequest(newApp, this.getUserName());
		
		return "Your app request: " + newApp.toString();
	}
	
	
	
	/**
	 * Checks if two Users are the same
	 * @param otherUser the user being compared
	 * @return true if the users is the same false otherwise
	 */
	public boolean equals(User otherUser) {
		// Compares the usernames
		if (otherUser.getUserName().equals(this.getUserName()) && otherUser.getPassword().equals(this.getPassword())) {
			return true;
		} else {
			return false;
		}
	}
}

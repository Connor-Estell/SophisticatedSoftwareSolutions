import java.util.ArrayList;

/**
 * Name: Sophisticated Software Solutions
 * Instructor: Dr Stephan
 * Course: CSE 201, Section C
 * Date: 4/24/2021
 * Assignment: Appdex
 * File: App.java
 * Description: This class creates an AppRequest object that
 * holds information about an app entry in the system awaiting approval.
 * It contains info including its name, a brief description
 * about what it is, who developed it, what platforms it works on, 
 * its versions, a link to its website, the price,
 * comments made about the app, and who requested it.
 */
public class AppRequest {
	// Creates fields
	private App newApp;
	private String user;
	
	/**
	 * Creates a new AppRequest to be feature 
	 * in the request boards
	 * @param newApp App the being requested
	 * @param user String the username of the account that suggested the app
	 */
	public AppRequest(App newApp, String user) {
		this.newApp = newApp;
		this.user = user;
	}
	
	/**
	 * Returns the requested app
	 * @return the app
	 */
	public App getNewApp() {
		return this.newApp;
	}
	
	/**
	 * Returns the requesting userName
	 * @return the userName
	 */
	public String getUser() {
		return this.user;
	}
	
	/**
	 * Approves the app and sends a message 
	 * to the user who suggested it
	 * @param mesaage String the reason for inclusion
	 * @param appList ArrayList<App> the main list of apps
	 * @param userLsit ArrayList<User> the list of all users
	 * @return Confirmation message to the admin who approved
	 */
	public String approveAppRequest(String message, ArrayList<App> appList, ArrayList<User> userlist) {
		// Adds the app to the list
		appList.add(getNewApp());
		
		// Finds the user to notify them
		for (User u : userlist) {
			if (u.getUserName().equals(this.getUser())) {
				u.addMessage("Congrats!\nYour App:\n" + this.getNewApp().toString() + "\nHas been Aproved\nMessage: " + message);
			}
		}
		return "Request Successfully Aproved";
	}
	
	/**
	 * Rejects the app and sends a message 
	 * to the user who suggested it
	 * @param mesaage String the reason for inclusion
	 * @param userLsit ArrayList<User> the list of all users
	 * @return Confirmation message to the admin who declined
	 */
	public String rejectAppRequest(String message, ArrayList<User> userList) {
		// Finds the user to notify them
		for (User u : userList) {
			if (u.getUserName().equals(this.getUser())) {
				u.addMessage("Unfornately\nYour App:\n" + this.getNewApp().toString() + "\nHasbeen Declined\nMessage: " + message);
			}
		}
		
		return "Request Successfully Denied";
	}
}

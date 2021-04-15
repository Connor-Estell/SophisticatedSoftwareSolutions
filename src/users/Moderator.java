import java.util.ArrayList;

/**
 * Name: Joe Alcini (alcinija)
 * Instructor: Dr Stephan
 * Course: CSE 201, Section C
 * Date: 4/14/2021
 * Assignment: Appdex
 * File: Moderator.java
 * Description: This class creates a Moderator object that extends
 * from the User and represents a moderator in the system that has an account. 
 * The user has a username, password and a list of apps that they contributed.
 * They have the ability to change there password, view their submitted apps,
 * request new apps to be submitted, and deleting comments for apps.
 */
public class Moderator extends User {
	/**
	 * Creates a moderator object for a moderator who signed in
	 * @param uName the username
	 * @param pass the password
	 * @param apps the list of apps the user submitted
	 */
	public Moderator(String uName, String pass, ArrayList<App> apps) {
		super(uName, pass, apps);
	}
	
	/**
	 * Removes comment from an app
	 * @param comment Comment the comment being removed
	 * @return true if the comment is removed successfully false otherwise
	 */
	public boolean deleteComment(Comment comment) {
		// Insert Removal Code here
		return false;
	}
}

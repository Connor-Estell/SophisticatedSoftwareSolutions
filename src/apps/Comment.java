/**
 * Name: Sophisticated Software Solutions
 * Instructor: Dr Stephan
 * Course: CSE 201, Section C
 * Date: 4/14/2021
 * Assignment: Appdex
 * File: Comment.java
 * Description: This class creates a comment that can
 * be posted to the discussion over an application.
 */
public class Comment {
	// Creates the fields for the comment
	private String text;
	private String userName;
	
	/**
	 * Creates the comment by taking 
	 * text and remembering the user it came from
	 * @param text String the contents of the comment
	 * @param userName String the user who posted the comment
	 */
	public Comment(String text, String userName) {
		this.text = text;
		this.userName = userName;
	}
	
	/**
	 * Returns the text content of the comment
	 * @return String the text of the comment
	 */
	public String getText() {
		return this.text;
	}
	
	/**
	 * Returns the username who posted the comment
	 * @return String the username of the poster
	 */
	public String getUserName() {
		return this.userName;
	}
}

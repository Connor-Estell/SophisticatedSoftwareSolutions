import java.util.ArrayList;

/**
 * Name: Sophisticated Software Solutions
 * Instructor: Dr Stephan
 * Course: CSE 201, Section C
 * Date: 4/23/2021
 * Assignment: Appdex
 * File: App.java
 * Description: This class creates an App object that
 * holds information about an app entry in the system.
 * It contains info including its name, a brief description
 * about what it is, who developed it, what platforms it works on, 
 * its versions, a link to its website, the price,
 * and comments made about the app.
 */
public class App {
	// Creates the fields for the user
	private String name;
	private String description;
	private String organization;
	private String platforms;
	private String versions;
	private String link;
	private double price;
	private ArrayList<Comment> comments;
	
	/**
	 * Creates an new app object that can hold info about the app
	 * that includes it's name, description, compatible platforms,
	 * and a link to its website
	 * @param name String the name of the app
	 * @param description String a brief overview of the app
	 * @param organization String the creator of the app
	 * @param platforms ArrayList<String> the list of compatible hardware
	 * @param versions ArrayList<String> list of versions of the app
	 * @param link String a link to the website of the app
	 * @param price double the cost of the app
	 */
	public App(String name, String description, String organization, String platforms, String versions, String link, double price) {
		this.name = name;
		this.description = description;
		this.organization = organization;
		this.platforms = platforms;
		this.versions = versions;
		this.link = link;
		this.price = price;
		this.comments = new ArrayList<Comment>();
	}
	
	/**
	 * Creates an app object of an existing app that can hold 
	 * info about the app that includes it's name, 
	 * description, compatible platforms, and a link to its website
	 * @param name String the name of the app
	 * @param description String a brief overview of the app
	 * @param organization String the creator of the app
	 * @param platforms ArrayList<String> the list of compatible hardware
	 * @param versions ArrayList<String> list of versions of the app
	 * @param link String a link to the website of the app
	 * @param price double the cost of the app
	 * @param comments ArrayList<Comment> holds a list of comments about the app
	 */
	public App(String name, String description, String organization, String platforms, String versions, String link, double price, ArrayList<Comment> comments) {
		this.name = name;
		this.description = description;
		this.organization = organization;
		this.platforms = platforms;
		this.versions = versions;
		this.link = link;
		this.price = price;
		this.comments = comments;
	}
	
	/**
	 * Returns the name of the app
	 * @return String the app's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the description about the app
	 * @return String description of the app
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Returns the organization of the app
	 * @return String the app's organization
	 */
	public String getOrganization() {
		return this.organization;
	}
	
	/**
	 * Returns the platforms the app runs on
	 * @return ArrayList<String> the list of platforms
	 */
	public String getPlatforms() {
		return this.platforms;
	}
	
	/**
	 * Returns the versions of the app 
	 * @return ArrayList<String> the list of versions
	 */
	public String getVersions() {
		return this.versions;
	}
	
	/**
	 * Returns the app's website address
	 * @return String the link to the app's website
	 */
	public String getLink() {
		return this.link;
	}
	
	/**
	 * Returns the app's price
	 * @return double the link to the app's price
	 */
	public double getPrice() {
		return this.price;
	}
	
	/**
	 * Returns the comments about the app
	 * @return ArrayList<Comment> the list of comments
	 */
	public ArrayList<Comment> getComments() {
		return this.comments;
	}
	
	/**
	 * Changes the description for the app
	 * @param newDesc the updated description for the app
	 * @return true if the description is changed false otherwise
	 */
	public boolean changeDescription(String newDesc) {
		// Checks if the new description is the same as the old one
		if (newDesc != this.description) {
			this.description = newDesc;
			return true;
		}
		
		// Returns false if they are the same
		return false;
	}
	
	/**
	 * Adds a new platform that the app can run on
	 * @param newPlatform 
	 * @return
	 */
	public boolean addPlatform(String newPlatform) {
		// Checks if platform is already included in the list
		if (!this.platforms.contains(newPlatform)) {
			this.platforms = this.platforms + ", " + newPlatform;
			return true;
		}
		
		// Returns false 
		return false;
	}
	
	/**
	 * Adds a comment to the comment section of the app
	 * @param newComment Comment the comment being added to the list
	 * @return true if the comment is added successfully
	 */
	public boolean addComment(Comment newComment) {
		// Adds a new comment to the list
		this.comments.add(newComment);
		
		// Alerts user that it was added
		return true;
	}
	
	/**
	 * Returns a formatted string containing all of
	 * the information about the app
	 */
	public String toString() {
		return "Name: " + this.getName() + ", Description: " + this.getDescription() + ", Organization: " + this.getOrganization() + ", Platforms: (" + this.getPlatforms() + 
				"), Versions: (" + this.getVersions() + "), Link: " + this.getLink() + ", Price: $" + this.getPrice();
	}
}

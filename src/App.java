import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/*
 * Name: Sophisticated Software Solutions
 * Instructor: Dr Stephan
 * Course: CSE 201, Section C
 * Date: 5/6/2021
 * Assignment: Appdex
 * File: App.java
 * Description: Implements an App object which allowed us to
 * read apps into files much easier. This class also creates
 * our object file, which is used when displaying/dealing with
 * comments.
 */
public class App implements Serializable {
	
	String name, desc, org, ver, link;
	static ArrayList<String> comments;
	double price;
	
	public App(String name, String desc, String org, String ver, String link, double price) {
		this.name = name;
		this.desc = desc;
		this.org = org;
		this.ver = ver;
		this.link = link;
		this.price = price;
		comments = new ArrayList<String>();
	}
	
	/*
	 * Writes the application as a coherent string
	 */
	public String print() {
		return "Application name: " + name + " : Description: " + desc + " : Organization: " + org + " : Versions: " + ver + 
				" : Link: " + link + " : Price: " + price;
	}
	
	/*
	 * Writes the application as one string with all
	 * parts only separated by a single comma. 
	 */
	public String printData() {
		return name + "," + desc + "," + org + "," + ver + "," + link + "," + price;
	}
	
	/*
	 * Save the app objects to a .dat file
	 */
	public void save() {
		try {
			 
            FileOutputStream fileOut = new FileOutputStream("app_object.dat", true);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	/*
	 * Reads the app objects from a .dat file to display them
	 */
	public void read() {
		try {
			 
            FileInputStream fileIn = new FileInputStream("app_object.dat");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
 
            App app = (App) objectIn.readObject();
            System.out.println(app.print());
            objectIn.close();
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	/*
	 * Retrieves comments from a specific application
	 */
	public ArrayList<String> getComments() {
		return comments;
	}
	
	/*
	 * Adds a comment String to a specific application
	 */
	public void addComment(String comment) {
		comments.add(comment);
	}
	
	/*
	 * Returns the name of a specific application. 
	 */
	public String printName() {
		return name;
	}
}
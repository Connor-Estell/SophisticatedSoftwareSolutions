import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

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
	
	public String print() {
		return "Application name: " + name + " : Description: " + desc + " : Organization: " + org + " : Versions: " + ver + 
				" : Link: " + link + " : Price: " + price;
	}
	
	public String printData() {
		return name + "," + desc + "," + org + "," + ver + "," + link + "," + price;
	}
	
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
	
	public ArrayList<String> getComments() {
		return comments;
	}
	
	public void addComment(String comment) {
		comments.add(comment);
	}
	
	public String printName() {
		return name;
	}
}

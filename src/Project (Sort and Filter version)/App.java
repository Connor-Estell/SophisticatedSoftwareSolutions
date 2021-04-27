import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

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
		this.comments = null;
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
			 
            FileOutputStream fileOut = new FileOutputStream("app_object.txt", true);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	public void read() {
		try {
			 
            FileInputStream fileIn = new FileInputStream("app_object.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
 
            App app = (App) objectIn.readObject();
            System.out.println(app.print());
            objectIn.close();
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	public void addComment(String comment) {
		comments.add(comment);
	}
	
	public static File readComment() throws IOException {
		File empty = new File("empty.txt");
		if(comments == null)
			return empty;
		BufferedWriter out = new BufferedWriter(new FileWriter("comments.txt", false));
		for(String line : comments)
			out.write(line + "\n");
		out.close();
		return new File("comments.txt");
	}
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class Database implements Comparator<App> {
	
	public static String name, level;
	
	public static void saveToFile(String username, String password) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter("Log-ins.txt", true));
		out.write(username + "," + password + "," + "User");
		out.newLine();
		out.close();
		name = username;
	}
	
	public static boolean search(String username) throws IOException {
		File file = new File("Log-ins.txt");
		file.createNewFile();
		BufferedReader read = new BufferedReader(new FileReader(file));
			String str;
			while ((str = read.readLine()) != null) {
			String[] arr = str.split(",");
			if(username.equals(arr[0])) {
				read.close();
				return true;
			}
			}
		read.close();
		return false;
	}
	
	public static boolean logging_in(String username, String password) throws IOException {
		BufferedReader read = new BufferedReader(new FileReader("Log-ins.txt"));
			String str;
			while ((str = read.readLine()) != null) {
			String[] arr = str.split(",");
			if(username.equals(arr[0]) && password.equals(arr[1])) {
				read.close();
				name = username;
				level = arr[2];
				return true;
			}
			}
		read.close();
		return false;
	}
	
	public static File filter_version(String vers) throws IOException {
		ArrayList<String> list = new ArrayList<String>();
		String line;
		BufferedReader read = new BufferedReader(new FileReader("app.txt"));
		while ((line = read.readLine()) != null) {
			String[] data = line.split(",");
			if(data[3].equals(vers))
				list.add(line);
		}
		read.close();
		BufferedWriter out = new BufferedWriter(new FileWriter("filter.txt", false));
		for(String app : list)
			out.write(app + "\n");
		out.close();
		File file = new File("filter.txt");
		return file;
	}
	
	public static File alphabet_sort_desc() throws IOException, ClassNotFoundException {
		ArrayList<App> list = new ArrayList<App>();
		boolean cont = true;
        FileInputStream fileIn = new FileInputStream("app_object.dat");
 
           while(cont) {
        	   try {
        		  ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        		  Object obj = objectIn.readObject();
        		  App app = (App) obj;
        			  list.add(app);
           } catch (Exception e) {
        	   cont = false;
           }
           }
           fileIn.close();
		Collections.sort(list, new Database());
		BufferedWriter out = new BufferedWriter(new FileWriter("sort.txt", false));
		for(App app : list)
			out.write(app.printData() + "\n");
		out.close();
		File file = new File("sort.txt");
		return file;
	}
	
	public static File alphabet_sort_asc() throws IOException, ClassNotFoundException {
		ArrayList<App> list = new ArrayList<App>();
		boolean cont = true;
        FileInputStream fileIn = new FileInputStream("app_object.dat");
 
           while(cont) {
        	   try {
        		  ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        		  Object obj = objectIn.readObject();
        		  App app = (App) obj;
        			  list.add(app);
           } catch (Exception e) {
        	   cont = false;
           }
           }
           fileIn.close();
		Collections.sort(list, new Database().reversed());
		BufferedWriter out = new BufferedWriter(new FileWriter("sort.txt", false));
		for(App app : list)
			out.write(app.printData() + "\n");
		out.close();
		File file = new File("sort.txt");
		return file;
	}
	
	public static File low_price() throws IOException, ClassNotFoundException {
		ArrayList<App> list = new ArrayList<App>();
		boolean cont = true;
        FileInputStream fileIn = new FileInputStream("app_object.dat");
 
           while(cont) {
        	   try {
        		  ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        		  Object obj = objectIn.readObject();
        		  App app = (App) obj;
        			  list.add(app);
           } catch (Exception e) {
        	   cont = false;
           }
           }
           fileIn.close();
		Collections.sort(list, new Price_Compare());
		BufferedWriter out = new BufferedWriter(new FileWriter("sort.txt", false));
		for(App app : list)
			out.write(app.printData() + "\n");
		out.close();
		File file = new File("sort.txt");
		return file;
	}
	
	public static File high_price() throws IOException, ClassNotFoundException {
		ArrayList<App> list = new ArrayList<App>();
		boolean cont = true;
        FileInputStream fileIn = new FileInputStream("app_object.dat");
 
           while(cont) {
        	   try {
        		  ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        		  Object obj = objectIn.readObject();
        		  App app = (App) obj;
        			  list.add(app);
           } catch (Exception e) {
        	   cont = false;
           }
           }
           fileIn.close();
		Collections.sort(list, new Price_Compare().reversed());
		BufferedWriter out = new BufferedWriter(new FileWriter("sort.txt", false));
		for(App app : list)
			out.write(app.printData() + "\n");
		out.close();
		File file = new File("sort.txt");
		return file;
	}

	public int compare(App a, App b) {
		return a.name.compareTo(b.name);
	}
}

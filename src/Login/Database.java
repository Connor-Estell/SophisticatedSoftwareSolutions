import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Database {
	
	public static void saveToFile(String username, String password) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter("Log-ins.txt", true));
		out.write(username + "," + password + "," + "User");
		out.newLine();
		out.close();
	}
	
	public static boolean search(String username) throws IOException {
		BufferedReader read = new BufferedReader(new FileReader("Log-ins.txt"));
			String str;
			while ((str = read.readLine()) != null) {
			String[] arr = str.split(",");
			if(username.equals(arr[0]))
				return true;
			}
		return false;
	}
	
	public static boolean logging_in(String username, String password) throws IOException {
		BufferedReader read = new BufferedReader(new FileReader("Log-ins.txt"));
			String str;
			while ((str = read.readLine()) != null) {
			String[] arr = str.split(",");
			if(username.equals(arr[0]) && password.equals(arr[1]))
				return true;
			}
		return false;
	}
}

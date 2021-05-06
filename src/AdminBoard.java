import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * 
 * @author samfisher (fishe108)
 * Name: Sophisticated Software Solutions
 * Instructor: Dr Stephan
 * Course: CSE 201, Section C
 * Date: 5/6/2021
 * Assignment: Appdex
 * File: AdminBoard.java
 * Description: Displays AdminBoard GUI, which shows 
 * the requests that have been submitted 
 * by users through the HomePage, and takes
 * them to the ReqMessage or DeclineMessage
 * screen to finish sending the submission
 * back to HomePage
 */
public class AdminBoard extends JFrame implements ActionListener {
	
	JButton approve, decline;
	JTextArea submissions;
	String reqInput;
	
	public AdminBoard() {
		super("Admin Board");
		setSize(1000, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		approve = new JButton("Approve");
		decline = new JButton("Decline");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(approve);
		buttonPanel.add(decline);
		
		submissions = new JTextArea(20, 80);
		
		JPanel subDisplayPanel = new JPanel();
		subDisplayPanel.setLayout(new FlowLayout());
		subDisplayPanel.add(submissions);
		
		approve.addActionListener(this);
		decline.addActionListener(this);
		
		add(subDisplayPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);
		
		readRequestsFromFile();
	}
	
	/*
	 * Reads requests from requests.txt and 
	 * displays them on the submissions Text area
	 */
	public void readRequestsFromFile() {
		// Declare a Scanner object to read from the file
		Scanner file = null;

		try {
			file = new Scanner(new File("requests.txt"));
		} catch (FileNotFoundException e) {
			// Print the stack trace if there is a FileNotFound exception
			e.printStackTrace();
			// Unable to locate the file. We don't want to perform rest of the operations
			return;
		}

		String str = "";
		while(file.hasNextLine()) {
			str += file.nextLine();
			if(file.hasNextLine())
				str += "\n";
		}
		file.close();
		submissions.setText(str);
		//return str;
	}
	
	/*
	 * Takes applications from app.txt and
	 * makes sure it is correctly stored
	 */
	public String readAppsFromFile() {
		Scanner file = null;

		try {
			file = new Scanner(new File("app.txt"));
		} catch (FileNotFoundException e) {
			// Print the stack trace if there is a FileNotFound exception
			e.printStackTrace();
			// Unable to locate the file. We don't want to perform rest of the operations
			return "";
		}

		String str = "";
		while(file.hasNextLine()) {
			str += file.nextLine();
			if(file.hasNextLine())
				str += "\n";
		}
		file.close();
		submissions.setText(str);
		return str;
	}
	
	/*
	 * Writes approved applications back to the HomePage
	 */
	public void writeAppsToFile() {
		// Declare an object PrintWriter object. It points to null
		PrintWriter pw = null;

		try {
			pw = new PrintWriter(new File("app.txt"));
			//pw.println(readAppsFromFile() + readFirstApp());
			pw.println(readAppsFromFile());
		} catch (FileNotFoundException e) {
			// Print the stack trace if there is a FileNotFound exception
			e.printStackTrace();
			// Unable to create file. We don't want to perform rest of the operations
			return;
		}
		pw.close();
	}
	
	/*
	 * takes the first requests within requests.txt
	 * and uses it to call the ReqMessage or DeclineMessage
	 * class accordingly. 
	 */
	public String readFirstApp() {
		Scanner file = null;

		try {
			file = new Scanner(new File("requests.txt"));
		} catch (FileNotFoundException e) {
			// Print the stack trace if there is a FileNotFound exception
			e.printStackTrace();
			// Unable to locate the file. We don't want to perform rest of the operations
			return "";
		}

		String str = "";
		for (int i = 0; i == 1; i++) {
			str += file.nextLine();
		}
		file.close();
		reqInput = str;
		return reqInput;
	}
	
	/*
	 * Deletes the first application from the 
	 * requests.txt, mostly used within the ReqMessage
	 * and DeclineMessage classes
	 */
	public void deleteAppFromFile() throws IOException {
		Scanner in = new Scanner("requests.txt");
		in.nextLine();
		
		FileWriter fs = new FileWriter("requests.txt");
		BufferedWriter output = new BufferedWriter(fs);
		while (in.hasNextLine()) {
			String fileCorrect = in.nextLine();
			if (fileCorrect.equals("\n")) {
				output.newLine();
			} else {
				output.write(fileCorrect);
			}
			output.newLine();
		}
		output.close();
	}
	
	public static void main (String[] args) {
		AdminBoard ab = new AdminBoard();
		ab.setSize(1000, 500);
		ab.setDefaultCloseOperation(EXIT_ON_CLOSE);
		ab.setVisible(true);
		//System.out.println(readAppsFromFile());
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("Approve")) {
			ReqMessage frame = new ReqMessage(readFirstApp());
			frame.setVisible(true);
			frame.setSize(500, 500);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			dispose();
			readRequestsFromFile();
			//writeAppsToFile();
		}
		if (arg0.getActionCommand().equals("Decline")) {
			DeclineMessage frame = new DeclineMessage(readFirstApp());
			frame.setVisible(true);
			frame.setSize(500, 500);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			dispose();
			readRequestsFromFile();
		}
	}
}

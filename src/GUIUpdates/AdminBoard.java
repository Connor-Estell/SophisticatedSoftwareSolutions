import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AdminBoard extends JFrame implements ActionListener {
	
	JButton approve, decline;
	JTextArea submissions;
	
	public AdminBoard() {
		super("Admin Board");
		
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
		
		add(subDisplayPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);
		
		readAppsFromFile();
	}
	
	public void writeAppsToFile() {
		// Declare an object PrintWriter object. It points to null
		PrintWriter pw = null;

		try {
			pw = new PrintWriter(new File("app.csv"));
			pw.println(submissions.getText());
		} catch (FileNotFoundException e) {
			// Print the stack trace if there is a FileNotFound exception
			e.printStackTrace();
			// Unable to create file. We don't want to perform rest of the operations
			return;
		}
		pw.close();
	}
	
	public void readAppsFromFile(){
		// Declare a Scanner object to read from the file
		Scanner file = null;

		try {
			file = new Scanner(new File("submissions.csv"));
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
	}
	
	public static void main (String[] args) {
		AdminBoard ab = new AdminBoard();
		ab.setSize(1000, 500);
		ab.setDefaultCloseOperation(EXIT_ON_CLOSE);
		ab.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
	}
}

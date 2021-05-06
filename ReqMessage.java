import java.awt.BorderLayout;
import java.awt.GridLayout;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ReqMessage extends JFrame implements ActionListener {
	
	private JLabel message, entry;
	private JTextField messageTF;
	private JTextArea entryTA;
	private JButton send, cancel;
	
	public ReqMessage(String input) {
		setTitle("Message to User");
		//setBounds(300, 90, 900, 600);
		setSize(1000, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel adminReqPanel = new JPanel();
		JPanel userReqPanel = new JPanel();
		
		//message = new JLabel("Message: ");
		//messageTF = new JTextField(400);
		send = new JButton("Send");
		cancel = new JButton("Cancel");
		
		adminReqPanel.setLayout(new GridLayout(2, 1));
		
		//adminReqPanel.add(message, 0);
		//adminReqPanel.add(messageTF, 1);
		adminReqPanel.add(send, 0);
		adminReqPanel.add(cancel, 1);
		
		entryTA = new JTextArea(10, 40);
		
		userReqPanel.add(entryTA);
		
		send.addActionListener(this);
		cancel.addActionListener(this);
		
		add(userReqPanel, BorderLayout.NORTH);
		add(adminReqPanel, BorderLayout.CENTER);
		
		entryTA.setText(input);
		readAppsFromFile();
	}
	
	public void readAppsFromFile(){
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
		entryTA.setText(str);
	}
	
	public void writeAppsToFile() throws IOException {
		// Declare an object PrintWriter object. It points to null
		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter("app.txt", true));
			bw.write(entryTA.getText() + " \n");
		} catch (FileNotFoundException e) {
			// Print the stack trace if there is a FileNotFound exception
			e.printStackTrace();
			// Unable to create file. We don't want to perform rest of the operations
			return;
		}
		bw.close();
	}
	
//	public void writeApprovalToInbox() {
//		PrintWriter pw = null;
//		
//		try {
//			pw = new PrintWriter(new File("inbox.txt"));
//			pw.println("Congrats! Your app: '" + entryTA.getText() + "' has been approved. Message: '" + messageTF.getText() + "'");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			return;
//		}
//		pw.close();
//	}
	
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Send")) {
			try {
				writeAppsToFile();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				deleteAppFromFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//readAppsFromFile();
			//writeApprovalToInbox();
			setVisible(false);
			HomePage hp =  new HomePage();
			hp.setSize(900, 900);
			hp.setDefaultCloseOperation(EXIT_ON_CLOSE);
			hp.setVisible(true);
		}
		if (e.getActionCommand().equals("Cancel")) {
			setVisible(false);
		}
	}
}

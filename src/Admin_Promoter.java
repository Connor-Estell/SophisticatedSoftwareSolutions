import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * Name: Sophisticated Software Solutions
 * Instructor: Dr Stephan
 * Course: CSE 201, Section C
 * Date: 5/6/2021
 * Assignment: Appdex
 * File: Admin_Promoter.java
 * Description: This class implements the functionality
 * for administrators to be able to promote
 * moderators to administrators. 
 */
public class Admin_Promoter  extends JFrame
implements ActionListener {
	  

private Container c;
private JLabel name;
protected JTextField tname;
protected JButton sub;
private static JTextArea tout;
public static int num, data;
public static ArrayList<String> list;


public Admin_Promoter()
{
    setTitle("User Selection");
    setBounds(200, 90, 1600, 900);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setResizable(false);

    c = getContentPane();
    c.setLayout(null);


    name = new JLabel("Select Option");
    name.setFont(new Font("Arial", Font.BOLD, 14));
    name.setSize(100, 20);
    name.setLocation(100, 100);
    c.add(name);

    tname = new JTextField();
    tname.setFont(new Font("Arial", Font.PLAIN, 15));
    tname.setSize(190, 20);
    tname.setLocation(100, 130);
    c.add(tname);
    
    sub = new JButton("Select");
    sub.setFont(new Font("Arial", Font.PLAIN, 15));
    sub.setSize(100, 20);
    sub.setLocation(100, 170);
    sub.addActionListener(this);
    c.add(sub);

    tout = new JTextArea();
    tout.setFont(new Font("Arial", Font.PLAIN, 12));
    tout.setSize(1000, 600);
    tout.setLocation(500, 100);
    tout.setLineWrap(true);
    tout.setEditable(false);
    c.add(tout);


    setVisible(true);
}

public static void main(String[] args) throws Exception
{
    Admin_Promoter f = new Admin_Promoter();
    userList();
}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sub) {
			if (tname.getText().equals("") || Integer.parseInt(tname.getText()) < 0 || Integer.parseInt(tname.getText()) > num)
				Login_Register.infoBox("Please put in a numberered option (0,1,2,etc)", "Comment error message");
			else {
				data = Integer.parseInt(tname.getText());
				String line;
			//	ArrayList<String> names = new ArrayList<String>();
				String str = list.get(data);
				String check = "";
				String[] arr = str.split(",");
				arr[2] = "Admin";
				str = arr[0] + "," + arr[1] + "," + arr[2];
				check = arr[0];
				try {
				BufferedReader read = new BufferedReader(new FileReader("Log-ins.txt"));
				list = new ArrayList<String>();
				list.add(str);
				while ((line = read.readLine()) != null) {
					arr = line.split(",");
					if(!check.equals(arr[0]))
						list.add(line);
				}
				read.close();
				} catch (Exception t) {
					
				}
				try {
				BufferedWriter out = new BufferedWriter(new FileWriter("Log-ins.txt", false));
				for (String word : list) {
					out.write(word);
					out.newLine();
				}
				out.close();
				} catch (Exception t) {
					
				}
				}
			Login_Register.infoBox("Mod upragaded to Admin", "Admin promotion success message");
				dispose();
			}
		}
	
	/*
	 * stores each moderators into a list that allows the admin
	 * to select the mod by a specific number in the list.
	 */
	public static void userList() throws IOException {
		File file = new File("Log-ins.txt");
		num = 0;
		list = new ArrayList<String>();
		BufferedReader read = new BufferedReader(new FileReader(file));
			String str;
			String line = "";
			while ((str = read.readLine()) != null) {
			String[] arr = str.split(",");
			if (arr[2].equals("Mod")) {
				line += "Option [" + num + "] Mod " + arr[0] + "\n\n";
				list.add(str);
				num++;
			}
			}
		read.close();
			tout.setText(line);
			}
}
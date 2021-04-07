import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Registration extends JFrame implements ActionListener{
	
	private Container contentPane;
	private JTextField tuser;
	private JTextField tpass;
	private JLabel username;
	private JLabel password;
	private JButton sub;
	private JButton ret;
	
	public Registration() {
		//Establish basic frame settings
		setTitle("Registration");
		setBounds(300, 90, 900, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//Establish content pane
		contentPane = getContentPane();
		contentPane.setLayout(null);
		
		//Establish username
		username = new JLabel("Set Username");
		username.setSize(100, 20);
		username.setLocation(100, 100);
		tuser = new JTextField();
		tuser.setSize(190, 20);
		tuser.setLocation(200, 100);
		contentPane.add(username);
		contentPane.add(tuser);
		
		//Establish password
		password = new JLabel("Set Password");
		password.setSize(100, 20);
		password.setLocation(100, 150);
		tpass = new JTextField();
		tpass.setSize(190, 20);
		tpass.setLocation(200, 150);
		contentPane.add(password);
		contentPane.add(tpass);
		
		//Establish submit button
		sub = new JButton("Submit");
		sub.setSize(100, 20);
		sub.setLocation(200, 200);
		sub.addActionListener(this);
		contentPane.add(sub);
		
		//Establish return button
		ret = new JButton("Go Back");
		ret.setSize(100, 20);
		ret.setLocation(350, 200);
		ret.addActionListener(this);
		contentPane.add(ret);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Submit")) {
			Boolean bool = null;
			try {
				bool = Database.search(tuser.getText());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if(tuser.getText().equals("") || tpass.getText().equals(""))
				Login_Register.infoBox("Please enter both a username and password", "Registration Error Message");
			else if(bool)
				Login_Register.infoBox("This username is already taken, please choose another.", "Registration Error Message");
			else if(tuser.getText().contains(",") || tpass.getText().contains(","))
				Login_Register.infoBox("Please do not use commas (,) when creating your Username and Password", "Registration Error Message");
			else if(tuser.getText().contains("\n") || tpass.getText().contains("\n"))
				Login_Register.infoBox("Please do not use \n when creating your Username and Password", "Registration Error Message");
			else if(tuser.getText().contains("\b") || tpass.getText().contains("\b"))
				Login_Register.infoBox("Please do not use \b when creating your Username and Password", "Registration Error Message");
			else if(tuser.getText().contains("\t") || tpass.getText().contains("\t"))
				Login_Register.infoBox("Please do not use \t when creating your Username and Password", "Registration Error Message");
			else if(tuser.getText().contains("\r") || tpass.getText().contains("\r"))
				Login_Register.infoBox("Please do not use \r when creating your Username and Password", "Registration Error Message");
			else if(tuser.getText().contains("\f") || tpass.getText().contains("\f"))
				Login_Register.infoBox("Please do not use \f when creating your Username and Password", "Registration Error Message");
			else if(tuser.getText().contains("\"") || tpass.getText().contains("\""))
				Login_Register.infoBox("Please do not use \" when creating your Username and Password", "Registration Error Message");
			else if(tuser.getText().contains("\\") || tpass.getText().contains("\\"))
				Login_Register.infoBox("Please do not use \\ when creating your Username and Password", "Registration Error Message");
			else {
			try {
				Database.saveToFile(tuser.getText(), tpass.getText());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Login_Register.infoBox("Thank you for registering!", "Registration Success");
			User frame = new User();
			frame.setVisible(true);
			frame.setSize(670, 670);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			dispose();
			}
		}
		
		if(e.getActionCommand().equals("Go Back"))
			setVisible(false);
	}
}

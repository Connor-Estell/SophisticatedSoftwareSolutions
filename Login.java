import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener{
	
	private Container contentPane;
	private JTextField tuser;
	private JTextField tpass;
	private JLabel username;
	private JLabel password;
	private JButton sub;
	private JButton ret;
	
	public Login() {
		//Establish basic frame settings
		setTitle("Login");
		setBounds(300, 90, 600, 400);
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
				bool = Database.logging_in(tuser.getText(), tpass.getText());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if(tuser.getText().equals("") || tpass.getText().equals(""))
				Login_Register.infoBox("Please enter both a username and password", "Log-in Error Message");
			else if(!bool)
				Login_Register.infoBox("Please input a correct username and password.", "Log-in Error Message");
			else if (Database.level.equals("Mod")) {
				Login_Register.infoBox("Thank you for logging in!", "Log-in Success");
				Moderator frame = new Moderator();
				frame.setVisible(true);
				frame.setSize(900, 900);
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				dispose();
			}
			else if (Database.level.equals("Admin")) {
				Login_Register.infoBox("Thank you for logging in!", "Log-in Success");
				Administrator frame = new Administrator();
				frame.setVisible(true);
				frame.setSize(900, 900);
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				dispose();
			}
			else {
				Login_Register.infoBox("Thank you for logging in!", "Log-in Success");
				User frame = new User();
				frame.setVisible(true);
				frame.setSize(900, 900);
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				dispose();
			}
	}
		
		if(e.getActionCommand().equals("Go Back"))
			setVisible(false);
}
}
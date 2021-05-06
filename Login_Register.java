import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Name: Sophisticated Software Solutions
 * Instructor: Dr Stephan
 * Course: CSE 201, Section C
 * Date: 5/6/2021
 * Assignment: Appdex
 * File: Login_Register.java
 * Description: This displays a simple gui that
 * gives the user the option of logging in or registering
 * after they have pressed the "Log-in/Register" button
 * from the HomePage. 
 */
public class Login_Register extends JFrame implements ActionListener{
	//Gets container
	private JPanel options;
	private Container window;
	
	public Login_Register() {
		//Gets container
		window = getContentPane();
		options = new JPanel();
		
		//Adds sign-in and register buttons
		JButton sign_in = new JButton("Log-in");
		JButton register = new JButton("Register"); 
		JPanel panel = new JPanel();
		
		//Adds action listeners
		sign_in.addActionListener(this);
		register.addActionListener(this);
		
		//Adds buttons to the panel
		options.add(sign_in);
		options.add(register);
		window.add(options);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Register")) {
			Registration frame = new Registration();
			frame.setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			dispose();
		}
		
		if(e.getActionCommand().equals("Log-in")) {
			Login frame = new Login();
			frame.setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			dispose();
		}
	}
	
	public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
	
	public static void main(String[] args) {
		Login_Register wl = new Login_Register();
		wl.setTitle("Log-in or Register");
		wl.setBounds(300, 90, 900, 600);
		wl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wl.setVisible(true);
	}
	
}
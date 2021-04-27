import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Comment_Section extends JFrame implements ActionListener{
	
	private JPanel options;
	private Container window;
	
	public Comment_Section() {
		//Gets container
		window = getContentPane();
		options = new JPanel();
		
		//Adds sign-in and register buttons
		JButton read = new JButton("Choose App");
		JTextField choice = new JTextField(50);
		JTextArea comTA = new JTextArea(20, 80);
		
		//Adds action listeners
		read.addActionListener(this);
		
		//Adds buttons to the panel
		comTA.setBounds(10, 10, 100, 100);;
		options.add(comTA);
		options.add(read);
		options.add(choice);
		window.add(options);
	}
	
	public static void main (String[] args) {
		Comment_Section hp =  new Comment_Section();
		hp.setSize(900, 900);
		hp.setDefaultCloseOperation(EXIT_ON_CLOSE);
		hp.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void appList() {
		
	}
}

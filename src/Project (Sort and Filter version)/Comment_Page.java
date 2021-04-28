import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Comment_Page  extends JFrame
implements ActionListener {
	  
// Components of the Form
private Container c;
private JLabel name, add;
private JButton sub, sub2;
private static JTextArea tout;

// constructor, to initialize the components
// with default values.
public Comment_Page()
{
    setTitle("App Comments");
    setBounds(200, 90, 1600, 900);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setResizable(false);

    c = getContentPane();
    c.setLayout(null);


    name = new JLabel("Read other comments");
    name.setFont(new Font("Arial", Font.BOLD, 14));
    name.setSize(200, 20);
    name.setLocation(100, 100);
    c.add(name);

    sub = new JButton("Select");
    sub.setFont(new Font("Arial", Font.PLAIN, 15));
    sub.setSize(100, 20);
    sub.setLocation(100, 150);
    sub.addActionListener(this);
    c.add(sub);
    
    add = new JLabel("Add comments to other apps");
    add.setFont(new Font("Arial", Font.BOLD, 14));
    add.setSize(250, 20);
    add.setLocation(100, 200);
    c.add(add);
    
    sub2 = new JButton("Select");
    sub2.setFont(new Font("Arial", Font.PLAIN, 15));
    sub2.setSize(100, 20);
    sub2.setLocation(100, 250);
    sub2.addActionListener(this);
    c.add(sub2);

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
    Comment_Page f = new Comment_Page();
}

	@Override
	public void actionPerformed(ActionEvent arg) {
		if (arg.getSource() == sub) {
			Comment_Section frame = new Comment_Section();
			try {
				Comment_Section.createList();
			} catch (IOException e) {
				e.printStackTrace();
			}
		    Comment_Section.appList();
			frame.setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			dispose();
		}
		if (arg.getSource() == sub2) {
			Comment_Add frame = new Comment_Add();
			try {
				Comment_Add.createList();
			} catch (IOException e) {
				e.printStackTrace();
			}
		    Comment_Add.appList();
			frame.setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			dispose();
		}
		}
	
	public void readComments(ArrayList<String> list) throws IOException {
		/*BufferedReader read = new BufferedReader(new FileReader(file));
		String str = "", line;
		while ((line = read.readLine()) != null)*/
		String str = "";
		for(String line : list)
			str += line + "\n\n";
		tout.setText(str);
		//read.close();
	}
}

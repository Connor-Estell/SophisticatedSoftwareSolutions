import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Comment_Add  extends JFrame
implements ActionListener {
	  
// Components of the Form
private Container c;
private JLabel name;
protected JTextField tname;
protected JButton sub;
private static JTextArea tout;
public static int num, data;
public static ArrayList<App> list;

// constructor, to initialize the components
// with default values.
public Comment_Add()
{
    setTitle("App Selection");
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
    Comment_Add f = new Comment_Add();
    createList();
    appList();
}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sub) {
			if (tname.getText().equals("") || Integer.parseInt(tname.getText()) < 0 || Integer.parseInt(tname.getText()) > num)
				Login_Register.infoBox("Please put in a numberered option (0,1,2,etc)", "Comment error message");
			else {
				data = Integer.parseInt(tname.getText());
				Add_Page frame = new Add_Page();
				frame.setBounds(300, 90, 600, 600);
				frame.setVisible(true);
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				dispose();
			}
		}
		
		
	}
	
	public static void appList() {
			String str = "";
			num = 0;
			for (App app : list) {
				str += "Option [" + num + "]  " + app.print() + "\n\n";
				num++;
			}
			tout.setText(str);
			}
	
	public static void createList() throws IOException {
		boolean cont = true;
	    FileInputStream fileIn = new FileInputStream("app_object.dat");
	    list = new ArrayList<App>();

	       while(cont) {
	    	   try {
	    		  ObjectInputStream objectIn = new ObjectInputStream(fileIn);
	    		  Object obj = objectIn.readObject();
	    		  App app = (App) obj;
	    			  list.add(app);
	       } catch (Exception e) {
	    	   cont = false;
	       }
	       }
	       fileIn.close();
	}	
}
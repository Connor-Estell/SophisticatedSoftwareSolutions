import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Comment_Delete_Page  extends JFrame
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
public Comment_Delete_Page()
{
    setTitle("Comment Selection");
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


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sub) {
			if (tname.getText().equals("") || Integer.parseInt(tname.getText()) < 0 || Integer.parseInt(tname.getText()) > num)
				Login_Register.infoBox("Please put in a numberered option (0,1,2,etc)", "Comment error message");
			else {
				data = Integer.parseInt(tname.getText());
				Comment_Delete_Selection.map.get(Comment_Delete_Selection.app.printName()).remove(data);
				try {
					Add_Page.save(Comment_Delete_Selection.map);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				Comment_Page frame = new Comment_Page();
				try {
					frame.readComments(Comment_Delete_Selection.map.get(Comment_Delete_Selection.app.printName()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setVisible(true);
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				dispose();
			}
		}
		
		
	}
	
	public void commentList(ArrayList<String> list) {
			String str = "";
			num = 0;
			for (String line : list) {
				str += "Option [" + num + "]  " + line + "\n\n";
				num++;
			}
			tout.setText(str);
			}
}
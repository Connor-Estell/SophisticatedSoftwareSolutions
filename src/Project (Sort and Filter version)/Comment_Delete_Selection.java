import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Comment_Delete_Selection extends Comment_Add {
	
	static HashMap<String, ArrayList<String>> map;
	static App app;
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sub) {
			if (tname.getText().equals("") || Integer.parseInt(tname.getText()) < 0 || Integer.parseInt(tname.getText()) > num)
				Login_Register.infoBox("Please put in a numberered option (0,1,2,etc)", "Comment error message");
			else {
				data = Integer.parseInt(tname.getText());
				Comment_Delete_Page frame = new Comment_Delete_Page();
				setBounds(200, 90, 1600, 900);
				app = list.get(data);
				try {
					map = Add_Page.getMap(app);
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.commentList(map.get(app.printName()));
				frame.setVisible(true);
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				dispose();
			}
		}
		
		
	}
}

package myVoca;

import java.awt.*;
import javax.swing.*;


public class NewFolder extends JFrame{
	
	JLabel newFold;
	JTextField folderName;
	JButton cancle, create;

	public NewFolder() {
		
		setSize(550, 250);
		setTitle("»õ Æú´õ »ý¼º");
		Container c = getContentPane();
		c.setBackground(Color.white);
		c.setLayout(null);
		
		newFold = new JLabel("»õ Æú´õ »ý¼º");
		newFold.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 25));
		newFold.setBounds(45, 40, 200, 30);
		
		folderName = new JTextField();
		folderName.setBounds(90, 95, 350, 30);
		
		cancle = new JButton("Ãë¼Ò");
		cancle.setFont(new Font("³ª´®½ºÄù¾î Bold", 0, 15));
		cancle.setBounds(310, 150, 80, 30);
		cancle.setBackground(Color.white);
		
		create = new JButton("»ý¼º");
		create.setFont(new Font("³ª´®½ºÄù¾î Bold", 0, 15));
		create.setBounds(400, 150, 80, 30);
		create.setBackground(Color.white);
		
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.add(newFold);
		c.add(folderName);
		c.add(cancle);
		c.add(create);
	}
	
	public static void main(String[] args) {
		new NewFolder();
	}
}

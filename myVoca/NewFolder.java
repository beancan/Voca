package myVoca;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class NewFolder extends JPanel implements ActionListener {
	
	JLabel newFold;
	JTextField folderName;
	JButton cancle, create;
	JPanel p;
	
	DBMgr mgr;
	static String myId;

	public NewFolder(String myId) {
		setSize(900, 550);
		setBackground(Color.white);
		setLayout(null);
		this.myId = myId;
		mgr = new DBMgr();
		
		TitledBorder border = new TitledBorder(new LineBorder(Color.gray));
		p = new JPanel();
		p.setBounds(170, 120, 550, 250);
		p.setBorder(border);
		p.setBackground(Color.white);
		p.setLayout(null);
		
		newFold = new JLabel("�� ���� ����");
		newFold.setFont(new Font("���������� ExtraBold", 0, 25));
		newFold.setBounds(45, 40, 200, 30);
		
		folderName = new JTextField();
		folderName.setBounds(90, 95, 350, 30);
		
		cancle = new JButton("���");
		cancle.setFont(new Font("���������� Bold", 0, 15));
		cancle.setBounds(310, 150, 80, 30);
		cancle.setBackground(Color.white);
		cancle.addActionListener(this);
		
		create = new JButton("����");
		create.setFont(new Font("���������� Bold", 0, 15));
		create.setBounds(400, 150, 80, 30);
		create.setBackground(Color.white);
		create.addActionListener(this);
		
		p.add(newFold);
		p.add(folderName);
		p.add(cancle);
		p.add(create);
		add(p);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj==cancle) {
			boolean wordflag = mgr.getWordFlag(myId);
			
			if(wordflag==true) {
				removeAll();
				setLayout(new BorderLayout());
				add(new ManageSet(myId));
				validate();
				repaint();
			}
			else {
				removeAll();
				setLayout(new BorderLayout());
				add(new NoSet(myId));
				revalidate();
				repaint();
			}
		}
		else if(obj==create) {
			removeAll();
			add(new NewSet(folderName.getText(), myId));
			validate();
			repaint();
		}
	}
}

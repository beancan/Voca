package myVoca;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class NewSet extends JPanel implements ActionListener {
	
	JLabel newFold;
	JTextField setName;
	JButton cancle, create;
	JPanel p;
	DBMgr mgr;
	String myId, fname;

	public NewSet(String fname, String myId) {
		setSize(900, 550);
		setBackground(Color.WHITE);
		setLayout(null);
		this.myId = myId;
		this.fname = fname;
		mgr = new DBMgr();
		
		TitledBorder border = new TitledBorder(new LineBorder(Color.gray));
		p = new JPanel();
		p.setBounds(170, 120, 550, 250);
		p.setBorder(border);
		p.setBackground(Color.white);
		p.setLayout(null);
		
		newFold = new JLabel("»õ ´Ü¾î¼¼Æ® »ý¼º");
		newFold.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 25));
		newFold.setBounds(45, 40, 200, 30);
		
		setName = new JTextField();
		setName.setBounds(90, 95, 350, 30);
		
		cancle = new JButton("Ãë¼Ò");
		cancle.setFont(new Font("³ª´®½ºÄù¾î Bold", 0, 15));
		cancle.setBounds(310, 150, 80, 30);
		cancle.setBackground(Color.white);
		cancle.addActionListener(this);
		
		create = new JButton("»ý¼º");
		create.setFont(new Font("³ª´®½ºÄù¾î Bold", 0, 15));
		create.setBounds(400, 150, 80, 30);
		create.setBackground(Color.white);
		create.addActionListener(this);
		
		p.add(newFold);
		p.add(setName);
		p.add(cancle);
		p.add(create);
		add(p);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		boolean wordflag = mgr.getWordFlag(myId);
		if(obj==cancle) {
			if(wordflag==false) {
				removeAll();
				setLayout(new BorderLayout());
				add(new NoSet(myId));
				revalidate();
				repaint();
			} else {
				removeAll();
				setLayout(new BorderLayout());
				add(new ManageSet(myId));
				revalidate();
				repaint();
			}
		}
		else if(obj==create) {
			removeAll();
			add(new CreateSet(fname, setName.getText(), myId));
			revalidate();
			repaint();
		}
	}	
}

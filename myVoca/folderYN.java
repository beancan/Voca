package myVoca;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class folderYN extends JPanel implements ActionListener{
	
	//JLabel newFold;
	JLabel guide;
	JButton noBtn, yesBtn;
	JPanel p;
	String myId;

	public folderYN(String myId) {
		setSize(900, 550);
		setBackground(Color.white);
		setLayout(null);
		
		this.myId = myId;

		TitledBorder border = new TitledBorder(new LineBorder(Color.gray));
		p = new JPanel();
		p.setBounds(170, 120, 550, 250);
		p.setBorder(border);
		p.setBackground(Color.white);
		p.setLayout(null);
		
		guide = new JLabel("Æú´õ¸¦ »ý¼ºÇÏ½Ã°Ú½À´Ï±î?");
		guide.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 25));
		guide.setBounds(145, 100, 350, 30);
		
		noBtn = new JButton("¾Æ´Ï¿À");
		noBtn.setFont(new Font("³ª´®½ºÄù¾î Bold", 0, 15));
		noBtn.setBounds(330, 180, 80, 30);
		noBtn.setBackground(Color.white);
		noBtn.addActionListener(this);
		
		yesBtn = new JButton("¿¹");
		yesBtn.setFont(new Font("³ª´®½ºÄù¾î Bold", 0, 15));
		yesBtn.setBounds(420, 180, 80, 30);
		yesBtn.setBackground(Color.white);
		yesBtn.addActionListener(this);
		
		setVisible(true);
		
		p.add(guide);
		p.add(noBtn);
		p.add(yesBtn);
		add(p);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==noBtn) {
			removeAll();
			add(new NewSet("null", myId));
			validate();
			repaint();
		
		} else if(obj==yesBtn) {
			removeAll();
			add(new NewFolder(myId, 2));
			validate();
			repaint();
		}
	}

}

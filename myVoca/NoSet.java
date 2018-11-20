package myVoca;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoSet extends JPanel implements ActionListener {
	ImageIcon icon;
	Image img;
	JLabel imgL, lb1, lb2;
	JButton createBtn;
	Line newLine = new Line();
	String myId;
	
	public NoSet(String myId) {
		setSize(900, 550);
		setBackground(Color.white);
		setLayout(null);
		this.myId = myId;
		
		lb1 = new JLabel("단어세트가 없어요.");
		lb1.setFont(new Font("나눔스퀘어 ExtraBold", 0, 25));
		lb1.setBounds(375, 200, 200, 30);
		lb1.setForeground(Color.gray);
		
		lb2 = new JLabel("단어세트를 생성해주세요!");
		lb2.setFont(new Font("나눔스퀘어 ExtraBold", 0, 25));
		lb2.setBounds(320, 240, 300, 30);
		lb2.setForeground(Color.gray);
		
		icon = new ImageIcon("myVoca/tear2.png");
		img = icon.getImage();
		img.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);
		imgL = new JLabel(icon);
		imgL.setSize(100, 100);
		imgL.setBounds(220, 145, 200, 100);
		
		// 라인
		newLine.setBounds(190, 300, 500, 20);
		newLine.setBackground(Color.white);
		
		// 버튼
		createBtn = new JButton("생성하기");
		createBtn.setFont(new Font("나눔스퀘어 ExtraBold", 0, 16));
		createBtn.setForeground(Color.gray);
		createBtn.setBounds(385, 330, 100, 30);
		createBtn.setBackground(Color.WHITE);
		createBtn.addActionListener(this);
		
		add(lb1);
		add(lb2);
		add(imgL);
		add(newLine);
		add(createBtn);
	}
	
	class Line extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.gray);
			g.drawLine(20, 10, 500, 10);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		removeAll();
		add(new folderYN(myId));
		validate();
		repaint();
	}
}

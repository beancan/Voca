package myVoca;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.*;

public class Friends extends JFrame {
	
	JButton btn, btn1, sksk;
	JPanel Mainpn1,Mainpn2, p1, p2;
	JLabel MainLb, Lb, LbPn, picLb;
	ImageIcon icon;
	BorderLayout bLayout = new BorderLayout();
	private String setPath = "myVoca/asdf.png";
	
	public Friends() {
		setSize(800, 550);
		Container c = getContentPane();
		c.setLayout(null);
		
		LbPn = new JLabel("�� ģ�� ���");
		LbPn.setBounds(80, 80, 150, 50);
		LbPn.setFont(new Font("���� ���", Font.BOLD, 20));
		
		Mainpn1 = new JPanel();
		Mainpn1.setSize(500, 300);
		Mainpn1.setLayout(new BorderLayout());
		Mainpn1.setBounds(70,172,280,280);
		
		p1 = new JPanel();
		p1.setLayout(new GridLayout(20, 1));
		for (int i = 0; i < 20; i++) {
			p1.add(sksk = new JButton("��ư" + i));
			sksk.setBorderPainted(false);
			sksk.setContentAreaFilled(false);
		}
		
		Lb = new JLabel("ģ�� ID");
		Lb.setFont(new Font("���������� ExtraBold", 0, 20));
		Lb.setOpaque(true);
		Lb.setBounds(70,142,279,30);
		Lb.setBackground(Color.DARK_GRAY);
		Lb.setForeground(Color.WHITE);
		Lb.setHorizontalAlignment(JLabel.CENTER);
		
		btn = new JButton("ģ�� ����");
		btn.setBounds(170,250,100,30);
		btn1 = new JButton("�ܾƮ ����");
		btn1.setBounds(50,250,115,30);
		
		Mainpn2 = new JPanel();
		Mainpn2.setSize(500, 300);
		Mainpn2.setBackground(Color.LIGHT_GRAY);
		Mainpn2.setLayout(null);
		Mainpn2.setBounds(450,150,280,300);
		
		icon = new ImageIcon(setPath);
		Image imgbf = icon.getImage();
		Image imgaf = imgbf.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		picLb = new JLabel(new ImageIcon(imgaf));
		picLb.setBounds(30, 80, 100, 100);
		
		MainLb = new JLabel("ģ�� 1");
		MainLb.setFont(new Font("���� ���", Font.BOLD, 20));
		MainLb.setBounds(150,90,123,70);

		JScrollPane sp = new JScrollPane(p1, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sp.getVerticalScrollBar().setUnitIncrement(16);
		
		c.add(LbPn);
		c.add(Mainpn1);
		Mainpn1.add(sp);
		c.add(Mainpn2);
		Mainpn2.add(btn);
		Mainpn2.add(btn1);
		Mainpn2.add(MainLb);
		Mainpn2.add(picLb);
		c.add(Lb);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}

	public static void main(String[] args) {
		new Friends();
	}

}

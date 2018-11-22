package myVoca;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

public class Friends extends JPanel implements ActionListener {
	
	JButton btn, btn1, btnadd;
	JButton[] sksk;
	JPanel Mainpn1,Mainpn2, p1, p2;
	JLabel MainLb, Lb, LbPn, picLb;
	ImageIcon icon;
	BorderLayout bLayout = new BorderLayout();
	Vector<VocaBean> btnStr;
	DBMgr dba;
	String myId;
	
	private String setPath = "myVoca/asdf.png";
	
	public Friends(String myId) {
		
		setSize(800, 550);
		setLayout(null);
		setBackground(Color.WHITE);
		this.myId = myId;
		
		LbPn = new JLabel("내 친구 목록");
		LbPn.setBounds(80, 84, 150, 50);
		LbPn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		Mainpn1 = new JPanel();
		Mainpn1.setSize(500, 300);
		Mainpn1.setLayout(new BorderLayout());
		Mainpn1.setBounds(70,172,280,280);
		
		p1 = new JPanel();
		p1.setLayout(new GridLayout(20, 1));
		dba = new DBMgr();
		btnStr = dba.getFriends(myId);
		
		sksk = new JButton[btnStr.size()];
		
		for (int i = 0; i < btnStr.size(); i++) {
			sksk[i] = new JButton(btnStr.elementAt(i).getId());
			sksk[i].addActionListener(this);
			sksk[i].setBorderPainted(false);
			sksk[i].setContentAreaFilled(false);
			p1.add(sksk[i]);
			repaint();
		}
		
		Lb = new JLabel("친구 ID");
		Lb.setFont(new Font("나눔스퀘어 ExtraBold", 0, 20));
		Lb.setOpaque(true);
		Lb.setBounds(70,142,279,30);
		Lb.setBackground(Color.DARK_GRAY);
		Lb.setForeground(Color.WHITE);
		Lb.setHorizontalAlignment(JLabel.CENTER);
		
		btn = new JButton("친구 삭제");
		btn.setBounds(170,250,100,30);
		btn.setBackground(Color.BLACK);
		btn.setForeground(Color.WHITE);
		btn1 = new JButton("단어세트 보기");
		btn1.setBounds(50,250,115,30);
		btn1.setBackground(Color.BLACK);
		btn1.setForeground(Color.WHITE);
		btnadd = new JButton("친구 추가");
		btnadd.setBounds(260,95,90,30);
		btnadd.setBackground(Color.BLACK);
		btnadd.setForeground(Color.WHITE);
		
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
		
		MainLb = new JLabel();
		MainLb.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		MainLb.setBounds(150,90,123,70);

		JScrollPane sp = new JScrollPane(p1, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sp.getVerticalScrollBar().setUnitIncrement(16);
		
		btn.addActionListener(this);
		btn1.addActionListener(this);
		btnadd.addActionListener(this);
		
		add(LbPn);
		add(Mainpn1);
		add(btnadd);
		add(Lb);
		Mainpn1.add(sp);
		
		Mainpn2.add(btn);
		Mainpn2.add(btn1);
		Mainpn2.add(MainLb);
		Mainpn2.add(picLb);
		add(Mainpn2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnadd) {
			new FrAdd(myId);
		}
		else if(obj == btn1) { // 친구 단어세트 보기
			removeAll();
			setLayout(new BorderLayout());
			System.out.println(MainLb.getText());
			add(new ViewFriendSets(MainLb.getText()), BorderLayout.CENTER);
			revalidate();
			repaint();
		}
		else if(obj == btn) { // 친구 삭제
			int ans = JOptionPane.showConfirmDialog(this, "친구를 삭제하시겠습니까?", "친구 삭제", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
			
			if(ans == 0) { // 예 눌렀을 때
				dba.deleteFriend(myId, MainLb.getText());
				removeAll();
				add(new Friends(myId));
				revalidate();
				repaint();
			}
			else if(ans == 1) { // 아니오 눌렀을 때
				return;
			}
		}
		
		for (int i = 0; i < btnStr.size(); i++) {
			if(obj == sksk[i]) {
				MainLb.setText(btnStr.elementAt(i).getId());
			}
		}
	}
}

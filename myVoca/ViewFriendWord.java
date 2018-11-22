package myVoca;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

public class ViewFriendWord extends JPanel implements ActionListener {
	
	JLabel setNameLbl, descLbl;
	JButton wordBtn;
	JPanel p1;
	String img;
	String friendId, setName;
	DBMgr mgr;
	Vector<VocaBean> words;
	int index = 0;
	
	public ViewFriendWord(String setName, String friendId) {
		
		setSize(900, 550);
		setLayout(null);
		setBackground(Color.WHITE);
		mgr = new DBMgr();
		words = new Vector<VocaBean>();
		words = mgr.getWordDesc(setName, friendId);
		this.setName = setName;
		this.friendId = friendId;
		
		setNameLbl = new JLabel(setName);
		setNameLbl.setFont(new Font("���������� ExtraBold", 0, 35));
		setNameLbl.setBounds(100, 60, 700, 30);
		
		p1 = new JPanel();
		p1.setSize(500, 450);
		p1.setBackground(Color.white);
		p1.setBounds(190, 90, 500, 450);
		p1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));

		// p1
		wordBtn = new JButton("����");
		wordBtn.setFont(new Font("���������� ExtraBold", 0, 40));
		wordBtn.setPreferredSize(new Dimension(500, 250)); // ��ưũ�� ����
		wordBtn.setBackground(Color.white);
		wordBtn.addActionListener(this);
		
		descLbl = new JLabel();
		p1.add(wordBtn);
		
		add(setNameLbl);
		add(p1);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if(obj==wordBtn) {
			System.out.println("���� ��: " + (index));
			
			if(index <= words.size()-1) {
				nextWord(index);
				index++;
			}
			else {
				JOptionPane.showMessageDialog(null, "�ܾ��� ����� ���Դϴ�. ó������ �ǵ��� ���ϴ�.");
				removeAll();
				add(new ViewFriendWord(setName, friendId));
				revalidate();
				repaint();
			}
		}
	}
	
	public void nextWord(int index) {
		String w = words.elementAt(index).getWord();
		String d = words.elementAt(index).getDesc();
		
		wordBtn.setText(w);
		descLbl.setText(newLine(d, 20));
		descLbl.setFont(new Font("������� ExtraBold", 0, 20));
		p1.add(descLbl);
	}
	
	public String newLine(String str, int maxCnt) {		// ���� ������ �Լ�
		String start = "<html><body>";
		String end = "</body></html>";
		int forx = str.length()/maxCnt;
		String result = "";
		
		for(int i=0;i<forx;i++) {
			result = result+str.substring(i*maxCnt, (i*maxCnt)+maxCnt)+"<br>";
		}
		if(str.length()%maxCnt >0) {
			result = result+str.substring((str.length()/maxCnt)*maxCnt);
		}
		
		return start+result+end;
	}
}

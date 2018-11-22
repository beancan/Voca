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
		setNameLbl.setFont(new Font("나눔스퀘어 ExtraBold", 0, 35));
		setNameLbl.setBounds(100, 60, 700, 30);
		
		p1 = new JPanel();
		p1.setSize(500, 450);
		p1.setBackground(Color.white);
		p1.setBounds(190, 90, 500, 450);
		p1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));

		// p1
		wordBtn = new JButton("시작");
		wordBtn.setFont(new Font("나눔스퀘어 ExtraBold", 0, 40));
		wordBtn.setPreferredSize(new Dimension(500, 250)); // 버튼크기 조절
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
			System.out.println("현재 값: " + (index));
			
			if(index <= words.size()-1) {
				nextWord(index);
				index++;
			}
			else {
				JOptionPane.showMessageDialog(null, "단어장 목록의 끝입니다. 처음으로 되돌아 갑니다.");
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
		descLbl.setFont(new Font("나눔고딕 ExtraBold", 0, 20));
		p1.add(descLbl);
	}
	
	public String newLine(String str, int maxCnt) {		// 글자 내리는 함수
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

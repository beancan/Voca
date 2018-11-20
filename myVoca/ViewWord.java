package myVoca;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.*;

public class ViewWord extends JPanel implements ActionListener {
	
	JLabel setNameL, descr;
	JButton wordBtn;
	JPanel p1;
	static String setName;
	static String myId;
	Vector<VocaBean> btnStr;
	int btnc = 0;	//	버튼 클릭 횟수 체크
	
	public ViewWord(String setName, String myId) {		// 매개변수 추가(단어세트 이름 받아와야 함)
		setSize(800, 550);
		setLayout(null);
		setBackground(Color.WHITE);
		
		this.setName = setName;
		this.myId = myId;
		
		setNameL = new JLabel(setName);
		setNameL.setFont(new Font("나눔스퀘어 ExtraBold", 0, 28));
		setNameL.setBounds(50, 50, 700, 30);
		
		p1 = new JPanel();
		p1.setSize(500, 450);
		p1.setBackground(Color.white);
		p1.setBounds(140, 90, 500, 450);
		p1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
		
		// p1
		wordBtn = new JButton("시작 버튼");
		wordBtn.setFont(new Font("나눔스퀘어 ExtraBold", 0, 40));
		wordBtn.setPreferredSize(new Dimension(500, 250));		// 버튼크기 조절
		wordBtn.setBackground(Color.white);
		
		descr = new JLabel();
		wordBtn.addActionListener(this);
		p1.add(wordBtn);
		
		add(setNameL);
		add(p1);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		DBMgr dba = new DBMgr();
		
		try {
			String e1 = dba.getWords(setName, myId).elementAt(btnc).getWord();
			String e2 = dba.getWords(setName, myId).elementAt(btnc).getDesc();
		
			wordBtn.setText(e1);
			String str = e2;		// 글자수 체크해서 끝에 <br>붙이기
			String result = newLine(str, 20);
		
			descr.setText(result);
			descr.setFont(new Font("나눔스퀘어 Bold", 0, 20));
			p1.add(descr);
		
			btnc++;
		
		}
		catch(ArrayIndexOutOfBoundsException E) {
			JOptionPane.showMessageDialog(null, "단어장 목록의 끝입니다. 처음으로 되돌아 갑니다.");
			btnc = 0;
		}
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

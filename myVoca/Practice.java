package myVoca;

import java.awt.*;
import javax.swing.*;

public class Practice extends JFrame {
	
	JLabel setNameL, descr;
	JButton wordBtn;
	JPanel p1;
	
	public Practice() {
		setSize(800, 550);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.WHITE);
		
		setNameL = new JLabel("단어세트 이름");
		setNameL.setFont(new Font("나눔스퀘어 ExtraBold", 0, 28));
		setNameL.setBounds(50, 50, 700, 30);
		
		p1 = new JPanel();
		p1.setSize(500, 450);
		p1.setBackground(Color.white);
		p1.setBounds(140, 90, 500, 450);
		p1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
		
		// p1
		wordBtn = new JButton("단어");
		wordBtn.setFont(new Font("나눔스퀘어 ExtraBold", 0, 40));
		wordBtn.setPreferredSize(new Dimension(500, 250));		// 버튼크기 조절
		wordBtn.setBackground(Color.white);
		String str = "설명명서런ㅇㅁㄻㅎㅁㄴㅇㄹ넝설명설명살명어어쩌고저쩌고랄라ㅜ루랄ㄹ라올안로릴ㄹㄹ리릴릴벱베킼라루니ㄹ닝라ㅜㅁㄴㅇ리라무니울미나";		// 글자수 체크해서 끝에 <br>붙이기
		String result = newLine(str, 20);
		descr = new JLabel(result);
		descr.setFont(new Font("나눔스퀘어 Bold", 0, 20));
		
		
		p1.add(wordBtn);
		p1.add(descr);
		
		c.add(setNameL);
		c.add(p1);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
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
	
	public static void main(String[] args) {
		new Practice();
	}
}

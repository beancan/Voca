package myVoca;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.*;

public class ViewWord extends JPanel implements ActionListener {
	
	JLabel setNameLbl, descLbl;
	JButton wordBtn, bookmarkBtn;
	JPanel p1;
	String img;
	String myId, setName;
	DBMgr mgr;
	Vector<VocaBean> words;
	int index = 0;
	
	public ViewWord(String setName, String myId) {		// 매개변수 추가(단어세트 이름 받아와야 함)
		
		setSize(900, 550);
		setLayout(null);
		setBackground(Color.WHITE);
		mgr = new DBMgr();
		words = new Vector<VocaBean>();
		words = mgr.getWordDesc(setName, myId);
		this.setName = setName;
		this.myId = myId;
		
		setNameLbl = new JLabel(setName);
		setNameLbl.setFont(new Font("나눔스퀘어 ExtraBold", 0, 35));
		setNameLbl.setBounds(100, 60, 700, 30);
	
		bookmarkBtn = new JButton(new ImageIcon("myVoca/noBookmark.png"));	
		bookmarkBtn.setBounds(700, 120, 35, 35);
		bookmarkBtn.setBackground(Color.white);
		bookmarkBtn.setBorder(null);
		bookmarkBtn.addActionListener(this);
		bookmarkBtn.setVisible(false);
		
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
		add(bookmarkBtn);
		add(p1);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if(obj==wordBtn) {
			System.out.println("현재 값: " + (index));
			
			if(index <= words.size()-1) {
				nextWord(index);
				bookmarkBtn.setVisible(true);
				index++;
			}
			else {
				JOptionPane.showMessageDialog(null, "단어장 목록의 끝입니다. 처음으로 되돌아 갑니다.");
				removeAll();
				add(new ViewWord(setName, myId));
				revalidate();
				repaint();
			}
		}
		else if (obj == bookmarkBtn) {
			int isMarked = words.elementAt(index-1).getBookmark();
			
			if(isMarked == 1) {
				VocaBean bean = new VocaBean();
				bookmarkBtn.setIcon(new ImageIcon("myVoca/noBookmark.png"));
				bean.setId(myId);
				System.out.println(myId);
				bean.setWord(words.elementAt(index-1).getWord());
				mgr.updateBookmark(bean, false);
			}
			else if(isMarked == 0) {
				VocaBean bean = new VocaBean();
				bookmarkBtn.setIcon(new ImageIcon("myVoca/bookmark.png"));
				bean.setId(myId);
				System.out.println(myId);
				bean.setWord(words.elementAt(index-1).getWord());
				mgr.updateBookmark(bean, true);
			}
		}
	}
	
	public void nextWord(int index) {
		int isMarked = words.elementAt(index).getBookmark();
		String w = words.elementAt(index).getWord();
		String d = words.elementAt(index).getDesc();
		
		wordBtn.setText(w);
		descLbl.setText(newLine(d, 30));
		descLbl.setFont(new Font("나눔고딕 ExtraBold", 0, 20));
		p1.add(descLbl);
		
		if(isMarked == 1) {
			bookmarkBtn.setIcon(new ImageIcon("myVoca/bookmark.png"));
		}
		else if(isMarked == 0) {
			bookmarkBtn.setIcon(new ImageIcon("myVoca/noBookmark.png"));
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

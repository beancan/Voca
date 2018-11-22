package myVoca;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Quiz_Word extends JPanel implements ActionListener{
	
	JPanel quizPnl, setNamePnl, quizLeftPnl, quizRightPnl, mainPnl,
			QL_InsidePnl, QR_InsidePnl, QL_InsidePnl2, QR_InsidePnl2; 
	JPanel[] QL_ex, QR_ex/*, QL_InsidePnl2*/;
	JLabel setNameL/*, word, desc*/;
	JLabel[] wordL, descL;
	JTextField wordT, descT;
	JTextField[] wordTx, descTx;
	JButton submit, okBtn, retryBtn;
	String myId, setName;
	String[] rand;
	JScrollPane scroll, scrollL, scrollR;
	Vector<VocaBean> getWord;
	DBMgr mgr;

	public Quiz_Word(String myId, String setName) {
		setSize(900, 550);
		setLayout(null);
		setBackground(Color.white);
		
		this.myId = myId;
		this.setName = setName;
		mgr = new DBMgr();
		getWord = new Vector<VocaBean>();
		getWord = mgr.getWordDesc(setName, myId);
		TitledBorder border = new TitledBorder(new LineBorder(Color.gray));
		
		
		// ¶óº§
		setNameL = new JLabel(setName);
		setNameL.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 24));
		setNameL.setHorizontalAlignment(JLabel.CENTER);
		/*word = new JLabel("1. ´Ü¾î1");
		word.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 15));
		desc = new JLabel("¶æ1");
		desc.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0 ,15));*/
		wordL = new JLabel[getWord.size()];
		descL = new JLabel[getWord.size()];
		
		
		// ÆÐ³Î
		setNamePnl = new JPanel();
		setNamePnl.setBackground(Color.white);
		setNamePnl.setBounds(0, 0, 900, 50);
		setNamePnl.setLayout(new GridLayout(1,1));
		mainPnl = new JPanel();
		mainPnl.setBackground(Color.green);
		mainPnl.setBounds(50, 50, 790, 400);
		mainPnl.setLayout(new BorderLayout());
		mainPnl.setBorder(border);
		quizPnl = new JPanel();
		quizPnl.setBackground(Color.black);
		quizPnl.setBounds(50, 50, 790, 400);
		quizPnl.setLayout(new GridLayout(1, 2));
		quizLeftPnl = new JPanel();
		quizLeftPnl.setBackground(Color.white);
		quizLeftPnl.setLayout(new BorderLayout());
		QL_InsidePnl = new JPanel();
		QL_InsidePnl.setBackground(Color.white);
		//QL_InsidePnl.setBounds(20, 20, 350, 400);
		quizRightPnl = new JPanel();
		quizRightPnl.setBackground(Color.white);
		quizRightPnl.setLayout(new BorderLayout());
		QR_InsidePnl = new JPanel();
		QR_InsidePnl.setBackground(Color.white);
		//QR_InsidePnl.setBounds(20, 20, 350, 400);
		QL_InsidePnl2 = new JPanel();
		QL_InsidePnl2.setBackground(Color.white);
		QL_InsidePnl2.setLayout(new GridLayout(getWord.size(),1));
		QR_InsidePnl2 = new JPanel();
		QR_InsidePnl2.setBackground(Color.white);
		QR_InsidePnl2.setLayout(new GridLayout(getWord.size(), 1));
		QL_ex = new JPanel[getWord.size()];
		QR_ex = new JPanel[getWord.size()];
		//QL_InsidePnl2 = new JPanel[getWord.size()];
		
		
		// ÅØ½ºÆ®
		wordT = new JTextField(20);
		descT = new JTextField(20);
		wordTx = new JTextField[getWord.size()];
		descTx = new JTextField[getWord.size()];

		/*for(int i = 0; i < 20; i++) {
			Random random = new Random();
			int rand = random.nextInt(getWord.size());
			VocaBean bean = getWord.get(rand);
			String word = bean.getWord();
			String desc = bean.getDesc();
		}*/
		
		

		// Å×½ºÆ® ´Ü¾î ºÒ·¯¿À±â
		for(int i = 0; i < getWord.size(); i+=2) {		
			VocaBean bean = getWord.get(i);
			String word = bean.getWord();
			String desc = bean.getDesc();
			
			QL_ex[i] = new JPanel(new GridLayout(2, 1));
			descL[i] = new JLabel(i+1 + ". " + desc);
			descL[i].setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 15));
			wordTx[i] = new JTextField(30);
			QL_ex[i].add(descL[i]);
			QL_ex[i].add(wordTx[i]);
			QL_InsidePnl2.add(QL_ex[i]);
			QL_InsidePnl.add(QL_InsidePnl2);
			/* ¿À¸¥ÂÊ ÄûÁî
			descL[i] = new JLabel(i+1 + ". " + desc);
			descL[i].setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 15));
			wordTx[i] = new JTextField(20);
			QR_InsidePnl2.add(descL[i]);
			QR_InsidePnl2.add(wordTx[i]);*/
		}
		
		for(int i = 1; i < getWord.size(); i+=2) {
			VocaBean bean = getWord.get(i);
			String word = bean.getWord();
			String desc = bean.getDesc();
			
			QR_ex[i] = new JPanel(new GridLayout(2, 1));
			descL[i] = new JLabel(i+1 + ". " + desc);
			descL[i].setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 15));
			wordTx[i] = new JTextField(30);
			QR_ex[i].add(descL[i]);
			QR_ex[i].add(wordTx[i]);
			QR_InsidePnl2.add(QR_ex[i]);
			QR_InsidePnl.add(QR_InsidePnl2);
			
		}
		
		// ¹öÆ°
			submit = new JButton("Á¦Ãâ");
			submit.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 16));
			submit.setBounds(760, 460, 70, 35);
			submit.setBackground(Color.black);
			submit.setForeground(Color.white);
			submit.addActionListener(this);
			
		// ½ºÅ©·Ñ
		scroll = new JScrollPane(quizPnl);
		scrollL = new JScrollPane(QL_InsidePnl);
		scrollL.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollL.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollR = new JScrollPane(QR_InsidePnl);
		scrollR.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollR.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		// add
		//QL_InsidePnl.add(word);
		//QL_InsidePnl.add(descT);
		
		QR_InsidePnl.add(QR_InsidePnl2);
		//quizLeftPnl.add(scrollL);
		quizLeftPnl.add(QL_InsidePnl);
		//quizRightPnl.add(scrollR);
		quizRightPnl.add(QR_InsidePnl);
		quizPnl.add(quizLeftPnl);
		quizPnl.add(quizRightPnl);
		setNamePnl.add(setNameL);
		//mainPnl.add(quizPnl);
		mainPnl.add(scroll);
		add(submit);
		add(mainPnl);
		add(setNamePnl);
	}
	
	public void Quiz_Result() {
		remove(mainPnl);
		remove(submit);
		JPanel result_main, result_main2;
		JPanel[] answerPnl1, answerPnl2, answerPnl, photo;
		JLabel[] word, myAns, ans, desc;
		JLabel correctImgL, wrongImgL;
		//JButton okBtn, retryBtn;
		ImageIcon icon;
		Image img;
		
		result_main = new JPanel(new BorderLayout());
		result_main.setBackground(Color.blue);
		result_main.setBounds(120, 50, 650, 400);
		result_main2 = new JPanel(new GridLayout(getWord.size(), 1));
		result_main2.setBackground(Color.red);
		answerPnl = new JPanel[getWord.size()];
		answerPnl1 = new JPanel[getWord.size()];
		answerPnl2 = new JPanel[getWord.size()];
		photo = new JPanel[getWord.size()];
		
		word = new JLabel[getWord.size()];
		myAns = new JLabel[getWord.size()];
		ans = new JLabel[getWord.size()];
		desc = new JLabel[getWord.size()];
		
		okBtn = new JButton("È®ÀÎ");
		okBtn.setBackground(Color.black);
		okBtn.setForeground(Color.white);
		okBtn.setFont(new Font("³ª´®°íµñ ExtraBold", 0, 16));
		okBtn.setBounds(770, 465, 65, 35);
		okBtn.addActionListener(this);
		retryBtn = new JButton("´Ù½ÃÇÏ±â");
		retryBtn.setBackground(Color.black);
		retryBtn.setForeground(Color.white);
		retryBtn.setFont(new Font("³ª´®°íµñ ExtraBold", 0, 16));
		retryBtn.setBounds(660, 465, 100, 35);
		retryBtn.addActionListener(this);
	
		
		TitledBorder border = new TitledBorder(new LineBorder(Color.gray));
		
		JScrollPane scroll_rs = new JScrollPane(result_main2);
		scroll_rs.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll_rs.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		String correct = "myVoca/0.png";
		String wrong = "myVoca/x.png";
		
		for(int i = 0; i < getWord.size(); i++) {			
			VocaBean bean = getWord.get(i);
			String wordAns = bean.getWord();
			String descAns = bean.getDesc();

			// Á¤´äÀÏ °æ¿ì
			if(wordTx[i].getText().equals(wordAns)) {
				answerPnl[i] = new JPanel(new BorderLayout());
				answerPnl[i].setBorder(border);
				photo[i] = new JPanel(new BorderLayout());
				photo[i].setBackground(Color.white);
				correctImgL = new JLabel(new ImageIcon(correct));
				correctImgL.setHorizontalAlignment(JLabel.CENTER);
				answerPnl1[i] = new JPanel(new GridLayout(2, 1));	// Á¤´äÀÏ °æ¿ì
				answerPnl1[i].setBorder(null);
				answerPnl1[i].setBackground(Color.white);
				desc[i] = new JLabel(i+1 + ". " + descAns);
				desc[i].setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 20));
				myAns[i] = new JLabel("        ÀÔ·ÂÇÑ ´ä : " + wordTx[i].getText());
				myAns[i].setFont(new Font("³ª´®¹Ù¸¥°íµñ", 0, 16));
				
				photo[i].add(correctImgL, BorderLayout.CENTER);
				answerPnl1[i].add(desc[i]);
				answerPnl1[i].add(myAns[i]);
				answerPnl[i].add(answerPnl1[i], BorderLayout.CENTER);
				answerPnl[i].add(photo[i], BorderLayout.EAST);
				result_main2.add(answerPnl[i]);
				
			} else {	// ¿À´äÀÏ °æ¿ì
				answerPnl[i] = new JPanel(new BorderLayout());
				answerPnl[i].setBackground(Color.white);
				answerPnl[i].setBorder(border);
				photo[i] = new JPanel();
				wrongImgL = new JLabel(new ImageIcon(wrong));
				wrongImgL.setHorizontalAlignment(JLabel.CENTER);
				answerPnl2[i] = new JPanel(new GridLayout(3, 1));  // ¿À´äÀÏ °æ¿ì
				answerPnl2[i].setBorder(null);
				desc[i] = new JLabel(i+1 + ". " + descAns);	
				desc[i].setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 20));
				myAns[i] = new JLabel("        ÀÔ·ÂÇÑ ´ä : " + wordTx[i].getText());	// 8¹ø
				myAns[i].setFont(new Font("³ª´®¹Ù¸¥°íµñ", 0, 16));
				ans[i] = new JLabel("        Á¤´ä : " + wordAns);	// 8¹ø
				ans[i].setFont(new Font("³ª´®°íµñ ExtraBold", 0, 16));
				ans[i].setForeground(Color.red);
				
				photo[i].add(wrongImgL);
				answerPnl2[i].add(desc[i]);
				answerPnl2[i].add(myAns[i]);
				answerPnl2[i].add(ans[i]);
				answerPnl[i].add(answerPnl2[i], BorderLayout.CENTER);
				answerPnl[i].add(photo[i], BorderLayout.EAST);
				result_main2.add(answerPnl[i]);
			}
		}
		result_main.add(scroll_rs);
		add(result_main);
		add(okBtn);
		add(retryBtn);
		validate();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==submit) {
			Quiz_Result();			
		} else if(obj==retryBtn) {
			removeAll();
			add(new Quiz_Word(myId, setName));
			validate();
			repaint();
		} else if(obj==okBtn) {
			removeAll();
			add(new Quiz_List(myId));
			validate();
			repaint();
		}
	}
	
/*	
	public static void main(String[] args) {
		new Quiz_Desc("aaa", "Á¤º¸Ã³¸®±â»ç");
	}
*/
}

package myVoca;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Quiz_select extends JPanel implements ActionListener{
	
	JPanel main;
	JLabel desc;
	JButton descBtn, wordBtn;
	String myId, setName;
	
	public Quiz_select(String myId, String setName) {
		setSize(900, 550);
		setLayout(null);
		setBackground(Color.white);
		
		this.myId = myId;
		this.setName = setName;
		TitledBorder border = new TitledBorder(new LineBorder(Color.gray));
		main = new JPanel();
		main.setLayout(null);
		main.setBorder(border);
		main.setBackground(Color.white);
		main.setBounds(140, 70, 600, 350);
		
		desc = new JLabel("답으로 입력할 퀴즈 유형을 선택해주세요.");
		desc.setFont(new Font("나눔스퀘어 ExtraBold", 0, 24));
		desc.setBounds(100, 130, 500, 35);
		descBtn = new JButton("설명");
		descBtn.setFont(new Font("나눔스퀘어 ExtraBold", 0, 16));
		descBtn.setBounds(225, 220, 70, 35);
		descBtn.setBackground(Color.white);
		descBtn.addActionListener(this);
		
		wordBtn = new JButton("단어");
		wordBtn.setFont(new Font("나눔스퀘어 ExtraBold", 0, 16));
		wordBtn.setBounds(305, 220, 70, 35);
		wordBtn.setBackground(Color.white);
		wordBtn.addActionListener(this);
		
		main.add(desc);
		main.add(descBtn);
		main.add(wordBtn);
		add(main);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==descBtn) {
			remove(main);
			add(new Quiz_Desc(myId, setName));
			validate();
			repaint();
		} else if(obj==wordBtn) {
			remove(main);
			add(new Quiz_Word(myId, setName));
			validate();
			repaint();
		}
	}
	
	public static void main(String[] args) {
		new Quiz_select("aaa", "정보처리기사");
	}


}

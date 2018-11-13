package myVoca;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;

public class CreateSet extends JPanel {
	
	Vector<VocaBean> sets;
	Vector<String> text;
	DBMgr mgr;
	
	JPanel leftPanel, rightPanel, btnPanel, tfPanel, mainPanel;
	JButton delBtn, cancelBtn, createBtn;
	JTextField setTf, wordTf, descTf;
	JLabel title, label;
	JList sl;
	
	public CreateSet(String myId) {
		mgr = new DBMgr();
		sets = new Vector<VocaBean>();
		sets = mgr.getwords(myId);
		mainPanel = new JPanel();
		leftPanel = new JPanel(new BorderLayout(10, 10));
		leftPanel.setSize(400, 500);
		rightPanel = new JPanel(new BorderLayout(10, 10));
		leftPanel.setSize(400, 500);
		btnPanel = new JPanel();
		tfPanel = new JPanel(new GridLayout(3, 1));
		setTf = new JTextField();
		wordTf = new JTextField();
		descTf = new JTextField();
		delBtn = new JButton("삭제");
		cancelBtn = new JButton("취소");
		createBtn = new JButton("생성");
		text = new Vector<String>();
		title = new JLabel("내 폴더 수정");
		label = new JLabel("단어          설명");
		
		for (int i = 0; i < sets.size(); i++) {
			VocaBean bean = sets.get(i);
			String s = "";
			s += bean.getWord().trim() + "     ";
			s += bean.getDesc().trim() + "     ";
			text.addElement(s);
		}
		
		sl = new JList<String>(text);
		
		title.setFont(new Font("나눔스퀘어 Bold", 0, 28));
		setLayout(new BorderLayout());
		
		leftPanel.add(label, BorderLayout.NORTH);
		leftPanel.add(sl, BorderLayout.CENTER);
		leftPanel.add(delBtn, BorderLayout.SOUTH);
		tfPanel.add(setTf);
		tfPanel.add(wordTf);
		tfPanel.add(descTf);
		btnPanel.add(cancelBtn);
		btnPanel.add(createBtn);
		
		rightPanel.add(tfPanel, BorderLayout.CENTER);
		rightPanel.add(btnPanel, BorderLayout.SOUTH);
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
		add(title, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
	}
}

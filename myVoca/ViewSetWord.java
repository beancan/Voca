package myVoca;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class ViewSetWord extends JPanel implements ActionListener {
	
	Vector<VocaBean> words;
	DBMgr mgr;
	TitledBorder tb;
	JPanel headPanel, contentPanel, mainPanel, footPanel, titlePanel, btnPanel, wordPanel;
//	JPanel leftPanel, rightPanel;
	JScrollPane sc;
	JTable table;
	DefaultTableModel model;
	
	JButton modBtn, studyBtn, quizBtn;
	JLabel title;
	JLabel leftLbl, rightLbl;
	
	String[] attr = {"단어", "단어설명"};
	String[][] value;
	String w, d = ""; // w : 단어, d : 설명
	String sname, myId;
	
	public ViewSetWord(String sname, String myId) {
		mgr = new DBMgr();
		words = new Vector<VocaBean>();
		words = mgr.getWords(sname, myId);
		this.sname = sname;
		this.myId = myId;
		setLayout(new BorderLayout(20, 20));
		setBackground(Color.WHITE);
		headPanel = new JPanel(new GridLayout(1, 2));
		headPanel.setBackground(Color.WHITE);
		headPanel.setAlignmentX(RIGHT_ALIGNMENT);
		contentPanel = new JPanel(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		footPanel = new JPanel(new FlowLayout());
		footPanel.setBackground(Color.WHITE);
		titlePanel = new JPanel();
		titlePanel.setBackground(Color.WHITE);
		btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnPanel.setBackground(Color.WHITE);
		wordPanel = new JPanel(new GridLayout(words.size(), 1));
		wordPanel.setBackground(Color.WHITE);
		title = new JLabel("단어보기");
		title.setFont(new Font("나눔스퀘어 ExtraBold", 0, 28));
		modBtn = new JButton("수정");
		modBtn.setBackground(Color.BLACK);
		modBtn.setForeground(Color.WHITE);
		modBtn.setPreferredSize(new Dimension(70, 40));
		modBtn.addActionListener(this);
		studyBtn = new JButton("학습");
		studyBtn.setBackground(Color.BLACK);
		studyBtn.setForeground(Color.WHITE);
		studyBtn.setPreferredSize(new Dimension(70, 40));
		modBtn.addActionListener(this);
		quizBtn = new JButton("퀴즈");
		quizBtn.setBackground(Color.BLACK);
		quizBtn.setForeground(Color.WHITE);
		quizBtn.setPreferredSize(new Dimension(70, 40));
		quizBtn.addActionListener(this);
		
		/*leftPanel = new JPanel();
		leftPanel.setBackground(Color.white);
		leftLbl = new JLabel("             ");
		rightPanel = new JPanel();
		rightPanel.setBackground(Color.white);
		rightLbl = new JLabel("             ");*/
		
		value = new String[words.size()][2];
		
		for (int i = 0; i < words.size(); i++) {
			VocaBean bean = words.get(i);
			int j = 0;
			
			w = bean.getWord().trim();
			d = bean.getDesc().trim();
			value[i][j] = w;
			value[i][j+1] = d;
		}
		
		model = new DefaultTableModel(value, attr);
		table = new JTable(model);
		table.setEnabled(false);
		table.setFillsViewportHeight(true);
		table.setBackground(Color.WHITE);
		sc = new JScrollPane(table);
		sc.setPreferredSize(new Dimension(500, 300));
		sc.setBackground(Color.WHITE);
		sc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		contentPanel.add(sc, BorderLayout.CENTER);
		titlePanel.add(title);
		btnPanel.add(modBtn);
		btnPanel.add(studyBtn);
		btnPanel.add(quizBtn);
		headPanel.add(titlePanel);
		headPanel.add(btnPanel);
		mainPanel.add(headPanel, BorderLayout.NORTH);
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		add(mainPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == modBtn) {
			removeAll();
			add(new ModSet(sname, myId));
			revalidate();
			repaint();
		}
		else if(obj == studyBtn) {
			removeAll();
			add(new ViewWord(sname, myId));
			revalidate();
			repaint();
		}
		else if(obj == quizBtn) {
			// mainPanel.add();
			revalidate();
			repaint();
		}
	}
}

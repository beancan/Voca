package myVoca;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class DelSet extends JPanel implements ActionListener {
	
	Vector<VocaBean> set;
	DBMgr mgr;
	TitledBorder tb;
	
	JScrollPane sc;
	JPanel mainPanel, contentPanel;
	JButton[] deleteBtn;
	JPanel[] panels;
	JPanel[] inner;
	JLabel[] idLabels;
	JLabel[] wordLabels;
	JLabel[] setName;
	JLabel title, empty1, empty2;
	int wordcount[];
	private String[] sname;
	String myId;
	
	public DelSet(String myId) {
		
		mgr = new DBMgr();
		set = new Vector<VocaBean>();
		set = mgr.getMySets(myId);
		this.myId = myId;
		
		this.setLayout(new BorderLayout(20, 20));
		tb = new TitledBorder(new LineBorder(Color.BLACK));
		mainPanel = new JPanel(new BorderLayout());
		contentPanel = new JPanel(new GridLayout(set.size(), 1));
		
		deleteBtn = new JButton[set.size()];
		panels = new JPanel[set.size()];
		inner = new JPanel[set.size()];
		idLabels = new JLabel[set.size()];
		wordLabels = new JLabel[set.size()];
		setName = new JLabel[set.size()];
		wordcount = new int[set.size()];
		title = new JLabel("내 단어세트 삭제");
		title.setFont(new Font("나눔스퀘어 Bold", 0, 28));
		empty1 = new JLabel("             ");
		empty2 = new JLabel("             ");
		
		for (int i = 0; i < set.size(); i++) {
			VocaBean bean = set.get(i);
			sname = new String[set.size()];
			sname[i] = bean.getSetname().trim();
			
			wordcount[i] = mgr.getSetWordsCount(sname[i], myId);
			panels[i] = new JPanel(new BorderLayout(10, 0));
			panels[i].setBorder(new EtchedBorder());
			inner[i] = new JPanel(new FlowLayout(0, 40, 0));
			wordLabels[i] = new JLabel("단어 개수 : " + wordcount[i]);
			idLabels[i] = new JLabel("ID: " + myId);
			deleteBtn[i] = new JButton("삭제");
			deleteBtn[i].addActionListener(this);
			setName[i] = new JLabel(sname[i]);
			inner[i].add(wordLabels[i]);
			inner[i].add(idLabels[i]);
			inner[i].add(deleteBtn[i]);
			panels[i].add(inner[i], BorderLayout.NORTH);
			panels[i].add(setName[i], BorderLayout.CENTER);
			contentPanel.add(panels[i]);
		}
		
		sc = new JScrollPane(contentPanel);
		sc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mainPanel.add(sc);
		add(title, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		add(empty1, BorderLayout.EAST);
		add(empty2, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		for(int i = 0; i < set.size(); i++) {
			if(obj == deleteBtn[i]) {
				if(mgr.deleteSet(sname[i], myId) == true) {
					revalidate();
					repaint();
				}
			}
		}
	}
}

package myVoca;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.util.Vector;

public class Home extends JPanel {
	
	Vector<VocaBean> folders;
	DBMgr mgr;
	TitledBorder tb;
	JPanel mainPanel, headPanel, btnPanel, titlePanel;
	JPanel folderPanel, nullPanel;
	JButton crtBtn, modBtn, delBtn; // 폴더 추가, 수정, 삭제 버튼
	JScrollPane sc1, sc2;
	
	JPanel[] panels;
	JPanel[] inner;
	JLabel[] idLabels;
	JLabel[] setLabels;
	JLabel[] folderLabels;

	String fname;
	int setcount[];
	
	public Home(String myId) {
		mgr = new DBMgr();
		folders = new Vector<VocaBean>();
		folders = mgr.getfolders(myId);
		
		panels = new JPanel[folders.size()];
		inner = new JPanel[folders.size()];
		idLabels = new JLabel[folders.size()];
		setLabels = new JLabel[folders.size()];
		folderLabels = new JLabel[folders.size()];
		setcount = new int[folders.size()];
		
		this.setLayout(new BorderLayout(20, 20));
		this.setBackground(Color.WHITE);
		mainPanel = new JPanel(new BorderLayout());
		headPanel = new JPanel();
		titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		btnPanel = new JPanel();
		folderPanel = new JPanel(new GridLayout(folders.size(), 1));
		crtBtn = new JButton("추가");
		modBtn = new JButton("수정");
		delBtn = new JButton("삭제");
		nullFolder(myId);
		sc1 = new JScrollPane(folderPanel);
		sc1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sc1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sc2 = new JScrollPane(nullPanel);
		sc2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sc2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		for (int i = 0; i < folders.size(); i++) {
			VocaBean bean = folders.get(i);
			fname = bean.getFolder().trim();
			
			setcount[i] = mgr.getSetCount(fname);
			panels[i] = new JPanel(new BorderLayout());
			panels[i].setBorder(new EtchedBorder());
			inner[i] = new JPanel(new FlowLayout(0, 40, 0));
			setLabels[i] = new JLabel("단어세트 개수 : " + setcount[i]);
			idLabels[i] = new JLabel("ID: " + myId);
			folderLabels[i] = new JLabel(fname);
			inner[i].add(setLabels[i]);
			inner[i].add(idLabels[i]);
			panels[i].add(inner[i], BorderLayout.NORTH);
			panels[i].add(folderLabels[i], BorderLayout.CENTER);
			folderPanel.add(panels[i]);
		}
		
		mainPanel.add(sc1, BorderLayout.NORTH);
		mainPanel.add(sc2, BorderLayout.SOUTH);
		btnPanel.add(crtBtn);
		btnPanel.add(modBtn);
		btnPanel.add(delBtn);
		headPanel.add(titlePanel);
		headPanel.add(btnPanel);
		add(headPanel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
	}
	
	void nullFolder(String myId) {
		Vector<VocaBean> set = new Vector<VocaBean>();
		set = mgr.getNoFolderSets(myId);
		JPanel[] panels_n = new JPanel[set.size()];
		JPanel[] inner_n = new JPanel[set.size()];
		JLabel[] idLabels_n = new JLabel[set.size()];
		JLabel[] setLabels_n = new JLabel[set.size()];
		int[] setcount_n = new int[set.size()];
		
		for (int i = 0; i < set.size(); i++) {
			setcount_n[i] = mgr.getWordsCount(myId);
			panels_n[i] = new JPanel(new BorderLayout());
			panels_n[i].setBorder(new EtchedBorder());
			inner_n[i] = new JPanel(new FlowLayout(0, 40, 0));
			setLabels_n[i] = new JLabel("단어 개수 : " + setcount_n[i]);
			idLabels_n[i] = new JLabel("ID: " + myId);
			inner_n[i].add(setLabels_n[i]);
			inner_n[i].add(idLabels_n[i]);
			panels_n[i].add(inner_n[i], BorderLayout.NORTH);
			nullPanel.add(panels_n[i]);
		}
	}
}

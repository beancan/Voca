package myVoca;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class ManageFolder extends JPanel implements ActionListener, MouseListener {
	
	Vector<VocaBean> folders;
	Vector<VocaBean> set;
	DBMgr mgr;
	TitledBorder tb;
	JPanel mainPanel, headPanel, footPanel, btnPanel, titlePanel;
	JPanel folderPanel;
	JButton crtBtn, modBtn, delBtn; // 폴더 추가, 수정, 삭제버튼
	JScrollPane sc1;
	
	JPanel[] panels;
	JPanel[] inner;
	JLabel[] idLabels;
	JLabel[] setLabels;
	JLabel[] folderLabels;
	JLabel title;
	JLabel leftLbl, rightLbl, sideLbl, sideLbl2;

	String fname[];
	String myId;
	int[] setcount;
	
	public ManageFolder(String myId) {
		mgr = new DBMgr();
		this.myId = myId;
		folders = new Vector<VocaBean>();
		folders = mgr.getFolders(myId);
		set = new Vector<VocaBean>();
		set = mgr.getNoFolderSets(myId);
		setLayout(new BorderLayout(20, 20));
		setBackground(Color.WHITE);
		
		panels = new JPanel[folders.size()];
		inner = new JPanel[folders.size()];
		idLabels = new JLabel[folders.size()];
		setLabels = new JLabel[folders.size()];
		folderLabels = new JLabel[folders.size()];
		setcount = new int[folders.size()];
		fname = new String[folders.size()];
		
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.WHITE);
		headPanel = new JPanel(new BorderLayout());
		headPanel.setBackground(Color.WHITE);
		footPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		footPanel.setBackground(Color.WHITE);
		titlePanel = new JPanel(new BorderLayout());
		titlePanel.setBackground(Color.WHITE);
		btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnPanel.setBackground(Color.WHITE);
		sideLbl2 = new JLabel("                                                                                                               ");
		folderPanel = new JPanel(new GridLayout(folders.size(), 1));
		folderPanel.setBackground(Color.WHITE);
		crtBtn = new JButton("추가");
		crtBtn.setBackground(Color.BLACK);
		crtBtn.setForeground(Color.WHITE);
		crtBtn.setPreferredSize(new Dimension(70, 40));
		crtBtn.addActionListener(this);
		modBtn = new JButton("수정");
		modBtn.setBackground(Color.BLACK);
		modBtn.setForeground(Color.WHITE);
		modBtn.setPreferredSize(new Dimension(70, 40));
		modBtn.addActionListener(this);
		delBtn = new JButton("삭제");
		delBtn.setBackground(Color.BLACK);
		delBtn.setForeground(Color.WHITE);
		delBtn.setPreferredSize(new Dimension(70, 40));
		delBtn.addActionListener(this);
		title = new JLabel("폴더 관리");
		title.setFont(new Font("나눔스퀘어 ExtraBold", 0, 28));
		leftLbl = new JLabel("     ");
		rightLbl = new JLabel("     ");
		sideLbl = new JLabel("        ");
		sc1 = new JScrollPane(folderPanel);
		sc1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sc1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		for (int i = 0; i < folders.size(); i++) {
			VocaBean bean = folders.get(i);
			fname[i] = bean.getFolder().trim();
			
			setcount[i] = mgr.getSetCount(fname[i]);
			panels[i] = new JPanel(new BorderLayout());
			panels[i].setBorder(new EtchedBorder());
			inner[i] = new JPanel(new FlowLayout(0, 40, 0));
			inner[i].setBackground(Color.WHITE);
			setLabels[i] = new JLabel("단어세트 개수 : " + setcount[i]);
			setLabels[i].setFont(new Font("나눔스퀘어 ExtraBold", 0, 15));
			idLabels[i] = new JLabel("ID: " + myId);
			idLabels[i].setFont(new Font("나눔스퀘어 ExtraBold", 0, 15));
			folderLabels[i] = new JLabel("      " + fname[i]);
			folderLabels[i].setFont(new Font("나눔스퀘어 ExtraBold", 0, 22));
			inner[i].add(setLabels[i]);
			inner[i].add(idLabels[i]);
			panels[i].add(inner[i], BorderLayout.NORTH);
			panels[i].add(folderLabels[i], BorderLayout.CENTER);
			panels[i].setBackground(Color.WHITE);
			panels[i].addMouseListener(this);
			panels[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
			folderPanel.add(panels[i]);
		}
		
		mainPanel.add(sc1, BorderLayout.CENTER);
		btnPanel.add(crtBtn);
		btnPanel.add(modBtn);
		btnPanel.add(delBtn);
		btnPanel.add(sideLbl);
		titlePanel.add(title);
		headPanel.add(titlePanel);
		headPanel.add(btnPanel, BorderLayout.EAST);
		headPanel.add(sideLbl2, BorderLayout.WEST);
		add(headPanel, BorderLayout.NORTH);
		add(leftLbl, BorderLayout.WEST);
		add(rightLbl, BorderLayout.EAST);
		add(mainPanel, BorderLayout.CENTER);
		add(footPanel, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == crtBtn) {
			removeAll();
			add(new NewFolder(myId, 1), BorderLayout.CENTER);
			revalidate();
			repaint();
		}
		else if(obj == modBtn) {
			removeAll();
			add(new ModFolder(myId), BorderLayout.CENTER);
			revalidate();
			repaint();
		}
		else if(obj == delBtn) {
			removeAll();
			add(new DelFolder(myId), BorderLayout.CENTER);
			revalidate();
			repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i = 0; i < folders.size(); i++) {
			if(e.getSource() == panels[i]) {
				removeAll();
				add(new ViewSet(fname[i], myId), BorderLayout.CENTER);
				revalidate();
				repaint();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

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
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class DelFolder extends JPanel implements ActionListener {
	Vector<VocaBean> folders;
	DBMgr mgr;
	TitledBorder tb;
	
	JPanel mainPanel, contentPanel;
	JScrollPane scroll;
	
	JPanel[] panels;
	JPanel[] inner;
	JButton[] deleteBtn;
	JLabel[] idLabels;
	JLabel[] setLabels;
	JLabel[] folderName;
	JLabel title, empty1, empty2;
	String[] fname;
	String myId;
	
	int setcount[];
	
	public DelFolder(String myId) {
		setBackground(Color.WHITE);
		mgr = new DBMgr();
		folders = new Vector<VocaBean>();
		folders = mgr.getFolders(myId);
		this.myId = myId;
		tb = new TitledBorder(new LineBorder(Color.BLACK));
		mainPanel = new JPanel(new BorderLayout());
		contentPanel = new JPanel(new GridLayout(folders.size(), 1));
		
		panels = new JPanel[folders.size()];
		inner = new JPanel[folders.size()];
		idLabels = new JLabel[folders.size()];
		setLabels = new JLabel[folders.size()];
		folderName = new JLabel[folders.size()];
		deleteBtn = new JButton[folders.size()];
		setcount = new int[folders.size()];
		fname = new String[folders.size()];
		
		title = new JLabel("내 폴더 삭제");
		title.setFont(new Font("나눔스퀘어 Bold", 0, 28));
		empty1 = new JLabel("             ");
		empty2 = new JLabel("             ");
		
		this.setLayout(new BorderLayout(20, 20));
		
		for (int i = 0; i < folders.size(); i++) {
			VocaBean bean = folders.get(i);
			fname[i] = bean.getFolder().trim();
			
			setcount[i] = mgr.getSetCount(fname[i]);
			panels[i] = new JPanel(new BorderLayout(10, 0));
			panels[i].setBackground(Color.WHITE);
			panels[i].setBorder(new EtchedBorder());
			inner[i] = new JPanel(new FlowLayout(0, 40, 0));
			inner[i].setBackground(Color.WHITE);
			inner[i].setBorder(new EtchedBorder());
			setLabels[i] = new JLabel("단어세트 개수 : " + setcount[i]);
			idLabels[i] = new JLabel("ID: " + myId);
			deleteBtn[i] = new JButton("삭제");
			deleteBtn[i].addActionListener(this);
			folderName[i] = new JLabel(fname[i]);
			inner[i].add(setLabels[i]);
			inner[i].add(idLabels[i]);
			inner[i].add(deleteBtn[i]);
			panels[i].add(inner[i], BorderLayout.NORTH);
			panels[i].add(folderName[i], BorderLayout.CENTER);
			contentPanel.add(panels[i]);
		}
		
		scroll = new JScrollPane(contentPanel);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		mainPanel.add(scroll);
		add(title, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		add(empty1, BorderLayout.EAST);
		add(empty2, BorderLayout.WEST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		for(int i = 0; i < folders.size(); i++) {
			if(obj == deleteBtn[i]) {
				mgr.deleteFolder(fname[i], myId);
				this.revalidate();
				this.repaint();
			}
		}
	}
}

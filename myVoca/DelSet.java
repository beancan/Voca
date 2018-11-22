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
	JPanel mainPanel, contentPanel, bodyPnl, titlePnl;
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
		
		this.setLayout(null);
		setBackground(Color.white);
		tb = new TitledBorder(new LineBorder(Color.BLACK));
		bodyPnl = new JPanel(new BorderLayout());
		bodyPnl.setBackground(Color.green);
		bodyPnl.setBounds(55, 80, 820, 420);
		titlePnl = new JPanel(new BorderLayout());
		titlePnl.setBounds(0, 0, 910, 80);
		titlePnl.setBackground(Color.white);
		mainPanel = new JPanel(new BorderLayout());
		contentPanel = new JPanel(new GridLayout(set.size(), 1));
		
		
		deleteBtn = new JButton[set.size()];
		panels = new JPanel[set.size()];
		inner = new JPanel[set.size()];
		idLabels = new JLabel[set.size()];
		wordLabels = new JLabel[set.size()];
		setName = new JLabel[set.size()];
		wordcount = new int[set.size()];
		title = new JLabel("´Ü¾î¼¼Æ® »èÁ¦");
		title.setFont(new Font("³ª´®°íµñ ExtraBold", 0, 28));
		title.setHorizontalAlignment(JLabel.CENTER);
		sname = new String[set.size()];
		
		for (int i = 0; i < set.size(); i++) {
			VocaBean bean = set.get(i);
			sname[i] = bean.getSetname().trim();
			
			wordcount[i] = mgr.getSetWordsCount(sname[i], myId);
			panels[i] = new JPanel(new BorderLayout(10, 0));
			panels[i].setBorder(new EtchedBorder());
			panels[i].setBackground(Color.white);
			inner[i] = new JPanel(new FlowLayout(0, 40, 0));
			inner[i].setBackground(Color.white);
			wordLabels[i] = new JLabel("´Ü¾î °³¼ö : " + wordcount[i]);
			wordLabels[i].setFont(new Font("³ª´®°íµñ ExtraBold", 0, 16));
			idLabels[i] = new JLabel("ID: " + myId);
			idLabels[i].setFont(new Font("³ª´®°íµñ ExtraBold", 0, 16));
			deleteBtn[i] = new JButton("»èÁ¦");
			deleteBtn[i].setFont(new Font("³ª´®°íµñ ExtraBold", 0, 16));
			deleteBtn[i].setBackground(Color.black);
			deleteBtn[i].setForeground(Color.white);
			deleteBtn[i].addActionListener(this);
			setName[i] = new JLabel("     " + sname[i]);
			setName[i].setFont(new Font("³ª´®°íµñ ExtraBold", 0, 30));
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
		bodyPnl.add(mainPanel, BorderLayout.CENTER);
		titlePnl.add(title);
		add(bodyPnl);
		add(titlePnl);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		for(int i = 0; i < set.size(); i++) {
			if(obj == deleteBtn[i]) {
				if(mgr.deleteSet(sname[i], myId) == true) {
					if(mgr.getWordFlag(myId) == true) {
						removeAll();
						setLayout(new BorderLayout());
						add(new DelSet(myId));
						revalidate();
						repaint();
					}
					else {
						removeAll();
						setLayout(new BorderLayout());
						add(new NoSet(myId));
						revalidate();
						repaint();
					}
				}
			}
		}
	}
}

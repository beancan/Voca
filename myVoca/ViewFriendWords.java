package myVoca;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EtchedBorder;


public class ViewFriendWords extends JPanel implements ActionListener {

	JPanel parentPnl, mainPnl, titlePnl, buttonPnl, contentPnl;
	JPanel wordPnl, descPnl;
	JLabel titleL, wordL, descL, descNull;
	JButton studyBtn, quizBtn;
	Vector<VocaBean> words;
	DBMgr mgr;
	
	JScrollPane sc;
	GridBagLayout gbl;
	String friendId, setName;
	
	private JPanel[] panels;
	private JPanel[] wordPnls;
	private JPanel[] descPnls;
	private JLabel[] word;
	private JLabel[] desc;
	private String[] w, d;
	
	public ViewFriendWords(String setName, String friendId) {
		setSize(900, 550);
		setBackground(Color.blue);
		setLayout(new BorderLayout());
		
		this.friendId = friendId;
		this.setName = setName;
		gbl = new GridBagLayout();
		mgr = new DBMgr();
		words = new Vector<VocaBean>();
		words = mgr.getWordDesc(setName, friendId);
		
		// ÆÐ³Î
		parentPnl = new JPanel(null);
		parentPnl.setBackground(Color.white);
		titlePnl = new JPanel(new BorderLayout());
		titlePnl.setBackground(Color.white);
		titlePnl.setBounds(0, 0, 900, 50);
		mainPnl = new JPanel(new BorderLayout());
		mainPnl.setBackground(Color.blue);
		mainPnl.setBounds(90, 125, 700, 380);
		buttonPnl = new JPanel(new GridLayout(1, 3));
		buttonPnl.setBackground(Color.pink);
		buttonPnl.setBounds(590, 50, 200, 30);
		contentPnl = new JPanel(gbl);
		contentPnl.setBackground(Color.white);
		wordPnl = new JPanel(new BorderLayout());
		wordPnl.setBackground(Color.darkGray);
		wordPnl.setBounds(90, 85, 180, 40);
		descPnl = new JPanel(new BorderLayout());
		descPnl.setBackground(Color.darkGray);
		descPnl.setBounds(270, 85, 520, 40);
		
		// ¶óº§
		titleL = new JLabel(setName);
		titleL.setFont(new Font("³ª´®°íµñ ExtraBold", 0, 30));
		titleL.setHorizontalAlignment(JLabel.CENTER);
		wordL = new JLabel("´Ü¾î");
		wordL.setFont(new Font("³ª´®°íµñ ExtraBold", 0, 16));
		wordL.setHorizontalAlignment(JLabel.CENTER);
		wordL.setForeground(Color.white);
		descL = new JLabel("¼³¸í");
		descL.setFont(new Font("³ª´®°íµñ ExtraBold", 0, 16));
		descL.setHorizontalAlignment(JLabel.CENTER);
		descL.setForeground(Color.white);
		descNull = new JLabel("                 ");
		descNull.setFont(new Font("³ª´®°íµñ ExtraBold", 0, 16));
		
		// ¹öÆ°
		studyBtn = new JButton("ÇÐ½À");
		studyBtn.setBackground(Color.BLACK);
		studyBtn.setForeground(Color.WHITE);
		studyBtn.setPreferredSize(new Dimension(70, 40));
		studyBtn.setFont(new Font("³ª´®°íµñ ExtraBold", 0, 16));
		studyBtn.addActionListener(this);
		quizBtn = new JButton("ÄûÁî");
		quizBtn.setBackground(Color.BLACK);
		quizBtn.setForeground(Color.WHITE);
		quizBtn.setPreferredSize(new Dimension(70, 40));
		quizBtn.setFont(new Font("³ª´®°íµñ ExtraBold", 0, 16));
		quizBtn.addActionListener(this);
		
		panels = new JPanel[words.size()];
		wordPnls = new JPanel[words.size()];
		descPnls = new JPanel[words.size()];
		word = new JLabel[words.size()];
		desc = new JLabel[words.size()];
		w = new String[words.size()];
		d = new String[words.size()];
		
		for (int i = 0; i < words.size(); i++) {
			VocaBean bean = words.get(i);
			w[i] = bean.getWord().trim();
			d[i] = bean.getDesc().trim();
			wordPnls[i] = new JPanel();
			wordPnls[i].setBackground(Color.WHITE);
			word[i] = new JLabel(w[i]);
			word[i].setBackground(Color.WHITE);
			word[i].setFont(new Font("³ª´®°íµñ ExtraBold", 0, 14));
			word[i].setHorizontalAlignment(JLabel.CENTER);
			word[i].setPreferredSize((new Dimension(150, 30)));
			wordPnls[i].add(word[i]);
			descPnls[i] = new JPanel();
			descPnls[i].setBackground(Color.WHITE);
			desc[i] = new JLabel(d[i]);
			desc[i].setBackground(Color.WHITE);
			desc[i].setFont(new Font("³ª´®°íµñ ExtraBold", 0, 14));
			desc[i].setHorizontalAlignment(JLabel.CENTER);
			desc[i].setPreferredSize(new Dimension(300, 30));
			descPnls[i].add(desc[i], FlowLayout.LEFT);
			panels[i] = new JPanel(gbl);
			gbInsert(wordPnls[i], panels[i], 0, 0, 1, 1, 0.1, 1.0);
			gbInsert(descPnls[i], panels[i], 1, 0, 1, 1, 1.0, 1.0);
			panels[i].setBorder(new EtchedBorder());
			panels[i].setBackground(Color.WHITE);
			gbInsert(panels[i], contentPnl, 0, i, 1, 1, 1.0, 1.0);
		}
		
		sc = new JScrollPane(contentPnl);
		sc.setBackground(Color.WHITE);
		sc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		// add
		wordPnl.add(wordL);
		descPnl.add(descL);
		descPnl.add(descNull, BorderLayout.EAST);
		mainPnl.add(sc);
		buttonPnl.add(studyBtn);
		buttonPnl.add(quizBtn);
		titlePnl.add(titleL);
		parentPnl.add(descPnl);
		parentPnl.add(wordPnl);
		parentPnl.add(descPnl);
		parentPnl.add(titlePnl);
		parentPnl.add(mainPnl);
		parentPnl.add(buttonPnl);
		add(parentPnl);
	}
	
	public void gbInsert(Component c, JPanel p, int x, int y, int w, int h, double wx, double wy) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = wx;
		gbc.weighty = wy;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbl.setConstraints(c, gbc);
		p.add(c);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
	
		if(obj == studyBtn) {
			removeAll();
			add(new ViewFriendWord(setName, friendId));
			revalidate();
			repaint();
		}
		else if(obj == quizBtn) {
			removeAll();
			add(new Quiz_select(friendId, setName));
			revalidate();
			repaint();
		}
	}
}
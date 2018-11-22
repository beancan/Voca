package myVoca;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ViewBookmark extends JPanel implements ActionListener {
	Vector<VocaBean> words;
	DBMgr mgr;
	
	GridBagLayout gbl;
	JPanel[] panels, wordPnls, descPnls;
	JPanel contentPnl, main, wordTitlePnl, descTitlePnl;
	JLabel[] word, desc;
	JLabel wordTitleL, descTitleL;
	JButton[] bmarkBtn;
	ImageIcon img;
	JLabel title;
	JScrollPane sc;
	String[] w, d;
	String myId;
	
	public ViewBookmark(String myId) {

		setSize(900, 550);
		setBackground(Color.WHITE);
		setLayout(null);
		mgr = new DBMgr();
		words = new Vector<VocaBean>();
		words = mgr.getMarkedWords(myId);
		this.myId = myId;
		gbl = new GridBagLayout();
		
		main = new JPanel(new BorderLayout());
		main.setBounds(95, 80, 700, 400);
		main.setBackground(Color.blue);
		contentPnl = new JPanel(gbl);
		contentPnl.setBackground(Color.WHITE);
		wordTitlePnl = new JPanel(new BorderLayout());
		wordTitlePnl.setBounds(95, 40, 180, 40);
		wordTitlePnl.setBackground(Color.darkGray);
		descTitlePnl = new JPanel(null);
		descTitlePnl.setBounds(275, 40, 520, 40);
		descTitlePnl.setBackground(Color.darkGray);
		
		// ¶óº§
		wordTitleL = new JLabel("´Ü¾î");
		wordTitleL.setHorizontalAlignment(JLabel.CENTER);
		wordTitleL.setForeground(Color.white);
		wordTitleL.setFont(new Font("³ª´®°íµñ ExtraBold", 0, 17));
		descTitleL = new JLabel("¼³¸í");
		descTitleL.setBounds(210, 0, 100, 40);
		descTitleL.setForeground(Color.white);
		descTitleL.setFont(new Font("³ª´®°íµñ ExtraBold", 0, 17));
		
		panels = new JPanel[words.size()];
		wordPnls = new JPanel[words.size()];
		descPnls = new JPanel[words.size()];
		bmarkBtn = new JButton[words.size()];
		word = new JLabel[words.size()];
		desc = new JLabel[words.size()];
		w = new String[words.size()];
		d = new String[words.size()];
		img = new ImageIcon("myVoca/bookmark.png");
				
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
			bmarkBtn[i] = new JButton(img);
			bmarkBtn[i].setBackground(Color.WHITE);
			bmarkBtn[i].setBorder(null);
			bmarkBtn[i].addActionListener(this);
			panels[i] = new JPanel(gbl);
			gbInsert(wordPnls[i], panels[i], 0, 0, 1, 1, 0.1, 1.0);
			gbInsert(descPnls[i], panels[i], 1, 0, 1, 1, 1.0, 1.0);
			gbInsert(bmarkBtn[i], panels[i], 2, 0, 1, 1, 0.1, 1.0);
			panels[i].setBorder(new EtchedBorder());
			panels[i].setBackground(Color.WHITE);
			gbInsert(panels[i], contentPnl, 0, i, 1, 1, 1.0, 1.0);
		}
		
		// ½ºÅ©·Ñ
		JScrollPane scroll = new JScrollPane(contentPnl);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		wordTitlePnl.add(wordTitleL);
		descTitlePnl.add(descTitleL);
		main.add(scroll);
		add(descTitlePnl);
		add(wordTitlePnl);
		add(main);
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
		VocaBean bean = new VocaBean();
		
		for (int i = 0; i < words.size(); i++) {
			if(obj == bmarkBtn[i]) {
				bmarkBtn[i].setIcon(new ImageIcon("myVoca/noBookmark.png"));
				bean.setId(myId);
				bean.setWord(words.elementAt(i).getWord());
				mgr.updateBookmark(bean, false);
				removeAll();
				add(new ViewBookmark(myId));
				revalidate();
				repaint();
			}
		}
	}
}

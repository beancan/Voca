package myVoca;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ViewSet extends JPanel implements MouseListener {
	
	Vector<VocaBean> sets;
	DBMgr mgr;
	TitledBorder tb;
	
	JScrollPane sc;
	JPanel setPanel, headPanel, footPanel, contentPanel, titlePanel, mainPanel;
	JPanel leftPanel, rightPanel;
	JLabel title;
	JPanel[] panels;
	JPanel[] inner;
	JLabel[] idLbls;
	JLabel[] setcntLbls, setnameLbls;
	String[] sname;
	int[] setcount;
	
	String myId;
	
	public ViewSet(String fname, String myId) {
		mgr = new DBMgr();
		sets = new Vector<VocaBean>();
		sets = mgr.getMySets(fname, myId);
		this.myId = myId;
		
		panels = new JPanel[sets.size()];
		inner = new JPanel[sets.size()];
		idLbls = new JLabel[sets.size()];
		setcntLbls = new JLabel[sets.size()];
		setnameLbls = new JLabel[sets.size()];
		setcount = new int[sets.size()];
		sname = new String[sets.size()];
		
		setLayout(new BorderLayout(20, 20));
		setBackground(Color.WHITE);
		setPanel = new JPanel(new GridLayout(sets.size(), 1));
		setPanel.setBackground(Color.WHITE);
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.WHITE);
		contentPanel = new JPanel(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		headPanel = new JPanel(new GridLayout(1, 2));
		headPanel.setBackground(Color.WHITE);
		headPanel.setAlignmentX(RIGHT_ALIGNMENT);
		footPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		footPanel.setBackground(Color.WHITE);
		titlePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		titlePanel.setBackground(Color.WHITE);
		title = new JLabel(fname);
		title.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 28));
		title.setHorizontalAlignment(JLabel.CENTER);
		leftPanel = new JPanel();
		leftPanel.setBackground(Color.WHITE);
		rightPanel = new JPanel();
		rightPanel.setBackground(Color.WHITE);
		sc = new JScrollPane(setPanel);
		sc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		for (int i = 0; i < sets.size(); i++) {
			VocaBean bean = sets.get(i);
			sname[i] = bean.getSetname().trim();
			
			setcount[i] = mgr.getSetWordsCount(sname[i], myId);
			panels[i] = new JPanel(new BorderLayout());
			panels[i].setBorder(new EtchedBorder());
			inner[i] = new JPanel(new FlowLayout(0, 40, 0));
			inner[i].setBackground(Color.WHITE);
			setcntLbls[i] = new JLabel("´Ü¾î °³¼ö : " + setcount[i]);
			setcntLbls[i].setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 15));
			idLbls[i] = new JLabel("ID: " + myId);
			idLbls[i].setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 15));
			inner[i].add(setcntLbls[i]);
			inner[i].add(idLbls[i]);
			setnameLbls[i] = new JLabel("      " + sname[i]);
			setnameLbls[i].setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 22));
			panels[i].add(inner[i], BorderLayout.NORTH);
			panels[i].add(setnameLbls[i], BorderLayout.CENTER);
			panels[i].setBackground(Color.WHITE);
			panels[i].addMouseListener(this);
			panels[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
			setPanel.add(panels[i]);
		}
		
		contentPanel.add(sc, BorderLayout.CENTER);
		titlePanel.add(title);
		headPanel.add(title);
		mainPanel.add(headPanel, BorderLayout.NORTH);
		mainPanel.add(leftPanel, BorderLayout.WEST);
		mainPanel.add(rightPanel, BorderLayout.EAST);
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		mainPanel.add(footPanel, BorderLayout.SOUTH);
		add(mainPanel);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		
		for (int i = 0; i < sets.size(); i++) {
			if(obj == panels[i]) {
				removeAll();
				add(new ViewSetWord(sname[i], myId));
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

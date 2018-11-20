package myVoca;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class ManageSet extends JPanel implements ActionListener,MouseListener {
	
	Vector<VocaBean> folderset, nofolderset;
	DBMgr mgr;
	TitledBorder tb;
	JPanel contentPanel, headPanel, footPanel, btnPanel, titlePanel, mainPanel;
	JPanel setPanel;
	JPanel leftPnl, rightPnl;
	JButton crtBtn, delBtn; // Æú´õ Ãß°¡, »èÁ¦¹öÆ°
	JScrollPane sc2;
	
	JPanel[] panels;
	JPanel[] inner;
	JLabel[] idLbls;
	JLabel[] setcntLbls, setnameLbls;
	
	JLabel title;
	JLabel leftNull, rightNull;
	JLabel nullL;

	String sname[], nofoldername[];
	String myId;
	int[] setcount;
	
	public ManageSet(String myId) {
		mgr = new DBMgr();
		this.myId = myId;
		folderset = new Vector<VocaBean>();
		folderset = mgr.getMySets(myId);
		
		panels = new JPanel[folderset.size()];
		inner = new JPanel[folderset.size()];
		idLbls = new JLabel[folderset.size()];
		setcntLbls = new JLabel[folderset.size()];
		setnameLbls = new JLabel[folderset.size()];
		setcount = new int[folderset.size()];
		sname = new String[folderset.size()];
		
		this.setLayout(new BorderLayout(20, 20));
		this.setBackground(Color.WHITE);
		contentPanel = new JPanel(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		headPanel = new JPanel(new GridLayout(1, 2));
		headPanel.setBackground(Color.WHITE);
		headPanel.setAlignmentX(RIGHT_ALIGNMENT);
		footPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		footPanel.setBackground(Color.WHITE);
		titlePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		titlePanel.setBackground(Color.WHITE);
		btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnPanel.setBackground(Color.WHITE);
		setPanel = new JPanel(new GridLayout(folderset.size(), 1));
		setPanel.setBackground(Color.WHITE);
		crtBtn = new JButton("Ãß°¡");
		crtBtn.setBackground(Color.BLACK);
		crtBtn.setForeground(Color.WHITE);
		crtBtn.setPreferredSize(new Dimension(70, 40));
		crtBtn.addActionListener(this);
		delBtn = new JButton("»èÁ¦");
		delBtn.setBackground(Color.BLACK);
		delBtn.setForeground(Color.WHITE);
		delBtn.setPreferredSize(new Dimension(70, 40));
		delBtn.addActionListener(this);
		title = new JLabel("¼¼Æ® °ü¸®");
		title.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 28));
		mainPanel = new JPanel(new BorderLayout());
		sc2 = new JScrollPane(setPanel);
		sc2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sc2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		nullL = new JLabel("          ");
		leftPnl = new JPanel();
		leftPnl.setBackground(Color.white);
		leftNull = new JLabel("             ");
		rightPnl = new JPanel();
		rightPnl.setBackground(Color.white);
		rightNull = new JLabel("               ");
		
		for (int i = 0; i < folderset.size(); i++) {
			VocaBean bean = folderset.get(i);
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
		
		contentPanel.add(sc2, BorderLayout.CENTER);
		btnPanel.add(crtBtn);
		btnPanel.add(delBtn);
		btnPanel.add(nullL);
		titlePanel.add(title);
		headPanel.add(titlePanel);
		headPanel.add(btnPanel);
		leftPnl.add(leftNull);
		rightPnl.add(rightNull);
		mainPanel.add(headPanel, BorderLayout.NORTH);
		mainPanel.add(leftPnl, BorderLayout.WEST);
		mainPanel.add(rightPnl, BorderLayout.EAST);
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		mainPanel.add(footPanel, BorderLayout.SOUTH);
		add(mainPanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == crtBtn) {
			removeAll();
			add(new NewSet(null, myId));
			revalidate();
			repaint();
		}
		else if(obj == delBtn) {
			removeAll();
			add(new DelSet(myId));
			revalidate();
			repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i = 0; i < folderset.size(); i++) {
			if(e.getSource()==panels[i]) {
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

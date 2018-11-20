package myVoca;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class HomePanel extends JPanel implements MouseListener {
	
	Vector<VocaBean> folders;
	Vector<VocaBean> set;
	DBMgr mgr;
	TitledBorder tb;
	GridBagLayout gbl;
	JPanel mainPanel, headPanel, footPanel;
	JPanel folderPanel, noSetPanel;
	JPanel leftPnl, rightPnl;
	JScrollPane sc1, sc2;
	
	JPanel[] panels, panels_n;
	JPanel[] inner, inner_n;
	JLabel[] idLabels, idLabels_n;
	JLabel[] setLabels, setcntLbl, setnameLbl;
	JLabel[] folderLabels;
	JLabel title, sub1, sub2;
	JLabel leftNull, rightNull;
	JLabel nullL;

	String myId, fname[], sname[];
	int[] setcount, setcount_n;
	
	public HomePanel(String myId) {
		mgr = new DBMgr();
		folders = new Vector<VocaBean>();
		folders = mgr.getFolders(myId);
		set = new Vector<VocaBean>();
		set = mgr.getNoFolderSets(myId);
		gbl = new GridBagLayout();
		this.myId = myId;
		
		panels = new JPanel[folders.size()];
		inner = new JPanel[folders.size()];
		idLabels = new JLabel[folders.size()];
		setLabels = new JLabel[folders.size()];
		folderLabels = new JLabel[folders.size()];
		setcount = new int[folders.size()];
		fname = new String[folders.size()];
		
		panels_n = new JPanel[set.size()];
		inner_n = new JPanel[set.size()];
		idLabels_n = new JLabel[set.size()];
		setcntLbl = new JLabel[set.size()];
		setnameLbl = new JLabel[set.size()];
		setcount_n = new int[set.size()];
		sname = new String[set.size()];
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		mainPanel = new JPanel(gbl);
		mainPanel.setBackground(Color.WHITE);
		headPanel = new JPanel(new GridLayout(1, 2));
		headPanel.setBackground(Color.WHITE);
		headPanel.setAlignmentX(CENTER_ALIGNMENT);
		folderPanel = new JPanel(new GridLayout(folders.size(), 1));
		folderPanel.setBackground(Color.WHITE);
		noSetPanel = new JPanel(new GridLayout(folders.size(), 1));
		noSetPanel.setBackground(Color.WHITE);
		title = new JLabel("È¨");
		title.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 28));
		title.setHorizontalAlignment(JLabel.CENTER);
		sub1 = new JLabel("³» Æú´õ");
		sub1.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 20));
		sub2 = new JLabel("Æú´õ ¾ø´Â ´Ü¾î¼¼Æ®");
		sub2.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 20));
		sc1 = new JScrollPane(folderPanel);
		sc1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sc1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sc2 = new JScrollPane(noSetPanel);
		sc2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sc2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		for (int i = 0; i < folders.size(); i++) {
			VocaBean bean = folders.get(i);
			fname[i] = bean.getFolder().trim();
			
			setcount[i] = mgr.getSetCount(fname[i]);
			panels[i] = new JPanel(new BorderLayout());
			panels[i].setBorder(new EtchedBorder());
			inner[i] = new JPanel(new FlowLayout(0, 40, 0));
			inner[i].setBackground(Color.WHITE);
			setLabels[i] = new JLabel("´Ü¾î¼¼Æ® °³¼ö : " + setcount[i]);
			setLabels[i].setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 15));
			idLabels[i] = new JLabel("ID: " + myId);
			idLabels[i].setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 15));
			folderLabels[i] = new JLabel("      " + fname[i]);
			folderLabels[i].setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 22));
			inner[i].add(setLabels[i]);
			inner[i].add(idLabels[i]);
			panels[i].add(inner[i], BorderLayout.NORTH);
			panels[i].add(folderLabels[i], BorderLayout.CENTER);
			panels[i].setBackground(Color.WHITE);
			panels[i].addMouseListener(this);
			panels[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
			folderPanel.add(panels[i]);
		}
		
		for (int i = 0; i < set.size(); i++) {
			VocaBean bean = set.get(i);
			sname[i] = bean.getSetname().trim();
			
			setcount_n[i] = mgr.getNoFolderCount(sname[i], myId);
			panels_n[i] = new JPanel(new BorderLayout());
			panels_n[i].setBorder(new EtchedBorder());
			inner_n[i] = new JPanel(new FlowLayout(0, 40, 0));
			inner_n[i].setBackground(Color.WHITE);
			setcntLbl[i] = new JLabel("´Ü¾î °³¼ö : " + setcount_n[i]);
			setcntLbl[i].setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 15));
			idLabels_n[i] = new JLabel("ID: " + myId);
			idLabels_n[i].setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 15));
			inner_n[i].add(setcntLbl[i]);
			inner_n[i].add(idLabels_n[i]);
			setnameLbl[i] = new JLabel("      " + sname[i]);
			setnameLbl[i].setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 22));
			panels_n[i].add(inner_n[i], BorderLayout.NORTH);
			panels_n[i].add(setnameLbl[i], BorderLayout.CENTER);
			panels_n[i].setBackground(Color.WHITE);
			panels_n[i].addMouseListener(this);
			panels_n[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
			noSetPanel.add(panels_n[i]);
		}
		
		headPanel.add(title);
		gbInsert(sub1, 0, 0, 3, 1);
		gbInsert(sc1, 0, 1, 1, 1);
		gbInsert(sub2, 0, 2, 3, 1);
		gbInsert(sc2, 0, 3, 1, 1);
		
		add(headPanel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		add(new JLabel("          "), BorderLayout.WEST);
		add(new JLabel("          "), BorderLayout.EAST);		
		add(new JLabel("          "), BorderLayout.SOUTH);		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		
		for (int i = 0; i < set.size(); i++) {
			if(obj == panels_n[i]) {
				removeAll();
				add(new ViewSetWord(sname[i], myId));
				revalidate();
				repaint();
			}
		}
		
		for (int i = 0; i < folders.size(); i++) {
			if(obj == panels[i]) {
				removeAll();
				add(new ViewSet(fname[i], myId));
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
	
	public void gbInsert(Component c, int x, int y, int w, int h) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 2.0;
		gbc.weighty = 1.0;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbl.setConstraints(c, gbc);
		mainPanel.add(c);
	}
}

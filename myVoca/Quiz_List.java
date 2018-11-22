package myVoca;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

public class Quiz_List extends JPanel implements MouseListener {
	
	Vector<VocaBean> sets;
	DBMgr mgr;
	
	GridBagLayout gbl;
	JPanel titlePnl, mainPnl, setPnl;
	JLabel titleL;
	
	JPanel[] panels, inner;
	JLabel[] idLbls, setcntLbls, setnameLbls;
	String[] sname;
	int[] setcount;
	String myId;
	
	public Quiz_List(String myId) {
		
		setSize(900, 550);
		setBackground(Color.white);
		setLayout(null);
		mgr = new DBMgr();
		sets = new Vector<VocaBean>();
		sets = mgr.getMySets(myId);
		gbl = new GridBagLayout();
		this.myId = myId;
		
		panels = new JPanel[sets.size()];
		inner = new JPanel[sets.size()];
		idLbls = new JLabel[sets.size()];
		setcntLbls = new JLabel[sets.size()];
		setnameLbls = new JLabel[sets.size()];
		setcount = new int[sets.size()];
		sname = new String[sets.size()];
		
		titlePnl = new JPanel(new BorderLayout());
		titlePnl.setBackground(Color.darkGray);
		titlePnl.setBounds(95, 20, 700, 50);
		mainPnl = new JPanel(new BorderLayout());
		mainPnl.setBackground(Color.blue);
		mainPnl.setBounds(95, 90, 700, 400);
		setPnl = new JPanel(gbl);
		setPnl.setBackground(Color.WHITE);
		
		titleL = new JLabel("ÄûÁî·Î ³¾ ´Ü¾î¼¼Æ®¸¦ ¼±ÅÃÇØÁÖ¼¼¿ä.");
		titleL.setFont(new Font("³ª´®°íµñ ExtraBold", 0, 25));
		titleL.setForeground(Color.white);
		titleL.setHorizontalAlignment(JLabel.CENTER);
		
		JScrollPane scroll = new JScrollPane(setPnl);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
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
			gbInsert(panels[i], setPnl, 0, i, 1, 1, 1.0, 1.0);
		}

		titlePnl.add(titleL, BorderLayout.CENTER);
		mainPnl.add(scroll);
		add(titlePnl);
		add(mainPnl);
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
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		
		for (int i = 0; i < sets.size(); i++) {
			if(obj == panels[i]) {
				removeAll();
				add(new Quiz_select(myId, sname[i]));
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

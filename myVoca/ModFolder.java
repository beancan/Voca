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

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ModFolder extends JPanel implements ActionListener {
	
	Vector<VocaBean> folders;
	DBMgr mgr;
	TitledBorder tb;
	
	JPanel mainPanel;
	JPanel contentPanel;
	JScrollPane scroll;
	
	JPanel[] panels;
	JPanel[] inner;
	JLabel[] idLabels;
	JLabel[] setLabels;
	JTextField[] tfs;
	JLabel title;
	
	String fname;
	int setcount[];
	
	public ModFolder(String myId) {	
		mgr = new DBMgr();
		folders = new Vector<VocaBean>();
		folders = mgr.getfolders(myId);
		tb = new TitledBorder(new LineBorder(Color.BLACK));
		mainPanel = new JPanel(new BorderLayout());
		contentPanel = new JPanel(new GridLayout(folders.size(), 1));
		
		panels = new JPanel[folders.size()];
		inner = new JPanel[folders.size()];
		idLabels = new JLabel[folders.size()];
		setLabels = new JLabel[folders.size()];
		setcount = new int[folders.size()];
		tfs = new JTextField[folders.size()];
		
		title = new JLabel("내 폴더 수정");
		title.setFont(new Font("나눔스퀘어 Bold", 0, 28));
		
		this.setLayout(new BorderLayout(20, 20));
		
		for (int i = 0; i < folders.size(); i++) {
			VocaBean bean = folders.get(i);
			fname = bean.getFolder().trim();
			
			setcount[i] = mgr.getSetCount(fname);
			panels[i] = new JPanel(new BorderLayout());
			panels[i].setBorder(new EtchedBorder());
			inner[i] = new JPanel(new FlowLayout(0, 40, 0));
			setLabels[i] = new JLabel("단어세트 개수 : " + setcount[i]);
			idLabels[i] = new JLabel("ID: " + myId);
			tfs[i] = new JTextField(15);
			tfs[i].addActionListener(this);
			tfs[i].setText(fname);
			inner[i].add(setLabels[i]);
			inner[i].add(idLabels[i]);
			panels[i].add(inner[i], BorderLayout.NORTH);
			panels[i].add(tfs[i], BorderLayout.CENTER);
			contentPanel.add(panels[i]);
		}
		
		scroll = new JScrollPane(contentPanel);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				
		mainPanel.add(scroll);
		add(title, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj.equals(tfs)) {
			VocaBean bean = new VocaBean();
			
			for (int i = 0; i < tfs.length; i++) {
				
			}
		}
	}
}

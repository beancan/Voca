package myVoca;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ModifyFolder extends JPanel {
	
	JPanel mainPanel;
	JLabel myFolder;
	Vector<VocaBean> folders;
	TitledBorder tb;
	DBMgr mgr;
	
	public ModifyFolder(String myId) {
		mgr = new DBMgr();
		mainPanel = new JPanel();
		myFolder = new JLabel("내 폴더 수정");
		folders = new Vector<VocaBean>();
		tb = new TitledBorder(new LineBorder(Color.BLACK));
		
		myFolder.setFont(new Font("나눔스퀘어 Bold", 0, 28));
		
		this.setLayout(new BorderLayout());
		mainPanel.setLayout(new BorderLayout(20, 20));
		mainPanel.add(myFolder, BorderLayout.NORTH);
		viewPanels(myId);
		
		add(mainPanel, BorderLayout.CENTER);
	}
	
	public void viewPanels(String myId) {
		folders = mgr.getfolders();
		JPanel motherPanel = new JPanel(new GridLayout(folders.size(), 1));
		JScrollPane scroll = new JScrollPane(motherPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel[] panels = new JPanel[folders.size()];
		JPanel[] inner = new JPanel[folders.size()];
		JLabel[] idlbls = new JLabel[folders.size()];
		JLabel[] setlbls = new JLabel[folders.size()];
		JTextField[] tfs = new JTextField[folders.size()];
		int setcount[] = new int[folders.size()];
		
		for (int i = 0; i < folders.size(); i++) {
			VocaBean bean = folders.get(i);
			String fname = bean.getFolder().trim();
			setcount[i] = mgr.getSetCount(fname);
			
			panels[i] = new JPanel(new BorderLayout());
			panels[i].setBorder(new EtchedBorder());
			inner[i] = new JPanel(new FlowLayout(0, 40, 0));
			setlbls[i] = new JLabel("단어세트 개수 : " + setcount[i]);
			idlbls[i] = new JLabel("ID: " + myId);
			tfs[i] = new JTextField(15);
			tfs[i].setText(fname);
			inner[i].add(setlbls[i]);
			inner[i].add(idlbls[i]);
			panels[i].add(inner[i], BorderLayout.NORTH);
			panels[i].add(tfs[i], BorderLayout.CENTER);
			motherPanel.add(panels[i]);
		}
		mainPanel.add(motherPanel, BorderLayout.CENTER);
	}
}

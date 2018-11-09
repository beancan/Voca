package myVoca;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ModifyFolder extends MFrame {
	
	JPanel panel;
	JLabel myFolder;
	Vector<VocaBean> folders;
	Vector<JPanel> panels;
	TitledBorder tb;
	DBMgr mgr;
	
	public ModifyFolder() {
		super(300, 200, new Color(255, 255, 255), true);
		mgr = new DBMgr();
		panel = new JPanel();
		myFolder = new JLabel("내 폴더 수정");
		folders = new Vector<VocaBean>();
		tb = new TitledBorder(new LineBorder(Color.BLACK));
		
		panel.add(myFolder, BorderLayout.NORTH);
		viewPanels();
		add(panel, BorderLayout.CENTER);
	}
	
	public void viewPanels() {
		folders = mgr.getfolders();
		JPanel[] fpanels = new JPanel[folders.size()-1];
		JLabel[] flabels = new JLabel[folders.size()-1];
		
		for (int i = 0; i < folders.size(); i++) {
			VocaBean bean = folders.get(i);
			String fname = bean.getFolder().trim();
			System.out.println(fname);
			
			/*
			flabels[i] = new JLabel(fname);
			fpanels[i].add(flabels[i]);
			panel.add(fpanels[i], BorderLayout.CENTER);
			*/
		}
	}
	
	public static void main(String[] args) {
		new ModifyFolder();
	}
}

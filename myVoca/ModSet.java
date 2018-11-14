package myVoca;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ModSet extends JPanel {
	
	Vector<VocaBean> sets;
	DBMgr mgr;
	
	DefaultTableModel model;
	
	JPanel leftPanel, rightPanel, btnPanel, tf1Panel, tf2Panel, tf3Panel, mainPanel;
	JButton plusBtn, minusBtn, cancelBtn, modBtn;
	JTextField wordTf, descTf;
	JScrollPane sc;
	JLabel title;
	JTable table;

	String[] attr = {"�ܾ�", "�ܾ��"};
	String[][] value;
	String word = "";
	String desc = "";
	
	public ModSet(String myId) {
		
		mgr = new DBMgr();
		model = new DefaultTableModel(value, attr);
		table = new JTable(model);
		sc = new JScrollPane(table);
		sets = new Vector<VocaBean>();
		sets = mgr.getwords(myId);
		mainPanel = new JPanel();
		leftPanel = new JPanel(new BorderLayout());
		rightPanel = new JPanel(new BorderLayout());
		btnPanel = new JPanel();
		tf1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tf2Panel = new JPanel();
		tf3Panel = new JPanel(new GridLayout(2, 1));
		wordTf = new JTextField(10);
		descTf = new JTextField(30);
		plusBtn = new JButton("+");
		minusBtn = new JButton("-");
		cancelBtn = new JButton("���");
		modBtn = new JButton("������� ����");
		title = new JLabel("�ܾƮ ����");
		
		value = new String[sets.size()][2];
		
		for (int i = 0; i < sets.size(); i++) {
			VocaBean bean = sets.get(i);
			int j = 0;
			
			word = bean.getWord().trim();
			desc = bean.getDesc().trim();
			value[i][j] = word;
			value[i][j+1] = desc;
		}
		
		title.setFont(new Font("���������� Bold", 0, 28));
		setLayout(new BorderLayout());
		
		tf1Panel.add(wordTf);
		tf1Panel.add(plusBtn);
		tf1Panel.add(minusBtn);
		tf2Panel.add(descTf);
		tf2Panel.add(cancelBtn);
		tf2Panel.add(modBtn);		
		
		leftPanel.add(sc, BorderLayout.CENTER);
		tf3Panel.add(tf1Panel);
		tf3Panel.add(tf2Panel);
		leftPanel.add(tf3Panel, BorderLayout.SOUTH);
		rightPanel.add(btnPanel, BorderLayout.EAST);
		mainPanel.add(leftPanel, BorderLayout.NORTH);
		mainPanel.add(rightPanel, BorderLayout.SOUTH);
		add(title, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
	}
}

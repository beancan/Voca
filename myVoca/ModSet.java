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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class ModSet extends JPanel implements ActionListener {
	
	Vector<VocaBean> sets, folders;
	DBMgr mgr;
	
	DefaultTableModel model;
	
	JPanel contentPanel, titlePanel, btnPanel, tf1Panel, tf2Panel, tf3Panel, mainPanel;
	JButton plusBtn, minusBtn, cancelBtn, modBtn;
	JTextField wordTf;
	JTextArea descTa;
	JScrollPane sc;
	JComboBox<String> combo;
	JLabel title, wordLbl, descLbl, comboLbl;
	JLabel empty1, empty2;
	JTable table;

	String[] fcombo;
	String[] attr = {"´Ü¾î", "´Ü¾î¼³¸í"};
	String[][] value;
	String word, desc = "";
	String myId, sname;
	
	public ModSet(String sname, String myId) {
		setSize(900, 550);
		setBackground(Color.WHITE);
		this.myId = myId;
		this.sname = sname;
		mgr = new DBMgr();
		sets = new Vector<VocaBean>();
		folders = new Vector<VocaBean>();
		sets = mgr.getWords(myId, sname);
		folders = mgr.getFolders(myId);
		TitledBorder border = new TitledBorder(new LineBorder(Color.gray));
		value = new String[sets.size()][2];
		fcombo = new String[folders.size()+1];
		
		for (int i = 0; i < sets.size(); i++) {
			VocaBean bean = sets.get(i);
			int j = 0;
			word = bean.getWord().trim();
			desc = bean.getDesc().trim();
			value[i][j] = word;
			value[i][j+1] = desc;
		}
		
		for (int i = 0; i < folders.size(); i++) {
			VocaBean bean = folders.get(i);
			fcombo[i] = bean.getFolder().trim();
		}
		fcombo[folders.size()] = null;
		
		mainPanel = new JPanel(new GridLayout(2, 1));
		mainPanel.setBackground(Color.WHITE);
		contentPanel = new JPanel(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		titlePanel = new JPanel(new GridLayout(1, 1));
		titlePanel.setBackground(Color.WHITE);
		btnPanel = new JPanel();
		btnPanel.setBackground(Color.WHITE);
		tf1Panel = new JPanel(null);
		tf1Panel.setBackground(Color.white);
		tf2Panel = new JPanel(null);
		tf2Panel.setBackground(Color.WHITE);
		tf3Panel = new JPanel(new GridLayout(1, 1));
		tf3Panel.setBackground(Color.WHITE);
		wordLbl = new JLabel("´Ü¾î");
		wordLbl.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 14));
		wordLbl.setBounds(40, 8, 50, 30);
		descLbl = new JLabel("¼³¸í");
		descLbl.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 14));
		descLbl.setBounds(40, 80, 50, 30);
		wordTf = new JTextField();
		wordTf.setBounds(70, 10, 550, 25);
		descTa = new JTextArea();
		descTa.setBounds(70, 50, 790, 100);
		descTa.setBorder(border);
		plusBtn = new JButton("+");
		plusBtn.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 10));
		plusBtn.setBackground(Color.black);
		plusBtn.setForeground(Color.white);
		plusBtn.setBounds(630, 9, 40, 28);
		plusBtn.addActionListener(this);
		minusBtn = new JButton("-");
		minusBtn.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 10));
		minusBtn.setBackground(Color.black);
		minusBtn.setForeground(Color.white);
		minusBtn.setBounds(670, 9, 40, 28);
		minusBtn.addActionListener(this);
		comboLbl = new JLabel("Æú´õ");
		comboLbl.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 14));
		comboLbl.setBounds(730, 8, 50, 30);
		combo = new JComboBox<String>(fcombo);
		combo.setBounds(770, 9, 100, 28);
		combo.setBackground(Color.WHITE);
		combo.addActionListener(this);
		cancelBtn = new JButton("Ãë¼Ò");
		cancelBtn.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 13));
		cancelBtn.setBackground(Color.black);
		cancelBtn.setForeground(Color.white);
		cancelBtn.setBounds(740, 170, 60, 30);
		cancelBtn.addActionListener(this);
		modBtn = new JButton("¼öÁ¤");
		modBtn.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 13));
		modBtn.setBackground(Color.black);
		modBtn.setForeground(Color.white);
		modBtn.setBounds(810, 170, 60, 30);
		modBtn.addActionListener(this);
		title = new JLabel("´Ü¾î¼¼Æ® ¼öÁ¤ - (" + sname + ")");
		title.setBackground(Color.WHITE);
		title.setHorizontalAlignment(JLabel.CENTER);
		empty1 = new JLabel("             ");
		empty2 = new JLabel("             ");
		
		title.setFont(new Font("³ª´®½ºÄù¾î Bold", 0, 28));
		setLayout(new BorderLayout(0, 20));
		
		model = new DefaultTableModel(value, attr);
		table = new JTable(model);
		table.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		sc = new JScrollPane(table);
		sc.setBackground(Color.WHITE);
		sc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		tf1Panel.add(wordTf);
		tf1Panel.add(plusBtn);
		tf1Panel.add(minusBtn);
		tf1Panel.add(comboLbl);
		tf1Panel.add(combo);
		tf1Panel.add(descTa);
		tf1Panel.add(wordLbl);
		tf1Panel.add(descLbl);
		tf1Panel.add(cancelBtn);
		tf1Panel.add(modBtn);		
		
		contentPanel.add(sc, BorderLayout.CENTER);
		contentPanel.add(empty1, BorderLayout.WEST);
		contentPanel.add(empty2, BorderLayout.EAST);
		tf3Panel.add(tf1Panel);
		titlePanel.add(title);
		mainPanel.add(contentPanel);
		mainPanel.add(tf3Panel);
		add(titlePanel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == plusBtn || obj == descTa) {
			String w = wordTf.getText().trim();
			String d = descTa.getText().trim();
			DefaultTableModel m = (DefaultTableModel)table.getModel();
			m.insertRow(model.getRowCount(), new String[] {w, d});
			table.updateUI();
			wordTf.setText("");
			descTa.setText("");
		}
		else if(obj == minusBtn) {
			if(model.getRowCount() > 0) {
				model.removeRow(model.getRowCount()-1);
				table.updateUI();
			}
		}
		else if(obj == cancelBtn) {
			removeAll();
			add(new ManageSet(myId));
			revalidate();
			repaint();
		}
		else if(obj == modBtn) {
			DefaultTableModel m = (DefaultTableModel)table.getModel();
			VocaBean bean = new VocaBean();
			boolean flag = mgr.getSetWordsFlag(myId, sname);
			if(flag == true) {
				mgr.deleteSetWord(sname, myId);
				
				for (int i = 0; i < m.getRowCount(); i++) {
					bean.setId(myId);
					bean.setSetname(sname);
					bean.setFolder(combo.getSelectedItem().toString());
					bean.setWord((String)m.getValueAt(i, 0));
					bean.setDesc((String)m.getValueAt(i, 1));
					
					mgr.updateSetFolder(bean);
					mgr.insertWords(bean);
				}
				
				removeAll();
				add(new ManageSet(myId));
				revalidate();
				repaint();
			}
			else if(flag == false) {
				for (int i = 0; i < m.getRowCount(); i++) {
					
					bean.setId(myId);
					bean.setSetname(sname);
					
					if(combo.getSelectedItem() == null)
						bean.setFolder(null);
					else
						bean.setFolder(combo.getSelectedItem().toString());
					
					bean.setWord((String)m.getValueAt(i, 0));
					bean.setDesc((String)m.getValueAt(i, 1));
					
					mgr.updateSetFolder(bean);
					mgr.insertWords(bean);
				}
				
				removeAll();
				add(new ManageSet(myId));
				revalidate();
				repaint();
			}
		}
	}
}

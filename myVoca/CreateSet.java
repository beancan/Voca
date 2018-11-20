package myVoca;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class CreateSet extends JPanel implements ActionListener {
	
	Vector<VocaBean> sets;
	DBMgr mgr;
	
	DefaultTableModel model;
	
	JPanel contentPanel, titlePanel, btnPanel, tf1Panel, tf2Panel, tf3Panel, mainPanel;
	JButton plusBtn, minusBtn, cancelBtn, createBtn;
	JTextField wordTf;
	JTextArea descTa;
	JScrollPane sc;
	JLabel title, wordLbl, descLbl;
	JLabel empty1, empty2;
	JTable table;

	String[] attr = {"´Ü¾î", "´Ü¾î¼³¸í"};
	String[][] value = {};
	String word = "";
	String desc = "";
	String myId, fname, sname;
	
	public CreateSet(String fname, String sname, String myId) {
		setSize(900, 550);
		setBackground(Color.WHITE);
		this.myId = myId;
		this.fname = fname;
		this.sname = sname;
		mgr = new DBMgr();
		sets = new Vector<VocaBean>();
		sets = mgr.getWords(myId, sname);
		TitledBorder border = new TitledBorder(new LineBorder(Color.gray));
		
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
		wordTf.setBounds(70, 10, 650, 25);
		descTa = new JTextArea();
		descTa.setBounds(70, 50, 650, 100);
		descTa.setBorder(border);
		plusBtn = new JButton("+");
		plusBtn.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 10));
		plusBtn.setBackground(Color.black);
		plusBtn.setForeground(Color.white);
		plusBtn.setBounds(720, 9, 40, 28);
		plusBtn.addActionListener(this);
		minusBtn = new JButton("-");
		minusBtn.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 10));
		minusBtn.setBackground(Color.black);
		minusBtn.setForeground(Color.white);
		minusBtn.setBounds(760, 9, 40, 28);
		minusBtn.addActionListener(this);
		cancelBtn = new JButton("Ãë¼Ò");
		cancelBtn.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 13));
		cancelBtn.setBackground(Color.black);
		cancelBtn.setForeground(Color.white);
		cancelBtn.setBounds(650, 170, 60, 30);
		cancelBtn.addActionListener(this);
		createBtn = new JButton("»ý¼º");
		createBtn.setFont(new Font("³ª´®½ºÄù¾î ExtraBold", 0, 13));
		createBtn.setBackground(Color.black);
		createBtn.setForeground(Color.white);
		createBtn.setBounds(720, 170, 60, 30);
		createBtn.addActionListener(this);

		title = new JLabel("´Ü¾î¼¼Æ® »ý¼º");
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
		tf1Panel.add(descTa);
		tf1Panel.add(wordLbl);
		tf1Panel.add(descLbl);
		tf1Panel.add(cancelBtn);
		tf1Panel.add(createBtn);		
		
		contentPanel.add(sc, BorderLayout.CENTER);
		tf3Panel.add(tf1Panel);
		titlePanel.add(title);
		mainPanel.add(contentPanel);
		mainPanel.add(tf3Panel);
		add(titlePanel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		add(empty1, BorderLayout.EAST);
		add(empty2, BorderLayout.WEST);
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
			new ManageSet(myId);
		}
		else if(obj == createBtn) {
			DefaultTableModel m = (DefaultTableModel)table.getModel();
			VocaBean bean = new VocaBean();
			bean.setId(myId);
			bean.setFolder(fname);			
			bean.setSetname(sname);
			mgr.insertFolder(bean);
			
			if(mgr.insertSet(bean)) {
				for (int i = 0; i < m.getRowCount(); i++) {
					bean.setId(myId);
					bean.setSetname(sname);
					bean.setWord((String)model.getValueAt(i, 0));
					bean.setDesc((String)model.getValueAt(i, 1));
					
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

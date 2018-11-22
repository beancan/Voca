package myVoca;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class AddOtherSet extends JPanel implements ActionListener {
	
	Vector<VocaBean> sets;
	JLabel newFold;
	JComboBox<String> setName;
	JButton cancle, create;
	JPanel p;
	DBMgr mgr;
	String myId, fname;
	String[] scombo;

	public AddOtherSet(String fname, String myId) {
		setSize(900, 550);
		setBackground(Color.white);
		setLayout(null);
		this.myId = myId;
		this.fname = fname;
		mgr = new DBMgr();
		sets = new Vector<VocaBean>();
		sets = mgr.getMySets(myId);
		
		TitledBorder border = new TitledBorder(new LineBorder(Color.gray));
		scombo = new String[sets.size()];
		for (int i = 0; i < sets.size(); i++) {
			VocaBean bean = sets.get(i);
			scombo[i] = bean.getSetname().trim();
		}
		
		p = new JPanel();
		p.setBounds(170, 120, 550, 250);
		p.setBorder(border);
		p.setBackground(Color.white);
		p.setLayout(null);
		
		newFold = new JLabel("기존 단어세트 추가");
		newFold.setFont(new Font("나눔스퀘어 ExtraBold", 0, 25));
		newFold.setBounds(45, 40, 200, 30);
		
		setName = new JComboBox<String>(scombo);
		setName.setBounds(90, 95, 350, 30);
		setName.setBackground(Color.WHITE);
		setName.addActionListener(this);
		
		cancle = new JButton("취소");
		cancle.setFont(new Font("나눔스퀘어 Bold", 0, 15));
		cancle.setBounds(310, 150, 80, 30);
		cancle.setBackground(Color.white);
		cancle.addActionListener(this);
		
		create = new JButton("추가");
		create.setFont(new Font("나눔스퀘어 Bold", 0, 15));
		create.setBounds(400, 150, 80, 30);
		create.setBackground(Color.white);
		create.addActionListener(this);
		
		p.add(newFold);
		p.add(setName);
		p.add(cancle);
		p.add(create);
		add(p);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		
		if(obj == cancle) {
			removeAll();
			setLayout(new BorderLayout(20, 20));
			add(new ViewSet(fname, myId), BorderLayout.CENTER);
			revalidate();
			repaint();
		}
		else if(obj == create) {
			VocaBean bean = new VocaBean();
			
			try {
				bean.setFolder(fname);
				bean.setId(myId);
				bean.setSetname(setName.getSelectedItem().toString());
				
				mgr.updateSetFolder(bean);
				JOptionPane.showMessageDialog(this, "세트의 폴더 변경이 완료되었습니다.", "변경 성공", JOptionPane.INFORMATION_MESSAGE);
			}
			catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(this, "세트의 폴더 변경에 실패했습니다.", "변경 실패", JOptionPane.ERROR_MESSAGE);
			}
			finally {
				removeAll();
				add(new ViewSet(fname, myId));
				revalidate();
				repaint();
			}
		}
	}
}

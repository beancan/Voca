package myVoca;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class NewFolder extends JPanel implements ActionListener {
	
	JLabel newFold;
	JTextField folderName;
	JButton cancle, create;
	JPanel p;
	
	DBMgr mgr;
	VocaBean bean;
	static String myId;
	static int num;

	public NewFolder(String myId, int num) {
		setSize(900, 550);
		setBackground(Color.white);
		setLayout(null);
		this.myId = myId;
		this.num = num;
		mgr = new DBMgr();
		bean = new VocaBean();
		
		TitledBorder border = new TitledBorder(new LineBorder(Color.gray));
		p = new JPanel();
		p.setBounds(170, 120, 550, 250);
		p.setBorder(border);
		p.setBackground(Color.white);
		p.setLayout(null);
		
		newFold = new JLabel("새 폴더 생성");
		newFold.setFont(new Font("나눔스퀘어 ExtraBold", 0, 25));
		newFold.setBounds(45, 40, 200, 30);
		
		folderName = new JTextField();
		folderName.setBounds(90, 95, 350, 30);
		
		cancle = new JButton("취소");
		cancle.setFont(new Font("나눔스퀘어 Bold", 0, 15));
		cancle.setBounds(310, 150, 80, 30);
		cancle.setBackground(Color.white);
		cancle.addActionListener(this);
		
		create = new JButton("생성");
		create.setFont(new Font("나눔스퀘어 Bold", 0, 15));
		create.setBounds(400, 150, 80, 30);
		create.setBackground(Color.white);
		create.addActionListener(this);
		
		p.add(newFold);
		p.add(folderName);
		p.add(cancle);
		p.add(create);
		add(p);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj==cancle) {
			boolean flag = mgr.getWordFlag(myId);
			
			if(flag == true) {
				removeAll();
				setLayout(new BorderLayout());
				add(new ManageFolder(myId));
				validate();
				repaint();
			}
			else {
				removeAll();
				setLayout(new BorderLayout());
				add(new NoSet(myId));
				revalidate();
				repaint();
			}
		}
		else if(obj==create) {
			if(num == 1) {
				bean.setFolder(folderName.getText());
				bean.setId(myId);
				boolean flag = mgr.insertFolder(bean);
				if(flag==true) {
					JOptionPane.showMessageDialog(this, "폴더가 생성되었습니다.");
					removeAll();
					setLayout(new BorderLayout());
					add(new ManageFolder(myId));
					validate();
					repaint();
				} else {
					JOptionPane.showMessageDialog(this, "폴더 생성에 실패하였습니다.");
				}
				
				
			} else if (num == 2) {
				removeAll();
				add(new NewSet(folderName.getText(), myId));
				validate();
				repaint();
			}
		}
	}
}

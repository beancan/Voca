package myVoca;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FrAdd extends JFrame implements ActionListener {

	JButton btn1, btn2;
	JTextField tf1;
	JLabel lb1;
	String myId;
	
	public FrAdd(String myId) {
		
		super("ģ�� �߰�");
		setSize(400,170);
		setLayout(null);
		this.myId = myId;
		
		btn1 = new JButton("Ȯ��");
		btn1.setBounds(200,80,80,30);
		btn1.setBackground(Color.BLACK);
		btn1.setForeground(Color.WHITE);
		btn2 = new JButton("���");
		btn2.setBounds(290,80,80,30);
		btn2.setBackground(Color.BLACK);
		btn2.setForeground(Color.WHITE);
		
		lb1 = new JLabel("ģ���� ID�� �Է� �ϼ���");
		lb1.setBounds(20,30,160,30);
		
		tf1 = new JTextField();
		tf1.setBounds(172,30,200,30);
		
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-frameSize.width)/2, (screenSize.height - frameSize.height)/2);
		
		add(btn1);
		add(btn2);
		add(tf1);
		add(lb1);
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String id = chkId().getId().trim();
		String Userid = myId;	//	�Ű�����ȭ �ؾ���
		DBMgr dba = new DBMgr();
		Vector<VocaBean> bean = new Vector<VocaBean>();
		
		if (obj == btn1) {
			bean = dba.checkAllId();
			
			for (int i = 0; i < bean.size(); i++) {
				if(Userid.trim().equals(id)) {
					JOptionPane.showMessageDialog(this, "������ ģ���� �߰� �� �� �����ϴ�");
				} else if(id.length() == 0){
					JOptionPane.showMessageDialog(this, "ģ���� ID�� �Է��ϼ���.");
				} else if(bean.elementAt(i).getId().trim().equals(tf1.getText().trim())) {
					dba.inputFriend(Userid, id);
					dispose();
					return;
				}
			}
			
			JOptionPane.showMessageDialog(this, "�������� �ʴ� ID �Դϴ�.");
		}
		else if (obj == btn2) {
			dispose();
		}
	}
	
	public VocaBean chkId() {
		VocaBean dbt = new VocaBean();
		String id = tf1.getText();
		dbt.setId(id);
		
		return dbt;
	}
}

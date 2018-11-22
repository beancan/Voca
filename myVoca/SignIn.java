package myVoca;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignIn extends JFrame implements ActionListener {

	JTextField txt_id, txt_Name, txt_email, txt_birth;
	JPasswordField pwTf, pwTf2;
	JLabel logo, id, pw, pw2, name, email, birth;
	JButton b_overlap, b_acc, b_cancle;
	
	public SignIn() { // ȸ������ ���̾ƿ�

		super("ȸ������");
		setSize(1024, 680);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.white);
		
		logo = new JLabel("ȸ �� �� ��");
		logo.setFont(new Font("���������� ExtraBold", Font.BOLD, 50));
		
		id = new JLabel("ID");
		id.setFont(new Font("������� Bold", Font.PLAIN, 20));
		b_overlap = new JButton("�ߺ�Ȯ��");
		b_overlap.setFont(new Font("���������� ExtraBold", 0, 16));
		b_overlap.setBackground(Color.black);
		b_overlap.setForeground(Color.white);
		txt_id = new JTextField();
		pw = new JLabel("PW");
		pw.setFont(new Font("������� Bold", Font.PLAIN, 20));
		pwTf = new JPasswordField();
		pw2 = new JLabel("PW ���Է�");
		pw2.setFont(new Font("������� Bold", Font.PLAIN, 20));
		pwTf2 = new JPasswordField();
		name = new JLabel("�̸�");
		name.setFont(new Font("������� Bold", Font.PLAIN, 20));
		txt_Name = new JTextField();
		email = new JLabel("�̸���");
		email.setFont(new Font("������� Bold", Font.PLAIN, 20));
		txt_email = new JTextField();
		birth = new JLabel("�������");
		birth.setFont(new Font("������� Bold", Font.PLAIN, 20));
		txt_birth = new JTextField();
		b_acc = new JButton("����");
		b_acc.setBackground(Color.black);
		b_acc.setForeground(Color.white);
		b_acc.setFont(new Font("���������� ExtraBold", 0, 16));
		b_cancle = new JButton("���");
		b_cancle.setBackground(Color.black);
		b_cancle.setForeground(Color.white);
		b_cancle.setFont(new Font("���������� ExtraBold", 0, 16));
		
		
		logo.setBounds(405, 100, 300, 50);
		id.setBounds(340, 200, 50, 20);
		txt_id.setBounds(380,195,295,30);
		b_overlap.setBounds(690,195,100,30);
		pw.setBounds(330,240,50,20);
		pwTf.setBounds(380,235,295,30);
		pw2.setBounds(270,280,100,20);
		pwTf2.setBounds(380, 275, 295, 30);
		name.setBounds(326,320,50,20);
		txt_Name.setBounds(380, 315, 70, 30);
		email.setBounds(460, 320, 60, 20);
		txt_email.setBounds(525, 315, 150, 30);
		birth.setBounds(288,360,80,20);
		txt_birth.setBounds(380, 355, 295, 30);
		b_acc.setBounds(690, 400, 70, 35);
		b_cancle.setBounds(600, 400, 70, 35);
		
		c.add(logo); c.add(id); c.add(txt_id); c.add(b_overlap); c.add(pw);
		c.add(pwTf); c.add(pw2); c.add(pwTf2); c.add(name); c.add(txt_Name);
		c.add(email); c.add(txt_email); c.add(birth); c.add(txt_birth);
		c.add(b_acc); c.add(b_cancle);
		
		b_overlap.addActionListener(this);
		b_acc.addActionListener(this);
		b_cancle.addActionListener(this);
		
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-frameSize.width)/2, (screenSize.height - frameSize.height)/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == b_overlap) {
			ovlap();
		}
		else if(obj == b_acc) {
			insertMember();
		}
		else if(obj == b_cancle) {
			this.dispose();
		}
	}
	
	public void ovlap() {
		String u_id = getViewData().getId().trim();
		DBMgr DBId = new DBMgr();
		
		if(u_id.length() == 0) {
			JOptionPane.showMessageDialog(this, "���̵� ����ֽ��ϴ�. �Է��ϼ���.");
		}
		else if(DBId.ub(u_id) == true)
			JOptionPane.showMessageDialog(this, "���̵� �ߺ��Դϴ� �ٸ� ���̵� �Է��ϼ���.");
		else
			JOptionPane.showMessageDialog(this, "���� ������ ���̵� �Դϴ�..");
	}

	private void insertMember() {
		VocaBean dbt = getViewData();
		DBMgr dba = new DBMgr();
		char[] pw1 = pwTf.getPassword();
		char[] pw2 = pwTf2.getPassword();
		
		if(Arrays.equals(pw1, pw2)) {
			boolean ok = dba.insertMember(dbt);
			JOptionPane.showMessageDialog(this, "������ �Ϸ�Ǿ����ϴ�.");
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "��й�ȣ�� ���� �ٸ��ϴ�.");
			return;
		}
	}
	
	public VocaBean getViewData() {
		VocaBean dbt = new VocaBean();
		String get_id = txt_id.getText();
		String get_pwd = pwTf.getText();
		String get_pwd2 = pw2.getText();
		String get_name = txt_Name.getText();
		String get_email = txt_email.getText();
		String get_birth = txt_birth.getText();
		
		dbt.setId(get_id);
		dbt.setPw(get_pwd);
		dbt.setPwd2(get_pwd2);
		dbt.setName(get_name);
		dbt.setEmail(get_email);
		dbt.setBirth(get_birth);
		
		return dbt;
	}
}

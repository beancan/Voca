package myVoca;

import java.awt.Font;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		
		logo = new JLabel("ȸ �� �� ��");
		logo.setFont(new Font("Dialog", Font.BOLD, 50));
		
		id = new JLabel("ID");
		id.setFont(new Font("Dialog", Font.PLAIN, 20));
		b_overlap = new JButton("�ߺ�Ȯ��");
		txt_id = new JTextField();
		pw = new JLabel("PW");
		pw.setFont(new Font("Dialog", Font.PLAIN, 20));
		pwTf = new JPasswordField();
		pw2 = new JLabel("PW ���Է�");
		pw2.setFont(new Font("Dialog", Font.PLAIN, 20));
		pwTf2 = new JPasswordField();
		name = new JLabel("�̸�");
		name.setFont(new Font("Dialog", Font.PLAIN, 20));
		txt_Name = new JTextField();
		email = new JLabel("�̸���");
		email.setFont(new Font("Dialog", Font.PLAIN, 20));
		txt_email = new JTextField();
		birth = new JLabel("�������");
		birth.setFont(new Font("Dialog", Font.PLAIN, 20));
		txt_birth = new JTextField();
		b_acc = new JButton("����");
		b_cancle = new JButton("���");
		
		logo.setBounds(380, 100, 300, 50);
		id.setBounds(330, 200, 50, 20);
		txt_id.setBounds(380,195,295,30);
		b_overlap.setBounds(690,195,90,30);
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
		b_acc.setBounds(580, 400, 90, 50);
		b_cancle.setBounds(690, 400, 90, 50);
		
		add(logo); add(id); add(txt_id); add(b_overlap); add(pw);
		add(pwTf); add(pw2); add(pwTf2); add(name); add(txt_Name);
		add(email); add(txt_email); add(birth); add(txt_birth);
		add(b_acc); add(b_cancle);
		
		b_overlap.addActionListener(this);
		b_acc.addActionListener(this);
		b_cancle.addActionListener(this);
		
		setBounds(100, 50, 1024, 680);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String u_id = getViewData().getId();
		DBMgr DBId = new DBMgr();
		Object obj = e.getSource();
		
		if(obj == b_overlap) {
			if(DBId.ub(u_id) == true)
				JOptionPane.showMessageDialog(this, "���̵� �ߺ��Դϴ� �ٸ� ���̵� �Է��ϼ���.");
			else
				JOptionPane.showMessageDialog(this, "���� ������ ���̵� �Դϴ�..");
		} else if(obj == b_acc) {
			insertMember();
		} else if(obj == b_cancle) {
			this.dispose();
		}
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

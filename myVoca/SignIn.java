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
	
	public SignIn() { // 회원가입 레이아웃

		super("회원가입");
		setSize(1024, 680);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.white);
		
		logo = new JLabel("회 원 가 입");
		logo.setFont(new Font("나눔스퀘어 ExtraBold", Font.BOLD, 50));
		
		id = new JLabel("ID");
		id.setFont(new Font("나눔고딕 Bold", Font.PLAIN, 20));
		b_overlap = new JButton("중복확인");
		b_overlap.setFont(new Font("나눔스퀘어 ExtraBold", 0, 16));
		b_overlap.setBackground(Color.black);
		b_overlap.setForeground(Color.white);
		txt_id = new JTextField();
		pw = new JLabel("PW");
		pw.setFont(new Font("나눔고딕 Bold", Font.PLAIN, 20));
		pwTf = new JPasswordField();
		pw2 = new JLabel("PW 재입력");
		pw2.setFont(new Font("나눔고딕 Bold", Font.PLAIN, 20));
		pwTf2 = new JPasswordField();
		name = new JLabel("이름");
		name.setFont(new Font("나눔고딕 Bold", Font.PLAIN, 20));
		txt_Name = new JTextField();
		email = new JLabel("이메일");
		email.setFont(new Font("나눔고딕 Bold", Font.PLAIN, 20));
		txt_email = new JTextField();
		birth = new JLabel("생년월일");
		birth.setFont(new Font("나눔고딕 Bold", Font.PLAIN, 20));
		txt_birth = new JTextField();
		b_acc = new JButton("가입");
		b_acc.setBackground(Color.black);
		b_acc.setForeground(Color.white);
		b_acc.setFont(new Font("나눔스퀘어 ExtraBold", 0, 16));
		b_cancle = new JButton("취소");
		b_cancle.setBackground(Color.black);
		b_cancle.setForeground(Color.white);
		b_cancle.setFont(new Font("나눔스퀘어 ExtraBold", 0, 16));
		
		
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
			JOptionPane.showMessageDialog(this, "아이디가 비어있습니다. 입력하세요.");
		}
		else if(DBId.ub(u_id) == true)
			JOptionPane.showMessageDialog(this, "아이디가 중복입니다 다른 아이디를 입력하세요.");
		else
			JOptionPane.showMessageDialog(this, "생성 가능한 아이디 입니다..");
	}

	private void insertMember() {
		VocaBean dbt = getViewData();
		DBMgr dba = new DBMgr();
		char[] pw1 = pwTf.getPassword();
		char[] pw2 = pwTf2.getPassword();
		
		if(Arrays.equals(pw1, pw2)) {
			boolean ok = dba.insertMember(dbt);
			JOptionPane.showMessageDialog(this, "가입이 완료되었습니다.");
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "비밀번호가 서로 다릅니다.");
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
